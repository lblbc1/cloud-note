import axiosInstance, { AxiosResponseProps } from '@/uitls/request';

export const login = (params: any) => {
	return axiosInstance.post('user/login', params);
};

export const register = (params: any) => {
	return axiosInstance.post('user/register', params);
};

export const queryNoteList = () => {
	return axiosInstance.get('note/notes');
};

export const queryNote = (noteId: string) => {
	return axiosInstance.get('note/notes/'+noteId);
};

export const addNote = (params: any) => {
	return axiosInstance.post('note/notes', params);
};

export const modifyNote = (noteId: string, params: any) => {
	return axiosInstance.put('note/notes/'+noteId, params);
};

export const deleteNote = (noteId: string) => {
	return axiosInstance.delete('note/notes/'+noteId);
};