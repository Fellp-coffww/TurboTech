package org.com.br.Infra.Repositories;

import org.com.br.Core.Domain.Models.Marca;
import org.com.br.Core.Domain.Models.Oficina;

import java.util.List;

public interface OficinaRepository {

    public void createOficina(Marca marca) throws Exception;

    public Oficina getOficinaById(Long id) throws Exception;

    public List<Oficina> getOficina() throws Exception;

    public void updateOficina(Oficina oficina) throws Exception;

    public void deleteOficina(Long id) throws Exception;



}
