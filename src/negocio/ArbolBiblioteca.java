
package negocio;

import modelo.UnidadBiblioteca;

public class ArbolBiblioteca {

    private NodoUnidad raiz;

    public ArbolBiblioteca(UnidadBiblioteca biblioteca) {
        if (biblioteca == null) {
            throw new IllegalArgumentException(
                "La biblioteca no puede ser nula"
            );
        }

        this.raiz = new NodoUnidad(biblioteca);
    }

    public NodoUnidad getRaiz() {
        return raiz;
    }

    // Buscar una unidad mediante su codigo
    public NodoUnidad buscar(String codigo) {
        return buscarRecursivo(raiz, codigo);
    }

    private NodoUnidad buscarRecursivo(NodoUnidad actual, String codigo) {
        if (actual == null) {
            return null;
        }

        if (actual.getDato().getCodigo().equals(codigo)) {
            return actual;
        }

        for (NodoUnidad hijo : actual.getHijos()) {
            NodoUnidad encontrado = buscarRecursivo(hijo, codigo);

            if (encontrado != null) {
                return encontrado;
            }
        }

        return null;
    }

    // Agregar un area o estante a una unidad existente
    public boolean agregarUnidad(String codigoPadre, UnidadBiblioteca unidad) {
        if (unidad == null) {
            return false;
        }

        if (buscar(unidad.getCodigo()) != null) {
            return false;
        }

        NodoUnidad padre = buscar(codigoPadre);

        if (padre == null) {
            return false;
        }

        return padre.agregarHijo(new NodoUnidad(unidad));
    }

    // Mostrar la estructura de la biblioteca
    public void mostrarEstructura() {
        mostrarRecursivo(raiz, 0);
    }

    private void mostrarRecursivo(NodoUnidad actual, int nivel) {
        if (actual == null) {
            return;
        }

        for (int i = 0; i < nivel; i++) {
            System.out.print("    ");
        }

        System.out.println(actual.getDato());

        for (NodoUnidad hijo : actual.getHijos()) {
            mostrarRecursivo(hijo, nivel + 1);
        }
    }

    // Contar las unidades del arbol general
    public int contarUnidades() {
        return contarRecursivo(raiz);
    }

    private int contarRecursivo(NodoUnidad actual) {
        if (actual == null) {
            return 0;
        }

        int total = 1;

        for (NodoUnidad hijo : actual.getHijos()) {
            total += contarRecursivo(hijo);
        }

        return total;
    }
}
