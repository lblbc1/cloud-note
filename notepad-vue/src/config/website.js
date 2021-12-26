/**
 * 厦门大学计算机专业 | 前华为工程师
 * 专注《零基础学编程系列》https://cxyxy.blog.csdn.net/article/details/121134634
 * 包含：Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
 * 公众号：花生皮编程
 */
/**
 * 全局配置文件
 */
export default {
    title: "",
    logo: "A",
    key: 'avuex',//配置主键,目前用于存储
    indexTitle: 'test',
    lockPage: '/lock',
    tokenTime: 6000,
    //http的status默认放行不才用统一处理的,
    statusWhiteList: [400],
    //配置首页不可关闭
    isFirstPage: false,
    fistPage: {
        label: "首页",
        value: "/a/index",
        params: {},
        query: {},
        meta: {
            i18n: 'dashboard'
        },
        group: [],
        close: false
    },
    //配置菜单的属性
    menu: {
        iconDefault: 'icon-caidan',
        props: {
            label: 'name',
            path: 'path',
            icon: 'source',
            children: 'children'
        }
    }
}
