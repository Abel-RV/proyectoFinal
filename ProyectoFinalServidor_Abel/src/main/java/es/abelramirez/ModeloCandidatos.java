package es.abelramirez;

import java.util.Arrays;
import java.util.List;

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


}
