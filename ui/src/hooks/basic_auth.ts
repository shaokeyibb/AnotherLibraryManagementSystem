import {useFetch} from "./fetch.ts"
import {User} from "../data";

export default function useBasicAuth() {
    const fetch = useFetch()

    async function _signIn(username: string, password: string) {
        const req = await fetch("/authorization/sign-in", {
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({username, password})
        }).post().json<User>()
        if (req.error.value) throw req.error.value
        return req;
    }

    async function _signUp(username: string, password: string) {
        const req = await fetch("/authorization/sign-up", {
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({username, password})
        }).post().json<User>()
        if (req.error.value) throw req.error.value
        return req;
    }

    async function _signOut() {
        const req = await fetch("/authorization/sign-out", {
            headers: {
                "Content-Type": "application/json"
            }
        }).post();
        if (req.error.value) throw req.error.value
        return req;
    }

    async function changePassword(password: string) {
        const req = await fetch("/authorization/change-password", {
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({password})
        }).post().json()
        if (req.error.value) throw req.error.value
        return req;
    }

    async function forgetPassword(username: string, password: string, captcha: string) {
        const req = await fetch("/authorization/forget-password", {
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({username, password, captcha})
        }).post().text()
        if (req.error.value) throw req.error.value
        return req;
    }

    return {
        _signIn,
        _signUp,
        _signOut,
        changePassword,
        forgetPassword
    }
}