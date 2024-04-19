import {createRouter, createWebHashHistory, RouteRecordRaw} from "vue-router";

const routes: Readonly<RouteRecordRaw[]> = [
    {
        path: "/",
        component: () => import("../views/welcome.vue")
    },
    {
        path: "/dashboard",
        component: () => import("../views/dashboard/index.vue"),
        children: [

        ]
    }
]

const router = createRouter({
    history: createWebHashHistory(),
    routes
})

export default router