/**
 * 厦门大学计算机专业 | 前华为工程师
 * 专注《零基础学编程系列》https://cxyxy.blog.csdn.net/article/details/121134634
 * 包含：Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
 * 公众号：花生皮编程
 */
import request from '../router/axios';
import {baseUrl} from "../config/env";

export const addBlog = (form) => {
    return request({
        url: baseUrl + 'blog/api/add',
        method: 'post',
        data: JSON.stringify(form),
        headers: {"Content-Type": "application/json"},
    })
};

export const modifyBlog = (form) => {
    return request({
        url: baseUrl + 'blog/api/modify',
        method: 'post',
        data: JSON.stringify(form),
        headers: {"Content-Type": "application/json"},
    })
};

export const listBlog = (userId) => {
    return request({
        url: baseUrl + 'blog/api/list/'+userId,
        method: 'get',
    })
};

export const queryBlog = (blogId) => {
    return request({
        url: baseUrl + 'blog/api/query/'+blogId,
        method: 'get',
    })
};

export const delBlog = (blogId) => {
    return request({
        url: baseUrl + 'blog/api/del/'+blogId,
        method: 'get',
    })
};
