package main;

import model.Candidato;
import service.ProcessoSeletivo;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Candidato> candidatos = Arrays.asList(
            new Candidato("Ana", 25, 3),
            new Candidato("Bruno", 17, 1),
            new Candidato("Carlos", 30, 5)
        );

        ProcessoSeletivo processoSeletivo = new ProcessoSeletivo();
        processoSeletivo.validarCandidatos(candidatos);
    }
}

