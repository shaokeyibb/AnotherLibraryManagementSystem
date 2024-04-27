export type Book = {
    id: number,
    isbn_number: number,
    title: string,
    author: string,
    publisher: string,
    number_of_copies: number,
    borrow_records: BorrowRecord[]
}

export type User = {
    id: number,
    username: string,
    role: Role,
    borrow_records: BorrowRecord[]
}

export type BorrowRecord = {
    id: number,
    user_id: number,
    book_id: number,
    borrow_date: string,
    return_date?: string
}

export enum Role {
    ADMIN = "ADMIN",
    USER = "USER",
    GUEST = "GUEST"
}

export type Error = {
    code: number,
    message: string
}