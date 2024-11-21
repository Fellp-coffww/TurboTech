package org.com.br.Servico;

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
