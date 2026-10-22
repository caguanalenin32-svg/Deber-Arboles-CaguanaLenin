
package negocio;

import modelo.Libro;

public class CatalogoABB {

    private NodoLibro raiz;

    public CatalogoABB() {
        this.raiz = null;
    }

    public NodoLibro getRaiz() {
        return raiz;
    }

    public void setRaiz(NodoLibro raiz) {
        this.raiz = raiz;
    }

    public boolean insertar(Libro libro) {
        if (libro == null) {
            throw new IllegalArgumentException("El libro no puede ser nulo");
        }

        if (buscar(libro.getCodigo()) != null) {
            return false;
        }

        raiz = insertarRecursivo(raiz, libro);
        return true;
    }

    private NodoLibro insertarRecursivo(NodoLibro actual, Libro libro) {
        if (actual == null) {
            return new NodoLibro(libro);
        }

        if (libro.getCodigo() < actual.getDato().getCodigo()) {
            actual.setIzquierdo(
                insertarRecursivo(actual.getIzquierdo(), libro)
            );
        } else if (libro.getCodigo() > actual.getDato().getCodigo()) {
            actual.setDerecho(
                insertarRecursivo(actual.getDerecho(), libro)
            );
        }

        return actual;
    }

    public Libro buscar(int codigo) {
        return buscarRecursivo(raiz, codigo);
    }

    private Libro buscarRecursivo(NodoLibro actual, int codigo) {
        if (actual == null) {
            return null;
        }

        if (codigo == actual.getDato().getCodigo()) {
            return actual.getDato();
        }

        if (codigo < actual.getDato().getCodigo()) {
            return buscarRecursivo(actual.getIzquierdo(), codigo);
        }

        return buscarRecursivo(actual.getDerecho(), codigo);
    }

    public int contar() {
        return contarRecursivo(raiz);
    }

    private int contarRecursivo(NodoLibro actual) {
        if (actual == null) {
            return 0;
        }

        return 1
            + contarRecursivo(actual.getIzquierdo())
            + contarRecursivo(actual.getDerecho());
    }
}
