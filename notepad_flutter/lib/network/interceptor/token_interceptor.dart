import 'package:dio/dio.dart';
import 'package:shared_preferences/shared_preferences.dart';

import '../../app_strings.dart';

/// 厦门大学计算机专业 | 前华为工程师
/// 分享编程技术，没啥深度，但看得懂，适合初学者。
/// Java | 安卓 | 前端 | 小程序 | 鸿蒙
/// 公众号：花生皮编程

class TokenInterceptor extends Interceptor {
  @override
  Future<void> onRequest(RequestOptions options, RequestInterceptorHandler handler) async {
    SharedPreferences sharedPreferences = await SharedPreferences.getInstance();
    String? token = sharedPreferences.getString(AppStrings.SP_KEY_TOKEN);
    if (token != null) {
      options.headers[AppStrings.HTTP_HEADER_AUTH] = AppStrings.HTTP_HEADER_TOKEN_PREFIX + token;
    }
    handler.next(options);
  }
}
