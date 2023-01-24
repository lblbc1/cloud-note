import prompt from '@ohos.prompt';
/**
 * 厦门大学计算机专业 | 前华为工程师
 * 专注《零基础学编程系列》  http://lblbc.cn/blog
 * 包含：Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
 * 公众号：蓝不蓝编程
 */
import { httpRequestPost } from '../../common/utils/HttpUtil';
import router from '@ohos.router';
import { Note } from '../../data/Note';

@Entry
@Component
struct AddNote {
  @State note: Note = new Note()

  build() {
    Column() {
      Row() {
        Image($r("app.media.back"))
          .width(50)
          .height(50)
          .padding(10)
          .onClick(() => {
            router.back()
          })
        Text('新建').fontSize(20)
        Blank()
        Text('保存').fontColor('#717171').fontSize(20)
          .onClick(() => {
            this.save()
          }).margin({ right: 20 })
      }.width('100%').margin({ bottom: 10 })

      TextArea({ placeholder: "请输入正文" })
        .width('100%')
        .height('100%')
        .backgroundColor('#FFFFFF')
        .fontSize(20)
        .margin({ top: 10, left: 20, right: 20 })
        .onChange((value) => {
          this.note.setContent(value)
        })
    }.width('100%').height('100%')
  }

  private async save() {
    var url = "note/notes"
    var params = {
      id: this.note.getId(),
      content: this.note.getContent()
    }
    var resp = await httpRequestPost(url, params)
    if (resp.code == 0) {
      router.back()
    } else {
      prompt.showToast({ message: resp.data.msg })
    }
  }
}