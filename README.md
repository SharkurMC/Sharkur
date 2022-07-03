<div align="center">
    <img src="https://avatars.githubusercontent.com/u/107707992?s=200&v=4" alt="Sharkur" />
</div>

## Sharkur
Sharkur is a fork of [Purpur](https://github.com/PurpurMC/Purpur) which adds more configuration and also some optimization.

## API

### [Javadoc](XX)

### Dependency Information
Maven
```xml
<repository>
    <id>sharkur</id>
    <url>https://reposilite.cezarsalat.tk/releases</url>
</repository>
```
```xml
<dependency>
    <groupId>org.sharkurmc.sharkur</groupId>
    <artifactId>sharkur-api</artifactId>
    <version>1.19-R0.1-SNAPSHOT</version>
    <scope>provided</scope>
</dependency>
```

Gradle
```kotlin
repositories {
    maven("https://reposilite.cezarsalat.tk/releases")
}
```
```kotlin
dependencies {
    compileOnly("org.sharkurmc.sharkur:sharkur-api:1.19-R0.1-SNAPSHOT")
}
```

Yes, this also includes all API provided by Purpur, Pufferfish, Paper, Spigot, and Bukkit.
