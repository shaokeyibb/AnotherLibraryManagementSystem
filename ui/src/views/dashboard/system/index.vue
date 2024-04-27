<script setup lang="ts">

import Book_management from "./book_management.vue";
import User_management from "./user_management.vue";
import Meta_management from "./meta_management.vue";
</script>

<template>
  <a-tabs>
    <a-tab-pane key="1" title="用户管理">
      <user_management/>
    </a-tab-pane>
    <a-tab-pane key="2" title="书籍管理">
      <book_management/>
    </a-tab-pane>
    <a-tab-pane key="3" title="元数据管理">
      <meta_management/>
    </a-tab-pane>
  </a-tabs>
</template>

<style scoped>

</style>