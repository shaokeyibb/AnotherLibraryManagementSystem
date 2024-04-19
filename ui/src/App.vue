<script setup lang="ts">
import {useUsersStore} from "./stores/users.ts";

const router = useRouter()
const user = useUsersStore()

// 如果用户未登录，跳转回登录页
router.beforeEach(async (to) => {
  if (to.path == "/") return true
  try {
    await user.updateUser()
  } catch (_ignored: unknown) {
    return {path: "/"}
  }
})
</script>

<template>
  <router-view/>
</template>

<style scoped>

</style>
