/**
 * 厦门大学计算机专业 | 前华为工程师
 * 专注《零基础学编程系列》https://cxyxy.blog.csdn.net/article/details/121134634
 * 包含：Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
 * 公众号：花生皮编程
 */
import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)
const router = new Router({
    routes: [

        {
            path: "/blog/login",
            name: "Login",
            component: () =>
                import( '@/views/Login'),
        },
        {
            path: "/blog/register",
            name: "Register",
            component: () =>
                import( '@/views/Register'),
        },
        {
            path: "/blog/list/*",
            name: "BlogList",
            component: () =>
                import( '@/views/BlogList'),
        },
        {
            path: "/blog/add",
            name: "AddBlog",
            component: () =>
                import( '@/views/AddBlog'),
            meta: {
                requiresAuth: true,
            }
        },
        {
            path: "/blog/modify",
            name: "ModifyBlog",
            component: () =>
                import( '@/views/ModifyBlog'),
            meta: {
                requiresAuth: true,
            }
        }
    ]
})

router.beforeEach((to, from, next) => {
    if (to.matched.some(record => record.meta.requiresAuth)) {
        if (sessionStorage.getItem("user_token") == null) {
            next({
                name: 'Login',
            })
        } else {
            next()
        }
    } else {
        next()
    }
})

export default router
