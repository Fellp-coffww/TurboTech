package org.com.br.Application.Desktop.Services;

import org.com.br.Core.Domain.Models.Veiculo;
import org.com.br.Infra.Interfaces.IVeiculo;

import java.util.List;

public class VeiculoService {

    private IVeiculo veiculoRepository;

    public VeiculoService (IVeiculo veiculoRepository){
        this.veiculoRepository = veiculoRepository;
    }



    public void criarVeiculo(Veiculo veiculo) throws Exception {
        if(veiculo.getPlaca() == null){
            throw new Exception("Placa inválida");
        } else if(veiculo.getAno() < 1900){
            throw new Exception("Ano inválido para o carro!");
        }else {
            veiculoRepository.createVeiculo(veiculo);
        }
    }

    public void deletarVeiculo(String placa) throws Exception {
        veiculoRepository.deleteVeiculo(placa);
    }


    public List<Veiculo> getVeiculo(){
        try {
            return veiculoRepository.getVeiculo();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

       

    }
    
    public void criarVeiculo(String placa, String chassi, String kilometragem, int ano, int numPropriedade) throws Exception{
        Veiculo veiculo = new Veiculo(placa, chassi, kilometragem, ano,numPropriedade);

        veiculoRepository.createVeiculo(veiculo);
    }
}


