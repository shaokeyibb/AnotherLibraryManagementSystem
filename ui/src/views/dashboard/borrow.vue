<script setup lang="ts">
import {Book, BorrowRecord} from "../../data";
import {Message, TableData} from "@arco-design/web-vue";
import {useUsersStore} from "../../stores/users.ts";
import useBook from "../../hooks/book.ts";

const user = useUsersStore()

type BorrowRecordTableData = TableData & BorrowRecord & { book: Book }

const book = useBook()

const data = ref<BorrowRecordTableData[]>()

async function updateTableData() {
  await user.updateUser();
  data.value = await Promise.all(user.data?.borrow_records?.map(async record => {
    return {
      ...record,
      book: await getBookCached(record.book_id),
      key: record.id.toString()
    }
  }) ?? [])
}

const books: {
  [id: number]: Book
} = {}

async function getBookCached(id: number) {
  if (!books[id]) {
    books[id] = (await book.getBook(id)).data.value!
  }
  return books[id]
}

async function returnBook(record: BorrowRecordTableData) {
  await book.returnBook(record.book_id as unknown as number)
  Message.success("归还成功")
  await updateTableData()
}

updateTableData()
</script>

<template>
  <a-table :data="data" :scroll="{y: '100%'}">
    <template #columns>
      <a-table-column title="书名" data-index="book.title"/>
      <a-table-column title="ISBN 号" data-index="book.isbn_number"/>
      <a-table-column title="借阅时间" data-index="borrow_date"/>
      <a-table-column title="归还时间" data-index="return_date"/>
      <a-table-column title="操作">
        <template #cell="{ record }">
          <a-button type="primary"
                    :disabled="record.return_date"
                    @click="returnBook(record)">归还</a-button>
        </template>
      </a-table-column>
    </template>
  </a-table>
</template>

<style scoped>

</style>