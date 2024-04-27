import {createRouter, createWebHashHistory, RouteRecordRaw} from "vue-router";

import 'vue-router'

declare module 'vue-router' {
    interface RouteMeta {
        displayName: string,
        role?: string
    }
}

const routes: Readonly<RouteRecordRaw[]> = [
    {
        name: "Welcome",
        path: "/",
        component: () => import("../views/welcome.vue"),
    },
    {
        name: "Forget Password",
        path: "/forget-password",
        component: () => import("../views/forget_password.vue"),
    },
    {
        name: "Dashboard",
        path: "/dashboard",
        component: () => import("../views/dashboard/index.vue"),
        children: [
            {
                name: "Read",
                path: "read",
                component: () => import("../views/dashboard/read.vue"),
                meta: {
                    displayName: "书籍检索"
                }
            },
            {
                name: "Borrow",
                path: "borrow-management",
                component: () => import("../views/dashboard/borrow.vue"),
                meta: {
                    displayName: "我的借阅"
                }
            },
            {
                name: "Account",
                path: "account-management",
                component: () => import("../views/dashboard/account.vue"),
                meta: {
                    displayName: "账号管理"
                }
            },
            {
                name: "System",
                path: "system-management",
                component: () => import("../views/dashboard/system/index.vue"),
                meta: {
                    displayName: "系统管理",
                    role: "ADMIN"
                }
            },
        ],
        meta: {
            displayName: "仪表盘"
        }
    }
]

const router = createRouter({
    history: createWebHashHistory(),
    routes
})

export default router