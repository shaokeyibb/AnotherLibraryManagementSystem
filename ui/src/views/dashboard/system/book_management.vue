<script setup lang="ts">
import {TableData} from "@arco-design/web-vue";
import {Book} from "../../../data";
import useBook from "../../../hooks/book.ts";

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
    </template>
  </a-table>
</template>

<style scoped>

</style>