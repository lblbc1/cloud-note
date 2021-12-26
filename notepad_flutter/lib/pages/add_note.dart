import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:note_flutter/network/http_manager.dart';

/// 厦门大学计算机专业 | 前华为工程师
/// 分享编程技术，没啥深度，但看得懂，适合初学者。
/// Java | 安卓 | 前端 | 小程序 | 鸿蒙
/// 公众号：花生皮编程
class AddNotePage extends StatefulWidget {
  AddNotePage({Key? key}) : super(key: key);

  @override
  createState() => _AddNotePageState();
}

class _AddNotePageState extends State<AddNotePage> {
  TextEditingController _contentController = TextEditingController();
  TextEditingController _titleController = TextEditingController();
  String title = "";
  String content = "";

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text("发博客"),
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
        TextField(
          style: TextStyle(fontSize: 20),
          decoration: InputDecoration(
            hintText: "请输入标题",
          ),
          controller: _titleController,
        ),
        Expanded(
            child: TextField(
          keyboardType: TextInputType.multiline,
          maxLines: 10,
          decoration: InputDecoration(
            hintText: "请输入正文",
          ),
          controller: _contentController,
        )),
        ElevatedButton(
          child: Text("发布"),
          onPressed: () {
            addNote();
          },
        ),
      ],
    );
  }

  addNote() async {
    String url = "note/api/add";
    String title = _titleController.text;
    String content = _contentController.text;
    HttpManager.getInstance().post(url, data: {"title": title, "content": content}).then((resp) {
      Navigator.pop(context);
    });
  }
}
