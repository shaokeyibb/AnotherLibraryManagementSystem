<script setup lang="ts">
import {Book} from "../../data";
import {TableData} from "@arco-design/web-vue";
import useBook from "../../hooks/book.ts";

const book = useBook()

const data = ref<(TableData | Book)[]>()

book.getBooks({}).then(books => {
  data.value = books.data.value?.map(book => {
    return {
      ...book,
      key: book.id
    }
  })
});
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
          <a-button type="primary" :disabled="!record.number_of_copies">借阅</a-button>
        </template>
      </a-table-column>
    </template>
  </a-table>
</template>

<style scoped>

</style>