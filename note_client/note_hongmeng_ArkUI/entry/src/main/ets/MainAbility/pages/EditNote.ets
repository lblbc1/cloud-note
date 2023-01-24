import prompt from '@ohos.prompt';
/**
 * 厦门大学计算机专业 | 前华为工程师
 * 专注《零基础学编程系列》  http://lblbc.cn/blog
 * 包含：Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
 * 公众号：蓝不蓝编程
 */
import { httpRequestGet, httpRequestPut, httpRequestDel } from '../../common/utils/HttpUtil';
import router from '@ohos.router';
import { Note } from '../../data/Note';

@Entry
@Component
struct EditNote {
  @State note: Note = new Note()
  private noteId: string = router.getParams()['noteId'];

  aboutToAppear() {
    this.queryData();
  }

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
        Text('编辑').fontSize(20)
        Blank()
        Text('保存').fontColor('#717171').fontSize(20)
          .onClick(() => {
            this.save()
          })
        Text(' | ').fontColor('#717171').fontSize(10).padding({ left: 10, right: 10 })
        Text('删除').fontColor('#717171').fontSize(20).margin({ right: 20 })
          .onClick(() => {
            this.delete()
          })
      }.width('100%').margin({ bottom: 10 })

      TextArea({ placeholder: "请输入正文", text: this.note.getContent() })
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

  private async queryData() {
    var url = "note/notes/" + this.noteId
    var data = await httpRequestGet(url)
    this.note.setId(data.data.id)
    this.note.setContent(data.data.content)
  }

  private async save() {
    var url = "note/notes/"+ this.noteId
    var params = {
      content: this.note.getContent()
    }
    var resp = await httpRequestPut(url, params)
    if (resp.code == 0) {
      router.back()
    } else {
      prompt.showToast({ message: resp.data.msg })
    }
  }

  private async delete() {
    var url = "note/notes/" + this.noteId
    await httpRequestDel(url)
    router.back()
  }
}