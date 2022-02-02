
import Foundation

struct QueryNoteResp: Hashable,Codable{
    var code = 0
    var msg:String
    var data:[Note]
}
