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
    implementation(libs.guava)
    testImplementation(platform(libs.junit.bom))
    testImplementation(libs.junit.jupiter)
    implementation(libs.picocli)
    implementation(libs.jackson.databind)
    implementation(libs.jackson.dataformat.yaml)
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
        property("sonar.verbose", "true") // Включить подробный вывод
        property("sonar.scanner.dumpToFile", "true") // Сохранить лог в файл
        property("sonar.login", System.getenv("SONAR_TOKEN"))
    }
}

tasks.test {
    useJUnitPlatform()
    finalizedBy(tasks.jacocoTestReport)
}

tasks.jacocoTestReport {
    dependsOn(tasks.test)

    reports {
        xml.required.set(true)
        csv.required.set(false)
        html.required.set(true)
    }
}

