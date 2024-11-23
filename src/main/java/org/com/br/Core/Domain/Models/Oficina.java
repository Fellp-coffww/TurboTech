package org.com.br.Core.Domain.Models;

public class Oficina {

private Long idOficina;

private String nome;

private String email;

private String complemento;

private String logradouro;

private String numero;

private int ddi;

private int ddd;

private int telefone;

    public Oficina(String complemento, int ddd, int ddi, String email, String logradouro, String nome, String numero, int telefone) {
        this.complemento = complemento;
        this.ddd = ddd;
        this.ddi = ddi;
        this.email = email;
        this.logradouro = logradouro;
        this.nome = nome;
        this.numero = numero;
        this.telefone = telefone;
    }

    public Oficina(Long idOficina, String nome, String email, String complemento, String logradouro, String numero, int ddi, int ddd, int telefone) {
        this.complemento = complemento;
        this.ddd = ddd;
        this.ddi = ddi;
        this.email = email;
        this.idOficina = idOficina;
        this.logradouro = logradouro;
        this.nome = nome;
        this.numero = numero;
        this.telefone = telefone;
    }

    public Long getIdOficina() {
        return idOficina;
    }

    public void setIdOficina(Long idOficina) {
        this.idOficina = idOficina;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public int getDdi() {
        return ddi;
    }

    public void setDdi(int ddi) {
        this.ddi = ddi;
    }

    public int getDdd() {
        return ddd;
    }

    public void setDdd(int ddd) {
        this.ddd = ddd;
    }

    public int getTelefone() {
        return telefone;
    }

    public void setTelefone(int telefone) {
        this.telefone = telefone;
    }


}
