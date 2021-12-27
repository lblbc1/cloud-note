import {WeatherModel, RealtimeWeatherData, WeatherData, getTest} from '../model/weatherModel.ets';
import prompt from '@system.prompt';
import http from '@ohos.net.http';

@Entry
@Component
struct Index {
  @State realtime: RealtimeWeatherData = new RealtimeWeatherData()
  @State future: Array<WeatherData> = getTest()
  @State loadingHint: string = ''
  @State isRequestSucceed: boolean = false

  @Builder RealtimeWeather() {
    Column() {
      Row() {
        Text('北京')
          .fontSize(15)
        Image($r('app.media.ic_location'))
          .width(15)
          .height(15)
          .margin({ left: 5 })
      }

      Row() {
        Text(this.realtime.temperature)
          .fontSize(60)

        Text('℃')
          .fontSize(15)
          .margin({ top: 10 })
      }
      .alignItems(VerticalAlign.Top)
      .margin({ top: 5 })

      Row() {
        Text(this.realtime.info)
          .fontSize(13)

        Divider()
          .vertical(true)
          .height(12)
          .margin({ left: 7, right: 7 })

        Text(this.realtime.direct)
          .fontSize(13)

      }
      .alignItems(VerticalAlign.Center)
      .margin({ top: 5 })
    }.margin({ top: 50 })
  }

  @Builder WeatherText(text:string) {
    Text(text)
      .fontSize(13)
      .layoutWeight(1)
      .textAlign(TextAlign.Center)
      .margin({ top: 10, bottom: 10 })
  }

  build() {
    Column() {

      Button() {
        Text('获取北京天气数据')
          .width('100%')
          .fontSize(15)
          .fontColor(Color.White)
          .textAlign(TextAlign.Center)
      }
      .padding(20)
      .onClick(() => {
        this.loadingHint = '加载中...'
        this.postRequest()
      })

      if (this.isRequestSucceed) {
        // 当前天气
        this.RealtimeWeather()

        // 往后天气
        Column() {
          Row() {
            this.WeatherText('日期')
            this.WeatherText('温度')
            this.WeatherText('天气')
            this.WeatherText('风向')
          }.backgroundColor('#ffe9c4')

          ForEach(this.future, (item: WeatherData) => {
            Row() {
              this.WeatherText(item.date)
              this.WeatherText(item.temperature)
              this.WeatherText(item.weather)
              this.WeatherText(item.direct)
            }
          }, item => item.date)
        }
        .padding(10)
        .margin({ top: 20 })

      } else {
        Text(this.loadingHint)
          .textAlign(TextAlign.Center)
          .fontSize(20)
          .width('100%')
          .height('80%')
      }
    }
    .width('100%')
    .height('100%')
  }

// 请求方式：GET
  getRequest() {
    // 每一个httpRequest对应一个http请求任务，不可复用
    let httpRequest = http.createHttp()
    let url = 'http://apis.juhe.cn/simpleWeather/query?key=397c9db4cb0621ad0313123dab416668&city=北京'
    httpRequest.request(url, (err, data) => {
      if (!err) {
        if (data.responseCode == 200) {
          console.info('=====data.result=====' + JSON.stringify(data.result))
          // 解析数据
          var weatherModel: WeatherModel = JSON.parse(JSON.stringify(data.result))
          // 判断接口返回码，0成功
          if (weatherModel.error_code == 0) {
            // 设置数据
            this.realtime = weatherModel.result.realtime
            this.future = weatherModel.result.future
            this.isRequestSucceed = true
          } else {
            // 接口异常，弹出提示
            prompt.showToast({ message: weatherModel.reason })
          }
        } else {
          // 请求失败，弹出提示
          prompt.showToast({ message: '网络异常' })
        }
      } else {
        // 请求失败，弹出提示
        prompt.showToast({ message: err.message })
      }
    })
  }

// 请求方式：POST
  postRequest() {
    // 每一个httpRequest对应一个http请求任务，不可复用
    let httpRequest = http.createHttp()
    let url = 'http://apis.juhe.cn/simpleWeather/query'
    httpRequest.request(url,
      {
        // 看源码得知method的类型为：RequestMethod
        // 但是设置 method: http.RequestMethod.POST 报错
        // 设置成 method: http.POST 可以
        method: http.POST,
        extraData: {
          'key': '397c9db4cb0621ad0313123dab416668',
          'city': '北京'
        }
      },
      (err, data) => {
        if (!err) {
          if (data.responseCode == 200) {
            console.info('=====data.result=====' + JSON.stringify(data.result))
            // 解析数据
            var weatherModel: WeatherModel = JSON.parse(JSON.stringify(data.result))
            // 判断接口返回码，0成功
            if (weatherModel.error_code == 0) {
              // 设置数据
              this.realtime = weatherModel.result.realtime
              this.future = weatherModel.result.future
              this.isRequestSucceed = true
            } else {
              // 接口异常，弹出提示
              prompt.showToast({ message: weatherModel.reason })
            }
          } else {
            // 请求失败，弹出提示
            prompt.showToast({ message: '网络异常' })
          }
        } else {
          // 请求失败，弹出提示
          prompt.showToast({ message: err.message })
        }
      })
  }
}