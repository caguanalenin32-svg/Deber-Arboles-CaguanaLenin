# Catálogo de la Biblioteca FISEI

Proyecto individual de Estructura de Datos.

Estudiante: Lenin Caguana

## Descripción
Aplicación desarrollada en Java para representar la estructura de una biblioteca mediante un árbol general y organizar los libros de sus estantes mediante árboles binarios de búsqueda.

## Tecnologías
- Java 21
- Visual Studio Code
- Git y GitHub


## Estructura de la biblioteca

El sistema representa la Biblioteca FISEI mediante un árbol general compuesto por:

- 1 biblioteca principal.
- 2 áreas: Informática y Administración.
- 3 estantes: Programación, Bases de Datos y Marketing.

Cada estante utiliza un árbol binario de búsqueda (ABB) para organizar sus libros por código.

## Funcionalidades implementadas

- Registro de 13 libros.
- Búsqueda de libros por código.
- Conteo de libros y unidades.
- Recorridos preorden, inorden y posorden.
- Validación de códigos duplicados.
- Validación de la estructura de la biblioteca.

## Compilación y ejecución

Compilar desde la carpeta principal del proyecto:

```powershell
javac -encoding UTF-8 -d out src/enums/*.java src/modelo/*.java src/negocio/*.java src/app/Main.java
```

Ejecutar:

```powershell
java -cp out app.Main
```
