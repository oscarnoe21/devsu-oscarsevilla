# Etapa 1: Construcción
FROM maven:3.8.4-openjdk-17 AS build

# Establecer el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copiar el archivo pom.xml y descargar las dependencias
COPY pom.xml .
RUN mvn dependency:go-offline

# Copiar el código fuente del proyecto
COPY src ./src

# Compilar el proyecto y construir el archivo JAR
RUN mvn clean package -DskipTests

# Etapa 2: Ejecución
FROM openjdk:17-jdk-slim

# Establecer el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copiar el archivo JAR desde la etapa de construcción
COPY --from=build /app/target/onsutest-1.0.0.jar app.jar

# Instalar MySQL
RUN apt-get update && \
    apt-get install -y mariadb-server && \
    rm -rf /var/lib/apt/lists/*

COPY entrypoint.sh /entrypoint.sh
RUN chmod +x /entrypoint.sh

# Exponer los puertos en los que la aplicación y MariaDB se ejecutarán
EXPOSE 8081 3306

# Definir variables de entorno predeterminadas
ENV MYSQL_URL=jdbc:mysql://localhost:3306/test
ENV MYSQL_USER=user
ENV MYSQL_PASSWORD=password

# Configurar el script de entrada como el punto de entrada del contenedor
ENTRYPOINT ["/entrypoint.sh"]