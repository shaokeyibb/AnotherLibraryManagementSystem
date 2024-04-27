<script setup lang="ts">
import {useUsersStore} from "./stores/users.ts";

const router = useRouter()
const user = useUsersStore()

// 如果用户未登录，跳转回登录页
router.beforeEach(async (to) => {
  if (!to.path.startsWith("/dashboard")) return true
  try {
    await user.updateUser()
  } catch (_ignored: unknown) {
    return {name: "Welcome"}
  }
})

// 如果用户角色不正确，跳转回控制台页
router.beforeEach(async (to) => {
  if (to.path.startsWith("/dashboard")) return true
  if (to.meta.role != null && to.meta.role != user.data?.role as string | undefined) {
    return {name: "Dashboard"}
  }
})
</script>

<template>
  <router-view/>
</template>

<style scoped>

</style>
