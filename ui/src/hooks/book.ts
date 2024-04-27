import {useFetch} from "./fetch.ts"
import {Book} from "../data";

export default function useBook() {
    const fetch = useFetch()

    async function getBook(bookId: number) {
        const req = await fetch(`/book/${bookId}`, {}).get().json<Book>()
        if (req.error.value) throw req.error.value
        return req;
    }

    async function getBooks({page: pPage, size: pSize}: { page?: number, size?: number }) {
        const page = pPage ?? 0;
        const size = pSize ?? 0x7fffffff;
        const req = await fetch(`/book?page=${page}&size=${size}`, {}).get().json<Book[]>()
        if (req.error.value) throw req.error.value
        return req;
    }

    async function borrowBook(bookId: number) {
        const req = await fetch(`/book/${bookId}/action/borrow`, {}).post()
        if (req.error.value) throw req.error.value
        return req;
    }

    async function returnBook(bookId: number) {
        const req = await fetch(`/book/${bookId}/action/return`, {}).post()
        if (req.error.value) throw req.error.value
        return req;
    }

    return {
        getBook,
        getBooks,
        borrowBook,
        returnBook
    }
}