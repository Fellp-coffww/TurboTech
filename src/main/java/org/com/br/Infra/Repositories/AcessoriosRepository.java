package org.com.br.Infra.Repositories;

import org.com.br.Core.Domain.Models.Acessorios;

import java.util.List;

public interface AcessoriosRepository {

    public void createAcessorios(Acessorios Acessorios) throws Exception;

    public Acessorios getAcessoriosById(Long id) throws Exception;

    public List<Acessorios> getAcessorios() throws Exception;

    public void updateAcessorios(Acessorios Acessorios) throws Exception;

    public void deleteAcessorios(Long id) throws Exception;

}
