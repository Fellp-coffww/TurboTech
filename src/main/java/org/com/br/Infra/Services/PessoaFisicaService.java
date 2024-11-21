package org.com.br.Infra.Services;

import org.com.br.Core.Domain.Models.PessoaFisica;
import org.com.br.Infra.Repositories.PessoaFisicaRepository;

import java.util.List;

public class PessoaFisicaService implements PessoaFisicaRepository {


    @Override
    public void createPessoaFisica(PessoaFisica pessoaFisica) throws Exception {

    }

    @Override
    public PessoaFisica getPessoaFisicaById(Long id) throws Exception {
        return null;
    }

    @Override
    public List<PessoaFisica> getPessoaFisica() throws Exception {
        return List.of();
    }

    @Override
    public void updatePessoaFisica(PessoaFisica PessoaFisica) throws Exception {

    }

    @Override
    public void deletePessoaFisica(Long id) throws Exception {

    }
}
