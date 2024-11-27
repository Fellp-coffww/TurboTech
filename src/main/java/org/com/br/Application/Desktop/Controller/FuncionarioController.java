package org.com.br.Application.Desktop.Controller;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.com.br.Application.Desktop.Services.FuncionarioService;
import org.com.br.Infra.Repository.FuncionarioRepository;


public class FuncionarioController {
    
    
    public FuncionarioController(){
        try {
            FuncionarioService funcionarioService = new FuncionarioService(new FuncionarioRepository());        
        } catch (Exception e) {
        }
    }
    
    private FuncionarioService funcionarioService;

    private JFrame frame;

    public FuncionarioController(JFrame frame) {
        this.frame = frame;
    }

    public void criarFuncionario(String nome, String cpf)throws Exception{
        try{
            
                        funcionarioService.criarFuncionario(nome, cpf);
            JOptionPane.showMessageDialog(frame, "Dados salvos com sucesso!");

        }catch (Exception e){
            JOptionPane.showMessageDialog(frame, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

}
