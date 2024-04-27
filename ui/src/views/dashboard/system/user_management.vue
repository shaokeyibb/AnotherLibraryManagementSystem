<script setup lang="ts">
import {Error, Role, UserReq} from "../../../data";
import {Message, TableData} from "@arco-design/web-vue";
import useUser from "../../../hooks/user.ts";

type UserTableData = TableData & UserReq

const user = useUser()

const data = ref<UserTableData[]>()

async function updateTableData() {
  const books = await user.getAllUsers()
  data.value = books.data.value?.map(user => {
    return {
      ...user,
      key: user.id.toString()
    }
  }) ?? []
}

async function addUser(record: UserTableData) {
  try {
    await user.addUser(record)
    Message.success("添加用户成功")
  } catch (e: unknown) {
    Message.error("添加用户失败：" + (e as Error).message)
  }
  await updateTableData()
}

async function updateUser(record: UserTableData) {
  try {
    await user.updateUser(record.key as unknown as number, record)
    Message.success("更新用户成功")
  } catch (e: unknown) {
    Message.error("更新用户失败：" + (e as Error).message)
  }
  await updateTableData()
}

async function deleteUser(record: UserTableData) {
  try {
    await user.deleteUser(record.key as unknown as number)
    Message.success("移除用户成功")
  } catch (e: unknown) {
    Message.error("移除用户失败：" + (e as Error).message)
  }
  await updateTableData()
}

async function addUserRecord() {
  if (data.value?.find(user => user.id == -1)) return
  data.value?.unshift({
    id: -1,
    username: "",
    password: "",
    role: Role.USER
  })
}

updateTableData()
</script>

<template>
  <a-space direction="vertical">
    <a-button type="primary" @click="addUserRecord()">添加用户</a-button>
    <a-table :data="data" :scroll="{y: '100%'}">
      <template #columns>
        <a-table-column title="ID">
          <template #cell="{ record }">
            <span v-if="record.id != -1">{{ record.id }}</span>
            <span v-else>新建用户</span>
          </template>
        </a-table-column>
        <a-table-column title="用户名">
          <template #cell="{ record }">
            <a-input v-model="record.username"/>
          </template>
        </a-table-column>
        <a-table-column title="密码">
          <template #cell="{ record }">
            <a-input-password v-model="record.password"/>
          </template>
        </a-table-column>
        <a-table-column title="角色">
          <template #cell="{ record }">
            <a-select v-model="record.role" placeholder="请选择用户角色...">
              <a-option v-for="role in Role" :value="role" :label="role"/>
            </a-select>
          </template>
        </a-table-column>
        <a-table-column title="操作">
          <template #cell="{ record }">
            <a-space>
              <a-button type="primary" @click="updateUser(record)" v-if="record.id != -1">更新</a-button>
              <a-button type="primary" @click="addUser(record)" v-else>添加</a-button>
              <a-button type="primary" status="danger" @click="deleteUser(record)" v-if="record.id != -1">移除
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