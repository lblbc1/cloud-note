// 厦门大学计算机专业 | 前华为工程师
// 专注《零基础学编程系列》https://cxyxy.blog.csdn.net/article/details/121134634
// 包含：Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
// 公众号：蓝不蓝编程

import SwiftUI
import SwiftyJSON
import HandyJSON

struct EditNoteView: View {
    var refreshViewModel: RefreshViewModel
    var note: Note
    private var text: State<String>
    @Environment(\.presentationMode) var presentationMode: Binding<PresentationMode>
    
    init(refreshViewModel: RefreshViewModel,note: Note) {
        self.refreshViewModel = refreshViewModel
        self.note = note
        self.text = .init(initialValue: note.content)
    }
    
    
    var body: some View {
        VStack {
            TextField("请输入内容...", text: self.text.projectedValue)
            Spacer()
        }
        .padding()
        .navigationBarTitle(Text("编辑"),displayMode: .inline)
        .navigationBarItems(trailing: Button("保存") {
            saveNote()
        })
    }
    
    private func saveNote(){
        let params = [
            "id": note.id,
            "content": self.text.wrappedValue
        ]
    
        LblProvider.request(.modifyData(params:params)) { result in
            if case let .success(response) = result {
                let data = try? response.mapJSON()
                let json = JSON(data!)
                if let mappedObject = JSONDeserializer<CommonResp>.deserializeFrom(json: json.description) {
                    refreshViewModel.shouldRefresh = true
                    goBack()
                }
            }
        }
    }
    private func goBack()
    {
        self.presentationMode.wrappedValue.dismiss()
    }
}

//struct EditNoteView_Previews: PreviewProvider {
//    static var previews: some View {
//        Group {
//            EditNoteView(refreshViewModel:RefreshViewModel())
//        }
//        .previewLayout(.fixed(width: 300, height: 50))
//    }
//}