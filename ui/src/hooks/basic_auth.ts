import {useFetch} from "./fetch.ts"
import {User} from "../data";

export default function useBasicAuth() {
    const fetch = useFetch()

    async function _signIn(username: string, password: string) {
        const req = fetch("/authorization/sign-in", {
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({username, password})
        }, {immediate: false}).post().json<User>()
        await req.execute(true)
        return req;
    }

    async function _signUp(username: string, password: string) {
        const req = fetch("/authorization/sign-up", {
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({username, password})
        }, {immediate: false}).post().json<User>()
        await req.execute(true)
        return req;
    }

    async function _signOut() {
        const req = fetch("/authorization/sign-out", {
            headers: {
                "Content-Type": "application/json"
            }
        }, {immediate: false}).post();
        await req.execute(true)
        return req;
    }

    async function changePassword(password: string) {
        const req = fetch("/authorization/change-password", {
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({password})
        }, {immediate: false}).post().json()
        await req.execute(true)
        return req;
    }

    return {
        _signIn,
        _signUp,
        _signOut,
        changePassword
    }
}