package org.com.br.Core.Domain.Models;

public class Servico {

    private long IdServico;

    private String descricao;

    private Double valorUnitario;

    public Servico(String descricao, Double valorUnitario) {
        this.descricao = descricao;
        this.valorUnitario = valorUnitario;
    }

    public Servico(String descricao, Double valorUnitario, long IdServico) {
        this.IdServico = IdServico;
        this.descricao = descricao;
        this.valorUnitario = valorUnitario;
    }

    public Servico(long IdServico, String descricao, Double valorUnitario) {
        this.IdServico = IdServico;
        this.descricao = descricao;
        this.valorUnitario = valorUnitario;
    }

    public Long getIdServico() {
        return IdServico;
    }

    public void setIdServico(Long idServico) {
        IdServico = idServico;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(Double valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

}

