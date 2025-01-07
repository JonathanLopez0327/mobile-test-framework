# Mobile Test Framework

Este proyecto es un marco de pruebas automatizadas para aplicaciones móviles, diseñado para facilitar la creación y ejecución de pruebas en dispositivos Android e iOS.

## Características

- **Appium**: Framework de automatización que permite pruebas en aplicaciones móviles nativas, híbridas y web. :contentReference[oaicite:0]{index=0}
- **TestNG**: Framework de pruebas utilizado para la gestión y ejecución de casos de prueba.
- **Maven**: Gestión de dependencias y construcción del proyecto.

## Estructura del Proyecto

- **src/**: Contiene el código fuente del proyecto.
    - **main/**: Código principal.
    - **test/**: Casos de prueba automatizados.
- **pom.xml**: Archivo de configuración de Maven que gestiona las dependencias del proyecto.
- **.gitignore**: Especifica los archivos y directorios que Git debe ignorar.

## Requisitos Previos

- **Java**: JDK 8 o superior.
- **Maven**: Para la gestión del proyecto y dependencias.
- **Appium**: Instalado y configurado en tu máquina.
- **Dispositivos o Emuladores**: Dispositivos Android/iOS físicos o emuladores configurados para las pruebas.

## Instalación

1. Clona este repositorio:

   ```bash
   git clone https://github.com/JonathanLopez0327/JavaSeleniumTestFramework.git

2. Navega al directorio del proyecto:

   ```bash
   cd JavaSeleniumTestFramework

3. Construye el proyecto utilizando Maven:

   ```bash
   mvn clean install
   

## Ejecución de Pruebas
1. Con Maven:
   ```bash
   mvn test
```
2. Con TestNG:
   ```bash
   mvn test -DsuiteXmlFile=TestSuite.xml
```
