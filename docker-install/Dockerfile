FROM openjdk:17-jdk-slim AS build

# Establecer el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copiar los ejecutables de la aplicación
COPY . .

# Etapa 2: Ejecución
FROM openjdk:17-jdk-slim

# Establecer el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copiar el archivo JAR desde la etapa de construcción
COPY --from=build /app/onsutest-clientes-1.0.0.jar clientes.jar
COPY --from=build /app/onsutest-movimientos-cuentas-1.0.0.jar cuentas.jar

# Se instala MariaDB en el contenedor para uso de la aplicacion
RUN apt-get update && \
    apt-get install -y mariadb-server && \
    rm -rf /var/lib/apt/lists/*

# Copiar el script de entrada al contenedor que iniciara la base de datos y ambas aplicaciones
COPY entrypoint.sh /entrypoint.sh
RUN chmod +x /entrypoint.sh

# Exponer los puertos en los que ambos servicios se ejecutarán
EXPOSE 8081 8082

# Definir variables de entorno predeterminadas para acceso a la base de datos
ENV MYSQL_URL=jdbc:mysql://localhost:3306/test
ENV MYSQL_USER=user
ENV MYSQL_PASSWORD=password

# Configurar el script de entrada como el punto de entrada del contenedor
ENTRYPOINT ["/entrypoint.sh"]