package org.com.br.Infra.Repositories;

import java.util.List;

import org.com.br.Core.Domain.Models.Oficina;

public interface OficinaRepository {

    public void createOficina(Oficina oficina) throws Exception;

    public Oficina getOficinaById(Long id) throws Exception;

    public List<Oficina> getOficina() throws Exception;

    public void updateOficina(Oficina oficina) throws Exception;

    public void deleteOficina(Long id) throws Exception;



}
