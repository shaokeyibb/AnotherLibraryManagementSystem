import {useFetch} from "./fetch.ts"
import {Book} from "../data";

export default function useBook() {
    async function getBook(isbnNumber: string) {
        return useFetch<Book>(`/book/${isbnNumber}`, {
            method: "GET",
        }).json();
    }

    async function getBooks({page: pPage, size: pSize}: { page?: number, size?: number }) {
        const page = pPage ?? 0;
        const size = pSize ?? 0x7fffffff;
        return useFetch<Book[]>(`/book?page=${page}&size=${size}`, {
            method: "GET",
        }).json();
    }

    return {
        getBook,
        getBooks
    }
}