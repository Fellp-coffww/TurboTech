package org.com.br.Application.Desktop.Services;

import javax.swing.JOptionPane;

import org.com.br.Core.Domain.Models.Funcionario;
import org.com.br.Infra.Interfaces.IFuncionario;
import org.com.br.Infra.Repository.FuncionarioRepository;

public class FuncionarioService {
    
    private IFuncionario funcionarioRepository;

    public FuncionarioService(FuncionarioRepository funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
    }

    public void criarFuncionario(String nome , String cpf) throws Exception{
        Funcionario funcionario = new Funcionario(nome, cpf);
        // Validar o campo antes de salvar
        if ((nome == null || nome.trim().isEmpty()) && (cpf == null || cpf.trim().isEmpty())) {
            JOptionPane.showMessageDialog(null, "O campo descrição é obrigatório!", "Erro", JOptionPane.ERROR_MESSAGE);
        } else {
            funcionarioRepository.createFuncionario(funcionario); // Salvar no repositório
            JOptionPane.showMessageDialog(null, "Marca salva com sucesso!");
        }
    }
    
}
