docker build -t keycloakcloud .

docker-compose up -d

# Array con las rutas de los proyectos
$projects = @("microservice-config", "microservice-eureka", "microservice-gateway", "microservice-client", "microservice-provider", "microservice-services")

# Iterar sobre cada proyecto y ejecutarlo
foreach ($project in $projects) {
    Write-Host "Ejecutando $project..."
    Set-Location $project
    # mvn clean install
    Start-Process "mvn" "spring-boot:run"
    Set-Location ..
    Start-Sleep -Seconds 2
}

Write-Host "Todos los proyectos se han ejecutado."
