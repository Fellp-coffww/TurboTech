package org.com.br.Infra.Interfaces;

import java.util.List;

import org.com.br.Core.Domain.Models.Marca;

public interface IMarca {

    public void createMarca(Marca marca) throws Exception;

    public Marca getMarcaById(Long id) throws Exception;

    public List<Marca> getMarcas() throws Exception;

    public int getMarcaId(Marca marca) throws Exception;

    public void updateMarca(Marca marca) throws Exception;

    public void deleteMarca(Long id) throws Exception;


}
