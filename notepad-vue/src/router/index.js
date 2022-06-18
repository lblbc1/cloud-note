/**
 * 厦门大学计算机专业 | 前华为工程师
 * 专注《零基础学编程系列》  http://lblbc.cn/blog
 * 包含：Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
 * 公众号：蓝不蓝编程
 */
import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)
const router = new Router({
    routes: [

        {
            path: "/note/login",
            name: "Login",
            component: () =>
                import( '@/views/Login'),
        },
        {
            path: "/note/register",
            name: "Register",
            component: () =>
                import( '@/views/Register'),
        },
        {
            path: "/note/list/*",
            name: "NoteList",
            component: () =>
                import( '@/views/NoteList'),
        },
        {
            path: "/note/add",
            name: "AddNote",
            component: () =>
                import( '@/views/AddNote'),
            meta: {
                requiresAuth: true,
            }
        },
        {
            path: "/note/modify",
            name: "ModifyNote",
            component: () =>
                import( '@/views/ModifyNote'),
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
