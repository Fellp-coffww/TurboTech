package org.com.br.Infra.Services;

import org.com.br.Core.Domain.Models.Acessorios;
import org.com.br.Infra.Repositories.AcessoriosRepository;

import java.util.List;

public class AcessoriosService implements AcessoriosRepository {


    @Override
    public void createAcessorios(Acessorios Acessorios) throws Exception {

    }

    @Override
    public Acessorios getAcessoriosById(Long id) throws Exception {
        return null;
    }

    @Override
    public List<Acessorios> getAcessorios() throws Exception {
        return List.of();
    }

    @Override
    public void updateAcessorios(Acessorios Acessorios) throws Exception {

    }

    @Override
    public void deleteAcessorios(Long id) throws Exception {

    }
}
