package org.com.br.Core.Domain.Models;

public class Peca {

    private Long idPeca;

    private String descricao;

    private int quantidade;

    private double valorUnitario;

    private String codigo;

    public Peca(String descricao, int quantidade, double valorUnitario, String codigo) {
        this.codigo = codigo;
        this.descricao = descricao;
        this.quantidade = quantidade;
        this.valorUnitario = valorUnitario;
    }

    public Peca(Long idPeca, String descricao, int quantidade, double valorUnitario, String codigo) {
        this.codigo = codigo;
        this.descricao = descricao;
        this.idPeca = idPeca;
        this.quantidade = quantidade;
        this.valorUnitario = valorUnitario;
    }

    public Peca(String descricao, int quantidade, double valorUnitario, String codigo, Long idPeca) {
        this.codigo = codigo;
        this.descricao = descricao;
        this.idPeca = idPeca;
        this.quantidade = quantidade;
        this.valorUnitario = valorUnitario;
    }

    public Long getIdPeca() {
        return idPeca;
    }

    public void setIdPeca(Long idPeca) {
        this.idPeca = idPeca;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(double valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

}
