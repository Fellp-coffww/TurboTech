package org.com.br.Infra.Repository;

import org.com.br.Core.Domain.Models.Funcionario;
import org.com.br.Core.Domain.Rules.ValidaCPF;
import org.com.br.Infra.Configuration.DbConnection;
import org.com.br.Infra.Interfaces.IFuncionario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FuncionarioRepository implements IFuncionario {

    private Connection connection = null;
    public FuncionarioRepository()throws Exception{
        connection = DbConnection.getConnection();
        if(connection == null) throw new Exception("ERRO DE CONEXAO");
    }
    @Override
    public void createFuncionario(Funcionario funcionario) throws Exception {
        if (ValidaCPF.isCPFValid(funcionario.getCpf())) {
        try {
            String sql =  "insert into Funcionario(nome, cpf)"
                    +     "values( ?, ?);";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, funcionario.getNome());
            preparedStatement.setString(2, funcionario.getCpf());
            preparedStatement.executeUpdate();
        } catch (SQLException erro) {
            //Erro do comando SQL - chave, coluna, nome da tabela, ...
            throw new Exception("SQL Erro: "+ erro.getMessage());
        } catch(Exception erro){
            throw new Exception("Incluir Persistencia: " + erro);
        }
        } else{

            throw new Exception("CPF inválido");
        }

    }

    @Override
    public Funcionario getFuncionarioByCpf(String cpf) throws Exception {
        String sql = "select * from funcionario where cpf =  ? ";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, cpf);
        ResultSet rs = statement.executeQuery();
        rs.next();
        Funcionario funcionario = new Funcionario(rs.getString("nome"), rs.getString("cpf"));
        return funcionario;
    }

    @Override
    public List<Funcionario> getFuncionarios() throws Exception {

        String sql = "select * from Funcionario";
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(sql);
        List<Funcionario> funcionarios = new ArrayList<Funcionario>();

        while(rs.next()){
            Funcionario funcionario = new Funcionario(rs.getString("nome"), rs.getString("cpf"));
            funcionarios.add(funcionario);
        }

        return funcionarios;
    }

    @Override
    public void updateFuncionario(Funcionario funcionario) throws Exception {
     try{
        String sql = "update Funcionario  Set nome = ? where cpf = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, funcionario.getNome());
        preparedStatement.setString(2, funcionario.getCpf());
        preparedStatement.executeUpdate();

        } catch (SQLException erro) {
         //Erro do comando SQL - chave, coluna, nome da tabela, ...
             throw new Exception("SQL Erro: "+ erro.getMessage());
        } catch(Exception erro){
             throw new Exception("Incluir Persistencia: " + erro);
        }
    }

    @Override
    public void deleteFuncionario(String cpf) throws Exception {

        try {
            String sql = "delete from Funcionario where cpf = ?" ;
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,cpf);
            preparedStatement.executeUpdate();
        } catch (SQLException erro) {
            //Erro do comando SQL - chave, coluna, nome da tabela, ...
            throw new Exception("SQL Erro: "+ erro.getMessage());
        } catch(Exception erro){
            throw new Exception("Incluir Persistencia: " + erro);
        }

    }

    @Override
    public Funcionario getFuncionarioByNome(String nome) throws Exception {
        String sql = "select * from funcionario where nome =  ? ";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, nome);
        ResultSet rs = statement.executeQuery();
        rs.next();
        Funcionario funcionario = new Funcionario(rs.getString("nome"), rs.getString("cpf"));
        return funcionario;
    }
}