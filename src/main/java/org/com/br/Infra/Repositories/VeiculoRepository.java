package org.com.br.Infra.Repositories;

import java.util.List;

import org.com.br.Core.Domain.Models.Veiculo;

public interface VeiculoRepository {

    public void createVeiculo(Veiculo veiculo) throws Exception;

    public Veiculo getVeiculoById(String placa) throws Exception;

    public List<Veiculo> getVeiculo() throws Exception;

    public void updateVeiculo(Veiculo Veiculo) throws Exception;

    public void deleteVeiculo(String placa) throws Exception;

}
