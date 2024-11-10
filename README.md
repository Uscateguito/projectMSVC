# Introducción

Proyecto para microservicios y cloud.

# Ejecución

---
Es posible ejecutar todo el proyecto desde la carpeta raiz con el siguiente comando:

Para powershell
```ps1
Set-ExecutionPolicy RemoteSigned
./run_projects.ps1
```

Para unix
```sh
chmod +x run_projects.sh
sh run_projects.sh
```

---

El comando primero crea la imagen de docker, después ejecuta el docker compose y finalmente, inicia los proyectos a un orden que se adecúa a las necesidades.

# Proyecto

El proyecto es un pequeño microservicio con springboot, en el cual se utiliza la seguridad de keycloak y se implementan diferentes tecnologías para hacer peticiones sencillas en un programa de gestión de servicios.

Entre las tecnologías de las peticiones, graphQL y para la persistencia, mongoDB.

# Autores

Alejandro Uscátegui Torres, David Pérez Medina y Lukas Quintero