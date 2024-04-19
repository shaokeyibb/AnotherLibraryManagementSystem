import {createFetch} from "@vueuse/core";
import {useRouter} from "vue-router";
import { Message } from '@arco-design/web-vue';

const router = useRouter()

const theFetch = createFetch({
    baseUrl: "/api",
    options: {
        async onFetchError({error, data, response}) {
            // 用户未登录
            if (response && response.status == 401 && !new URL(response.url).pathname.startsWith("/api/authorization")) {
                Message.error("登录状态失效，请重新登录")
                await router.push({ path: "/" })
            }
            return {error, data}
        },
    }
})

export const useFetch = theFetch;