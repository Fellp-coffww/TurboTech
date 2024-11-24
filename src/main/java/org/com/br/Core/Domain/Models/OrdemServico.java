package org.com.br.Core.Domain.Models;

import java.sql.Date;

public class OrdemServico {

    private long idOrdemServico;

    private Date data;

    private String StatusOS;

    private double precoTotal;

    private double precoPago;


    public OrdemServico(String StatusOS, Date data, double precoPago, double precoTotal) {
        this.StatusOS = StatusOS;
        this.data = data;
        this.precoPago = precoPago;
        this.precoTotal = precoTotal;
    }

    public OrdemServico(long idOrdemServico,String StatusOS, Date data,  double precoPago, double precoTotal) {
        this.idOrdemServico = idOrdemServico;
        this.StatusOS = StatusOS;
        this.data = data;
        this.precoPago = precoPago;
        this.precoTotal = precoTotal;
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
