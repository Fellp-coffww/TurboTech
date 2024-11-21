package org.com.br.Infra.Repositories;

import org.com.br.Core.Domain.Models.Modelo;

import java.util.List;

public interface ModeloRepository {

    public void createModelo(Modelo modelo) throws Exception;

    public Modelo getModeloById(Long id) throws Exception;

    public List<Modelo> getModelo() throws Exception;

    public void updateModelo(Modelo modelo) throws Exception;

    public void deleteModelo(Long id) throws Exception;


}