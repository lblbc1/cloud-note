// 厦门大学计算机专业 | 前华为工程师
// 专注《零基础学编程系列》https://cxyxy.blog.csdn.net/article/details/121134634
// 包含：Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
// 公众号：蓝不蓝编程

import SwiftUI

struct NoteListView : View {
    @StateObject private var lblViewModel = LblViewModel()
    @State private var isLoginViewPresented: Bool = false
    @StateObject private var refreshViewModel = RefreshViewModel()
    
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
                NavigationLink(destination: EditNoteView(refreshViewModel: refreshViewModel, note: note)) {
                    NoteRowView(note: note)
                }
            }
            .navigationBarTitle(Text("记事本-蓝不蓝编程"), displayMode: .inline)
            .navigationBarItems(trailing: NavigationLink(destination: AddNoteView(refreshViewModel:refreshViewModel)) {
                Text("新建")
            })
        }.sheet(isPresented: $isLoginViewPresented, content: {
            LoginView(refreshViewModel: refreshViewModel, isLoginViewPresented: $isLoginViewPresented)
        })
            .onAppear {
                if(LoginViewModel.shared.isLoggedIn())
                {
                    lblViewModel.queryData()
                }else{
                    isLoginViewPresented = true
                }
            }
            .onChange(of: refreshViewModel.shouldRefresh, perform: { value in
                if(value)
                {
                    refreshViewModel.shouldRefresh = false
                    lblViewModel.queryData()
                }
            })
    }
    
    struct NoteRowView: View {
        var note: Note
        var body: some View {
            HStack {
                Text(note.content)
            }
        }
    }
    
}

#if DEBUG
struct NoteList_Previews : PreviewProvider {
    static var previews: some View {
        NoteListView()
    }
}
#endif

