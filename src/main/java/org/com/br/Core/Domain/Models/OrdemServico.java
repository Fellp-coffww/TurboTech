package org.com.br.Core.Domain.Models;

public class OrdemServico {

    private Long idOrdemServico;

    private String data;

    private String StatusOS;

    private double precoTotal;

    private double precoPago;

    private String placa;

    public OrdemServico(String StatusOS, String data, String placa, double precoPago, double precoTotal) {
        this.StatusOS = StatusOS;
        this.data = data;
        this.placa = placa;
        this.precoPago = precoPago;
        this.precoTotal = precoTotal;
    }

    public OrdemServico(Long idOrdemServico, String data, String StatusOS, double precoPago, double precoTotal, String placa) {
        this.StatusOS = StatusOS;
        this.data = data;
        this.idOrdemServico = idOrdemServico;
        this.placa = placa;
        this.precoPago = precoPago;
        this.precoTotal = precoTotal;
    }

    public Long getIdOrdemServico() {
        return idOrdemServico;
    }

    public void setIdOrdemServico(Long idOrdemServico) {
        this.idOrdemServico = idOrdemServico;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getStatusOS() {
        return StatusOS;
    }

    public void setStatusOS(String StatusOS) {
        this.StatusOS = StatusOS;
    }

    public double getPrecoTotal() {
        return precoTotal;
    }

    public void setPrecoTotal(double precoTotal) {
        this.precoTotal = precoTotal;
    }

    public double getPrecoPago() {
        return precoPago;
    }

    public void setPrecoPago(double precoPago) {
        this.precoPago = precoPago;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

}
