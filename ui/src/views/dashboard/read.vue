<script setup lang="ts">
import {Book, BorrowRecord} from "../../data";
import {Message, TableData} from "@arco-design/web-vue";
import useBook from "../../hooks/book.ts";
import {useUsersStore} from "../../stores/users.ts";

const router = useRouter()

const user = useUsersStore()

type BookTableData = TableData & Book

const book = useBook()

const data = ref<BookTableData[]>()

async function updateTableData() {
  const books = await book.getBooks({})
  data.value = books.data.value?.map(book => {
    return {
      ...book,
      key: book.id.toString()
    }
  }) ?? []
}

async function borrowBook(record: BookTableData) {
  await book.borrowBook(record.key as unknown as number)
  Message.success("借阅成功，别忘记按时归还噢")
  await updateTableData()
}

async function returnBook() {
  await router.push({name: "Borrow"})
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
      <a-table-column title="可借馆藏数" data-index="number_of_available_copies"/>
      <a-table-column title="操作">
        <template #cell="{ record }">
          <a-space>
            <a-button type="primary"
                      :disabled="!record.number_of_copies || record.borrow_records.some((it:BorrowRecord) => it.user_id == user.data?.id && !it.return_date)"
                      @click="borrowBook(record)">借阅
            </a-button>
            <a-button type="primary"
                      :disabled="!record.borrow_records.some((it:BorrowRecord) => it.user_id == user.data?.id && !it.return_date)"
                      @click="returnBook()">前往归还
            </a-button>
          </a-space>
        </template>
      </a-table-column>
    </template>
  </a-table>
</template>

<style scoped>

</style>