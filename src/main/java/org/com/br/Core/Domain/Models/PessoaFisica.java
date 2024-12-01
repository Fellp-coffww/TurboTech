package org.com.br.Core.Domain.Models;


public class PessoaFisica {

    private String cpf;

    private String nome;

    private String email;

    private String ddi1;

    private String ddd1;

    private String telefone1;

    private String ddi2;

    private String ddd2;

    private String telefone2;

    private String logradouro;

    private String complemento;

    private String numeroEnd;

    public PessoaFisica(String cpf, String nome, String email, String ddi1, String ddd1, String telefone1, String ddi2, String ddd2,
                        String telefone2, String logradouro, String complemento, String numeroEnd) {
        this.cpf = cpf;
        this.nome = nome;
        this.email = email;
        this.ddi1 = ddi1;
        this.ddd1 = ddd1;
        this.telefone1 = telefone1;
        this.ddi2 = ddi2;
        this.ddd2 = ddd2;
        this.telefone2 = telefone2;
        this.logradouro = logradouro;
        this.complemento = complemento;
        this.numeroEnd = numeroEnd;
    }

    public PessoaFisica() {
    }

    public String getDdi1() {
        return ddi1;
    }

    public void setDdi1(String ddi1) {
        this.ddi1 = ddi1;
    }

    public String getDdd1() {
        return ddd1;
    }

    public void setDdd1(String ddd1) {
        this.ddd1 = ddd1;
    }

    public String getTelefone1() {
        return telefone1;
    }

    public void setTelefone1(String telefone1) {
        this.telefone1 = telefone1;
    }

    public String getDdi2() {
        return ddi2;
    }

    public void setDdi2(String ddi2) {
        this.ddi2 = ddi2;
    }

    public String getDdd2() {
        return ddd2;
    }

    public void setDdd2(String ddd2) {
        this.ddd2 = ddd2;
    }

    public String getTelefone2() {
        return telefone2;
    }

    public void setTelefone2(String telefone2) {
        this.telefone2 = telefone2;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getNumeroEnd() {
        return numeroEnd;
    }

    public void setNumeroEnd(String numeroEnd) {
        this.numeroEnd = numeroEnd;
    }

    @Override
    public String toString() {
        return  cpf;
    }
}
