package org.com.br.Propriedade;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PessoaFisica {

    private long idPropriedade;

    private date dataInicio;

    private date dataFim;

    private long idCliente;

    private String placa;

}