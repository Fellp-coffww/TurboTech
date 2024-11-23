package org.com.br.Infra.Services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.com.br.Core.Domain.Models.PessoaFisica;
import org.com.br.Infra.Configuration.DbConnection;
import org.com.br.Infra.Repositories.PessoaFisicaRepository;

public class PessoaFisicaService implements PessoaFisicaRepository {

    private Connection connection = null;
    public PessoaFisicaService()throws Exception{
        connection = DbConnection.getConnection();
        if(connection == null) throw new Exception("ERRO DE CONEXAO");
    }
    @Override
    public void createPessoaFisica(PessoaFisica pessoaFisica) throws Exception {
        try {
            String sql =  "insert into PessoaFisica(nome,cpf,email,complemento,logradouro,numero,ddi,ddd,telefone)"
                    +     "values(?,?,?,?,?,?,?,?,?);";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,pessoaFisica.getNome());
            preparedStatement.setString(2, pessoaFisica.getCpf());
            preparedStatement.setString(3, pessoaFisica.getEmail());
            preparedStatement.setString(4, pessoaFisica.getComplemento());
            preparedStatement.setString(5, pessoaFisica.getLogradouro());
            preparedStatement.setString(6, pessoaFisica.getNumero());
            preparedStatement.setInt(7, pessoaFisica.getDdi());
            preparedStatement.setInt(8, pessoaFisica.getDdd());
            preparedStatement.setInt(9, pessoaFisica.getTelefone());
            preparedStatement.executeUpdate();
        } catch (SQLException erro) {
            //Erro do comando SQL - chave, coluna, nome da tabela, ...
            throw new Exception("SQL Erro: "+ erro.getMessage());
        } catch(Exception erro){
            throw new Exception("Incluir Persistencia: " + erro);
        }
    }

    @Override
    public PessoaFisica getPessoaFisicaById(Long id) throws Exception {
        String sql = "select * from PessoaFisica where idCliente = " + id;
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(sql);
        rs.next();
        PessoaFisica pessoaFisica = new PessoaFisica(rs.getLong("idCliente"), rs.getString("nome"), rs.getString("Cpf"), rs.getString("email"), rs.getString("complemento"), rs.getString("logradouro"), rs.getString("numero"), rs.getInt("ddi"), rs.getInt("ddd"), rs.getInt("telefone"));
        return pessoaFisica;
    }

    @Override
    public List<PessoaFisica> getPessoaFisica() throws Exception {
        String sql = "select * from PessoaFisica";
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(sql);
        List<PessoaFisica> pessoasFisicas = new ArrayList<PessoaFisica>();

        while(rs.next()){
            PessoaFisica pessoaFisica = new PessoaFisica(rs.getLong("idCliente"), rs.getString("nome"), rs.getString("Cpf"), rs.getString("email"), rs.getString("complemento"), rs.getString("logradouro"), rs.getString("numero"), rs.getInt("ddi"), rs.getInt("ddd"), rs.getInt("telefone"));
            pessoasFisicas.add(pessoaFisica);
        }

        return pessoasFisicas;
    }

    @Override
    public void updatePessoaFisica(PessoaFisica pessoaFisica) throws Exception {
        try{
            String sql = "update PessoaFisica  Set nome = ?, cpf = ?, email = ?,complemento = ?,logradouro = ?,numero = ?,ddi = ?,ddd = ?,telefone = ? where idCliente = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,pessoaFisica.getNome());
            preparedStatement.setString(2, pessoaFisica.getCpf());
            preparedStatement.setString(3, pessoaFisica.getEmail());
            preparedStatement.setString(4, pessoaFisica.getComplemento());
            preparedStatement.setString(5, pessoaFisica.getLogradouro());
            preparedStatement.setString(6, pessoaFisica.getNumero());
            preparedStatement.setInt(7, pessoaFisica.getDdi());
            preparedStatement.setInt(8, pessoaFisica.getDdd());
            preparedStatement.setInt(9, pessoaFisica.getTelefone());
            preparedStatement.setInt(10, pessoaFisica.getTelefone());
            preparedStatement.executeUpdate();
    
            } catch (SQLException erro) {
             //Erro do comando SQL - chave, coluna, nome da tabela, ...
                 throw new Exception("SQL Erro: "+ erro.getMessage());
            } catch(Exception erro){
                 throw new Exception("Incluir Persistencia: " + erro);
            }
    }

    @Override
    public void deletePessoaFisica(Long id) throws Exception {
        try {
            String sql = "delete from PessoaFisica where idCliente = " + id;
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
