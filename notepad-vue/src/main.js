/**
 * 厦门大学计算机专业 | 前华为工程师
 * 专注《零基础学编程系列》https://cxyxy.blog.csdn.net/article/details/121134634
 * 包含：Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
 * 公众号：花生皮编程
 */
import Vue from 'vue'
import App from './App.vue'
import router from './router'
// 引入element-ui
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
// import echarts from 'echarts'
import store from './store'
// import '../theme/index.css'


Vue.use(ElementUI)

Vue.config.productionTip = false

// Vue.prototype.$echarts = echarts

new Vue({
  el:"#app",
  router,
  store,
  render: h => h(App),
}).$mount('#app')

