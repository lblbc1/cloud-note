package cn.hsp.login.service;

import cn.hsp.login.bean.UserDetail;
import cn.hsp.login.bean.response.LoginResp;
import cn.hsp.login.bean.response.Resp;

/**
 * 厦门大学计算机专业 | 前华为工程师
 * 专注《零基础学编程系列》https://cxyxy.blog.csdn.net/article/details/121134634
 * 包含：Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
 * 公众号：蓝不蓝编程
 */
public interface AuthService {
    /**
     * 注册用户
     */
    Resp<String> register(UserDetail userDetail);

    /**
     * 登陆
     */
    Resp<LoginResp> login(String username, String password);
}
