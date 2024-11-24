package org.com.br.Infra.Repositories;

import org.com.br.Core.Domain.Models.PessoaFisica;

import java.util.List;

public interface PessoaFisicaRepository {

    public void createPessoaFisica(PessoaFisica pessoaFisica) throws Exception;

    public PessoaFisica getPessoaFisicaByCpf(String cpf) throws Exception;

    public List<PessoaFisica> getPessoaFisica() throws Exception;

    public void updatePessoaFisica(PessoaFisica PessoaFisica) throws Exception;

    public void deletePessoaFisica(String cpf) throws Exception;

}
