
package negocio;

import java.util.ArrayList;
import java.util.List;

import enums.TipoUnidad;
import modelo.UnidadBiblioteca;

public class NodoUnidad {

    private UnidadBiblioteca dato;
    private List<NodoUnidad> hijos;
    private CatalogoABB catalogo;

    public NodoUnidad(UnidadBiblioteca dato) {
        if (dato == null) {
            throw new IllegalArgumentException("La unidad no puede ser nula");
        }

        this.dato = dato;
        this.hijos = new ArrayList<>();

        if (dato.getTipo() == TipoUnidad.ESTANTE) {
            this.catalogo = new CatalogoABB();
        } else {
            this.catalogo = null;
        }
    }

    public UnidadBiblioteca getDato() {
        return dato;
    }

    public List<NodoUnidad> getHijos() {
        return hijos;
    }

    public CatalogoABB getCatalogo() {
        return catalogo;
    }

    public boolean agregarHijo(NodoUnidad hijo) {
        if (hijo == null) {
            return false;
        }

        if (!dato.getTipo().puedeTenerHijo(hijo.getDato().getTipo())) {
            return false;
        }

        hijos.add(hijo);
        return true;
    }

    public boolean esHoja() {
        return hijos.isEmpty();
    }
}
