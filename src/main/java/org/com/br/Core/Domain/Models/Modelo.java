package org.com.br.Core.Domain.Models;

public class Modelo{

    private long idModelo;

    private String descricao;

    private long idMarca;
    
    public Modelo(long idModelo,String descricao, long idMarca) {
        this.descricao = descricao;
        this.idMarca = idMarca;
        this.idModelo = idModelo;
    }

    public Modelo(String descricao, long idMarca) {
        this.descricao = descricao;
        this.idMarca = idMarca;
    }


    public long getIdModelo() {
        return idModelo;
    }

    public void setIdModelo(long idModelo) {
        this.idModelo = idModelo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public long getIdMarca() {
        return idMarca;
    }

    public void setIdMarca(long idMarca) {
        this.idMarca = idMarca;
    }

}