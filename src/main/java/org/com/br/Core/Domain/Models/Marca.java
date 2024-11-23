package org.com.br.Core.Domain.Models;

public class Marca {

    private Long idMarca;

    private String descricao;

    public Marca(String descricao) {
        this.descricao = descricao;
    }

    public Marca(Long idMarca, String descricao) {
        this.descricao = descricao;
        this.idMarca = idMarca;
    }

    public Long getIdMarca() {
        return idMarca;
    }

    public void setIdMarca(Long idMarca) {
        this.idMarca = idMarca;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

}
