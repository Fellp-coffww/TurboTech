package org.com.br.Application.Desktop.Controller;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.com.br.Application.Desktop.Services.FuncionarioService;
import org.com.br.Core.Domain.Models.Funcionario;
import org.com.br.Infra.Repository.FuncionarioRepository;

import java.util.List;


public class FuncionarioController {
    
    
    public FuncionarioController(){
        try {
            this.funcionarioService = new FuncionarioService(new FuncionarioRepository());        
        } catch (Exception e) {
        }
    }
    
    private FuncionarioService funcionarioService;

    private JFrame frame;

    public FuncionarioController(JFrame frame) {
        this.frame = frame;
        try {
            this.funcionarioService = new FuncionarioService(new FuncionarioRepository());        
        } catch (Exception e) {
        }
    }

    public void criarFuncionario(String nome, String cpf)throws Exception{
        try{
            
                        funcionarioService.criarFuncionario(nome, cpf);
            JOptionPane.showMessageDialog(frame, "Dados salvos com sucesso!");

        }catch (Exception e){
            JOptionPane.showMessageDialog(frame, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void deleteFuncionario(String cpf)throws Exception{
        try {
            funcionarioService.deleteFuncionario(cpf);
        }catch (Exception e){
            JOptionPane.showMessageDialog(frame, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public List<Funcionario> getFuncionario(){
        return funcionarioService.getFuncionario();
    }



}
