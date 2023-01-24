import { createRouter, createWebHashHistory, RouteRecordRaw } from "vue-router";
const routes: Array<RouteRecordRaw> = [
	{
		path: "/note",
		name: "NoteList",
		meta: {
			title: "首页",
			keepAlive: true
		},
		component: () => import("../views/NoteList.vue"),
	},
	{
		path: "/note/login",
		name: "Login",
		meta: {
			title: "登录",
			keepAlive: true
		},
		component: () => import("../views/Login.vue"),
	}, {
		path: "/note/addNote",
		name: "AddNote",
		meta: {
			title: "新建云笔记",
			keepAlive: true
		},
		component: () => import("../views/AddNote.vue"),
	}, {
		path: "/note/modifyNote",
		name: "ModifyNote",
		meta: {
			title: "编辑云笔记",
			keepAlive: true
		},
		component: () => import("../views/ModifyNote.vue"),
	},
];
const router = createRouter({
	history: createWebHashHistory(),
	routes
});
export default router;