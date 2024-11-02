#!/bin/bash

# Iniciar el servicio MariaDB
service mariadb start

# Esperar a que MariaDB inicie completamente
sleep 10

# Configurar MariaDB
mariadb -e "CREATE DATABASE IF NOT EXISTS test;"
mariadb -e "CREATE USER IF NOT EXISTS 'user'@'%' IDENTIFIED BY 'password';"
mariadb -e "GRANT ALL PRIVILEGES ON test.* TO 'user'@'%';"
mariadb -e "FLUSH PRIVILEGES;"

# Ejecutar la aplicación Spring Boot
exec java -jar app.jar