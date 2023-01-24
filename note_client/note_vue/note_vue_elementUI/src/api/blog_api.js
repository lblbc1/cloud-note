import request from '../router/axios';
import {baseUrl} from "../config/env";

export const addNote = (form) => {
    return request({
        url: baseUrl + 'note/notes',
        method: 'post',
        data: JSON.stringify(form),
        headers: {"Content-Type": "application/json"},
    })
};

export const modifyNote = (noteId, form) => {
    return request({
        url: baseUrl + 'note/notes/'+noteId,
        method: 'put',
        data: JSON.stringify(form),
        headers: {"Content-Type": "application/json"},
    })
};

export const listNote = () => {
    return request({
        url: baseUrl + 'note/notes',
        method: 'get',
    })
};

export const queryNote = (noteId) => {
    return request({
        url: baseUrl + 'note/notes/'+noteId,
        method: 'get',
    })
};

export const delNote = (noteId) => {
    return request({
        url: baseUrl + 'note/notes/'+noteId,
        method: 'delete',
    })
};
