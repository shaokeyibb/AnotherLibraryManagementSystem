export type Book = {
    id: number,
    isbn_number: number,
    title: string,
    author: string,
    publisher: string,
    number_of_copies: number,
    number_of_available_copies: number
    borrow_records: BorrowRecord[]
}

export type BookReq =
    Omit<Book, keyof Pick<Book, 'author' | 'publisher' | 'borrow_records' | 'number_of_available_copies'>>
    & Partial<Pick<Book, 'author' | 'publisher'>>

export type User = {
    id: number,
    username: string,
    role: Role,
    borrow_records: BorrowRecord[]
}

export type UserReq = Omit<User, keyof Pick<User, 'borrow_records'>>

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