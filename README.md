"# PublicacionAplicaci-nWebServidor" 
#!/bin/bash

# 🚀 Script completo para desplegar el sistema Productos y Categorías con Docker Compose
#    En AWS EC2 Ubuntu 22.04 o cualquier Ubuntu compatible
#    Autor: Alexander
#    Fecha: 2025-08-12

set -e  # Detener script si hay error

echo "1️⃣ Actualizando sistema operativo..."
sudo apt update && sudo apt upgrade -y
echo "   Sistema actualizado."

echo "2️⃣ Instalando Docker y Docker Compose..."
sudo apt install -y docker.io docker-compose
echo "   Docker y Docker Compose instalados."

echo "3️⃣ Habilitando y arrancando Docker automáticamente..."
sudo systemctl enable docker --now
echo "   Docker activo y habilitado para inicio automático."

echo "4️⃣ Agregando usuario actual ($USER) al grupo docker para evitar usar sudo en comandos docker..."
sudo usermod -aG docker $USER
echo "   Usuario agregado al grupo docker."
echo "   ⚠️ IMPORTANTE: Cierra sesión SSH y vuelve a conectar para aplicar este cambio."

echo "5️⃣ Creando archivo docker-compose.yml con la configuración del proyecto..."
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
echo "   Archivo docker-compose.yml creado correctamente."

echo "6️⃣ Levantando los contenedores en modo desacoplado (detached)..."
docker-compose up -d
echo "   Contenedores levantados exitosamente."

echo "
✅ ¡Despliegue completado!

▶️ Recuerda:
  - Después de ejecutar este script, cierra tu sesión SSH y vuelve a entrar para que el grupo docker se aplique correctamente y puedas usar docker sin sudo.
  - Para verificar que los contenedores están corriendo, ejecuta: docker ps
  - Accede al frontend desde tu navegador en: http://<IP_PUBLICA_EC2>:8080
"

# --- Explicación rápida en comentarios para tu entendimiento ---

: '
1️⃣ Actualización del sistema:
   sudo apt update && sudo apt upgrade -y
   Esto actualiza la lista de paquetes y actualiza el sistema a versiones recientes.

2️⃣ Instalación Docker y Docker Compose:
   sudo apt install -y docker.io docker-compose
   Instala Docker (motor de contenedores) y Docker Compose (orquestador multi-contenedores).

3️⃣ Habilitar Docker al inicio y arrancar ahora:
   sudo systemctl enable docker --now
   Para que Docker se inicie siempre al prender la máquina.

4️⃣ Añadir usuario al grupo docker:
   sudo usermod -aG docker $USER
   Permite usar docker sin sudo (aplica después de reiniciar sesión).

5️⃣ Crear docker-compose.yml:
   Definimos servicios:
   - mysql (base de datos)
   - categoria (backend)
   - producto (backend)
   - catalogo-frontend (frontend)
   Creamos volumen para persistencia y red para comunicación.

6️⃣ Levantar contenedores:
   docker-compose up -d
   Ejecuta contenedores en segundo plano (no bloquea terminal).
'

