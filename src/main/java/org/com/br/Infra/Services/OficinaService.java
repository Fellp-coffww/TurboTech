package org.com.br.Infra.Services;

import org.com.br.Core.Domain.Models.Marca;
import org.com.br.Core.Domain.Models.Oficina;
import org.com.br.Infra.Repositories.OficinaRepository;

import java.util.List;

public class OficinaService implements OficinaRepository {


    @Override
    public void createOficina(Marca marca) throws Exception {

    }

    @Override
    public Oficina getOficinaById(Long id) throws Exception {
        return null;
    }

    @Override
    public List<Oficina> getOficina() throws Exception {
        return List.of();
    }

    @Override
    public void updateOficina(Oficina oficina) throws Exception {

    }

    @Override
    public void deleteOficina(Long id) throws Exception {

    }
}
