package org.com.br.Core.Domain.Models;

import java.sql.Date;

public class OrdemServico {

    private long idOrdemServico;

    private Date data;

    private String StatusOS;

    private double precoTotal;

    private double precoPago;

    private String placa;

    public OrdemServico(Date data, String statusOS, double precoTotal, double precoPago, String placa) {
        this.data = data;
        StatusOS = statusOS;
        this.precoTotal = precoTotal;
        this.precoPago = precoPago;
        this.placa = placa;
    }

    public OrdemServico(long idOrdemServico, String statusOS , Date data, double precoTotal, double precoPago, String placa) {
        this.idOrdemServico = idOrdemServico;
        this.data = data;
        StatusOS = statusOS;
        this.precoTotal = precoTotal;
        this.precoPago = precoPago;
        this.placa = placa;
    }

    public OrdemServico() {

    }

    public void setIdOrdemServico(long idOrdemServico) {
        this.idOrdemServico = idOrdemServico;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public Long getIdOrdemServico() {
        return idOrdemServico;
    }

    public void setIdOrdemServico(Long idOrdemServico) {
        this.idOrdemServico = idOrdemServico;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
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


}
