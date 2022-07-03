plugins {
    java
    `maven-publish`
    id("com.github.johnrengelman.shadow") version "7.1.2" apply false
    id("io.papermc.paperweight.patcher") version "1.3.8"
}

val paperMavenPublicUrl = "https://papermc.io/repo/repository/maven-public/"

allprojects {
    apply(plugin = "java")
    apply(plugin = "maven-publish")

    java {
        toolchain {
            languageVersion.set(JavaLanguageVersion.of(17))
        }
    }
}

subprojects {
    tasks.withType<JavaCompile>().configureEach {
        options.encoding = Charsets.UTF_8.name()
        options.release.set(17)
    }
    tasks.withType<Javadoc> {
        options.encoding = Charsets.UTF_8.name()
    }
    tasks.withType<ProcessResources> {
        filteringCharset = Charsets.UTF_8.name()
    }

    repositories {
        mavenCentral()
        maven(paperMavenPublicUrl)
        maven("https://jitpack.io")
    }
}

repositories {
    mavenCentral()
    maven(paperMavenPublicUrl) {
        content {
            onlyForConfigurations(configurations.paperclip.name)
        }
    }
}

dependencies {
    remapper("net.fabricmc:tiny-remapper:0.8.2:fat")
    decompiler("net.minecraftforge:forgeflower:1.5.605.7")
    paperclip("io.papermc:paperclip:3.0.2")
}

paperweight {
    serverProject.set(project(":sharkur-server"))

    remapRepo.set(paperMavenPublicUrl)
    decompileRepo.set(paperMavenPublicUrl)

    usePaperUpstream(providers.gradleProperty("purpurCommit")) {
        withPaperPatcher {
            apiPatchDir.set(layout.projectDirectory.dir("patches/api"))
            apiOutputDir.set(layout.projectDirectory.dir("Sharkur-API"))

            serverPatchDir.set(layout.projectDirectory.dir("patches/server"))
            serverOutputDir.set(layout.projectDirectory.dir("Sharkur-Server"))
        }
    }
}

tasks.generateDevelopmentBundle {
    apiCoordinates.set("org.sharkurmc.sharkur:sharkur-api")
    mojangApiCoordinates.set("io.papermc.paper:paper-mojangapi")
    libraryRepositories.set(
            listOf(
                    "https://repo.maven.apache.org/maven2/",
                    paperMavenPublicUrl,
                    "https://repo.purpurmc.org/snapshots",
                    "https://reposilite.cezarsalat.tk/snapshots"
            )
    )
}

allprojects {
    publishing {
        repositories {
            maven("https://reposilite.cezarsalat.tk/snapshots") {
                name = "sharkur"
                credentials(PasswordCredentials::class)
            }
        }
    }
}

publishing {
    publications.create<MavenPublication>("devBundle") {
        artifact(tasks.generateDevelopmentBundle) {
            artifactId = "dev-bundle"
        }
    }
}