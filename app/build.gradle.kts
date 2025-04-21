plugins {
    application
    jacoco
    id("java")
    id("checkstyle")
    id("io.freefair.lombok") version "8.13.1"
    id("org.sonarqube") version "6.0.1.5171"
}

group = "hexlet.code"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

application {
    mainClass = "hexlet.code.App"
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation ("info.picocli:picocli:4.7.6")
    implementation ("com.fasterxml.jackson.core:jackson-databind:2.15.2")
}

checkstyle {
    // Укажите путь к файлу конфигурации
    configFile = File(rootDir, "./config/checkstyle/checkstyle.xml")
}

sonar {
    properties {
        property ("sonar.projectKey", "maruseevvlad_java-project-71")
        property ("sonar.organization", "maruseevvlad")
        property ("sonar.host.url", "https://sonarcloud.io")
    }
}

tasks.test {
    useJUnitPlatform()
}

