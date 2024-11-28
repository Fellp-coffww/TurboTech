package org.com.br.Application.Desktop.Controller;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.com.br.Application.Desktop.Services.PecaService;
import org.com.br.Infra.Repository.PecaRepository;

public class PecaController  {

    public PecaController(){
        try {
            this.pecaService = new PecaService(new PecaRepository());        
        } catch (Exception e) {
        }
    }

    private PecaService pecaService;
   
    private JFrame frame;

    public PecaController(JFrame frame) {
        this.frame = frame;
        try {
             this.pecaService = new PecaService(new PecaRepository());        
        } catch (Exception e) {
        }
    }

    public void criarPeca(String descricao, int quantidade, double valorUnitario, String codigo)throws Exception{
        try{
            pecaService.criarPeca(descricao, quantidade, valorUnitario, codigo);
            JOptionPane.showMessageDialog(frame, "Dados salvos com sucesso!");

        }catch (Exception e){
            JOptionPane.showMessageDialog(frame, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }


}
