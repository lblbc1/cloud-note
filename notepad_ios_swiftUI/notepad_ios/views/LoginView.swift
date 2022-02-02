import SwiftUI

struct LoginView : View {
    @StateObject private var lblViewModel = LblViewModel()
    @State var userName: String = ""
    @State var password: String = ""
    @State var showPwd = false
    var isCanLogin: Bool {
        userName.count > 0 &&
        password.count > 0
    }
    var body: some View {
        VStack {
            HStack {
                Image(systemName: "person")
                TextField("请输入用户名", text: $userName, onCommit: {
                    
                })
            }
            Divider()
            HStack {
                Image(systemName: "lock")
                if showPwd {
                    TextField("请输入密码", text: $password, onCommit: {
                        
                    })
                } else {
                    SecureField("请输入密码", text: $password, onCommit: {
                        
                    })
                }
                Button(action: {
                    self.showPwd.toggle()
                }) {
                    Image(systemName: self.showPwd ? "eye" : "eye.slash")
                }
                
            }
            Divider()
            Button(action: {
                print("login action")
            }) {
                Text("Login")
                    .foregroundColor(.white)
            }
            .frame(width: 100, height: 45, alignment: .center)
            .background(isCanLogin ? Color.blue: Color.gray)
            .cornerRadius(10)
            .disabled(!isCanLogin)
            Spacer()
        }
        .padding(.top, 100)
        .padding(.leading)
        .padding(.trailing)
    }
}

#if DEBUG
struct LoginView_Previews : PreviewProvider {
    static var previews: some View {
        LoginView()
    }
}
#endif

