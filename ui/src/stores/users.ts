import {User} from "../data";

export const useUsersStore = defineStore('user', () => {
    const data = ref<User | null>()

    return {data}
})