<script setup lang="ts">
import {Message, TableData} from "@arco-design/web-vue";
import {Configuration, configurationDescription, Error} from "../../../data";
import useConfiguration from "../../../hooks/configuration.ts";

type ConfigurationTableData = TableData & Configuration & {
  description: string
}

const configuration = useConfiguration()

const data = ref<ConfigurationTableData[]>()

async function updateTableData() {
  const configurations = await configuration.getAllConfigurations()
  data.value = Object.entries(configurations.data.value!).map(([key, value]) => {
    return {
      key,
      value,
      description: configurationDescription[key as keyof typeof configurationDescription]
    }
  }) ?? []
}

async function updateConfiguration(record: ConfigurationTableData) {
  try {
    await configuration.setConfiguration(record.key, record.value)
    Message.success("更新配置成功")
  } catch (e: unknown) {
    Message.error("更新配置失败：" + (e as Error).message)
  }
  await updateTableData()
}

updateTableData()
</script>

<template>
  <a-table :data="data" :scroll="{y: '100%'}">
    <template #columns>
      <a-table-column title="键" data-index="key"/>
      <a-table-column title="值">
        <template #cell="{ record }">
          <a-input v-model="record.value"/>
        </template>
      </a-table-column>
      <a-table-column title="描述" data-index="description"/>
      <a-table-column title="操作">
        <template #cell="{ record }">
          <a-space>
            <a-button type="primary" @click="updateConfiguration(record)">更新</a-button>
          </a-space>
        </template>
      </a-table-column>
    </template>
  </a-table>
</template>

<style scoped>

</style>