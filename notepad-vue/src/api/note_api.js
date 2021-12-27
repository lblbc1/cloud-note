/**
 * 厦门大学计算机专业 | 前华为工程师
 * 专注《零基础学编程系列》https://cxyxy.blog.csdn.net/article/details/121134634
 * 包含：Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
 * 公众号：花生皮编程
 */
import request from '../router/axios';
import {baseUrl} from "../config/env";

export const addNote = (form) => {
    return request({
        url: baseUrl + 'note/api/add',
        method: 'post',
        data: JSON.stringify(form),
        headers: {"Content-Type": "application/json"},
    })
};

export const modifyNote = (form) => {
    return request({
        url: baseUrl + 'note/api/modify',
        method: 'post',
        data: JSON.stringify(form),
        headers: {"Content-Type": "application/json"},
    })
};

export const listNote = (userId) => {
    return request({
        url: baseUrl + 'note/api/list/'+userId,
        method: 'get',
    })
};

export const queryNote = (noteId) => {
    return request({
        url: baseUrl + 'note/api/query/'+noteId,
        method: 'get',
    })
};

export const delNote = (noteId) => {
    return request({
        url: baseUrl + 'note/api/del/'+noteId,
        method: 'get',
    })
};
