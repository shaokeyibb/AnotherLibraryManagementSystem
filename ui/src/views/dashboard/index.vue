<script setup lang="ts">
import {
  IconCaretRight,
  IconCaretLeft,
  IconUser,
  IconApps
} from '@arco-design/web-vue/es/icon';
import routed_breadcrumb from '../../components/routed_breadcrumb.vue';
import {useUsersStore} from "../../stores/users.ts";
import {useRouter} from "vue-router";

const route = useRoute()
const router = useRouter()
const user = useUsersStore()

async function signOut() {
  await user.signOut()
  await router.push({name: "Welcome"})
}

const menus = computed(() => router.getRoutes().find(r => r.name == "Dashboard")!.children)

const selectedMenuKey = computed(() => [route.name as string])

function onClickMenuItem(key: string) {
  router.push({name: key})
}

router.beforeEach(async (to) => {
  if (to.name == "Dashboard") {
    return {name: "Read"}
  }
})
</script>

<template>
  <a-layout :style="{ width: '100vw', height: '100vh' }">
    <a-layout-header>
      <a-page-header title="图书管理系统" :show-back="false">
        <template #extra>
          <a-trigger :auto-fit-position="true" :unmount-on-close="false">
            <a-avatar :style="{ backgroundColor: '#3370ff' }">
              <IconUser/>
            </a-avatar>
            <template #content>
              <div class="flex flex-col justify-center items-center w-max px-5 py-3 rounded shadow-lg"
                   style="background: var(--color-bg-popup)">
                <span class="mb-2">你好，{{ user.data?.username ?? "游客" }}</span>
                <a-button @click="signOut()">退出登录</a-button>
              </div>
            </template>
          </a-trigger>
        </template>
      </a-page-header>
    </a-layout-header>
    <a-layout>
      <a-layout-sider collapsible breakpoint="xl">
        <a-menu :style="{ width: '100%'}" :selected-keys="selectedMenuKey" @menu-item-click="onClickMenuItem">
          <a-menu-item v-for="menu in menus" :key="menu.name" v-show="menu.meta?.role == null || menu.meta.role == user.data?.role as string | undefined">
            {{ menu.meta?.displayName ?? menu.path }}
            <template #icon>
              <IconApps/>
            </template>
          </a-menu-item>
        </a-menu>
        <template #trigger="{ collapsed }">
          <IconCaretRight v-if="collapsed"></IconCaretRight>
          <IconCaretLeft v-else></IconCaretLeft>
        </template>
      </a-layout-sider>
      <a-layout-content>
        <div class="flex flex-col w-full h-full p-3 lg:px-10 lg:pb-10" style="background: var(--color-fill-2)">
          <routed_breadcrumb/>
          <main class="p-3 flex-1" style="background: var(--color-bg-1)">
            <router-view/>
          </main>
        </div>
      </a-layout-content>
    </a-layout>
  </a-layout>
</template>

<style scoped>

</style>