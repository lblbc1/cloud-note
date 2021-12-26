import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:note_flutter/network/http_managerart';
import 'package:shared_preferences/shared_preferences.dart';

import '../app_strings.dart';
import 'add_note.dart';
import 'view_note.dart';

/// 厦门大学计算机专业 | 前华为工程师
/// 分享编程技术，没啥深度，但看得懂，适合初学者。
/// Java | 安卓 | 前端 | 小程序 | 鸿蒙
/// 公众号：花生皮编程
class NoteListPage extends StatelessWidget {
  final parentContext;

  NoteListPage(this.parentContext);

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: '花生皮博客',
      home: NoteListWidget(this.parentContext),
    );
  }
}

class NoteListWidget extends StatefulWidget {
  final parentContext;

  NoteListWidget(this.parentContext);

  @override
  createState() => _NoteListState();
}

class _NoteListState extends State<NoteListWidget> {
  List notes = [];

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
        onPressed: gotoAddNotePage,
        tooltip: 'addNote',
        child: Icon(Icons.add),
      ),
      body: Center(
        child: getBody(),
      ),
    );
  }

  gotoAddNotePage() {
    Navigator.push(widget.parentContext, MaterialPageRoute(builder: (context) => AddNotePage()));
  }

  loadData() async {
    SharedPreferences sharedPreferences = await SharedPreferences.getInstance();
    int? userId = sharedPreferences.getInt(AppStrings.SP_KEY_USER_ID);
    if (userId != null) {
      String url = "note/api/list/" + userId.toString();
      HttpManager.getInstance().get(url).then((resp) {
        Map<String, dynamic> result = new Map<String, dynamic>.from(resp);
        setState(() {
          notes = result['data'];
        });
      });
    }
  }

  getItem(note) {
    var row = Container(
      margin: EdgeInsets.all(4.0),
      child: InkWell(
        onTap: () {
          onRowClick(note);
        },
        child: buildRow(note),
      ),
    );
    return Card(
      child: row,
    );
  }

  Row buildRow(note) {
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
                note['title'],
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
    if (notes.length != 0) {
      return ListView.builder(
          itemCount: notes.length,
          itemBuilder: (BuildContext context, int position) {
            return getItem(notes[position]);
          });
    } else {
      // 加载菊花
      return CupertinoActivityIndicator();
    }
  }

  onRowClick(note) {
    Navigator.push(widget.parentContext, MaterialPageRoute(builder: (context) => ViewNotePage(noteId: note['id'])));
  }
}
