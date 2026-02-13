package es.abelramirez;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ModeloCandidatos {

    private List<Candidatos> candidatos;

    public ModeloCandidatos(){
        Candidatos[] arrayCandidatos={
                new Candidatos(1,"Java"),
                new Candidatos(2,"Python"),
                new Candidatos(3,"C"),
                new Candidatos(4,"Kotlin"),
                new Candidatos(5,"C++"),
                new Candidatos(6,"Ensamblador"),
                new Candidatos(7,"JavaScript")
        };
        candidatos= Arrays.asList(arrayCandidatos);
    }

    public Candidatos getById(int id){
        return candidatos.stream()
                .filter(candidato -> candidato.getId() == id)
                .findFirst().orElse(null);
    }

    public List<Candidatos> getCandidatos(){
        return candidatos;
    }

    public synchronized boolean votar(String nombre){
        for(Candidatos c:candidatos){
            if(c.getNombre().equalsIgnoreCase(nombre)){
                c.setNumVotos(c.getNumVotos() + 1);
                return true;
            }
        }
        return false;
    }

    public List<String> obtenerResultados(){
        return candidatos.stream()
                .map(c->c.getNombre()+": "+c.getNumVotos())
                .collect(Collectors.toList());
    }

    public List<String> obtenerListasNombre(){
        return candidatos.stream().
                map(c->c.getId()+". "+c.getNombre())
                .collect(Collectors.toList());
    }



}
