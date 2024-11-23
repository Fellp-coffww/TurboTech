package org.com.br.Infra.Services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.com.br.Core.Domain.Models.PessoaJuridica;
import org.com.br.Infra.Configuration.DbConnection;
import org.com.br.Infra.Repositories.PessoaJuridicaRepository;

public class PessoaJuridicaService implements PessoaJuridicaRepository {

    private Connection connection = null;
    public PessoaJuridicaService()throws Exception{
        connection = DbConnection.getConnection();
        if(connection == null) throw new Exception("ERRO DE CONEXAO");
    }
    @Override
    public void createPessoaJuridica(PessoaJuridica pessoaJuridica) throws Exception {
try {
            String sql =  "insert into PessoaFisica(nome,cnpj,email,complemento,logradouro,numero,ddi,ddd,telefone,inscricaoEstadual,contato,razaoSocial)"
                    +     "values(?,?,?,?,?,?,?,?,?,?,?,?);";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,pessoaJuridica.getNome());
            preparedStatement.setString(2, pessoaJuridica.getCnpj());
            preparedStatement.setString(3, pessoaJuridica.getEmail());
            preparedStatement.setString(4, pessoaJuridica.getComplemento());
            preparedStatement.setString(5, pessoaJuridica.getLogradouro());
            preparedStatement.setString(6, pessoaJuridica.getNumero());
            preparedStatement.setInt(7, pessoaJuridica.getDdi());
            preparedStatement.setInt(8, pessoaJuridica.getDdd());
            preparedStatement.setInt(9, pessoaJuridica.getTelefone());
            preparedStatement.setString(10, pessoaJuridica.getInscricaoEstadual());
            preparedStatement.setString(11, pessoaJuridica.getContato());
            preparedStatement.setString(12, pessoaJuridica.getRazaoSocial());
            preparedStatement.executeUpdate();
        } catch (SQLException erro) {
            //Erro do comando SQL - chave, coluna, nome da tabela, ...
            throw new Exception("SQL Erro: "+ erro.getMessage());
        } catch(Exception erro){
            throw new Exception("Incluir Persistencia: " + erro);
        }
    }

    @Override
    public PessoaJuridica getPessoaJuridicaById(Long id) throws Exception {
        String sql = "select * from PessoaJuridica where idModelo = " + id;
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(sql);
        rs.next();
        PessoaJuridica pessoaJuridica = new PessoaJuridica(rs.getLong("idCliente"), rs.getString("nome"), rs.getString("Cnpj"), rs.getString("email"), rs.getString("complemento"), rs.getString("logradouro"), rs.getString("numero"), rs.getInt("ddi"), rs.getInt("ddd"), rs.getInt("telefone"), rs.getNString("inscricaoEstadual"), rs.getNString("contato"), rs.getNString("razaoSocial"));
        return pessoaJuridica;
    }

    @Override
    public List<PessoaJuridica> getPessoaJuridica() throws Exception {
        String sql = "select * from PessoaJuridica";
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(sql);
        List<PessoaJuridica> pessoasJuridicas = new ArrayList<PessoaJuridica>();

        while(rs.next()){
            PessoaJuridica pessoaJuridica = new PessoaJuridica(rs.getLong("idCliente"), rs.getString("nome"), rs.getString("Cnpj"), rs.getString("email"), rs.getString("complemento"), rs.getString("logradouro"), rs.getString("numero"), rs.getInt("ddi"), rs.getInt("ddd"), rs.getInt("telefone"), rs.getNString("inscricaoEstadual"), rs.getNString("contato"), rs.getNString("razaoSocial"));
            pessoasJuridicas.add(pessoaJuridica);
        }

        return pessoasJuridicas;
    }

    @Override
    public void updatePessoaJuridica(PessoaJuridica pessoaJuridica) throws Exception {
        try{
            String sql = "update PessoaJuridica  Set nome = ?, cnpj = ?, email = ?,complemento = ?,logradouro = ?,numero = ?,ddi = ?,ddd = ?,telefone = ?,inscricaoEstadual = ?,contato = ?,razaoSocial = ? where idCliente = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,pessoaJuridica.getNome());
            preparedStatement.setString(2, pessoaJuridica.getCnpj());
            preparedStatement.setString(3, pessoaJuridica.getEmail());
            preparedStatement.setString(4, pessoaJuridica.getComplemento());
            preparedStatement.setString(5, pessoaJuridica.getLogradouro());
            preparedStatement.setString(6, pessoaJuridica.getNumero());
            preparedStatement.setInt(7, pessoaJuridica.getDdi());
            preparedStatement.setInt(8, pessoaJuridica.getDdd());
            preparedStatement.setInt(9, pessoaJuridica.getTelefone());
            preparedStatement.setInt(10, pessoaJuridica.getTelefone());
            preparedStatement.setString(11, pessoaJuridica.getInscricaoEstadual());
            preparedStatement.setString(12, pessoaJuridica.getContato());
            preparedStatement.setString(13, pessoaJuridica.getRazaoSocial());
            preparedStatement.executeUpdate();
    
            } catch (SQLException erro) {
             //Erro do comando SQL - chave, coluna, nome da tabela, ...
                 throw new Exception("SQL Erro: "+ erro.getMessage());
            } catch(Exception erro){
                 throw new Exception("Incluir Persistencia: " + erro);
            }
    }

    @Override
    public void deletePessoaJuridica(Long id) throws Exception {
        try {
            String sql = "delete from PessoaJuridica where idCliente = " + id;
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
