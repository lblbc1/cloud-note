import Foundation

public struct LblAPIService {
    let baseURL = "http://192.168.31.10:8080/"
    
    public static let shared = LblAPIService()
    let decoder = JSONDecoder()
    
    
    public enum APIError: Error {
        case noResponse
        case jsonDecodingError(error: Error)
        case networkError(error: Error)
    }
    
    public func post<T: Codable>(url: String,
                                 params: [String: String]?, headers: [String: String]?,
                                 completionHandler: @escaping (Result<T, APIError>) -> Void) {
        let queryURL = baseURL+url
        guard let url = URL(string: queryURL) else {
            debugPrint("error url: \(queryURL)")
            return
        }
        var components = URLComponents(url: url, resolvingAgainstBaseURL: true)!
        if let params = params {
            for (_, value) in params.enumerated() {
                components.queryItems?.append(URLQueryItem(name: value.key, value: value.value))
            }
        }
        debugPrint(components.url!)
        var request = URLRequest(url: components.url!, cachePolicy: .reloadIgnoringLocalAndRemoteCacheData, timeoutInterval: 60.0)
        request.httpMethod = "POST"
        if headers != nil {
            request.allHTTPHeaderFields = headers
        }
        request.setValue("iAppStore/1.0 Mobile/15E148 Safari/604.1", forHTTPHeaderField: "User-Agent")
        let task = URLSession.shared.dataTask(with: request) { (data, response, error) in
            guard let data = data else {
                DispatchQueue.main.async {
                    completionHandler(.failure(.noResponse))
                }
                return
            }
            guard error == nil else {
                DispatchQueue.main.async {
                    completionHandler(.failure(.networkError(error: error!)))
                }
                return
            }
            guard let response = response as? HTTPURLResponse, response.statusCode == 200 else {
                DispatchQueue.main.async {
                    completionHandler(.failure(.noResponse))
                }
                return
            }
            do {
                let object = try self.decoder.decode(T.self, from: data)
                DispatchQueue.main.async {
                    completionHandler(.success(object))
                }
            } catch let error {
                DispatchQueue.main.async {
                    #if DEBUG
                    print("JSON Decoding Error: \(error)")
                    #endif
                    completionHandler(.failure(.jsonDecodingError(error: error)))
                }
            }
        }
        task.resume()
    }
    
    
    public func get<T: Codable>(url: String,
                                params: [String: String]?,headers: [String: String]?,
                                completionHandler: @escaping (Result<T, APIError>) -> Void) {
        let queryURL = baseURL+url
        var components = URLComponents(url: URL(string: queryURL)!, resolvingAgainstBaseURL: true)!
        if let params = params {
            for (_, value) in params.enumerated() {
                components.queryItems?.append(URLQueryItem(name: value.key, value: value.value))
            }
        }
        var request = URLRequest(url: components.url!)
        request.httpMethod = "GET"
        if headers != nil {
            request.allHTTPHeaderFields = headers
        }
        let task = URLSession.shared.dataTask(with: request) { (data, response, error) in
            guard let data = data else {
                DispatchQueue.main.async {
                    completionHandler(.failure(.noResponse))
                }
                return
            }
            guard error == nil else {
                DispatchQueue.main.async {
                    completionHandler(.failure(.networkError(error: error!)))
                }
                return
            }
            
            guard let response = response as? HTTPURLResponse, response.statusCode == 200 else {
                DispatchQueue.main.async {
                    completionHandler(.failure(.noResponse))
                }
                return
            }
            
            do {
                let object = try self.decoder.decode(T.self, from: data)
                DispatchQueue.main.async {
                    completionHandler(.success(object))
                }
            } catch let error {
                DispatchQueue.main.async {
                    #if DEBUG
                    print("JSON Decoding Error: \(error)")
                    #endif
                    completionHandler(.failure(.jsonDecodingError(error: error)))
                }
            }

            
        }
        task.resume()
    }
    
}
