package org.com.br.Core.Domain.Models;

public class Veiculo {

    private String placa;

    private String chassi;

    private String Kilometragem;

    private long idModelo;

    private Long idPropriedade;

    private int ano;

    private int nPropriedade;


    public Veiculo(String placa, String chassi, String kilometragem, Long idModelo, Long idPropriedade, int ano, int nPropriedade) {
        this.placa = placa;
        this.chassi = chassi;
        this.Kilometragem = kilometragem;
        this.idModelo = idModelo;
        this.idPropriedade = idPropriedade;
        this.ano = ano;
        this.nPropriedade = nPropriedade;
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

    public Long getIdPropriedade() {
        return idPropriedade;
    }

    public void setIdPropriedade(Long idPropriedade) {
        this.idPropriedade = idPropriedade;
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
