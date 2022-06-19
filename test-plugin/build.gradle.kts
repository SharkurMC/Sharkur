version = "1.0.0-SNAPSHOT"

dependencies {
    compileOnly(project(":sharkur-api"))
}

tasks.processResources {
    val apiVersion = "1.19".split(".", "-").take(2).joinToString(".")
    val props = mapOf(
            "version" to project.version,
            "apiversion" to apiVersion,
    )
    inputs.properties(props)
    filesMatching("plugin.yml") {
        expand(props)
    }
}