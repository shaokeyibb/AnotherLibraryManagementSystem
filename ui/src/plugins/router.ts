import {createRouter, createWebHashHistory, RouteRecordRaw} from "vue-router";

const routes: Readonly<RouteRecordRaw[]> = [
    {
        name: "Welcome",
        path: "/",
        component: () => import("../views/welcome.vue"),
    },
    {
        name: "Dashboard",
        path: "/dashboard",
        component: () => import("../views/dashboard/index.vue"),
        children: [
            {
                name: "Read",
                path: "",
                component: () => import("../views/dashboard/read.vue"),
                meta: {
                    displayName: "书籍检索"
                }
            }
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