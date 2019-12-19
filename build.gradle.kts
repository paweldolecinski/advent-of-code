plugins {
    java
}

group = "pl.dolecinski.leetcode"
version = "1.0-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

repositories {
    jcenter()
}

dependencies {
    implementation("org.junit.jupiter:junit-jupiter:5.5.2")
}

sourceSets.test {
    java.srcDir("src/main/java")
    resources.srcDir("src/main/resources")
}

tasks.test {
    useJUnitPlatform()
}
