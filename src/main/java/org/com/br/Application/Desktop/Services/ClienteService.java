package org.com.br.Application.Desktop.Services;

import org.com.br.Core.Domain.Exceptions.InvalidCPFException;
import org.com.br.Core.Domain.Exceptions.InvalidCPNJException;
import org.com.br.Core.Domain.Exceptions.InvalidTelefoneException;
import org.com.br.Core.Domain.Models.PessoaFisica;
import org.com.br.Core.Domain.Models.PessoaJuridica;
import org.com.br.Core.Domain.Rules.ValidaCNPJ;
import org.com.br.Core.Domain.Rules.ValidaCPF;
import org.com.br.Core.Domain.Rules.ValidaTelefone;
import org.com.br.Infra.Interfaces.IPessoaFisica;
import org.com.br.Infra.Interfaces.IPessoaJuridica;
import org.com.br.Infra.Repository.PessoaFisicaRepository;
import org.com.br.Infra.Repository.PessoaJuridicaRepository;

public class ClienteService {

    private IPessoaFisica pessoaFisicaRepository;

    private IPessoaJuridica pessoaJuridicaRepository;

    public ClienteService(IPessoaFisica pessoaFisicaRepository, IPessoaJuridica pessoaJuridicaRepository){
        this.pessoaFisicaRepository = pessoaFisicaRepository;
        this.pessoaJuridicaRepository = pessoaJuridicaRepository;
    }

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

            if(!ValidaCPF.isCPFValid(cpfCnpj)){
                throw new InvalidCPFException("CPF inválido, checar se CPF está escrito corretamente!");
            }

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

            pessoaFisicaRepository.createPessoaFisica(pessoaFisica);

        } else if (tipo.equals("CNPJ")) {

            if (!ValidaCNPJ.isValidCnpj(cpfCnpj)){
                throw new InvalidCPNJException("CNPJ inválido, checar se CNPJ está escrito corretamente!");
            }

            PessoaJuridica pessoaJuridica = new PessoaJuridica();
            pessoaJuridica.setCnpj(cpfCnpj.trim());
            pessoaJuridica.setNome(nome.trim());
            pessoaJuridica.setEmail(email.trim());

            if(!ValidaTelefone.validarTelefone(telefone)){

                throw new InvalidTelefoneException("Checar telefone, valor não digitado no padrão +DDI (DDD) XXXX-XXXX");

            }

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

            pessoaJuridicaRepository.createPessoaJuridica(pessoaJuridica);
        }

    }


}

