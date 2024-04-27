<script setup lang="ts">
import {Book, BorrowRecord} from "../../data";
import {Message, Modal, Notification, TableData} from "@arco-design/web-vue";
import {useUsersStore} from "../../stores/users.ts";
import useBook from "../../hooks/book.ts";
import useConfiguration from "../../hooks/configuration.ts";

const user = useUsersStore()
const configuration = useConfiguration();

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
  try {
    const overdue = new Date(record.borrow_date).getTime() + parseInt((await configuration.getConfiguration("max_book_borrow_days")).data.value!) * 24 * 60 * 60 * 1000
        - new Date().getTime()
    if (overdue < 0) {
      const money = (-overdue / (24 * 60 * 60 * 1000) * parseInt((await configuration.getConfiguration("overdue_fine_per_day")).data.value!)).toFixed(2);
      await new Promise((resolve, reject) => {
        Modal.info({
          title: '借阅已超期',
          content: `您需要缴纳超期费用 ￥${money}，是否确认？`,
          closable: false,
          okText: '好',
          cancelText: '取消',
          onOk(e) {
            Notification.info(`交易已完成，已扣除 ￥${money}`)
            resolve(e)
          },
          onCancel(e) {
            Notification.info("交易已取消，您没有被扣除任何费用")
            reject(e)
          },
        });
      })
    }
    await book.returnBook(record.book_id as unknown as number)
    Message.success("归还成功")
    await updateTableData()
  } catch (e: unknown) {
  }
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
                    @click="returnBook(record)">归还
          </a-button>
        </template>
      </a-table-column>
    </template>
  </a-table>
</template>

<style scoped>

</style>