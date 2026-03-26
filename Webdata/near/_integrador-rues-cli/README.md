# GENERACION ALTERNATIVA 1

subor docker de aplicacion LectorCERL
cambiar puerto a 9080 al subir docker para que no entre en conflicto.

el stub lciente se genera con swagger-codegen:
cd ~/git/Webdata/near/_integrador-rues-cli

wget https://repo1.maven.org/maven2/io/swagger/codegen/v3/swagger-codegen-cli/3.0.20/swagger-codegen-cli-3.0.20.jar

### generacion completa

java -jar swagger-codegen-cli-3.0.20.jar generate -i http://localhost:9080/swagger.json -l java -o generated-dir/_complete

### generacion dtos

java -Dmodels -DsupportingFiles -jar swagger-codegen-cli-3.0.20.jar generate -i http://localhost:9080/swagger.json --lang java -o generated-dir/_dtos

NOTA: se peude correr un [docker](https://github.com/swagger-api/swagger-codegen)
java -jar swagger-codegen-cli-3.0.20.jar --help generate


** alternativa **
 https://www.site24x7.com/tools/json-to-java.html
 
# GENERACION ALTERNATIVA 2
* [src](https://github.com/joelittlejohn/jsonschema2pojo)
* [web](http://www.jsonschema2pojo.org/)

decirle source: json / copiar un ejemplo del json
target language: java
package: com.osmosyscol.datasuite.near.interop.lectorcerl.rest_cli.json
classname: DataOut
    