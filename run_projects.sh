#!/bin/bash

docker build -t keycloakcloud .

docker-compose up -d

# Array con las rutas de los proyectos
projects="microservice-config microservice-eureka microservice-gateway microservice-client microservice-provider microservice-services"

# Iterar sobre cada proyecto y ejecutarlo en una nueva ventana de gnome-terminal
for project in $projects
do
  echo "Ejecutando $project en una nueva ventana..."
  
  gnome-terminal --window --title="$project" -- bash -c "
    cd \"$project/\" &&
    mvn spring-boot:run;
    exec bash"
  
  sleep 3
done

echo "Todos los proyectos se están ejecutando en diferentes ventanas."
