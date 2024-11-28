package org.com.br.Core.Domain.Models;

public class Marca {

    private Long idMarca;
    private String descricao;

    // Construtor para criar uma nova marca
    public Marca(String descricao) {
        this.descricao = descricao;
    }

    // Construtor para editar uma marca existente (com ID)
    public Marca(Long idMarca, String descricao) {
        this.idMarca = idMarca;
        this.descricao = descricao;
    }

    // Getters e Setters
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
