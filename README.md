# Sistema Productos y Categorías con Docker Compose

**Autor:** Alexander  
**Fecha:** 2025-08-12

---

## 📋 Descripción del Proyecto

Este proyecto implementa un sistema modular para gestionar **Productos** y **Categorías**, usando una arquitectura basada en microservicios.  
Cada servicio (frontend, backend, base de datos) corre en contenedores Docker independientes y está orquestado con Docker Compose.

Tecnologías usadas:  
- Frontend: Angular  
- Backend: Spring Boot (Java 17)  
- Base de datos: MySQL  
- Orquestación: Docker y Docker Compose  

Este enfoque permite escalabilidad, despliegues independientes y un mantenimiento sencillo.

---

## 🛠️ Tecnologías

| Tecnología       | Versión / Detalle                |
|------------------|---------------------------------|
| Java             | 17                              |
| Spring Boot      | Última versión compatible        |
| MySQL            | Última imagen oficial Docker     |
| Angular          | 19                              |
| Docker           | Docker Engine + Docker Compose   |

---

## 📂 Estructura del Proyecto

```plaintext
.
├── backend/               # Servicios backend: producto y categoría
├── frontend/              # Aplicación frontend en Angular
├── docker-compose.yml     # Archivo para levantar todo el sistema con Docker Compose

# Despliegue del Sistema Productos y Categorías

Este documento explica cómo clonar el proyecto desde GitHub y desplegarlo en un servidor Ubuntu (por ejemplo, una instancia EC2 en AWS) usando Docker y Docker Compose.

---

## 1. Requisitos previos

- Servidor con Ubuntu 22.04 o superior.
- Acceso SSH al servidor.
- Tener instalado `git` (si no está instalado, se instalará en el script).
- Acceso a Internet para descargar imágenes Docker y el código fuente.

---

## 2. Pasos para clonar y desplegar el proyecto

# ----------------------------------------
# 1. Conectarse al servidor vía SSH (desde tu máquina local)
# ----------------------------------------
# Ejecuta este comando en tu terminal local (fuera del servidor)
# Reemplaza ruta/mi-clave.pem, usuario e IP_DEL_SERVIDOR por tus datos reales
ssh -i ruta/mi-clave.pem usuario@<IP_DEL_SERVIDOR>

# ----------------------------------------
# 2. Dentro del servidor: actualizar sistema y preparar entorno
# ----------------------------------------
sudo apt update && sudo apt upgrade -y

# Instalar Docker, Docker Compose y Git si no están instalados
sudo apt install -y docker.io docker-compose git

# Habilitar e iniciar el servicio Docker
sudo systemctl enable docker --now

# Agregar el usuario actual al grupo docker para no usar sudo en futuros comandos
sudo usermod -aG docker $USER

# ⚠️ IMPORTANTE: Cierra sesión SSH y vuelve a conectarte para aplicar permisos

# ----------------------------------------
# 3. Clonar el repositorio del proyecto
# ----------------------------------------
git clone https://github.com/alexander553/tu-repositorio.git

# Entrar a la carpeta del proyecto (ajusta el nombre según tu repo)
cd tu-repositorio

# ----------------------------------------
# 4. Levantar los contenedores Docker con docker-compose
# ----------------------------------------
docker-compose up -d

# ----------------------------------------
# 5. Verificar que los contenedores estén corriendo
# ----------------------------------------
docker ps

# ----------------------------------------
# 6. Acceder al frontend
# ----------------------------------------
# Abre tu navegador y visita:
# http://<IP_DEL_SERVIDOR>:8080
