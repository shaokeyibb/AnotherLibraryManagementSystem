<script setup lang="ts">

import useBasicAuth from "../hooks/basic_auth.ts";
import {Message, Notification} from "@arco-design/web-vue";
import useUser from "../hooks/user.ts";

const router = useRouter()
const user = useUser()
const basicAuth = useBasicAuth()

enum Stage {
  USERNAME,
  CAPTCHA
}

const stage = ref<Stage>(Stage.USERNAME)

const formModel = reactive({
  username: "",
  password: "",
  captcha: Math.floor(Math.random() * 1000000).toString()
});
const formRules = {
  username: [
    {
      required: true,
      message: '请填写用户名',
    },
  ],
  captcha: [
    {
      required: true,
      message: '请填写验证码',
    },
  ]
}

const requesting = ref(false)

async function sendCaptcha() {
  if ((await user.getUser(formModel.username)).statusCode.value == 404) {
    Message.error("用户不存在")
    return
  }
  try {
    requesting.value = true
    Message.success("请输入我们向您电子邮箱中发送的六位验证码")
    stage.value = Stage.CAPTCHA
  } catch (e: unknown) {
    Message.error("验证码发送失败，请稍后再试")
  } finally {
    requesting.value = false
  }
}

async function handleFormSubmit(values: Record<string, any>) {
  try {
    requesting.value = true
    const password = (await basicAuth.forgetPassword(values.username, values.password, values.captcha)).data.value
    Notification.success({
      title: "找回密码成功",
      content: `您的新密码为：${password}`,
      closable: true,
      duration: -1
    })
    await router.push({name: "Welcome"})
  } catch (e: unknown) {
    Message.error("找回密码失败")
  } finally {
    requesting.value = false
  }
}
</script>

<template>
  <main class="w-screen h-screen bg-cover bg-no-repeat bg-img">
    <div id="form"
         class="fixed flex flex-col justify-center items-center w-11/12 lg:w-1/3 lg:h-auto top-1/2 left-1/2 lg:left-auto lg:right-10 -translate-x-1/2 lg:translate-x-0 -translate-y-1/2 px-3 pt-5 bg-white bg-opacity-50 backdrop-blur-sm backdrop-contrast-125 rounded-xl shadow-2xl">
      <span class="text-xl mb-5">找回密码</span>
      <a-form :rules="formRules" :model="formModel" @submit-success="handleFormSubmit"
              :auto-label-width="true"
              :disabled="requesting">
        <a-form-item field="username" label="用户名">
          <a-input v-model="formModel.username" placeholder="请输入用户名..."/>
        </a-form-item>
        <a-form-item field="captcha" label="验证码" v-if="stage == Stage.CAPTCHA">
          <a-verification-code v-model="formModel.captcha" style="width: 300px"/>
        </a-form-item>
        <a-form-item v-if="stage == Stage.USERNAME">
          <a-button type="primary" @click="sendCaptcha()">发送验证码</a-button>
        </a-form-item>
        <a-form-item v-if="stage == Stage.CAPTCHA">
          <a-button type="primary" html-type="submit">提交</a-button>
        </a-form-item>
      </a-form>
    </div>
  </main>
</template>

<style scoped>
main {
  background-image: url("../assets/background.jpg");
}
</style>