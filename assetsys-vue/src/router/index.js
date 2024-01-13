// index.js

import { createRouter, createWebHistory } from 'vue-router';
import Register from '../views/Register.vue';
import Login from '../views/Login.vue';
import Main from '../views/Main.vue';
import Admin from "../views/Admin.vue";
import AssetLabel from "@/views/AssetLabel.vue";
import Org from "@/views/Org.vue";
import Asset from "@/views/Asset.vue";
import TaskManage from "@/views/TaskManage.vue";
import Mytask from "@/views/Mytask.vue";
import Scan from "@/views/Scan.vue";
import Exploit from "@/views/Exploit.vue";
import Move from "@/views/Move.vue";
import UserManage from "@/views/UserManage.vue";
import AssetCategory from "@/views/AssetCategory.vue";
const routes = [
    {
        path: '/register',
        name: 'Register',
        component: Register,
    },
    {
        path: '/login',
        name: 'Login',
        component: Login,
    },
    {
        path: '/main',
        name: 'Main',
        component: Main,
    },
    {
        path: '/admin',
        name: 'Admin',
        component: Admin,
        meta: { requiresAuth: true }
    },
    {
        path: '/assetLabel',
        name: 'AssetLabel',
        component: AssetLabel,
    },
    {
        path: '/assetCategory',
        name: 'AssetCategory',
        component: AssetCategory,
    },
    {
        path: '/org',
        name: 'Org',
        component: Org,
    },
    {
        path:'/asset',
        name:'Asset',
        component:Asset,
    },
    {
        path: '/',
        redirect: '/login',
        component: Login,
    },
    {
        path: '/taskManage',
        name: 'TaskManage',
        component: TaskManage,
    },
    {
        path: '/mytask',
        name: 'Mytask',
        component: Mytask,
    },
    {
        path: '/myscan',
        name: 'Scan',
        component: Scan,
    },
    {
        path: '/exploit',
        name: 'Exploit',
        component: Exploit,
    },
    {
        path: '/move',
        name: 'Move',
        component: Move,
    },
    {
        path: '/userManage',
        name: 'UserManage',
        component: UserManage,
    },


];

const router = createRouter({
    history: createWebHistory(),
    routes,
});


//导航卫士
router.beforeEach((to, from, next) => {
    // 检查是否需要登录
    if (to.meta.requiresAuth || !['/login', '/main', '/register'].includes(to.path)) {
        // 检查本地存储中是否存在有效的令牌
        const token = localStorage.getItem('token');
        console.log('Token:', token);
        if (token) {
            // 存在有效令牌，允许访问
            next();
        } else {
            // 不存在有效令牌，重定向到登录页面
            next('/login');
        }
    } else {
        // 不需要登录，允许访问
        next();
    }
});

export default router;
