import 'package:blog_flutter/network/http_manager.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:shared_preferences/shared_preferences.dart';

import '../app_strings.dart';
import 'add_blog.dart';
import 'view_blog.dart';

/// 厦门大学计算机专业 | 前华为工程师
/// 分享编程技术，没啥深度，但看得懂，适合初学者。
/// Java | 安卓 | 前端 | 小程序 | 鸿蒙
/// 公众号：花生皮编程
class BlogListPage extends StatelessWidget {
  final parentContext;

  BlogListPage(this.parentContext);

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: '花生皮博客',
      home: BlogListWidget(this.parentContext),
    );
  }
}

class BlogListWidget extends StatefulWidget {
  final parentContext;

  BlogListWidget(this.parentContext);

  @override
  createState() => _BlogListState();
}

class _BlogListState extends State<BlogListWidget> {
  List blogs = [];

  @override
  void initState() {
    super.initState();
    loadData();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text("博客"),
      ),
      floatingActionButton: FloatingActionButton(
        onPressed: gotoAddBlogPage,
        tooltip: 'addBlog',
        child: Icon(Icons.add),
      ),
      body: Center(
        child: getBody(),
      ),
    );
  }

  gotoAddBlogPage() {
    Navigator.push(widget.parentContext, MaterialPageRoute(builder: (context) => AddBlogPage()));
  }

  loadData() async {
    SharedPreferences sharedPreferences = await SharedPreferences.getInstance();
    int? userId = sharedPreferences.getInt(AppStrings.SP_KEY_USER_ID);
    if (userId != null) {
      String url = "blog/api/list/" + userId.toString();
      HttpManager.getInstance().get(url).then((resp) {
        Map<String, dynamic> result = new Map<String, dynamic>.from(resp);
        setState(() {
          blogs = result['data'];
        });
      });
    }
  }

  getItem(blog) {
    var row = Container(
      margin: EdgeInsets.all(4.0),
      child: InkWell(
        onTap: () {
          onRowClick(blog);
        },
        child: buildRow(blog),
      ),
    );
    return Card(
      child: row,
    );
  }

  Row buildRow(blog) {
    return Row(
      children: <Widget>[
        Expanded(
            child: Container(
          margin: EdgeInsets.only(left: 8.0),
          height: 40.0,
          child: Column(
            crossAxisAlignment: CrossAxisAlignment.start,
            mainAxisAlignment: MainAxisAlignment.center,
            children: <Widget>[
              Text(
                blog['title'],
                style: TextStyle(
                  fontSize: 18.0,
                ),
                maxLines: 1,
              ),
            ],
          ),
        ))
      ],
    );
  }

  getBody() {
    if (blogs.length != 0) {
      return ListView.builder(
          itemCount: blogs.length,
          itemBuilder: (BuildContext context, int position) {
            return getItem(blogs[position]);
          });
    } else {
      // 加载菊花
      return CupertinoActivityIndicator();
    }
  }

  onRowClick(blog) {
    Navigator.push(widget.parentContext, MaterialPageRoute(builder: (context) => ViewBlogPage(blogId: blog['id'])));
  }
}
