# ApiRestProjects

# Riwi-Project

## Descripción
Riwi-Project es una API Rest desarrollada en Java utilizando Spring Boot, Spring Security y JPA con MySQL. Esta API permite gestionar proyectos y las tareas asociadas a cada proyecto, con un enfoque en la autenticación y autorización mediante JWT, así como la notificación por correo electrónico.

## Objetivo
El objetivo de esta API es proporcionar una solución robusta para gestionar proyectos y tareas, permitiendo la creación de proyectos y sus tareas asociadas en un único endpoint. Se implementa un sistema de roles que permite a los administradores gestionar proyectos y tareas, mientras que los usuarios regulares solo pueden ver las tareas asignadas.

## Requisitos
1. **Arquitectura**: Utiliza MVC o Hexagonal Architecture.
2. **JPA y ORM**: Relación entre Proyecto y Tarea, auditoría de cambios.
3. **DTOs**: Para solicitudes y respuestas, con mapeo entre entidades.
4. **Manejo de errores**: Proporcionar respuestas claras para errores en peticiones y lógica de negocio.
5. **Swagger**: Documentación completa de todos los endpoints.
6. **Git y Git Flow**: Gestión adecuada de ramas y commits siguiendo convenciones.
7. **Autenticación y Autorización**: Roles y rutas protegidas con Spring Security y JWT.
8. **Correo electrónico**: Notificaciones por cambios importantes en los proyectos y tareas.
9. **Clean Code**: Aplicar principios de código limpio y uso de variables de entorno.

## Arquitectura
- Se ha utilizado una **Arquitectura MVC** para mantener la separación de responsabilidades en el código.

## Modelo ER
A continuación se presenta el diagrama entidad-relación de proyectos y tareas:

![Diagrama ER](/home/nar/Descargas/ApiProjectDiagramaER.png)

## API Documentada
La API está documentada utilizando **Swagger**. Puedes acceder a la documentación en el siguiente enlace:

[Documentación Swagger](http://localhost:8080/swagger-ui.html)

## Tabla de Tareas

| Tarea                                  | Descripción                                            | Estado          |
|----------------------------------------|-------------------------------------------------------|------------------|
| Crear modelo ER                        | Diagrama entidad-relación de proyectos y tareas.      | Completada       |
| Implementar endpoints de proyectos     | Endpoints para crear, leer, actualizar y eliminar.    | En Progreso      |
| Configurar Spring Security              | Implementación de autenticación y autorización.        | Completada       |
| Implementar notificaciones por correo   | Envío de correos electrónicos en cambios importantes.  | Pendiente        |
| Documentar API con Swagger             | Crear y mantener la documentación de la API.          | En Progreso      |
| Migración de MySQL a PostgreSQL        | Informe sobre la investigación de migración.          | Pendiente        |

## Instalación

Para ejecutar el proyecto, sigue estos pasos:

1. Clona el repositorio:
   ```bash
   git clone https://github.com/tu_usuario/Riwi-Project.git
   cd Riwi-Project

