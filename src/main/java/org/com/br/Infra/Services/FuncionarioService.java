package org.com.br.Infra.Services;

import org.com.br.Core.Domain.Models.Funcionario;
import org.com.br.Infra.Configuration.DbConnection;
import org.com.br.Infra.Repositories.FuncionarioRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FuncionarioService implements FuncionarioRepository {

    private Connection connection = null;
    public FuncionarioService()throws Exception{
        connection = DbConnection.getConnection();
        if(connection == null) throw new Exception("ERRO DE CONEXAO");
    }
    @Override
    public void createFuncionario(Funcionario funcionario) throws Exception {
        try {
            String sql =  "insert into Funcionario(cpf, nome)"
                    +     "values( ?, ?);";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, funcionario.getCpf());
            preparedStatement.setString(2, funcionario.getNome());
            preparedStatement.executeUpdate();
        } catch (SQLException erro) {
            //Erro do comando SQL - chave, coluna, nome da tabela, ...
            throw new Exception("SQL Erro: "+ erro.getMessage());
        } catch(Exception erro){
            throw new Exception("Incluir Persistencia: " + erro);
        }
    }

    @Override
    public Funcionario getFuncionarioByCpf(String cpf) throws Exception {
        String sql = "select * from funcionario where cpf =  ? ";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, cpf);
        ResultSet rs = statement.executeQuery();
        rs.next();
        Funcionario funcionario = new Funcionario(rs.getString("cpf"), rs.getString("nome"));
        return funcionario;
    }

    @Override
    public List<Funcionario> getFuncionarios() throws Exception {

        String sql = "select * from Funcionario";
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(sql);
        List<Funcionario> funcionarios = new ArrayList<Funcionario>();

        while(rs.next()){
            Funcionario funcionario = new Funcionario(rs.getString("cpf"), rs.getString("nome"));
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
            String sql = "delete from Funcionario where cpf = " + cpf;
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
        } catch (SQLException erro) {
            //Erro do comando SQL - chave, coluna, nome da tabela, ...
            throw new Exception("SQL Erro: "+ erro.getMessage());
        } catch(Exception erro){
            throw new Exception("Incluir Persistencia: " + erro);
        }

    }
}