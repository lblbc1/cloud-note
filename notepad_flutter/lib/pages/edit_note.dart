import 'package:note_flutter/network/http_manager.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';

/// 厦门大学计算机专业 | 前华为工程师
/// 分享编程技术，没啥深度，但看得懂，适合初学者。
/// Java | 安卓 | 前端 | 小程序 | 鸿蒙
/// 公众号：花生皮编程
class EditNotePage extends StatefulWidget {
  final int noteId;

  EditNotePage({Key? key, required this.noteId}) : super(key: key);

  @override
  createState() => _EditNotePageState(noteId);
}

class _EditNotePageState extends State<EditNotePage> {
  String title = "";
  String content = "";
  int _noteId = 0;

  TextEditingController _titleController = TextEditingController();
  TextEditingController _contentController = TextEditingController();

  _EditNotePageState(int noteId) {
    _noteId = noteId;
  }

  @override
  void initState() {
    super.initState();
    loadData();
  }

  loadData() async {
    String url = "note/api/query/$_noteId";
    HttpManager.getInstance().get(url).then((resp) {
      Map<String, dynamic> result = new Map<String, dynamic>.from(resp);
      setState(() {
        var data = result['data'];
        title = data['title'];
        content = data['content'];
        _titleController.text = title;
        _contentController.text = content;
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
            modifyNote();
          },
        ),
      ],
    );
  }

  modifyNote() async {
    String url = "note/api/modify";
    String title = _titleController.text;
    String content = _contentController.text;
    HttpManager.getInstance().post(url, data: {"id": widget.noteId, "title": title, "content": content}).then((resp) {
      Navigator.of(context).popUntil((route) => route.isFirst); //回到首页
    });
  }
}