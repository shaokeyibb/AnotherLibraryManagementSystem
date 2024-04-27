import {useFetch} from "./fetch.ts";

export default function useConfiguration() {
    const fetch = useFetch()

    async function getAllConfigurations() {
        const req = await fetch("/configuration/all").get().json<{
            [key: string]: string
        }>()
        if (req.error.value) throw req.error.value
        return req;
    }

    async function getConfiguration(key: string) {
        const req = await fetch(`/configuration/${key}`).get().json<string>()
        if (req.error.value) throw req.error.value
        return req;
    }

    async function setConfiguration(key: string, value: string) {
        const req = await fetch(`/configuration/${key}/${value}`, {}).put()
        if (req.error.value) throw req.error.value
        return req;
    }

    return {
        getAllConfigurations,
        getConfiguration,
        setConfiguration
    }
}