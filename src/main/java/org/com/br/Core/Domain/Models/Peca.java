package org.com.br.Core.Domain.Models;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Peca {

    private Long idPeca;

    private String descricao;

    private int quantidade;

    private double valorUnitario;

    private String codigo;

}
