# App de Gestión Empresarial (Componentes XML)

## 📋 Descripción
Este proyecto implementa una solución modular para la gestión de tareas empresariales utilizando **persistencia en Base de Datos Nativa XML**.
El sistema demuestra el uso de componentes de software para separar la lógica de negocio del acceso a datos, cumpliendo con los principios de modularidad y escalabilidad.

## ⚙️ Arquitectura de Componentes
1.  **Modelo de Datos (`modelo.Tarea.java`, `modelo.BaseDatosXML.java`):** Define la estructura de la información utilizando anotaciones JAXB.
2.  **Conector de Datos (`datos.ComponenteGestorXML.java`):** Componente independiente encargado de:
    * **Marshalling:** Convertir objetos Java a formato XML (Escritura).
    * **Unmarshalling:** Convertir documentos XML a objetos Java (Lectura).
3.  **Interfaz de Usuario (`vista.AppGestionTareas.java`):** Capa visual que interactúa con el usuario y utiliza el conector para persistir la información.

## 🛠️ Requisitos Técnicos
* **Java JDK:** 17 o superior.
* **Maven:** Para la gestión de dependencias JAXB.
* **Dependencias:** `jakarta.xml.bind-api` y `jaxb-runtime`.

## 🚀 Guía de Ejecución
1.  Abre el proyecto en **IntelliJ IDEA**.
2.  Asegúrate de que Maven ha descargado las dependencias del `pom.xml`.
3.  Ejecuta la clase `vista.AppGestionTareas`.
4.  Inserta tareas y cierra la aplicación.
5.  Verifica que se ha creado un archivo llamado `bbdd_tareas.xml` en la raíz del proyecto con la estructura de datos guardada.

## 📄 Ejemplo de XML generado
```xml
<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<gestion_empresarial>
    <tarea>
        <estado>Pendiente</estado>
        <nombre>Revisar balance mensual</nombre>
        <prioridad>Alta</prioridad>
    </tarea>
</gestion_empresarial>
```

---
## 📦 Actividad 5: Empaquetado y Distribución (JAR)

### Objetivo
Generar un archivo **JAR (Java ARchive)** autoejecutable que contenga todos los binarios, recursos y metadatos necesarios para la entrega del software al cliente final.

### Detalles del Entregable
Se ha generado el archivo `AppGestionTareas.jar` mediante la configuración de artefactos en IntelliJ IDEA. Este archivo incluye:
1.  **Bytecode:** Todas las clases compiladas de los paquetes `modelo`, `datos` y `vista`.
2.  **Dependencias:** Librerías JAXB integradas (extracted) para funcionar sin instalaciones externas.
3.  **Metadatos (`MANIFEST.MF`):** Archivo de manifiesto configurado con versión, autor y clase principal (`Main-Class`).

### Instrucciones de Ejecución
Para iniciar la aplicación en cualquier entorno con Java instalado (JRE 17+), ejecutar en terminal:

```bash
java -jar AppGestionTareas.jar
```
