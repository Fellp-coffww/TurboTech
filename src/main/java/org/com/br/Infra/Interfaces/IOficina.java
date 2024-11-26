package org.com.br.Infra.Interfaces;

import java.util.List;

import org.com.br.Core.Domain.Models.Oficina;

public interface IOficina {

    public void createOficina(Oficina oficina) throws Exception;

    public Oficina getOficinaById(Long id) throws Exception;

    public List<Oficina> getOficina() throws Exception;

    public void updateOficina(Oficina oficina) throws Exception;

    public void deleteOficina(Long id) throws Exception;



}
