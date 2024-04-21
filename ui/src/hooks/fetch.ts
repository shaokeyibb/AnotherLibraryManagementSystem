import {createFetch} from "@vueuse/core";
import {useRouter} from "vue-router";
import {Message} from '@arco-design/web-vue';
import {Error} from "../data";

export function useFetch() {
    const router = useRouter()

    return createFetch({
        baseUrl: "/api",
        options: {
            async onFetchError({response}) {
                // 用户未登录
                if (response && response.status == 401 && !new URL(response.url).pathname.startsWith("/api/authorization")) {
                    Message.error("登录状态失效，请重新登录")
                    await router.push({name: "Welcome"})
                }
                // 设置返回 data 为 error
                return {
                    error: {
                        ...await response?.json(),
                        code: response?.status
                    } as Error, data: null
                }
            },
        }
    })
}