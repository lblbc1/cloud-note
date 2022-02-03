// 厦门大学计算机专业 | 前华为工程师
// 分享编程技术，没啥深度，但看得懂，适合初学者。
// Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
// 公众号：蓝不蓝编程

import Foundation
import Moya
import HandyJSON


let LblProvider = MoyaProvider<LblAPI>()


enum LblAPI {
    case queryData
}

extension LblAPI: TargetType {
    public var baseURL: URL {
        return URL(string: "https://gitee.com/lblbc")!
    }
    
    var path: String {
        return "/swiftui_demos/raw/master/networkDemo/simpleNetworkDemo/data.json"
//        switch self {
//        case .queryData: return "/swiftui_demos/raw/master/networkDemo/simpleNetworkDemo/data.json"
//        }
    }
    
    var method: Moya.Method { return .get }
    var task: Task {
        var parmeters:[String:Any] = [:]
//        switch self {
//        case .queryData:
//            parmeters = [
//                "device":"iPhone",
//                "appid":0,]
//        }

        
        return .requestParameters(parameters: parmeters, encoding: URLEncoding.default)
    }
    
    var sampleData: Data { return "".data(using: String.Encoding.utf8)! }
    var headers: [String : String]? { return nil }
}

