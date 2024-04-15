import com.github.gradle.node.pnpm.task.PnpmTask

plugins {
    id("com.github.node-gradle.node") version "7.0.2"
}

tasks.register("clean", Delete::class) {
    delete(layout.buildDirectory)
    delete(layout.projectDirectory.dir("dist"))
}

tasks.register<PnpmTask>("build") {
    dependsOn(tasks.named("pnpmInstall"))
    args.set(listOf("run", "build"))
    inputs.files(fileTree(layout.projectDirectory) {
        include(
            "src/**",
            "public/**",
            "node_modules/**",
            "index.html",
            "package.json",
            "pnpm-lock.yaml",
            "tsconfig.json",
            "tsconfig.node.json",
            "vite.config.ts"
        )
    })
    outputs.dir(layout.buildDirectory.dir("dist"))
    configure<ExtraPropertiesExtension> {
        shouldRunAfter(tasks.named("clean"))
    }
}

tasks.register<PnpmTask>("dev") {
    dependsOn(tasks.named("pnpmInstall"))
    args.set(listOf("run", "dev"))
}

tasks.register<PnpmTask>("preview") {
    dependsOn(tasks.named("build"))
    args.set(listOf("run", "preview"))
}
