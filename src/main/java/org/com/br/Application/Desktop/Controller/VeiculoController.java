package org.com.br.Application.Desktop.Controller;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.com.br.Application.Desktop.Services.VeiculoService;
import org.com.br.Infra.Repository.VeiculoRepository;

public class VeiculoController {
    
    public VeiculoController(){
        try {
            this.veiculoService = new VeiculoService(new VeiculoRepository());
        } catch (Exception e) {
        }
    }

    private VeiculoService veiculoService;

    private JFrame frame;

    public VeiculoController(JFrame frame) {
        this.frame = frame;
        try {
            this.veiculoService = new VeiculoService(new VeiculoRepository());
        } catch (Exception e) {
        }
    }

    public void criarVeiculo(String placa, String chassi, String kilometragem, int ano, int numPropriedade) throws Exception{
        try {
            veiculoService.criarVeiculo(placa, chassi, kilometragem, ano, numPropriedade);
            JOptionPane.showMessageDialog(frame, "Dados salvos com sucesso!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(frame, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

}
