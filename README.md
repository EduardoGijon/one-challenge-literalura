# 📚 Literalura - Catálogo de Libros

![Java](https://img.shields.io/badge/Java-17-orange)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3+-green)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-Database-blue)

## 📝 Descripción

Literalura es una aplicación de consola desarrollada en **Java** con **Spring Boot** que permite buscar, guardar y gestionar libros consumiendo la API pública de [Gutendex](https://gutendex.com/).

El objetivo principal es crear una base de datos local de libros y autores, permitiendo realizar consultas complejas y filtrar información de manera persistente utilizando una base de datos relacional.

Este proyecto forma parte del desafío Back-End de **Oracle Next Education (ONE) + Alura Latam**.

## ⚙️ Funcionalidades

La aplicación ofrece un menú interactivo con las siguientes opciones:

1.  **🔍 Buscar libro por título:** Consume la API de Gutendex, busca el libro, y si existe, guarda el libro y su autor en la base de datos local (evitando duplicados).
2.  **📚 Listar libros registrados:** Muestra todos los libros almacenados en la base de datos con detalles como título, autor, idioma y número de descargas.
3.  **✍️ Listar autores registrados:** Muestra los autores guardados con sus fechas de nacimiento y fallecimiento.
4.  **📅 Autores vivos en un año determinado:** Permite ingresar un año y consulta en la base de datos qué autores estaban vivos en esa fecha.
5.  **🌍 Listar libros por idioma:** Filtra los libros guardados por idioma (ES, EN, FR, PT).

## 🛠️ Tecnologías Utilizadas

* **Java 17**: Lenguaje principal.
* **Spring Boot**: Framework para la creación de la aplicación.
* **Spring Data JPA**: Para la persistencia de datos y mapeo objeto-relacional (ORM).
* **PostgreSQL**: Base de datos relacional.
* **Jackson**: Para la deserialización de datos JSON de la API.
* **Maven**: Gestor de dependencias.

## 🚀 Cómo ejecutar el proyecto

### Prerrequisitos
* Java 17 instalado.
* PostgreSQL instalado y ejecutándose.
* Maven (opcional, el proyecto incluye el wrapper `mvnw`).

### Configuración de Variables de Entorno
El proyecto utiliza variables de entorno para la conexión a la base de datos por seguridad. Debes configurarlas en tu sistema o en tu IDE:

* `DB_HOST`: Host de tu base de datos (ej. `localhost`)
* `DB_USER`: Tu usuario de PostgreSQL (ej. `postgres`)
* `DB_PASSWORD`: Tu contraseña de PostgreSQL

*Alternativamente, puedes editar el archivo `src/main/resources/application.properties` y poner tus credenciales directamente (no recomendado si vas a subirlo a GitHub).*

### Pasos
1.  **Clonar el repositorio:**
    ```bash
    git clone <url-de-tu-repositorio>
    ```
2.  **Crear la base de datos:**
    Crea una base de datos vacía en PostgreSQL llamada `literalura_db`.
3.  **Ejecutar la aplicación:**
    Desde la terminal en la carpeta raíz del proyecto:
    ```bash
    ./mvnw spring-boot:run
    ```

## 🗃️ Estructura de la Base de Datos

La aplicación genera automáticamente las tablas gracias a JPA (`update`):

* **Tabla `libros`**: Almacena título, idioma, descargas y la relación con el autor.
* **Tabla `autores`**: Almacena nombre, año de nacimiento, año de fallecimiento y tiene una relación *Uno a Muchos* con libros.

## 👤 Autor

Desarrollado por **[Jose Eduardo Gijon Mora]**

---
