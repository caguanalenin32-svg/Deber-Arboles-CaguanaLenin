
package app;

import enums.TipoUnidad;
import modelo.Libro;
import modelo.UnidadBiblioteca;
import negocio.ArbolBiblioteca;
import negocio.NodoUnidad;
import negocio.CatalogoABB;

public class Main {

    public static void main(String[] args) {

        System.out.println("========================================");
        System.out.println("       BIBLIOTECA FISEI - CATALOGO");
        System.out.println("========================================");

        // 1. Crear la biblioteca
        UnidadBiblioteca biblioteca = new UnidadBiblioteca(
            "B001", "Biblioteca FISEI", TipoUnidad.BIBLIOTECA
        );

        ArbolBiblioteca arbol = new ArbolBiblioteca(biblioteca);

        // 2. Crear areas
        arbol.agregarUnidad("B001", new UnidadBiblioteca(
            "A001", "Area de Informatica", TipoUnidad.AREA
        ));

        arbol.agregarUnidad("B001", new UnidadBiblioteca(
            "A002", "Area de Administracion", TipoUnidad.AREA
        ));

        // 3. Crear estantes
        arbol.agregarUnidad("A001", new UnidadBiblioteca(
            "E001", "Estante de Programacion", TipoUnidad.ESTANTE
        ));

        arbol.agregarUnidad("A001", new UnidadBiblioteca(
            "E002", "Estante de Bases de Datos", TipoUnidad.ESTANTE
        ));

        arbol.agregarUnidad("A002", new UnidadBiblioteca(
            "E003", "Estante de Marketing", TipoUnidad.ESTANTE
        ));

        // 4. Mostrar el arbol general
        System.out.println("\n1. ESTRUCTURA DE LA BIBLIOTECA");
        arbol.mostrarEstructura();

        System.out.println("\nTotal de unidades: "
            + arbol.contarUnidades());

        // 5. Obtener los catalogos de cada estante
        NodoUnidad estante1 = arbol.buscar("E001");
        NodoUnidad estante2 = arbol.buscar("E002");
        NodoUnidad estante3 = arbol.buscar("E003");

        CatalogoABB catalogo1 = estante1.getCatalogo();
        CatalogoABB catalogo2 = estante2.getCatalogo();
        CatalogoABB catalogo3 = estante3.getCatalogo();

        // 6. Insertar libros en sus respectivos estantes
        catalogo1.insertar(new Libro(
            50, "Java Basico", "Juan Perez", 2020
        ));
        catalogo1.insertar(new Libro(
            30, "Estructuras de Datos", "Ana Lopez", 2021
        ));
        catalogo1.insertar(new Libro(
            70, "Programacion Orientada a Objetos", "Carlos Ruiz", 2022
        ));
        catalogo1.insertar(new Libro(
            20, "Algoritmos", "Maria Torres", 2019
        ));
        catalogo1.insertar(new Libro(
            40, "Desarrollo de Software", "Luis Gomez", 2023
        ));

        catalogo2.insertar(new Libro(
            10, "Fundamentos de SQL", "Pedro Mena", 2020
        ));
        catalogo2.insertar(new Libro(
            15, "Bases de Datos Relacionales", "Sofia Castro", 2022
        ));
        catalogo2.insertar(new Libro(
            5, "Modelado de Datos", "Andres Vega", 2021
        ));
        catalogo2.insertar(new Libro(
            25, "Administracion de Bases de Datos", "Laura Diaz", 2024
        ));

        catalogo3.insertar(new Libro(
            60, "Marketing Digital", "Daniel Mora", 2023
        ));
        catalogo3.insertar(new Libro(
            45, "Comportamiento del Consumidor", "Elena Paz", 2022
        ));
        catalogo3.insertar(new Libro(
            80, "Investigacion de Mercados", "Roberto Leon", 2021
        ));
        catalogo3.insertar(new Libro(
            65, "Estrategias de Marketing", "Paula Rios", 2024
        ));

        // 7. Contar libros
        int totalLibros = catalogo1.contar()
                        + catalogo2.contar()
                        + catalogo3.contar();

        System.out.println("\n2. CANTIDAD DE LIBROS");
        System.out.println("Estante E001: " + catalogo1.contar());
        System.out.println("Estante E002: " + catalogo2.contar());
        System.out.println("Estante E003: " + catalogo3.contar());
        System.out.println("Total de libros: " + totalLibros);

        // 8. Buscar un libro
        System.out.println("\n3. BUSQUEDA DE LIBROS");
        Libro encontrado = catalogo1.buscar(30);

        if (encontrado != null) {
            System.out.println("Libro encontrado: " + encontrado);
        } else {
            System.out.println("Libro no encontrado");
        }

        // 9. Recorridos del ABB
        System.out.println("\n4. PREORDEN - ESTANTE E001");
        catalogo1.preorden();

        System.out.println("\n5. INORDEN - ESTANTE E001");
        catalogo1.inorden();

        System.out.println("\n6. POSORDEN - ESTANTE E001");
        catalogo1.posorden();

        // 10. Comprobar duplicados
        System.out.println("\n7. PRUEBA DE CODIGO DUPLICADO");

        boolean duplicado = catalogo1.insertar(new Libro(
            50, "Libro Repetido", "Autor Prueba", 2024
        ));

        System.out.println("Se inserto el duplicado: " + duplicado);

        // 11. Comprobar que no se pueden crear areas dentro de estantes
        System.out.println("\n8. PRUEBA DE ESTRUCTURA INVALIDA");

        boolean invalido = arbol.agregarUnidad(
            "E001",
            new UnidadBiblioteca(
                "A003", "Area Incorrecta", TipoUnidad.AREA
            )
        );

        System.out.println("Se agrego el area al estante: " + invalido);

        System.out.println("\n========================================");
        System.out.println("          FIN DEL PROGRAMA");
        System.out.println("========================================");
    }
}
