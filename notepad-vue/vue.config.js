module.exports = {
    publicPath: './', // 根据你的实际情况更改这里
    lintOnSave: true,
    productionSourceMap: false,

    chainWebpack: (config) => {

        const entry = config.entry('app')
        entry
            .add('babel-polyfill')
            .end()
        entry
            .add('classlist-polyfill')
            .end()
    },
    devServer: {
        // 端口配置
        port: 8090,
    }
}
