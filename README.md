# BackOffice SOLID

Proyecto backend para venta

## Requisitos

-  jdk 21 ([configurar variables de entorno PATH y JAVA_HOME](https://www.aprenderaprogramar.com/index.php?option=com_content&view=article&id=389:configurar-java-en-windows-variables-de-entorno-javahome-y-path-cu00610b&catid=68&Itemid=188))
-  Apache Maven 3.9.9 ([configurar variables de entorno](https://dev.to/vanessa_corredor/instalar-manualmente-maven-en-windows-10-50pb))
-  [mariadb:10.3](https://community.chocolatey.org/packages/mariadb/10.3.16)
-  [NSSM](https://community.chocolatey.org/packages/NSSM) (se puede instalar usando Chocolatey) `choco install nssm`

## Instalación Inicial

-  Asegurarse de tener instalado el JDK de [java](https://www.aprenderaprogramar.com/index.php?option=com_content&view=article&id=389:configurar-java-en-windows-variables-de-entorno-javahome-y-path-cu00610b&catid=68&Itemid=188) en su versión 21, sin olvidarse de configurar el PATH y el JAVA_HOME correctamente
-  Asegurarse de tener instalado [maven](https://dev.to/vanessa_corredor/instalar-manualmente-maven-en-windows-10-50pb) correctamente y configurar el PATH del sistema operativo para que se pueda ejecutar maven desde consola
-  Configurar el gestor de base de datos [mariadb](https://community.chocolatey.org/packages/mariadb/10.3.16), para este es necesario tomar en cuenta el puerto y host en que utiliza para configurarlo como parámetros en el pipeline bajo los parámetros `DB_HOST` y `DB_PORT`, el nombre de la base de datos es configurable bajo el nombre de parámetro `DB_DATA_BASE` aunque es opcional crear la base de datos, caso contrario se crea automáticamente, finalmente es importante configurar configurar correctamente el nombre de usuario y password para el gestor de base de datos, bajo el parámetro `DB_USERNAME` y `DB_PASSWORD`
-  Es necesario aclarar el puerto que utilizara el frontend `FRONTEND_PORT` y `FRONTEND_HOST` para configurar el CORS en Spring Boot, que limitara desde donde se puede consumir la API
-  Es necesario tener instalado [NSSM](https://community.chocolatey.org/packages/NSSM) (se puede instalar usando Chocolatey) que nos ayudará a exponer el archivo jar generado por al empaquetar el proyecto, para exponerlo como servicio de windows.
-  Finalmente es necesario definir en `JAR_LOCATION` a partir de qué ruta se trabajará para crear el servicio de windows con el que se expondrá el API

### Levantar servicio

La app deberá quedar instalada como servicio de windows `solid` y debe iniciarse por si solo, en caso no se inicie, intentar con

```bash
net start solid
```
