import 'package:flutter/material.dart';
import 'package:shared_preferences/shared_preferences.dart';

import 'app_strings.dart';
import 'pages/blog_list.dart';
import 'pages/login.dart';
import 'pages/mine.dart';

/// 厦门大学计算机专业 | 前华为工程师
/// 分享编程技术，没啥深度，但看得懂，适合初学者。
/// Java | 安卓 | 前端 | 小程序 | 鸿蒙
/// 公众号：花生皮编程
class HomeWidget extends StatefulWidget {
  @override
  createState() => _HomeWidgetState();
}

class _HomeWidgetState extends State<HomeWidget> {
  final bottomNavigationColor = Colors.blue;
  int _currentIndex = 0;
  List<Widget> list = [];

  @override
  initState() {
    super.initState();
    list
      ..add(BlogListPage(context))
      ..add(MinePage());
    checkLogin();
  }

  Future<void> checkLogin() async {
    SharedPreferences sharedPreferences = await SharedPreferences.getInstance();
    String? token = sharedPreferences.getString(AppStrings.SP_KEY_TOKEN);
    if (token == null) {
      Navigator.push(context, MaterialPageRoute(builder: (context) => LoginPage()));
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: list[_currentIndex],
      bottomNavigationBar: BottomNavigationBar(
        items: [
          BottomNavigationBarItem(
              icon: Icon(
                Icons.home,
                color: bottomNavigationColor,
              ),
              title: Text(
                '首页',
                style: TextStyle(color: bottomNavigationColor),
              )),
          BottomNavigationBarItem(
              icon: Icon(
                Icons.person,
                color: bottomNavigationColor,
              ),
              title: Text(
                '我的',
                style: TextStyle(color: bottomNavigationColor),
              )),
        ],
        type: BottomNavigationBarType.fixed,
        currentIndex: _currentIndex,
        onTap: (int index) {
          setState(() {
            _currentIndex = index;
          });
        },
      ),
    );
  }
}
