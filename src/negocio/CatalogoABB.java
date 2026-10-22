
package negocio;

import java.util.ArrayList;
import java.util.List;

import enums.TipoRecorrido;
import modelo.Libro;

public class CatalogoABB {

    private NodoLibro raiz;

    public CatalogoABB() {
        raiz = null;
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

    private NodoLibro insertarRecursivo(NodoLibro nodo, Libro libro) {
        if (nodo == null) {
            return new NodoLibro(libro);
        }

        if (libro.getCodigo() < nodo.getDato().getCodigo()) {
            nodo.setIzquierdo(insertarRecursivo(nodo.getIzquierdo(), libro));
        } else if (libro.getCodigo() > nodo.getDato().getCodigo()) {
            nodo.setDerecho(insertarRecursivo(nodo.getDerecho(), libro));
        }

        return nodo;
    }

    public Libro buscar(int codigo) {
        return buscarRecursivo(raiz, codigo);
    }

    private Libro buscarRecursivo(NodoLibro nodo, int codigo) {
        if (nodo == null) {
            return null;
        }

        int actual = nodo.getDato().getCodigo();

        if (codigo == actual) {
            return nodo.getDato();
        }

        if (codigo < actual) {
            return buscarRecursivo(nodo.getIzquierdo(), codigo);
        }

        return buscarRecursivo(nodo.getDerecho(), codigo);
    }

    public int contar() {
        return contarRecursivo(raiz);
    }

    private int contarRecursivo(NodoLibro nodo) {
        if (nodo == null) {
            return 0;
        }

        return 1 + contarRecursivo(nodo.getIzquierdo())
                 + contarRecursivo(nodo.getDerecho());
    }

    public List<Libro> recorrer(TipoRecorrido tipo) {
        if (tipo == null) {
            throw new IllegalArgumentException("El recorrido no puede ser nulo");
        }

        List<Libro> libros = new ArrayList<>();
        recorrerRecursivo(raiz, tipo, libros);
        return libros;
    }

    private void recorrerRecursivo(
            NodoLibro nodo, TipoRecorrido tipo, List<Libro> libros) {

        if (nodo == null) {
            return;
        }

        if (tipo == TipoRecorrido.PREORDEN) {
            libros.add(nodo.getDato());
        }

        recorrerRecursivo(nodo.getIzquierdo(), tipo, libros);

        if (tipo == TipoRecorrido.INORDEN) {
            libros.add(nodo.getDato());
        }

        recorrerRecursivo(nodo.getDerecho(), tipo, libros);

        if (tipo == TipoRecorrido.POSORDEN) {
            libros.add(nodo.getDato());
        }
    }

    public String trazaRecorrido(TipoRecorrido tipo) {
        if (tipo == null) {
            throw new IllegalArgumentException("El recorrido no puede ser nulo");
        }

        StringBuilder resultado = new StringBuilder();
        trazaRecursiva(raiz, tipo, 0, resultado);

        resultado.append("Secuencia ").append(tipo).append(":");

        for (Libro libro : recorrer(tipo)) {
            resultado.append(" ").append(libro.getCodigo());
        }

        return resultado.toString();
    }

    private void trazaRecursiva(
            NodoLibro nodo, TipoRecorrido tipo,
            int nivel, StringBuilder resultado) {

        String espacios = "  ".repeat(nivel);

        if (nodo == null) {
            resultado.append(espacios)
                     .append("null -> regresa\n");
            return;
        }

        int codigo = nodo.getDato().getCodigo();

        resultado.append(espacios)
                 .append("entra ").append(codigo).append("\n");

        if (tipo == TipoRecorrido.PREORDEN) {
            resultado.append(espacios)
                     .append("VISITA ").append(codigo).append("\n");
        }

        trazaRecursiva(nodo.getIzquierdo(), tipo, nivel + 1, resultado);

        if (tipo == TipoRecorrido.INORDEN) {
            resultado.append(espacios)
                     .append("VISITA ").append(codigo).append("\n");
        }

        trazaRecursiva(nodo.getDerecho(), tipo, nivel + 1, resultado);

        if (tipo == TipoRecorrido.POSORDEN) {
            resultado.append(espacios)
                     .append("VISITA ").append(codigo).append("\n");
        }
    }

    public boolean modificar(
            int codigo, String titulo, String autor, int anio) {

        Libro libro = buscar(codigo);

        if (libro == null) {
            return false;
        }

        // Validar todos los datos antes de modificar el libro
        new Libro(codigo, titulo, autor, anio);

        libro.setTitulo(titulo);
        libro.setAutor(autor);
        libro.setAnio(anio);

        return true;
    }

    public boolean eliminar(int codigo) {
        if (buscar(codigo) == null) {
            return false;
        }

        raiz = eliminarRecursivo(raiz, codigo);
        return true;
    }

    private NodoLibro eliminarRecursivo(NodoLibro nodo, int codigo) {
        if (nodo == null) {
            return null;
        }

        int actual = nodo.getDato().getCodigo();

        if (codigo < actual) {
            nodo.setIzquierdo(eliminarRecursivo(nodo.getIzquierdo(), codigo));
        } else if (codigo > actual) {
            nodo.setDerecho(eliminarRecursivo(nodo.getDerecho(), codigo));
        } else {

            // Caso 1: sin hijos
            if (nodo.getIzquierdo() == null &&
                nodo.getDerecho() == null) {
                return null;
            }

            // Caso 2: un solo hijo
            if (nodo.getIzquierdo() == null) {
                return nodo.getDerecho();
            }

            if (nodo.getDerecho() == null) {
                return nodo.getIzquierdo();
            }

            // Caso 3: dos hijos, usar sucesor inorden
            NodoLibro sucesor = minimo(nodo.getDerecho());
            nodo.setDato(sucesor.getDato());
            nodo.setDerecho(
                eliminarRecursivo(
                    nodo.getDerecho(),
                    sucesor.getDato().getCodigo()
                )
            );
        }

        return nodo;
    }

    private NodoLibro minimo(NodoLibro nodo) {
        if (nodo.getIzquierdo() == null) {
            return nodo;
        }

        return minimo(nodo.getIzquierdo());
    }

    public int contarEnRango(int a, int b) {
        if (a > b) {
            return 0;
        }

        return contarRangoRecursivo(raiz, a, b);
    }

    private int contarRangoRecursivo(NodoLibro nodo, int a, int b) {
        if (nodo == null) {
            return 0;
        }

        int codigo = nodo.getDato().getCodigo();

        if (codigo < a) {
            return contarRangoRecursivo(nodo.getDerecho(), a, b);
        }

        if (codigo > b) {
            return contarRangoRecursivo(nodo.getIzquierdo(), a, b);
        }

        return 1
            + contarRangoRecursivo(nodo.getIzquierdo(), a, b)
            + contarRangoRecursivo(nodo.getDerecho(), a, b);
    }

    public List<Integer> caminoBusqueda(int codigo) {
        List<Integer> camino = new ArrayList<>();
        caminoRecursivo(raiz, codigo, camino);
        return camino;
    }

    private void caminoRecursivo(
            NodoLibro nodo, int codigo, List<Integer> camino) {

        if (nodo == null) {
            return;
        }

        int actual = nodo.getDato().getCodigo();

        camino.add(actual);

        if (codigo == actual) {
            return;
        }

        if (codigo < actual) {
            caminoRecursivo(nodo.getIzquierdo(), codigo, camino);
        } else {
            caminoRecursivo(nodo.getDerecho(), codigo, camino);
        }
    }

    public boolean esABBValido() {
        return validarRecursivo(raiz, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private boolean validarRecursivo(
            NodoLibro nodo, long minimo, long maximo) {

        if (nodo == null) {
            return true;
        }

        long codigo = nodo.getDato().getCodigo();

        if (codigo <= minimo || codigo >= maximo) {
            return false;
        }

        return validarRecursivo(nodo.getIzquierdo(), minimo, codigo)
            && validarRecursivo(nodo.getDerecho(), codigo, maximo);
    }

    // Metodos de compatibilidad con el programa anterior
    public void preorden() {
        imprimirRecorrido(TipoRecorrido.PREORDEN);
    }

    public void inorden() {
        imprimirRecorrido(TipoRecorrido.INORDEN);
    }

    public void posorden() {
        imprimirRecorrido(TipoRecorrido.POSORDEN);
    }

    private void imprimirRecorrido(TipoRecorrido tipo) {
        for (Libro libro : recorrer(tipo)) {
            System.out.println(libro);
        }
    }
}
