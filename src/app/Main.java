
package app;

import modelo.Libro;
import negocio.CatalogoABB;

public class Main {

    public static void main(String[] args) {

        CatalogoABB catalogo = new CatalogoABB();

        System.out.println("==================================");
        System.out.println("   CATALOGO BIBLIOTECA FISEI");
        System.out.println("==================================");

        // Registrar libros en el arbol binario
        System.out.println("\n1. REGISTRO DE LIBROS");

        catalogo.insertar(new Libro(50, "Java Basico", "Juan Perez", 2020));
        catalogo.insertar(new Libro(30, "Estructuras de Datos", "Ana Lopez", 2021));
        catalogo.insertar(new Libro(70, "Programacion", "Carlos Ruiz", 2022));
        catalogo.insertar(new Libro(20, "Algoritmos", "Maria Torres", 2019));
        catalogo.insertar(new Libro(40, "Bases de Datos", "Luis Gomez", 2023));

        System.out.println("Libros registrados correctamente.");

        // Contar libros
        System.out.println("\n2. TOTAL DE LIBROS");
        System.out.println("Cantidad: " + catalogo.contar());

        // Buscar un libro
        System.out.println("\n3. BUSQUEDA DE LIBROS");

        Libro encontrado = catalogo.buscar(30);

        if (encontrado != null) {
            System.out.println("Libro encontrado: " + encontrado);
        } else {
            System.out.println("Libro no encontrado.");
        }

        // Recorridos del arbol
        System.out.println("\n4. RECORRIDO PREORDEN");
        catalogo.preorden();

        System.out.println("\n5. RECORRIDO INORDEN");
        catalogo.inorden();

        System.out.println("\n6. RECORRIDO POSORDEN");
        catalogo.posorden();

        // Comprobar duplicados
        System.out.println("\n7. PRUEBA DE CODIGO DUPLICADO");

        boolean insertado = catalogo.insertar(
            new Libro(50, "Libro Repetido", "Autor Prueba", 2024)
        );

        System.out.println("Se inserto el duplicado: " + insertado);

        System.out.println("\n==================================");
        System.out.println("       FIN DEL PROGRAMA");
        System.out.println("==================================");
    }
}
