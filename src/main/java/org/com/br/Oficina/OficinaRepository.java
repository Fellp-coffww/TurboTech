package org.com.br.Oficina;

import org.com.br.Marca.Marca;

import java.util.List;

public interface OficinaRepository {

    public void createOficina(Marca marca) throws Exception;

    public Oficina getOficinaById(Long id) throws Exception;

    public List<Oficina> getOficina() throws Exception;

    public void updateOficina(Oficina oficina) throws Exception;

    public void deleteOficina(Long id) throws Exception;



}
