package org.com.br.Infra.Interfaces;

import java.util.List;

import org.com.br.Core.Domain.Models.Modelo;

public interface IModelo {

    public void createModelo(Modelo modelo) throws Exception;

    public Modelo getModeloById(Long id) throws Exception;

    public List<Modelo> getModelo() throws Exception;

    public void updateModelo(Modelo modelo) throws Exception;

    public void deleteModelo(Long id) throws Exception;

    public List<Modelo> getModelosByMarcaId(Long id) throws Exception;
}