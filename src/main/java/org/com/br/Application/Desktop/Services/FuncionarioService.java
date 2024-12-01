package org.com.br.Application.Desktop.Services;

import org.com.br.Core.Domain.Exceptions.InvalidCPFException;
import org.com.br.Core.Domain.Models.Funcionario;
import org.com.br.Core.Domain.Rules.ValidaCPF;
import org.com.br.Infra.Interfaces.IFuncionario;

import java.util.List;

public class FuncionarioService {
    
    private IFuncionario funcionarioRepository;

    public FuncionarioService(IFuncionario funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
    }

    public FuncionarioService() {
    }

    public void criarFuncionario(String nome , String cpf) throws Exception{
        if(!ValidaCPF.isCPFValid(cpf)){
                throw new InvalidCPFException("CPF inválido, checar se CPF está escrito corretamente!");
            }
        Funcionario funcionario = new Funcionario(nome, cpf);
        // Validar o campo antes de salvar
        if ((nome == null || nome.trim().isEmpty()) && (cpf == null || cpf.trim().isEmpty())) {
            throw new Exception("Erro no nome ou cpf");
        } else {
            funcionarioRepository.createFuncionario(funcionario); // Salvar no repositório
        }
    }

    public void deleteFuncionario(String cpf) throws Exception{
        funcionarioRepository.deleteFuncionario(cpf);
    }

    public List<Funcionario> getFuncionario() {
        try {
            return funcionarioRepository.getFuncionarios();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
