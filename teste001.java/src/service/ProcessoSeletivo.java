package service;

import java.util.List;
import model.Candidato;

public class ProcessoSeletivo {
    public void validarCandidatos(List<Candidato> candidatos) {
        for (Candidato candidato : candidatos) {
            try {
                validarCandidato(candidato);
            } catch (Exception e) {
                System.out.println("Erro ao validar candidato " + candidato.getNome() + ": " + e.getMessage());
            }
        }
    }

    private void validarCandidato(Candidato candidato) throws Exception {
        if (candidato.getIdade() < 18) {
            throw new Exception("Idade insuficiente.");
        }
        if (candidato.getAnosExperiencia() < 2) {
            throw new Exception("Experiência insuficiente.");
        }
        System.out.println("Candidato " + candidato.getNome() + " validado com sucesso.");
    }
}
