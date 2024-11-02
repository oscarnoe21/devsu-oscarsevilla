**Modulo de Clientes:**
- Este se encuentra en la carpeta onsutest

**Modulo de cuentas:**
- Se encuentra en la carpeta onsutest-cuentas-movimientos

**Compilacion**
- Debe tener instalado mariaDB en la PC donde se esta compilando la aplicacion (apunta a la base de datos local)
- ejecutar el script en de Base de datos *"BaseDatos.sql"* que esta en la carpeta de otros archivos.
- Ejecutar la carpeta el comando * "mvn install"* en ambos modulos

**Instalacion en Docker:**
1. En la carpeta docker-install  deberan estar complilados finales de ambas aplicaciones que deben ser copiados desde el target de ambos modulos despues de la compilacion (onsutest-clientes-1.0.0.jar y onsutest-movimientos-cuentas-1.0.0.jar), asi como el Docker File a Ejecutar, En el contenedor se instala MariaDB para uso de las aplicaciones ambas en un mismo contenedor.

Nota: En el Archivo comprimido del test ya estan ambos archivos compilados, en el repositorio git no ha permitido subirlos por el tamaño de los compilados.

2. Ejecutar con el comando: ***docker build -t devsu-osevilla .***

**Otros Archivos:**
- Se encuentra la coleccion de postman y el script de base de datos

**Enlace a GitHub:**
https://github.com/oscarnoe21/devsu-oscarsevilla

**Notas:**
- El Saldo de la cuenta se maneja en el campo saldo del ultimo movimiento de la cuenta
- Las validaciones de nuevo movimiento descritas en F3 se ejecutan el el Metodo POST de movimientos
