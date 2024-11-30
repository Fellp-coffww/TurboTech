package org.com.br.Application.Desktop.Services;

import org.com.br.Core.Domain.Models.Veiculo;
import org.com.br.Infra.Interfaces.IVeiculo;

public class VeiculoService {
    
    private IVeiculo veiculoRepository;

    public VeiculoService (IVeiculo veiculoRepository){
        this.veiculoRepository = veiculoRepository;
    }

    public VeiculoService (){
    }

    public void criarVeiculo(String placa, String chassi, String kilometragem, int ano, long idModelo, int numPropriedade){
        Veiculo veiculo = new Veiculo(placa, chassi, kilometragem, ano, idModelo,numPropriedade);
    }

}
