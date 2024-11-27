package org.com.br.Application.Desktop.Controller;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.com.br.Application.Desktop.Services.ServicoService;
import org.com.br.Infra.Repository.ServicoRepository;

public class ServicoController {
    
        
            

    public ServicoController(){
        try {
            ServicoService servicoService = new ServicoService(new ServicoRepository());        
        } catch (Exception e) {
        }
    }

    private ServicoService servicoService;

    private JFrame frame;

    public ServicoController(JFrame frame) {
        this.frame = frame;
    }

    public void criarServico(String descricao, double valorUnitario)throws Exception{
        try{
            servicoService.criarServico(descricao, valorUnitario);
            JOptionPane.showMessageDialog(frame, "Dados salvos com sucesso!");

        }catch (Exception e){
            JOptionPane.showMessageDialog(frame, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }


}