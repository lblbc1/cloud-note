/**
 * 厦门大学计算机专业 | 前华为工程师
 * 专注《零基础学编程系列》https://cxyxy.blog.csdn.net/article/details/121134634
 * 包含：Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
 * 公众号：花生皮编程
 */
import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({

    state: {
        userToken: '',
    },

    mutations: {
        setUserToken(userToken) {
            this.state.userToken = userToken
        },
    },

    getters: {
        getUserToken: state => state.userToken,
    },

    actions: {
        //这个部分我暂时用不上
    },

    modules: {
        //这里是我自己理解的是为了给全局变量分组，所以需要写提前声明其他store文件，然后引入这里
    }
})

