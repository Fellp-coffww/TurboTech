package org.com.br.Core.Domain.Models;

public class Funcionario {

    private String cpf;

    private String nome;

    public Funcionario(String nome,String cpf) {
        this.cpf = cpf;
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return cpf;
    }
}
