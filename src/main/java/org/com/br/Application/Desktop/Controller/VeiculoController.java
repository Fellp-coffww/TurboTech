package org.com.br.Application.Desktop.Controller;

import org.com.br.Application.Desktop.Services.VeiculoService;
import org.com.br.Core.Domain.Models.Veiculo;
import org.com.br.Infra.Interfaces.IVeiculo;
import org.com.br.Infra.Repository.VeiculoRepository;

import javax.swing.*;
import java.util.List;

public class VeiculoController {

    VeiculoService veiculoService;

    JFrame frame;

    public VeiculoController(JFrame frame) throws Exception {

        veiculoService = new VeiculoService(new VeiculoRepository());
        this.frame = frame;

    }

    public void createVeiculo(String placa, String chassi, String kilometragem, int ano, long idModelo) {
        try {
            veiculoService.criarVeiculo(new Veiculo(placa, chassi, kilometragem, ano, idModelo));
            JOptionPane.showMessageDialog(frame, "Veículo salvo com sucesso!");
        }catch (Exception e){
            JOptionPane.showMessageDialog(frame, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void deletarVeiculo(String placa) {
        try {
            veiculoService.deletarVeiculo(placa);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<Veiculo> getVeiculos(){
        return veiculoService.getVeiculo();
    }

}
