# Persistencia con Java usando JPA y EclipseLink

Este proyecto implementa una arquitectura de persistencia basada en **Java Persistence API (JPA)** para manejar información de competidores y productos en una aplicación simulada de concursos. Utiliza **EclipseLink** como proveedor de persistencia (JPA) y **Apache Derby** como base de datos embebida, todo desarrollado en **NetBeans**.

El objetivo principal fue construir una aplicación capaz de realizar operaciones CRUD sobre entidades, aprovechando las capacidades de mapeo objeto-relacional que ofrece JPA.

---

## Tecnologías utilizadas

- **Java SE**
- **JPA (Java Persistence API)**
- **EclipseLink**
- **Apache Derby**
- **NetBeans 8.0.2**
- **JAX-RS (para servicios REST)**
- **Postman** (para pruebas de endpoints)

---

## Funcionalidades implementadas

### 💾 Persistencia de datos

- Configuración de una **unidad de persistencia** (`persistence.xml`) con generación automática de tablas (`drop-and-create`).
- Creación de entidades `Competitor` y `Producto`, con sus respectivos mapeos y relaciones.
- Manejo de relaciones `OneToMany` y `ManyToOne` entre competidores y productos.

### 🌐 Servicios REST

Se exponen varios servicios REST que permiten interactuar con los datos desde aplicaciones cliente. Algunos de los endpoints implementados son:

- **GET `/get`**  
  Retorna todos los competidores registrados, ordenados alfabéticamente por apellido.

- **POST `/add`**  
  Permite registrar un nuevo competidor con sus datos básicos.

- **POST `/login`**  
  Servicio de autenticación que valida correo y contraseña, retornando los datos del competidor si las credenciales son válidas.

> Todos los servicios se comunican con la base de datos a través de un `EntityManager`, manejado mediante un patrón singleton para optimizar las conexiones.

---

## Entidades

### 🧍 Competitor

Representa a un concursante con atributos como nombre, edad, teléfono, ciudad, etc. Además:

- Tiene una clave primaria autogenerada.
- Se agregaron campos de control de tiempo (`createdAt`, `updatedAt`) manejados automáticamente con anotaciones `@PrePersist` y `@PreUpdate`.
- Está relacionada con múltiples productos a través de una colección `Set<Producto>`.

### 📦 Producto

Representa un producto asociado a un competidor. Incluye campos como nombre, precio y descripción. Tiene una relación `@ManyToOne` con la entidad `Competitor`.

---

## Estructura del proyecto

```
src/
├── com.example.models        # Clases entidad (Competitor, Producto)
├── com.example.services      # Servicios REST
├── com.example.persistence   # Singleton de EntityManagerFactory
├── META-INF/
│   └── persistence.xml       # Configuración JPA
```

---

## Cómo ejecutar

1. Clonar el repositorio y abrir el proyecto en NetBeans 8.0.2.
2. Verificar que Derby esté activo.
3. Limpiar y construir el proyecto.
4. Ejecutar `Main.java` para iniciar el servidor.
5. Probar los endpoints con herramientas como Postman.

---

## Capturas de ejemplo

📌 Consultar todos los competidores:

```http
GET http://localhost:8080/competitor/get
```

📌 Agregar un nuevo competidor:

```http
POST http://localhost:8080/competitor/add
Content-Type: application/json

{
  "name": "Laura",
  "surname": "Gomez",
  "address": "laura.gomez@gmail.com",
  "age": 20,
  "telephone": "7659675",
  "cellphone": "3002345436",
  "city": "Bogota",
  "country": "Colombia",
  "password": "1234"
}
```
