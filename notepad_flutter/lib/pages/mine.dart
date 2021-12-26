import 'package:flutter/material.dart';

/// 厦门大学计算机专业 | 前华为工程师
/// 专注《零基础学编程系列》https://cxyxy.blog.csdn.net/article/details/121134634
/// 包含：Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
/// 公众号：花生皮编程
class MinePage extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('我的'),
      ),
      body: Center(
        child: Container(margin: EdgeInsets.fromLTRB(20, 20, 20, 20), child: buildColumn()),
      ),
    );
  }

  Column buildColumn() {
    return Column(
      crossAxisAlignment: CrossAxisAlignment.center,
      mainAxisAlignment: MainAxisAlignment.center,
      children: <Widget>[
        CircleAvatar(backgroundColor: Colors.white, backgroundImage: AssetImage("assets/images/user_logo.png")),
        Text(
          "花生皮编程",
          style: TextStyle(fontSize: 15),
        ),
        Expanded(child: Text("haha")),
        Text(
          "©2021 花生皮编程",
          style: TextStyle(fontSize: 15),
        ),
      ],
    );
  }
}
