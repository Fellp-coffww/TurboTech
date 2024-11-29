package org.com.br.Infra.Interfaces;

import org.com.br.Core.Domain.Models.Funcionario;

import java.util.List;


public interface IFuncionario {
    
    public void createFuncionario(Funcionario funcionario) throws Exception;

    public Funcionario getFuncionarioByCpf(String cpf) throws Exception;

    public List<Funcionario> getFuncionarios() throws Exception;

    public void updateFuncionario(Funcionario funcionario) throws Exception;

    public void deleteFuncionario(String cpf) throws Exception;

    public Funcionario getFuncionarioByNome(String nome) throws Exception;

}
