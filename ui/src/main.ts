import {createApp} from 'vue'
import './style.css'
import App from './App.vue'
import Router from './plugins/router.ts'
import Pinia from "./plugins/pinia.ts";
import {Message, Notification} from '@arco-design/web-vue';

const app = createApp(App)

Message._context = app._context
Notification._context = app._context;
app.use(Pinia).use(Router).mount('#app')