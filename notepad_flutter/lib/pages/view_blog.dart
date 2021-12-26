import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';

import '/network/http_manager.dart';
import '/pages/edit_blog.dart';

/// 厦门大学计算机专业 | 前华为工程师
/// 分享编程技术，没啥深度，但看得懂，适合初学者。
/// Java | 安卓 | 前端 | 小程序 | 鸿蒙
/// 公众号：花生皮编程
class ViewBlogPage extends StatefulWidget {
  final int blogId;

  ViewBlogPage({Key? key, required this.blogId}) : super(key: key);

  @override
  createState() => _ViewBlogPageState(blogId);
}

class _ViewBlogPageState extends State<ViewBlogPage> {
  String title = "";
  String content = "";
  int _blogId = 0;

  _ViewBlogPageState(int blogId) {
    _blogId = blogId;
  }

  @override
  void initState() {
    super.initState();
    loadData();
  }

  loadData() async {
    String url = "blog/api/query/$_blogId";
    HttpManager.getInstance().get(url).then((resp) {
      Map<String, dynamic> result = new Map<String, dynamic>.from(resp);
      setState(() {
        var data = result['data'];
        title = data['title'];
        content = data['content'];
      });
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        leading: IconButton(
          icon: Icon(Icons.arrow_back),
          onPressed: () {
            Navigator.pop(context);
          },
        ),
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
        Text(
          title,
          style: TextStyle(fontSize: 20),
        ),
        Expanded(child: Text(content)),
        ElevatedButton(
          child: Text("修改"),
          onPressed: () {
            Navigator.push(context, MaterialPageRoute(builder: (context) => EditBlogPage(blogId: _blogId)));
          },
        ),
      ],
    );
  }
}
