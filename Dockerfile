# Usa la imagen base de Keycloak
FROM quay.io/keycloak/keycloak:25.0.5

# Establece las variables de entorno
ENV KEYCLOAK_ADMIN=admin
ENV KEYCLOAK_ADMIN_PASSWORD=admin

# Define un volumen para persistir datos
VOLUME ["/opt/keycloak/data"]

# Comando para iniciar Keycloak en modo de desarrollo
CMD ["start-dev"]