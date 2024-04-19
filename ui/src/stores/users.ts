import {Error, User} from "../data";
import useBasicAuth from "../hooks/basic_auth.ts";
import {Message} from "@arco-design/web-vue";
import useUser from "../hooks/user.ts";

export const useUsersStore = defineStore('user', () => {
    const data = ref<User | null>()

    async function signIn(username: string, password: string) {
        try {
            data.value = (await useBasicAuth()._signIn(username, password)).data.value
        } catch (_e: any) {
            const e = _e as Error
            switch (e.message) {
                case "Unauthorized": {
                    Message.error("用户名或密码错误")
                    break
                }
                default: {
                    Message.error(`未知错误: ${e.message}`)
                    break
                }
            }
            throw e;
        }
    }

    async function signUp(username: string, password: string) {
        try {
            data.value = (await useBasicAuth()._signUp(username, password)).data.value
        } catch (_e: any) {
            const e = _e as Error
            switch (e.message) {
                case "Conflict": {
                    Message.error("用户名已存在，请更换一个")
                    break
                }
                default: {
                    Message.error(`未知错误: ${e.message}`)
                    break
                }
            }
            throw e;
        }
    }

    async function updateUser() {
        try {
            data.value = (await useUser()._getCurrentUser()).data.value
        } catch (e: unknown) {
            data.value = null
            throw e;
        }
    }

    async function signOut() {
        await useBasicAuth()._signOut()
        data.value = null
    }

    return {data, signIn, signUp, signOut, updateUser}
})