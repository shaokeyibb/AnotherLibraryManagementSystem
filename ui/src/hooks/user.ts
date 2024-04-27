import {User, UserReq} from "../data";
import {useFetch} from "./fetch.ts";

export default function useUser() {
    const fetch = useFetch()

    async function _getCurrentUser() {
        const req = await fetch("/user", {}).get().json<User>()
        if (req.error.value) throw req.error.value
        await req.execute(true)
        return req;
    }

    async function getUser(username: string) {
        const req = await fetch(`/user/${username}`, {}).get().json<User>()
        if (req.error.value) throw req.error.value
        return req;
    }

    async function getAllUsers() {
        const req = await fetch("/user/all", {}).get().json<User[]>()
        if (req.error.value) throw req.error.value
        return req;
    }

    async function addUser(user: Omit<UserReq, 'id'>) {
        const req = await fetch(`/user`, {
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(user)
        }).post()
        if (req.error.value) throw req.error.value
        return req;
    }

    async function updateUser(userId: number, user: UserReq) {
        const req = await fetch(`/user/${userId}`, {
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(user)
        }).put()
        if (req.error.value) throw req.error.value
        return req;
    }

    async function deleteUser(bookId: number) {
        const req = await fetch(`/user/${bookId}`, {}).delete()
        if (req.error.value) throw req.error.value
        return req;
    }

    return {
        _getCurrentUser,
        getUser,
        getAllUsers,
        addUser,
        updateUser,
        deleteUser
    }
}