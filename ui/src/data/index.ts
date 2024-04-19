export type Book = {
    isbn_number: number,
    title: string,
    author: string,
    publisher: string,
    number_of_copies: number
}

export type User = {
    id: number,
    username: string,
    role: Role
}

export enum Role {
    ADMIN = "ADMIN",
    USER = "USER",
    GUEST = "GUEST"
}