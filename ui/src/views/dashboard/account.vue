<script setup lang="ts">
import {DescData, Message} from "@arco-design/web-vue";
import {useUsersStore} from "../../stores/users.ts";
import useBasicAuth from "../../hooks/basic_auth.ts";

const basicAuth = useBasicAuth();
const userStore = useUsersStore()
const userData = userStore.data!

const data = computed<DescData[]>(() => [
  {
    label: '用户 ID',
    value: userData.id.toString(),
  },
  {
    label: '用户名',
    value: userData.username,
  },
  {
    label: '用户角色',
    value: userData.role,
  }
])

const passwordChangeRequesting = ref<boolean>(false);
const password = ref<string | undefined>();

async function changePassword() {
  if (!password.value){
    Message.info("请先输入需要修改的密码")
    return
  }
  try {
    passwordChangeRequesting.value = true;
    await basicAuth.changePassword(password.value)
    Message.success("密码修改成功")
  } catch (e: unknown) {
    Message.error("密码修改失败")
  } finally {
    passwordChangeRequesting.value = false;
  }
}
</script>

<template>
  <div class="flex flex-col gap-5">
    <a-descriptions :data="data" title="用户信息" size="large" layout="vertical" bordered/>
    <a-space>
      <template #split>
        <a-divider direction="vertical"/>
      </template>
      <h2>修改密码</h2>
      <a-space>
        <a-input-password
            v-model="password"
            placeholder="请输入密码"
            :defaultVisibility="false"
            allow-clear
        />
        <a-button type="primary" @click="changePassword" :disabled="passwordChangeRequesting">修改</a-button>
      </a-space>
    </a-space>
  </div>
</template>

<style scoped>

</style>