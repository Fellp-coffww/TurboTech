package org.com.br.Application.Desktop.Controller;

import org.com.br.Core.Domain.Models.PessoaFisica;
import org.com.br.Core.Domain.Models.PessoaJuridica;
import org.com.br.Infra.Services.PessoaFisicaService;
import org.com.br.Infra.Services.PessoaJuridicaService;

import javax.swing.*;

public class ClienteController {

    public ClienteController() throws Exception {
    }

    private PessoaJuridicaService pessoaJuridicaService = new PessoaJuridicaService();

    private PessoaFisicaService pessoaFisicaService = new PessoaFisicaService();

    public void criarPessoa(String tipo,
                            String nome,
                            String email,
                            String telefone,
                            String endereco,
                            String complemento,
                            String numero,
                            String cpfCnpj,
                            String inscricaoEstadual,
                            String contato,
                            String razaoSocial) throws Exception {
        if (tipo.equals("CPF")) {
            PessoaFisica pessoaFisica = new PessoaFisica();
            pessoaFisica.setCpf(cpfCnpj.trim().replaceAll("\\D", ""));
            pessoaFisica.setNome(nome.trim());
            pessoaFisica.setEmail(email.trim());

            // Extrai DDI e DDD do telefone
            pessoaFisica.setDdi1(telefone.substring(1, 3));  // DDI: "+55"
            pessoaFisica.setDdd1(telefone.substring(5, 7)); // DDD: "(XX)"
            pessoaFisica.setTelefone1(telefone.substring(9).replace("-", ""));

            pessoaFisica.setLogradouro(endereco.trim());
            pessoaFisica.setComplemento(complemento.trim());
            pessoaFisica.setNumeroEnd(numero.trim());

            pessoaFisicaService.createPessoaFisica(pessoaFisica);

        } else if (tipo.equals("CNPJ")) {
            PessoaJuridica pessoaJuridica = new PessoaJuridica();
            pessoaJuridica.setCnpj(cpfCnpj.trim());
            pessoaJuridica.setNome(nome.trim());
            pessoaJuridica.setEmail(email.trim());

            // Extrai DDI e DDD do telefone
            pessoaJuridica.setDdi1(telefone.substring(0, 3));  // DDI: "+55"
            pessoaJuridica.setDdd1(telefone.substring(5, 7)); // DDD: "(XX)"
            pessoaJuridica.setTelefone1(telefone.substring(9).replace("-", ""));

            pessoaJuridica.setLogradouro(endereco.trim());
            pessoaJuridica.setComplemento(complemento.trim());
            pessoaJuridica.setNumeroEnd(numero.trim());

            pessoaJuridica.setInscricaoEstadual(inscricaoEstadual != null ? inscricaoEstadual.trim() : "");
            pessoaJuridica.setContato(contato != null ? contato.trim() : "");
            pessoaJuridica.setRazaoSocial(razaoSocial != null ? razaoSocial.trim() : "");

            pessoaJuridicaService.createPessoaJuridica(pessoaJuridica);
        }
    }


}
