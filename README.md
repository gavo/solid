# BackOffice SOLID

Proyecto backend para venta

### Requisitos

-  jdk 21 ([configurar variables de entorno PATH y JAVA_HOME](https://www.aprenderaprogramar.com/index.php?option=com_content&view=article&id=389:configurar-java-en-windows-variables-de-entorno-javahome-y-path-cu00610b&catid=68&Itemid=188))
-  Apache Maven 3.9.9 ([configurar variables de entorno](https://dev.to/vanessa_corredor/instalar-manualmente-maven-en-windows-10-50pb))
-  [mariadb:10.3](https://community.chocolatey.org/packages/mariadb/10.3.16)
-  [NSSM](https://community.chocolatey.org/packages/NSSM) (se puede instalar usando Chocolatey) `choco install nssm`

### Levantar servicio

La app debera quedar instalada como servicio de windows `solid` y debe iniciarse por si solo, en caso no se inicie, intentar con

```bash
net start solid
```
