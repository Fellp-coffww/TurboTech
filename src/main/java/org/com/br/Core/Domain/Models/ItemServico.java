package org.com.br.Core.Domain.Models;

public class ItemServico {

    private Long idItemServico;

    private Long idOrdemServico;

    private Long idServico;

    private String cpf;

    private int quantidade;

    private double valorUnitario;

    private double valorTotal;

    public ItemServico(String cpf, Long idOrdemServico, Long idServico, int quantidade, double valorTotal, double valorUnitario) {
        this.cpf = cpf;
        this.idOrdemServico = idOrdemServico;
        this.idServico = idServico;
        this.quantidade = quantidade;
        this.valorTotal = valorTotal;
        this.valorUnitario = valorUnitario;
    }

    public ItemServico(Long idItemServico, Long idOrdemServico, Long idServico, String cpf, int quantidade, double valorUnitario, double valorTotal) {
        this.cpf = cpf;
        this.idItemServico = idItemServico;
        this.idOrdemServico = idOrdemServico;
        this.idServico = idServico;
        this.quantidade = quantidade;
        this.valorTotal = valorTotal;
        this.valorUnitario = valorUnitario;
    }

    public Long getIdItemServico() {
        return idItemServico;
    }

    public void setIdItemServico(Long idItemServico) {
        this.idItemServico = idItemServico;
    }

    public Long getIdOrdemServico() {
        return idOrdemServico;
    }

    public void setIdOrdemServico(Long idOrdemServico) {
        this.idOrdemServico = idOrdemServico;
    }

    public Long getIdServico() {
        return idServico;
    }

    public void setIdServico(Long idServico) {
        this.idServico = idServico;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
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