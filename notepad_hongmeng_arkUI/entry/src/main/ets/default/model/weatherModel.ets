export class WeatherModel {
  reason: string //返回说明
  error_code: number //返回码，0为查询成功
  result: {
    realtime: RealtimeWeatherData // 实时天气
    future: Array<WeatherData> // 往后天气情况
  }
}

export class RealtimeWeatherData {
  info: string = '多云' // 天气情况
  temperature: string = '10' // 当前温度
  direct: string = '东风' //风向
}

export class WeatherData {
  date: string // 日期
  temperature: string // 温度
  direct: string // 风向
  weather: string // 天气情况
}

export function getTest() {
  return [
    {
      "date": "2021-11-15",
      "direct": "东北风转北风",
      "temperature": "-1/12℃",
      "weather": "多云转晴",
      "wid": {
        "day": "01",
        "night": "00"
      }
    },
    {
      "date": "2021-11-16",
      "direct": "西南风",
      "temperature": "0/13℃",
      "weather": "晴转多云",
      "wid": {
        "day": "00",
        "night": "01"
      }
    },
    {
      "date": "2021-11-17",
      "direct": "西南风",
      "temperature": "0/13℃",
      "weather": "晴",
      "wid": {
        "day": "00",
        "night": "00"
      }
    },
    {
      "date": "2021-11-18",
      "direct": "东北风",
      "temperature": "2/12℃",
      "weather": "晴",
      "wid": {
        "day": "00",
        "night": "00"
      }
    },
    {
      "date": "2021-11-19",
      "direct": "东北风转东南风",
      "temperature": "2/10℃",
      "weather": "多云",
      "wid": {
        "day": "01",
        "night": "01"
      }
    }
  ]
}