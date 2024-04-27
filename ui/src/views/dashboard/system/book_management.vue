<script setup lang="ts">
import {Message, TableData} from "@arco-design/web-vue";
import {BookReq, Error} from "../../../data";
import useBook from "../../../hooks/book.ts";

type BookTableData = TableData & BookReq

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

async function addBook(record: BookTableData) {
  try {
    await book.addBook(record)
    Message.success("添加书籍成功")
  } catch (e: unknown) {
    Message.error("添加书籍失败：" + (e as Error).message)
  }
  await updateTableData()
}

async function updateBook(record: BookTableData) {
  try {
    await book.updateBook(record.key as unknown as number, record)
    Message.success("更新书籍成功")
  } catch (e: unknown) {
    Message.error("更新书籍失败：" + (e as Error).message)
  }
  await updateTableData()
}

async function deleteBook(record: BookTableData) {
  try {
    await book.deleteBook(record.key as unknown as number)
    Message.success("移除书籍成功")
  } catch (e: unknown) {
    Message.error("移除书籍失败：" + (e as Error).message)
  }
  await updateTableData()
}

async function addBookRecord() {
  if (data.value?.find(book => book.id == -1)) return
  data.value?.unshift({
    id: -1,
    isbn_number: 0,
    title: "",
    author: "",
    publisher: "",
    number_of_copies: 0
  })
}

updateTableData()
</script>

<template>
  <a-space direction="vertical">
    <a-button type="primary" @click="addBookRecord()">添加图书</a-button>
    <a-table :data="data" :scroll="{y: '100%'}">
      <template #columns>
        <a-table-column title="ID">
          <template #cell="{ record }">
            <span v-if="record.id != -1">{{ record.id }}</span>
            <span v-else>新建图书</span>
          </template>
        </a-table-column>
        <a-table-column title="书名">
          <template #cell="{ record }">
            <a-input v-model="record.title"/>
          </template>
        </a-table-column>
        <a-table-column title="作者">
          <template #cell="{ record }">
            <a-input v-model="record.author"/>
          </template>
        </a-table-column>
        <a-table-column title="出版社">
          <template #cell="{ record }">
            <a-input v-model="record.publisher"/>
          </template>
        </a-table-column>
        <a-table-column title="ISBN 号">
          <template #cell="{ record }">
            <a-input-number v-model="record.isbn_number" :min="1000000000000" :max="9999999999999"/>
          </template>
        </a-table-column>
        <a-table-column title="馆藏数">
          <template #cell="{ record }">
            <a-input-number v-model="record.number_of_copies" :min="0"/>
          </template>
        </a-table-column>
        <a-table-column title="可借馆藏数" data-index="number_of_available_copies"/>
        <a-table-column title="操作">
          <template #cell="{ record }">
            <a-space>
              <a-button type="primary" @click="updateBook(record)" v-if="record.id != -1">更新</a-button>
              <a-button type="primary" @click="addBook(record)" v-else>添加</a-button>
              <a-button type="primary" status="danger" @click="deleteBook(record)" v-if="record.id != -1">移除
              </a-button>
            </a-space>
          </template>
        </a-table-column>
      </template>
    </a-table>
  </a-space>
</template>

<style scoped>

</style>