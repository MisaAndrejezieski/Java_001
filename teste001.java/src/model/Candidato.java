package model;

public class Candidato {
    private String nome;
    private int idade;
    private int anosExperiencia;

    public Candidato(String nome, int idade, int anosExperiencia) {
        this.nome = nome;
        this.idade = idade;
        this.anosExperiencia = anosExperiencia;
    }

    public String getNome() {
        return nome;
    }

    public int getIdade() {
        return idade;
    }

    public int getAnosExperiencia() {
        return anosExperiencia;
    }
}
