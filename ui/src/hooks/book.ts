import {useFetch} from "./fetch.ts"
import {Book} from "../data";

export default function useBook() {
    const fetch = useFetch()

    async function getBook(isbnNumber: string) {
        const req = fetch(`/book/${isbnNumber}`, {}, {immediate: false}).get().json<Book>()
        await req.execute(true)
        return req;
    }

    async function getBooks({page: pPage, size: pSize}: { page?: number, size?: number }) {
        const page = pPage ?? 0;
        const size = pSize ?? 0x7fffffff;
        const req = fetch(`/book?page=${page}&size=${size}`, {}, {immediate: false}).get().json<Book[]>()
        await req.execute(true)
        return req;
    }

    return {
        getBook,
        getBooks
    }
}