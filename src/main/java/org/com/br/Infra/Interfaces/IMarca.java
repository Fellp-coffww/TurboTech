package org.com.br.Infra.Interfaces;

import org.com.br.Core.Domain.Models.Marca;

import java.util.List;

public interface IMarca {

    public void createMarca(Marca marca) throws Exception;

    public Marca getMarcaById(Long id) throws Exception;

    public List<Marca> getMarcas() throws Exception;

    public void updateMarca(Marca marca) throws Exception;

    public void deleteMarca(Long id) throws Exception;


}
