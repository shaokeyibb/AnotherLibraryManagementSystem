import {defineConfig} from 'vite'
import vue from '@vitejs/plugin-vue'
import AutoImport from 'unplugin-auto-import/vite'
import Components from 'unplugin-vue-components/vite'
import {vitePluginForArco} from '@arco-plugins/vite-vue'

// https://vitejs.dev/config/
export default defineConfig({
    plugins: [vue(), AutoImport({
        include: [
            /\.[tj]sx?$/, // .ts, .tsx, .js, .jsx
            /\.vue$/,
            /\.vue\?vue/, // .vue
            /\.md$/, // .md
        ],
        imports: [
            'vue',
            'vue-router',
            'pinia'
        ]
    }), Components({}), vitePluginForArco({
        style: "css"
    })],
})
