//花生皮编程(CSDN 、 简书 、 掘金 、 今日头条 、 微信公众号 、 抖音 、 快手 、 B站 、 西瓜视频)
//编程学习资料及开源项目见：https://juejin.cn/post/7002792005688360968
var http = require('../../utils/httputils.js');

Page({
  data: {
    blogId: 0,
    blogTitle: "",
    blogContent: ""
  },
  onLoad: function (option) {
    this.queryBlog(Number(option.id))
  },
  methods: {
  },
  queryBlog(blogId) {
    let _this = this
    http.get('blog/api/query/' + blogId, '',
      function (resp) {
        _this.setData({
          blogId: blogId,
          blogTitle: resp.data.title,
          blogContent: resp.data.content
        })
      },
      function (err) { })
  },
  modifyBlog(e) {
    var params = {
      id: this.data.blogId,
      title: e.detail.value.title,
      content: e.detail.value.content,
    }
    http.post('blog/api/modify', params,
      function (resp) {
        wx.switchTab({ url: '/pages/list/list' })
      },
      function (err) { })
  }

})