![Release](https://jitpack.io/v/WycliffeAssociates/jdenticon-kotlin.svg)
(https://jitpack.io/#WycliffeAssociates/jdenticon-kotlin)

# Jdenticon Kotlin

Kotlin library for generating highly recognizable identicons.

![Sample identicons](https://jdenticon.com/hosted/github-samples.png)

This is based on the [JavaScript Jdenticon library](https://github.com/dmester/jdenticon).

## Getting Started

### Setting Up the dependency

##### Maven
```groovy
<dependency>
	<groupId>org.wycliffeassociates</groupId>
	<artifactId>jdenticon-kotlin</artifactId>
	<version>1.x</version>
	<type>pom</type>
</dependency>
```

##### Gradle
```groovy
repositories {
    jcenter()
}

implementation 'org.wycliffeassociates:jdenticon-kotlin:1.x'
```

(Please replace `x` with the latest version numbers) `y` 

#### Generate an icon

* `Jdenticon.toSvg(hash: String, size: Int, padding: Float?)`

  Generates an SVG string containing an icon. Padding is optional (default is null).

---


## License

Jdenticon Kotlin is available under the MIT License. See the LICENSE file for more info.