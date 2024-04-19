import {useFetch} from "./fetch.ts"

export default function useBasicAuth() {
    async function signIn(username: string, password: string) {
        return useFetch("/authorization/sign-in", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({username, password})
        });
    }

    async function signUp(username: string, password: string) {
        return useFetch("/authorization/sign-up", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({username, password})
        });
    }

    async function signOut() {
        return useFetch("/authorization/sign-out", {
            method: "POST",
        });
    }

    async function changePassword(password: string) {
        return useFetch("/authorization/change-password", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({password})
        });
    }

    return {
        signIn,
        signUp,
        signOut,
        changePassword
    }
}