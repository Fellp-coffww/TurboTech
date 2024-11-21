package org.com.br.Infra.Services;

import org.com.br.Core.Domain.Models.PessoaJuridica;
import org.com.br.Infra.Repositories.PessoaJuridicaRepository;

import java.util.List;

public class PessoaJuridicaService implements PessoaJuridicaRepository {


    @Override
    public void createPessoaJuridica(PessoaJuridica PessoaJuridica) throws Exception {

    }

    @Override
    public PessoaJuridica getPessoaJuridicaById(Long id) throws Exception {
        return null;
    }

    @Override
    public List<PessoaJuridica> getPessoaJuridica() throws Exception {
        return List.of();
    }

    @Override
    public void updatePessoaJuridica(PessoaJuridica PessoaJuridica) throws Exception {

    }

    @Override
    public void deletePessoaJuridica(Long id) throws Exception {

    }
}
