package es.abelramirez;

import java.io.Serializable;

public class Candidatos implements Serializable {
    private static final long serialVersionUID=1L;

    private int id;
    private String nombre;
    private int numVotos;

    public Candidatos(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
        this.numVotos=0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getNumVotos() {
        return numVotos;
    }

    public void setNumVotos(int numVotos) {
        this.numVotos = numVotos;
    }
}
