# Etapa 1: Build Angular
FROM node:20-alpine AS builder

WORKDIR /app

COPY package*.json ./
RUN npm install

COPY . .

# Construcción con configuración de producción (nombre del proyecto: Catalogo)
RUN npm run build -- --configuration production

# Etapa 2: Servir con NGINX
FROM nginx:alpine

# Copiar archivos compilados
COPY --from=builder /app/dist/Catalogo/browser /usr/share/nginx/html

# Configuración personalizada de NGINX para SPA Angular
COPY nginx.conf /etc/nginx/conf.d/default.conf

EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]
