import request from '../utils/request'

export function login(params) {
	return request(params, 'user/login', 'post').then(resp => resp)
}

export function register(params) {
	return request(params, 'user/register', 'post').then(resp => resp)
}

export function queryNotes() {
	return request('', 'note/notes', 'get').then(resp => resp)
}

export function queryNote(noteId) {
	return request('', `note/notes/${noteId}`, 'get').then(resp => resp)
}

export function addNote(params) {
	return request(params, 'note/notes', 'post').then(resp => resp)
}

export function modifyNote(noteId, params) {
  return request(params, `note/notes/${noteId}`, 'put').then(resp => resp)
}

export function delNote(noteId) {
  return request('', `note/notes/${noteId}`, 'delete').then(resp => resp)
}

