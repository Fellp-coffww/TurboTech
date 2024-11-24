package org.com.br.Core.Domain.Models;

public class Veiculo {

    private String placa;

    private String chassi;

    private String Kilometragem;

    private long idModelo;

    private int ano;

    private int nPatrimonio;


    public Veiculo(String placa, String chassi, String kilometragem, Long idModelo, int ano, int nPatrimonio) {
        this.placa = placa;
        this.chassi = chassi;
        this.Kilometragem = kilometragem;
        this.idModelo = idModelo;
        this.ano = ano;
        this.nPatrimonio = nPatrimonio;
    }

    public Veiculo(String placa, String kilometragem, int ano, long idModelo) {
        this.placa = placa;
        Kilometragem = kilometragem;
        this.ano = ano;
        this.idModelo = idModelo;
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

    public void setKilometragem(String Kilometragem) {
        this.Kilometragem = Kilometragem;
    }

    public long getIdModelo() {
        return idModelo;
    }

    public void setIdModelo(long idModelo) {
        this.idModelo = idModelo;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public int getnPropriedade() {
        return nPatrimonio;
    }

    public void setnPatrimonio(int nPropriedade) {
        this.nPatrimonio = nPropriedade;
    }

    
    
}
