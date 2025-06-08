# Java-project-71

### Hexlet tests and linter status:
[![Actions Status](https://github.com/maruseevvlad/java-project-71/actions/workflows/hexlet-check.yml/badge.svg)](https://github.com/maruseevvlad/java-project-71/actions)
[![SonarQube](https://github.com/maruseevvlad/java-project-71/actions/workflows/build.yml/badge.svg)](https://github.com/maruseevvlad/java-project-71/actions/workflows/build.yml)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=maruseevvlad_java-project-71&metric=coverage)](https://sonarcloud.io/summary/new_code?id=maruseevvlad_java-project-71)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=maruseevvlad_java-project-71&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=maruseevvlad_java-project-71)

## О проекте

Java-project-71 — это консольное приложение, предназначенное для вычисления и вывода различий между файлами форматов JSON, YAML и YML. Приложение поддерживает сравнение как плоских, так и вложенных структур данных. 

### Основные возможности:
- 🎨 **Форматы вывода различий**:
  - stylish (формат по умолчанию)
  - plain
  - json
  
- 📁 **Гибкость в указании путей к файлам**: 
  - Поддерживаются как абсолютные, так и относительные пути.

- ⚙️ **Парсинг файлов**: 
  - Для обработки данных используется библиотека [jackson-databind](https://github.com/FasterXML/jackson-databind).

### 💻 Интерфейс
Интерфейс приложения реализован с помощью библиотеки [picocli](https://picocli.info/), что обеспечивает удобное взаимодействие с пользователем.

## Требования
- Java JDK 17+
- Gradle 7.4+

### Скриншоты работы приложения

Результат работы метода Differ.generate():

![image](https://github.com/user-attachments/assets/5ff472df-274a-458d-a8f7-607bd6a84809)

Результат работы метода Differ.generate() с .yaml и .yml файлами:

![image](https://github.com/user-attachments/assets/e5bde44e-ab48-4d2a-8e10-17cd46ee5451)

Результат работы метода Differ.generate() с вложенными структурами JSON файлов:

<img width="400" alt="Screenshot_1" src="https://github.com/user-attachments/assets/e27d8e85-54af-4465-b3c1-e6621a39dca7" />

Результат работы приложения при сравнении файлов JSON и YAML. Вывод в консоль в формате plain:

![image](https://github.com/user-attachments/assets/e9c59bd6-8cb2-44ce-a6cb-8352d567fe3a)

Результат работы приложения при сравнении файлов JSON и YAML. Вывод в консоль в формате json:

![image](https://github.com/user-attachments/assets/cc3b3ad4-739c-4ebf-8f50-6c8a201d7f91)

## О курсе
Проект выполнен в рамках прохождения курса по обучению языку Java на платформе [Hexlet](https://hexlet.io/).
