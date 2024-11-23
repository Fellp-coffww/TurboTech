package org.com.br.Core.Domain.Models;

public class Propriedade {

    private long idPropriedade;

    private int dataInicio;

    private int dataFim;

    private long idCliente;

    private String placa;

    public Propriedade(long idPropriedade, int dataFim, int dataInicio, long idCliente,  String placa) {
        this.dataFim = dataFim;
        this.dataInicio = dataInicio;
        this.idCliente = idCliente;
        this.idPropriedade = idPropriedade;
        this.placa = placa;
    }

    public Propriedade(int dataInicio, int dataFim, long idCliente, String placa) {
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.idCliente = idCliente;
        this.placa = placa;
    }

    public long getIdPropriedade() {
        return idPropriedade;
    }

    public void setIdPropriedade(long idPropriedade) {
        this.idPropriedade = idPropriedade;
    }

    public int getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(int dataInicio) {
        this.dataInicio = dataInicio;
    }

    public int getDataFim() {
        return dataFim;
    }

    public void setDataFim(int dataFim) {
        this.dataFim = dataFim;
    }

    public long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(long idCliente) {
        this.idCliente = idCliente;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }
}
