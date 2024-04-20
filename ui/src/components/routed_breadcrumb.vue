<script setup lang="ts">
import {RouteRecordRaw} from "vue-router";

const route = useRoute()

const chain = computed(() => {
  const matched = route.matched
  const chain: RouteRecordRaw[] = []
  for (const record of matched) {
    chain.push(record)
  }
  return chain
});
</script>

<template>
  <a-breadcrumb class="m-4">
    <template v-for="route in chain">
      <a-breadcrumb-item v-if="route.meta?.displayName"> {{ route.meta.displayName }}</a-breadcrumb-item>
    </template>
  </a-breadcrumb>
</template>

<style scoped>

</style>