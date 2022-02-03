// 厦门大学计算机专业 | 前华为工程师
// 专注《零基础学编程系列》https://cxyxy.blog.csdn.net/article/details/121134634
// 包含：Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
// 公众号：蓝不蓝编程

import SwiftUI

struct NoteListView : View {
    @EnvironmentObject var userData: UserData
    @StateObject private var lblViewModel = LblViewModel()
    @State private var isLoginViewPresented: Bool = false
    
    let screnDelegate: UIWindowSceneDelegate? = {
        var uiScreen: UIScene?
        UIApplication.shared.connectedScenes.forEach { (screen) in
            uiScreen = screen
        }
        return (uiScreen?.delegate as? UIWindowSceneDelegate)
    }()
    
    var body: some View {
        NavigationView {
            List(lblViewModel.noteList) { note in
                NavigationLink(destination: NoteDetail(note: note)
                                .environmentObject(self.userData)) {
                    NoteRow(note: note)
                }
            }
            .navigationBarTitle(Text("记事本-蓝不蓝编程"), displayMode: .inline)
            .navigationBarItems(trailing: Button(action: self.createNote, label: { Text("新建") }))
        }.sheet(isPresented: $isLoginViewPresented, content: {
            LoginView(isLoginViewPresented: $isLoginViewPresented)
        })
            .onAppear {
                if(userData.userInfo == nil)
                {
                    isLoginViewPresented = true
                }else{
                    lblViewModel.queryData()
                }
            }
    }
    
    private func createNote() {
        //        var numberThree: Int = Int(arc4random_uniform(100))
        //        print(numberThree)
        
        //        let newNote = Note(text: String(numberThree))
        //        self.userData.notes.insert(newNote, at: 0)
    }
}

#if DEBUG
struct NoteList_Previews : PreviewProvider {
    static var previews: some View {
        NoteListView().environmentObject(UserData())
    }
}
#endif

