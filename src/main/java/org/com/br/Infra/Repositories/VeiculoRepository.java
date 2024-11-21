package org.com.br.Infra.Repositories;

import org.com.br.Core.Domain.Models.Veiculo;

import java.util.List;

public interface VeiculoRepository {

    public void createVeiculo(Veiculo Veiculo) throws Exception;

    public Veiculo getVeiculoById(Long id) throws Exception;

    public List<Veiculo> getVeiculo() throws Exception;

    public void updateVeiculo(Veiculo Veiculo) throws Exception;

    public void deleteVeiculo(Long id) throws Exception;

}
