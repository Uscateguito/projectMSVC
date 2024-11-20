# Usar una imagen base de Node.js
FROM node:19

# Crear y moverse al directorio de trabajo
WORKDIR /app

# Copiar el package.json y package-lock.json para instalar las dependencias
COPY package*.json ./

# Instalar dependencias
RUN npm install

# Copiar el resto del código fuente
COPY . .

# Exponer el puerto que Next.js utiliza por defecto
EXPOSE 3000

# Comando para iniciar la aplicación
CMD ["npm", "run", "dev"]
