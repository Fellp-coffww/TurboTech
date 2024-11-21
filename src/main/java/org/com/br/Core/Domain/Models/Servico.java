package org.com.br.Core.Domain.Models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Servico {

    private Long IdServico;

    private String descricao;

    private Double valorUnitario;

}
