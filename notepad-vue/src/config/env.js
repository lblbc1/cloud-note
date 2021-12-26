/**
 * 厦门大学计算机专业 | 前华为工程师
 * 专注《零基础学编程系列》https://cxyxy.blog.csdn.net/article/details/121134634
 * 包含：Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
 * 公众号：花生皮编程
 */

// 配置编译环境和线上环境之间的切换

let baseUrl = '';
const env = process.env
if (env.NODE_ENV == 'development') {
    baseUrl = `http://localhost:8080/`; // 开发环境地址
} else if (env.NODE_ENV == 'production') {
    baseUrl = ``; //生产环境地址
} else if (env.NODE_ENV == 'graph.vue') {
    baseUrl = ``; //测试环境地址
}
export {
    baseUrl,
    env
}
