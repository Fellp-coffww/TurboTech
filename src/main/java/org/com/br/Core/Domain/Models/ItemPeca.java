package org.com.br.Core.Domain.Models;

public class ItemPeca {

    private Long idItemPeca;

    private Long idOrdemServico;

    private Long idPeca;

    private int quantidade;

    private double valorUnitario;

    private double valorTotal;

    public ItemPeca(Long idOrdemServico, Long idPeca, int quantidade, double valorTotal, double valorUnitario) {
        this.idOrdemServico = idOrdemServico;
        this.idPeca = idPeca;
        this.quantidade = quantidade;
        this.valorTotal = valorTotal;
        this.valorUnitario = valorUnitario;
    }

    public ItemPeca(Long idItemPeca, Long idOrdemServico, Long idPeca, int quantidade, double valorTotal, double valorUnitario) {
        this.idItemPeca = idItemPeca;
        this.idOrdemServico = idOrdemServico;
        this.idPeca = idPeca;
        this.quantidade = quantidade;
        this.valorTotal = valorTotal;
        this.valorUnitario = valorUnitario;
    }

    public Long getIdItemPeca() {
        return idItemPeca;
    }

    public void setIdItemPeca(Long idItemPeca) {
        this.idItemPeca = idItemPeca;
    }

    public Long getIdOrdemServico() {
        return idOrdemServico;
    }

    public void setIdOrdemServico(Long idOrdemServico) {
        this.idOrdemServico = idOrdemServico;
    }

    public Long getIdPeca() {
        return idPeca;
    }

    public void setIdPeca(Long idPeca) {
        this.idPeca = idPeca;
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

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

}
