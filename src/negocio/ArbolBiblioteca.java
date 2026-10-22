
package negocio;

import modelo.UnidadBiblioteca;
import enums.TipoUnidad;

public class ArbolBiblioteca {

    private NodoUnidad raiz;

    public ArbolBiblioteca(UnidadBiblioteca biblioteca) {
        if (biblioteca == null || biblioteca.getTipo() != TipoUnidad.BIBLIOTECA) {
            throw new IllegalArgumentException(
                "La raiz debe ser una biblioteca valida"
            );
        }

        this.raiz = new NodoUnidad(biblioteca);
    }

    public NodoUnidad getRaiz() {
        return raiz;
    }

    // Buscar una unidad sin distinguir mayusculas y minusculas
    public NodoUnidad buscar(String codigo) {
        if (codigo == null || codigo.trim().isEmpty()) {
            return null;
        }

        return buscarRecursivo(raiz, codigo.trim());
    }

    private NodoUnidad buscarRecursivo(NodoUnidad actual, String codigo) {
        if (actual == null) {
            return null;
        }

        if (actual.getDato().getCodigo().equalsIgnoreCase(codigo)) {
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

    // Agregar una unidad respetando la jerarquia
    public boolean agregar(String codigoPadre, UnidadBiblioteca nueva) {
        if (nueva == null || buscar(nueva.getCodigo()) != null) {
            return false;
        }

        NodoUnidad padre = buscar(codigoPadre);

        if (padre == null) {
            return false;
        }

        return padre.agregarHijo(new NodoUnidad(nueva));
    }

    // Compatibilidad con el programa que ya tenemos
    public boolean agregarUnidad(String codigoPadre, UnidadBiblioteca unidad) {
        return agregar(codigoPadre, unidad);
    }

    // Calcular altura en numero de aristas
    public int altura() {
        return alturaRecursiva(raiz);
    }

    private int alturaRecursiva(NodoUnidad actual) {
        if (actual == null) {
            return -1;
        }

        int mayorAltura = -1;

        for (NodoUnidad hijo : actual.getHijos()) {
            int alturaHijo = alturaRecursiva(hijo);

            if (alturaHijo > mayorAltura) {
                mayorAltura = alturaHijo;
            }
        }

        return mayorAltura + 1;
    }

    // Contar unidades que no tienen hijos
    public int contarHojas() {
        return contarHojasRecursivo(raiz);
    }

    private int contarHojasRecursivo(NodoUnidad actual) {
        if (actual == null) {
            return 0;
        }

        if (actual.esHoja()) {
            return 1;
        }

        int total = 0;

        for (NodoUnidad hijo : actual.getHijos()) {
            total += contarHojasRecursivo(hijo);
        }

        return total;
    }

    // Obtener nivel de una unidad
    public int nivel(String codigo) {
        if (codigo == null || codigo.trim().isEmpty()) {
            return -1;
        }

        return nivelRecursivo(raiz, codigo.trim(), 0);
    }

    private int nivelRecursivo(NodoUnidad actual, String codigo, int nivelActual) {
        if (actual == null) {
            return -1;
        }

        if (actual.getDato().getCodigo().equalsIgnoreCase(codigo)) {
            return nivelActual;
        }

        for (NodoUnidad hijo : actual.getHijos()) {
            int resultado = nivelRecursivo(hijo, codigo, nivelActual + 1);

            if (resultado != -1) {
                return resultado;
            }
        }

        return -1;
    }

    // Mostrar jerarquia en preorden con dos espacios por nivel
    public String mostrarJerarquia() {
        StringBuilder resultado = new StringBuilder();
        mostrarJerarquiaRecursivo(raiz, 0, resultado);
        return resultado.toString();
    }

    private void mostrarJerarquiaRecursivo(
            NodoUnidad actual, int nivel, StringBuilder resultado) {

        if (actual == null) {
            return;
        }

        for (int i = 0; i < nivel; i++) {
            resultado.append("  ");
        }

        resultado.append(actual.getDato()).append("\n");

        for (NodoUnidad hijo : actual.getHijos()) {
            mostrarJerarquiaRecursivo(hijo, nivel + 1, resultado);
        }
    }

    // Compatibilidad con el programa anterior
    public void mostrarEstructura() {
        System.out.print(mostrarJerarquia());
    }

    // Contar libros de todos los estantes
    public int contarLibrosTotal() {
        return contarLibrosRecursivo(raiz);
    }

    private int contarLibrosRecursivo(NodoUnidad actual) {
        if (actual == null) {
            return 0;
        }

        int total = 0;

        if (actual.getCatalogo() != null) {
            total += actual.getCatalogo().contar();
        }

        for (NodoUnidad hijo : actual.getHijos()) {
            total += contarLibrosRecursivo(hijo);
        }

        return total;
    }

    // Contar todas las unidades del arbol
    public int contarUnidades() {
        return contarUnidadesRecursivo(raiz);
    }

    private int contarUnidadesRecursivo(NodoUnidad actual) {
        if (actual == null) {
            return 0;
        }

        int total = 1;

        for (NodoUnidad hijo : actual.getHijos()) {
            total += contarUnidadesRecursivo(hijo);
        }

        return total;
    }
}
