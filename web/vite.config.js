import {defineConfig} from "vite"
import react from "@vitejs/plugin-react"

export default defineConfig({
    base: "/",
    build: {
        assetsDir: "assets",
        manifest: true,
    },
    plugins: [react()],
    server: {
        proxy: {
            '/api': {
                target: 'http://localhost:8081',
                changeOrigin: true
            }
        }
    },
    test: {
        environment: 'jsdom',
    },
})
