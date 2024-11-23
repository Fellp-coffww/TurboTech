package org.com.br.Core.Domain.Models;

public class PessoaJuridica {

    private long idCliente;

    private String nome;

    private String cnpj;

    private String email;

    private String complemento;

    private String logradouro;

    private String numero;

    private int ddi;

    private int ddd;

    private int telefone;

    private String inscricaoEstadual;

    private String contato;

    private String razaoSocial;

    public PessoaJuridica(String cnpj, String complemento, String contato, int ddd, int ddi, String email, String inscricaoEstadual, String logradouro, String nome, String numero, String razaoSocial, int telefone) {
        this.cnpj = cnpj;
        this.complemento = complemento;
        this.contato = contato;
        this.ddd = ddd;
        this.ddi = ddi;
        this.email = email;
        this.inscricaoEstadual = inscricaoEstadual;
        this.logradouro = logradouro;
        this.nome = nome;
        this.numero = numero;
        this.razaoSocial = razaoSocial;
        this.telefone = telefone;
    }

    public PessoaJuridica(Long idCliente, String nome, String cpf, String email, String complemento, String logradouro, String numero, int ddi, int ddd, int telefone, String inscricaoEstadual, String contato, String razaoSocial) {
        this.cnpj = cnpj;
        this.complemento = complemento;
        this.contato = contato;
        this.ddd = ddd;
        this.ddi = ddi;
        this.email = email;
        this.idCliente = idCliente;
        this.inscricaoEstadual = inscricaoEstadual;
        this.logradouro = logradouro;
        this.nome = nome;
        this.numero = numero;
        this.razaoSocial = razaoSocial;
        this.telefone = telefone;
    }

    public long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(long idCliente) {
        this.idCliente = idCliente;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
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

    public String getInscricaoEstadual() {
        return inscricaoEstadual;
    }

    public void setInscricaoEstadual(String inscricaoEstadual) {
        this.inscricaoEstadual = inscricaoEstadual;
    }

    public String getContato() {
        return contato;
    }

    public void setContato(String contato) {
        this.contato = contato;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }


}
