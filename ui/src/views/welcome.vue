<script setup lang="ts">
const FormType = {
  SIGN_IN: {
    name: "登录"
  },
  SIGN_UP: {
    name: "注册"
  }
}
const formModel = reactive({
  username: "",
  password: ""
});
const formRules = {
  username: [
    {
      required: true,
      message: '请填写用户名',
    },
  ],
  password: [
    {
      required: true,
      message: '请填写密码',
    },
  ],
}

const requesting = ref(false)

function handleFormSubmit(values: Record<string, any>, evt: SubmitEvent) {
  const submitType = evt.submitter?.dataset["submitType"]
}
</script>

<template>
  <main class="w-screen h-screen bg-cover bg-no-repeat bg-img">
    <div id="form"
         class="fixed flex flex-col justify-center items-center w-11/12 lg:w-1/3 lg:h-auto top-1/2 left-1/2 lg:left-auto lg:right-10 -translate-x-1/2 lg:translate-x-0 -translate-y-1/2 px-3 pt-5 bg-white bg-opacity-50 backdrop-blur-sm backdrop-contrast-125 rounded-xl shadow-2xl">
      <span class="text-xl mb-5">欢迎进入图书管理系统</span>
      <a-form :rules="formRules" :model="formModel" @submit-success="handleFormSubmit"
              :auto-label-width="true"
              :disabled="requesting">
        <a-form-item field="username" label="用户名">
          <a-input v-model="formModel.username" placeholder="请输入用户名..."/>
        </a-form-item>
        <a-form-item field="password" label="密码">
          <a-input v-model="formModel.password" placeholder="请输入密码..."/>
        </a-form-item>
        <a-form-item>
          <a-space>
            <a-button html-type="submit" data-submit-type="SIGN_IN">{{ FormType.SIGN_IN.name }}</a-button>
            <a-button html-type="submit" data-submit-type="SIGN_UP">{{ FormType.SIGN_UP.name }}</a-button>
          </a-space>
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