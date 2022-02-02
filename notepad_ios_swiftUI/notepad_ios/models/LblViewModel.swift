import Foundation


class LblViewModel: ObservableObject {
    
    @Published var noteList:[Note] = []
    
    func queryData() {
        LblAPIService.shared.get(url: "note/api/list", params: nil, headers: nil) { (result: Result<QueryNoteResp, LblAPIService.APIError>) in
            switch result {
            case let .success(response):
                self.noteList = response.data
            case .failure(_):
                break
            }
        }
    }
}
