//花生皮编程(CSDN 、 简书 、 掘金 、 今日头条 、 微信公众号 、 抖音 、 快手 、 B站 、 西瓜视频)
//编程学习资料及开源项目见：https://juejin.cn/post/7002792005688360968
var app = getApp();
  
Component({
  data: {
    dailyFreeParseNum: '--',
    totalParseNum: '--',
    userInfo: null,
    hasUserInfo: false,
  },  
  /**
   * 组件的方法列表
   */
  methods: {
    onLoad: function() {},
    onShow: function() {},

    testTap() {
      wx.showToast({
        title: 'xxxxxxx',
        icon: 'none'
      })
    },
    /**
     * 授权登录
     */
    getUserInfo(e) {
      if (e.detail.errMsg !== 'getUserInfo:ok') {
        wx.showToast({
          title: '未授权，登录失败',
          icon: 'none'
        })
        return false;
      }
      wx.showLoading({
        title: "正在登录",
        mask: true
      });
      // 执行登录
      app.getUserInfo(res => {
        this.setData({
          userInfo: app.globalData.userInfo,
          hasUserInfo: app.globalData.hasUserInfo,
        })
        wx.hideLoading();
      })
    },

    //分享小程序
    onShareAppMessage: function() {
      return {
        title: this.data.config_base_list.share_title ? this.data.config_base_list.share_title : '推荐一款超好用的视频去水印工具，免费解析不限次，大家都在用',
        path: '/pages/index/index',
        imageUrl: this.data.config_base_list.share_imageUrl ? this.data.config_base_list.share_imageUrl : '/images/share.jpg',
        success: function(e) {
          wx.showToast({
            title: "分享成功",
            icon: "success",
            duration: 2e3
          });
        },
        fail: function(e) {
          wx.showToast({
            title: "分享失败",
            icon: "none",
            duration: 2e3
          });
        }
      }
    },
  }
})