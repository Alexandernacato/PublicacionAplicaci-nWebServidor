# 🚀 Despliegue Completo con Docker Compose  
## Sistema Productos y Categorías  

---

### 📅 Autor: Alexander  
### 📍 Fecha: 2025-08-12  

---

## 📖 Índice

- [Descripción](#descripción)  
- [Script Bash para despliegue](#script-bash-para-despliegue)  
- [Explicación paso a paso](#explicación-paso-a-paso)  
- [Cómo ejecutar el script](#cómo-ejecutar-el-script)  
- [Notas importantes](#notas-importantes)  

---

## 📄 Descripción  

Este script automatiza la instalación de Docker, la creación del archivo `docker-compose.yml` con tus servicios (MySQL, backend categorías y productos, frontend Angular) y levanta los contenedores en modo detached.  
Ideal para máquinas Ubuntu (como una instancia EC2 en AWS).

---

## 🖥️ Script Bash para despliegue  

```bash
#!/bin/bash

################################################################################
# 🚀 Script Profesional para Desplegar Sistema Productos y Categorías con Docker
# Autor: Alexander
# Fecha: 2025-08-12
################################################################################

# Colores para resaltar mensajes
GREEN="\033[1;32m"
YELLOW="\033[1;33m"
CYAN="\033[1;36m"
RED="\033[1;31m"
NC="\033[0m" # Sin color

set -e  # Detener al primer error

echo -e "${CYAN}#############################################################${NC}"
echo -e "${CYAN}#      Iniciando despliegue Sistema Productos y Categorías   #${NC}"
echo -e "${CYAN}#############################################################${NC}"
echo

function print_step() {
    echo -e "${YELLOW}➡️  Paso $1: $2${NC}"
}

print_step 1 "Actualizar sistema operativo y paquetes..."
sudo apt update && sudo apt upgrade -y
echo -e "${GREEN}✔ Sistema actualizado correctamente.${NC}"
echo

print_step 2 "Instalar Docker y Docker Compose..."
sudo apt install -y docker.io docker-compose
echo -e "${GREEN}✔ Docker y Docker Compose instalados.${NC}"
echo

print_step 3 "Habilitar y arrancar Docker automáticamente..."
sudo systemctl enable docker --now
echo -e "${GREEN}✔ Docker activo y habilitado.${NC}"
echo

print_step 4 "Agregar usuario '$USER' al grupo docker para usar docker sin sudo..."
sudo usermod -aG docker $USER
echo -e "${GREEN}✔ Usuario agregado al grupo docker.${NC}"
echo -e "${RED}⚠️ Recuerda cerrar sesión SSH y volver a entrar para aplicar cambios.${NC}"
echo

print_step 5 "Crear archivo docker-compose.yml con configuración del proyecto..."

cat > docker-compose.yml << EOF
version: "3.8"

services:
  mysql:
    image: mysql:latest
    container_name: mysql
    restart: unless-stopped
    environment:
      MYSQL_ROOT_PASSWORD: admin123
      MYSQL_DATABASE: test
    ports:
      - "3306:3306"
    volumes:
      - mysql_data:/var/lib/mysql
    networks:
      - backend

  categoria:
    image: alexander553/categoria:latest
    container_name: categoria
    restart: unless-stopped
    ports:
      - "8084:8084"
    environment:
      PORT: 8084
      DB_HOST: mysql
      DB_PORT: 3306
      DB_DATABASE: test
      DB_USER: root
      DB_PASSWORD: admin123
    depends_on:
      - mysql
    networks:
      - backend

  producto:
    image: alexander553/producto:latest
    container_name: producto
    restart: unless-stopped
    ports:
      - "8082:8082"
    environment:
      PORT: 8082
      DB_HOST: mysql
      DB_PORT: 3306
      DB_DATABASE: test
      DB_USER: root
      DB_PASSWORD: admin123
    depends_on:
      - mysql
    networks:
      - backend

  catalogo-frontend:
    image: alexander553/catalogo1:latest
    container_name: catalogo-frontend
    restart: unless-stopped
    ports:
      - "8080:80"
    networks:
      - backend

volumes:
  mysql_data:

networks:
  backend:
    driver: bridge
EOF

echo -e "${GREEN}✔ Archivo docker-compose.yml creado exitosamente.${NC}"
echo

print_step 6 "Levantar contenedores con Docker Compose (modo detached)..."
docker-compose up -d
echo -e "${GREEN}✔ Contenedores levantados correctamente.${NC}"
echo

echo -e "${CYAN}##########################################${NC}"
echo -e "${CYAN}#           DESPLIEGUE FINALIZADO         #${NC}"
echo -e "${CYAN}##########################################${NC}"
echo
echo -e "✅ Recuerda:"
echo -e "  - Cierra sesión SSH y vuelve a entrar para aplicar permisos de grupo docker."
echo -e "  - Verifica contenedores activos con: ${YELLOW}docker ps${NC}"
echo -e "  - Abre el frontend en: ${YELLOW}http://<IP_PUBLICA_EC2>:8080${NC}"
echo
echo -e "${CYAN}¡Éxito con tu proyecto! 🚀${NC}"
echo
