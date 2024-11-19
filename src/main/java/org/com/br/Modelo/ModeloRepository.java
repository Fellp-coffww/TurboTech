package org.com.br.Modelo;

import java.util.List;

public interface ModeloRepository {

    public void createModelo(Modelo modelo) throws Exception;

    public Modelo getModeloById(Long id) throws Exception;

    public List<Marca> getModelo() throws Exception;

    public void updateModelo(Marca marca) throws Exception;

    public void deleteModelo(Long id) throws Exception;


}