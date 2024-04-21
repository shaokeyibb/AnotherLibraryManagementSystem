<script setup lang="ts">
import {Book, BorrowRecord, Error} from "../../data";
import {Message, TableData} from "@arco-design/web-vue";
import useBook from "../../hooks/book.ts";
import {useUsersStore} from "../../stores/users.ts";

const user = useUsersStore()

type BookTableData = TableData & Book

const book = useBook()

const data = ref<BookTableData[]>()

async function updateTableData() {
  const books = await book.getBooks({})
  // @ts-ignore
  data.value = books.data.value?.map(book => {
    return {
      ...book,
      key: book.id
    }
  }) ?? []
}

async function borrowBook(record: BookTableData) {
  await book.borrowBook(record.key!)
  Message.success("借阅成功，别忘记按时归还噢")
  await updateTableData()
}

async function returnBook(record: BookTableData) {
  await book.returnBook(record.key!)
  Message.success("归还成功")
  await updateTableData()
}

updateTableData()
</script>

<template>
  <a-table :data="data" :scroll="{y: '100%'}">
    <template #columns>
      <a-table-column title="书名" data-index="title"/>
      <a-table-column title="作者" data-index="author"/>
      <a-table-column title="出版社" data-index="publisher"/>
      <a-table-column title="ISBN 号" data-index="isbn_number"/>
      <a-table-column title="馆藏数" data-index="number_of_copies"/>
      <a-table-column title="操作">
        <template #cell="{ record }">
         <a-space>
           <a-button type="primary"
                     :disabled="!record.number_of_copies || record.borrow_records.some((it:BorrowRecord) => it.user_id == user.data?.id)"
                     @click="borrowBook(record)">借阅
           </a-button>
           <a-button type="primary"
                     :disabled="!record.borrow_records.some((it:BorrowRecord) => it.user_id == user.data?.id)"
                     @click="returnBook(record)">归还
           </a-button>
         </a-space>
        </template>
      </a-table-column>
    </template>
  </a-table>
</template>

<style scoped>

</style>