/**
 * 厦门大学计算机专业 | 前华为工程师
 * 专注《零基础学编程系列》https://cxyxy.blog.csdn.net/article/details/121134634
 * 包含：Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
 * 公众号：蓝不蓝编程
 */
import request from '../router/axios';
import {baseUrl} from "../config/env";
import qs from 'qs'

export const login = (form) => {
    window.qs = qs;
    return request({
        url: baseUrl + 'user/login',
        method: 'post',
        data: {
            name: form.username,
            password: form.password
        },
        headers: { "Content-Type": "application/json" },
    })
};

export const register = (form) => {
    window.qs = qs;
    return request({
        url: baseUrl + 'user/register',
        method: 'post',
        data: {
            name: form.username,
            password: form.password
        },
        headers: { "Content-Type": "application/json" },
    })
};


