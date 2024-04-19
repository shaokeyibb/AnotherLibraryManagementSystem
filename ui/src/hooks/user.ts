import {User} from "../data";
import {useFetch} from "./fetch.ts";

export default function useUser() {
    const fetch = useFetch()

    async function _getCurrentUser() {
        const req = fetch("/user", {}, {immediate: false}).get().json<User>()
        await req.execute(true)
        return req;
    }

    async function getUser(userId: number) {
        const req = fetch(`/user/${userId}`, {}, {immediate: false}).get().json<User>()
        await req.execute(true)
        return req;
    }

    return {
        _getCurrentUser,
        getUser
    }
}