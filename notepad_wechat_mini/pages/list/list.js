//index.js
//获取应用实例
var http = require('../../utils/httputils.js');
const app = getApp()

Page({
  data: {
    dataList: [],
  },
  onLoad: function () {
    this.getBlogList()
  },
  onShow: function () {
    this.getBlogList()
  },
  methods: {
  },
  getBlogList() {
    let _this = this
    let userId = app.getGlobalUserInfo().id
    http.get('blog/api/list/' + userId, '',
      function (resp) {
        _this.setData({
          dataList: resp.data
        })
      },
      function (err) { })
  },
  addBlog(){
    // wx.navigateTo({
    //   url: '/pages/blog-add/blog-add'
    // })
    wx.navigateTo({
      url: '/pages/blog-add/blog-add'
    })
  },
  viewBlog(e) {
    let blogId = e.currentTarget.dataset['blogid'];
    wx.navigateTo({
      url: '/pages/blog-view/blog-view?id=' + blogId
    })
  }

})