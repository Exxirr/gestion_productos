# 🛒 Sistema de Gestión de Productos

Este es un proyecto backend desarrollado con **Java + Spring Boot**, que permite gestionar productos y categorías. El sistema cuenta con endpoints RESTful, validaciones, manejo de errores global y arquitectura por capas.

---

## 🧑‍💻 Tecnologías usadas

- Java 17
- Spring Boot 3.x
- Spring Data JPA
- MySQL
- Lombok
- Maven
- DTOs y Builder Pattern
- Validaciones con `jakarta.validation`
- Manejo global de excepciones (`@RestControllerAdvice`)
- ResponseEntity personalizado

---

## 📦 Funcionalidades principales

### Productos:
- Crear producto
- Listar todos los productos
- Buscar producto por ID
- Actualizar producto
- Eliminar producto
- Buscar productos por nombre

### Categorías:
- Crear categoría
- Listar todas las categorías
- Buscar categoría por ID
- Actualizar categoría
- Eliminar categoría

---

## 🧪 Validaciones y errores

- Uso de anotaciones como `@NotBlank`, `@Size`, `@DecimalMin`, etc.
- Manejo global de excepciones:
  - `MethodArgumentNotValidException`
  - `ResourceNotFoundException`
  - `BadRequestException`
  - Otros errores generales

---

## 🧰 Cómo ejecutar el proyecto

### 🔧 Requisitos
- Java 17+
- Maven
- MySQL Server

### 🔌 Configuración de base de datos

Asegúrate de tener creada una base de datos `gestion_productos` en MySQL. Luego configura `application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/gestion_productos
spring.datasource.username=tu_usuario
spring.datasource.password=tu_contraseña
spring.jpa.hibernate.ddl-auto=update


🧪 Próximas mejoras
 Autenticación con Spring Security + JWT

 Pruebas unitarias con JUnit + Mockito

 Despliegue en la nube (Render o Railway)

📫 Contacto
Proyecto desarrollado por Exxirr
