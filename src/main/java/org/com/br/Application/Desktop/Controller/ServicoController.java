package org.com.br.Application.Desktop.Controller;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.com.br.Application.Desktop.Services.ServicoService;
import org.com.br.Infra.Repository.ServicoRepository;

public class ServicoController { 

    public ServicoController(){
        try {
            this.servicoService = new ServicoService(new ServicoRepository());        
        } catch (Exception e) {
        }
    }

    private ServicoService servicoService;

    private JFrame frame;

    public ServicoController(JFrame frame) {
        this.frame = frame;
        try {
            this.servicoService = new ServicoService(new ServicoRepository());        
        } catch (Exception e) {
        }
    }

    public void criarServico(String descricao, double valorUnitario)throws Exception{
        try{
            servicoService.criarServico(descricao, valorUnitario);
            JOptionPane.showMessageDialog(frame, "Dados salvos com sucesso!");

        }catch (Exception e){
            JOptionPane.showMessageDialog(frame, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void deletarServico(long id){

        try {
            servicoService.deleteServico(id);
        }catch (Exception e){

        }

    }


}
