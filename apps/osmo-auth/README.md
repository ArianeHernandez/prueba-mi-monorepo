#OsmoAutenticador

## Requerimientos

- [AdoptOpenJDK 1.8.0_222-b10](https://adoptopenjdk.net/?variant=openjdk8&jvmVariant=hotspot) 
- Maven wrapper 3.6.3. No require instalar maven porque se usa maven wrapper que viene incluido en el proyecto

## Creación del WAR

Ejecutar el siguiente comando para construir el artefacto (clean elimina la carpeta target y package ejecuta las fases para compilar y crear el artefacto)

```
./mvnw clean package
```

En caso que se necesite forzar una actualización de los artefactos, usar la opción `-U`

```
./mvnw clean package -U
```

El recurso generado es: `target/OsmoAutenticador-1.0.0.war`

## Local

Ejecutar con docker

Run: 
```
docker-compose -f src/main/docker/docker-compose.yaml up
```

Stop:
```
docker-compose -f src/main/docker/docker-compose.yaml down -v
```

## Publicar artefacto

Ejecutar el siguiente comando, debe contar con un token válido (valor válido para la variable de entorno CODEARTIFACT_AUTH_TOKEN):

```
./mvnw -Dhttps.protocols=TLSv1.2 deploy
```

## Release

Se está usando el plugin [Maven Release](http://maven.apache.org/maven-release/maven-release-plugin/index.html), el cual genera una versión automáticamente.

A continuación los pasos a ejecutar:

**1.. Generar un token para publicar en aws CodeArtifact** (valor válido para la variable de entorno CODEARTIFACT_AUTH_TOKEN). Sólo válido por 12 horas

```
export CODEARTIFACT_AUTH_TOKEN=`aws codeartifact get-authorization-token --domain nuvu --domain-owner 624097007419 --query authorizationToken --output text`
```

**2.. Limpiar anteriores releases**

```
./mvnw release:clean
```

**3.. Preparar el release.** En este paso se verifica que no haya archivos pendientes por commit. Si este paso se ejecuta exitosamente, se creará un tag remoto con el nombre del nuevo tag.
La opción `--batch-mode` es para tomar los valores sugeridos por el plugin, que son la siguiente
versión para el tag y la nueva versión para el tag desarrollo.

```
./mvnw release:prepare --batch-mode
```

**4.. Completar el release.** En este paso se ejecuta la fase **deploy** la cual actualiza 
el artefacto en aws CodeArtifact con la nueva versión.

```
./mvnw release:perform --batch-mode -Darguments="-Dmaven.javadoc.skip=true"
```
