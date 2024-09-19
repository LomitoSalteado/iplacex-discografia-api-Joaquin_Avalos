# STAGE 1: Compilación de la aplicación con Gradle
FROM gradle:jdk21 as builder

WORKDIR /app

# Copia los archivos de configuración de Gradle
COPY ./build.gradle .
COPY ./settings.gradle .

# Copia el código fuente del proyecto
COPY src ./src

# Ejecuta el proceso de construcción del proyecto
RUN gradle build --no-daemon

# STAGE 2: Imagen final con OpenJDK
FROM openjdk:21-jdk-slim

WORKDIR /app

# Copia el archivo JAR compilado desde el stage de construcción
COPY --from=builder /app/build/libs/*.jar discografia-1.jar

# Expone el puerto 8080
EXPOSE 8080

# Comando para ejecutar la aplicación
CMD [ "java", "-jar", "discografia-1.jar" ]
