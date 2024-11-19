package org.com.br.Cliente;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PessoaFisica {

    private long idCliente;

    private String nome;

    private String cpf;

    private String email;

    private String complemento;

    private String logradouro;

    private String numero;

    private int ddi;

    private int ddd;

    private int telefone;

}
