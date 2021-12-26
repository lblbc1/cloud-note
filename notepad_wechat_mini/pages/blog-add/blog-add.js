//花生皮编程(CSDN 、 简书 、 掘金 、 今日头条 、 微信公众号 、 抖音 、 快手 、 B站 、 西瓜视频)
//编程学习资料及开源项目见：https://juejin.cn/post/7002792005688360968
var http = require('../../utils/httputils.js');

Page({
  data: {
    blogTitle: "",
    blogContent: ""
  },
  methods: {
  },
  onShow: function () {
    this.setData({
      blogTitle: "",
      blogContent: ""
    })
  },
  addBlog(e) {
    var params = {
      title: e.detail.value.title,
      content: e.detail.value.content,
    }
    http.post('blog/api/add', params,
      function (resp) {
        wx.switchTab({ url: '/pages/list/list' })
      },
      function (err) { })
  }

})