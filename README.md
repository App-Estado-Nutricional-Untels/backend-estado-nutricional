## Backend Estado Nutricional

- Java 8^
- SpringBoot
- Hibernate & JPA
- MySQL

### Para empezar

1. Clonar el archivo `example.application.properties` y renombrarlo por `application.properties`.

2. Establecer los parámetros necesarios en `application.properties`.

3. Correr la aplicación.

### Para correr la aplicación

1. Instalar [Maven Apache CGI](https://maven.apache.org/download.cgi).

2. Instalar el JDK 11 de Java.

3. Establecer las variables de entorno del sistema:

- `MAVEN_HOME` en Windows

```
MAVEN_HOME=C:\path\a\maven
```

- `Maven bin` en Windows

```
C:\path\a\maven\bin
```

4. Ejecutar una instancia de MySQL y crear la base de datos de acuerdo a los parámetros establecidos en el archivo `application.properties`

5. Luego, abrir un terminal en la raíz del proyecto y ejecutar

```
mvn spring-boot:run
```

6. Si existe algún problema, ejecutar:

```
clean install
```

O asegurarse de que la variable de entorno del sistem `JAVA_HOME` se encuentra establecida:

En Windows:

```
echo %JAVA_HOME%
```

En caso de no estarlo, puede establecerla

```
JAVA_HOME=C:\path\a\jdkjava
```

7. Si todo funcionó, siempre puede usar el script `init.bat` para inicializar el programa a partir del punto `5.`
