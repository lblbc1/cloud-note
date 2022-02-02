import Foundation


class LblViewModel: ObservableObject {
    
    @Published var userInfo: UserInfo? = nil
    
    func queryData() {
        LblAPIService.shared.get(url: "swiftui_demos/raw/master/networkDemo/data.json", params: nil, headers: nil) { (result: Result<UserInfo, LblAPIService.APIError>) in
            switch result {
            case let .success(response):
                self.userInfo = response
            case .failure(_):
                break
            }
        }
    }
}
