package org.com.br.Core.Domain.Models;

public class Veiculo {

    private String placa;

    private String chassi;

    private String Kilometragem;

    private Modelo modelo;

    private Propriedade Propriedade;

    private int ano;

    private int nPropriedade;

    public Veiculo(String placa, String chassi, String kilometragem, Modelo modelo, Propriedade propriedade, int ano, int nPropriedade) {
        this.placa = placa;
        this.chassi = chassi;
        Kilometragem = kilometragem;
        this.modelo = modelo;
        Propriedade = propriedade;
        this.ano = ano;
        this.nPropriedade = nPropriedade;
    }

    public Veiculo() {
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getChassi() {
        return chassi;
    }

    public void setChassi(String chassi) {
        this.chassi = chassi;
    }

    public String getKilometragem() {
        return Kilometragem;
    }

    public void setKilometragem(String kilometragem) {
        Kilometragem = kilometragem;
    }

    public Modelo getModelo() {
        return modelo;
    }

    public void setModelo(Modelo modelo) {
        this.modelo = modelo;
    }

    public Propriedade getPropriedade() {
        return Propriedade;
    }

    public void setPropriedade(Propriedade propriedade) {
        Propriedade = propriedade;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public int getnPropriedade() {
        return nPropriedade;
    }

    public void setnPropriedade(int nPropriedade) {
        this.nPropriedade = nPropriedade;
    }
}
