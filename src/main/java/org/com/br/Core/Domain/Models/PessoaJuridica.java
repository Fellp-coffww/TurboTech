package org.com.br.Core.Domain.Models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PessoaJuridica {

    private long idCliente;

    private String nome;

    private String cnpj;

    private String email;

    private String complemento;

    private String logradouro;

    private String numero;

    private int ddi;

    private int ddd;

    private int telefone;

    private String inscricaoEstadual;

    private String contato;

    private String razaoSocial;

}
