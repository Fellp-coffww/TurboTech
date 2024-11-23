package org.com.br.Core.Domain.Models;

public class Acessorios {

    private Long idAcessorios;

    private String descricao;

    private String placa;

    public Acessorios(String descricao, String placa) {
        this.descricao = descricao;
        this.placa = placa;
    }

    public Acessorios(Long idAcessorios, String descricao,String placa) {
        this.descricao = descricao;
        this.idAcessorios = idAcessorios;
        this.placa = placa;
    }

    public Long getIdAcessorios() {
        return idAcessorios;
    }

    public void setIdAcessorios(Long idAcessorios) {
        this.idAcessorios = idAcessorios;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

}
