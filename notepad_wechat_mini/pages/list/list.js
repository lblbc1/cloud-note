//index.js
//获取应用实例
var http = require('../../utils/httputils.js');
const app = getApp()

Page({
  data: {
    dataList: [],
  },
  onLoad: function () {
    this.getNoteList()
  },
  onShow: function () {
    this.getNoteList()
  },
  methods: {
  },
  getNoteList() {
    let _this = this
    let userId = app.getGlobalUserInfo().id
    http.get('note/api/list/' + userId, '',
      function (resp) {
        _this.setData({
          dataList: resp.data
        })
      },
      function (err) { })
  },
  addNote(){
    // wx.navigateTo({
    //   url: '/pages/note-add/note-add'
    // })
    wx.navigateTo({
      url: '/pages/note-add/note-add'
    })
  },
  viewNote(e) {
    let noteId = e.currentTarget.dataset['noteid'];
    wx.navigateTo({
      url: '/pages/note-edit/note-edit?id=' + noteId
    })
  }

})