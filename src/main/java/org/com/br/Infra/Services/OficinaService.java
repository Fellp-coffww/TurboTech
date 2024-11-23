package org.com.br.Infra.Services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.com.br.Core.Domain.Models.Oficina;
import org.com.br.Infra.Configuration.DbConnection;
import org.com.br.Infra.Repositories.OficinaRepository;

public class OficinaService implements OficinaRepository {

    private Connection connection = null;
    public OficinaService()throws Exception{
        connection = DbConnection.getConnection();
        if(connection == null) throw new Exception("ERRO DE CONEXAO");
    }
    @Override
    public void createOficina(Oficina oficina) throws Exception {
        try {
            String sql =  "insert into Modelo(nome,email,complemento,logradouro,numero,ddi,ddd,telefone)"
                    +     "values(?,?,?,?,?,?,?,?);";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,oficina.getNome());
            preparedStatement.setString(2, oficina.getEmail());
            preparedStatement.setString(3, oficina.getComplemento());
            preparedStatement.setString(4, oficina.getLogradouro());
            preparedStatement.setString(5, oficina.getNumero());
            preparedStatement.setInt(6, oficina.getDdi());
            preparedStatement.setInt(7, oficina.getDdd());
            preparedStatement.setInt(8, oficina.getTelefone());
            preparedStatement.executeUpdate();
        } catch (SQLException erro) {
            //Erro do comando SQL - chave, coluna, nome da tabela, ...
            throw new Exception("SQL Erro: "+ erro.getMessage());
        } catch(Exception erro){
            throw new Exception("Incluir Persistencia: " + erro);
        }
    }

    @Override
    public Oficina getOficinaById(Long id) throws Exception {
        String sql = "select * from Oficina where idModelo = " + id;
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(sql);
        rs.next();
        Oficina oficina = new Oficina(rs.getLong("idOficina"), rs.getString("nome"), rs.getString("email"), rs.getString("complemento"), rs.getString("logradouro"), rs.getString("numero"), rs.getInt("ddi"), rs.getInt("ddd"), rs.getInt("telefone"));
        return oficina;
    }

    @Override
    public List<Oficina> getOficina() throws Exception {
        
        String sql = "select * from Oficina";
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(sql);
        List<Oficina> oficinas = new ArrayList<Oficina>();

        while(rs.next()){
            Oficina oficina = new Oficina(rs.getLong("idOficina"), rs.getString("nome"), rs.getString("email"), rs.getString("complemento"), rs.getString("logradouro"), rs.getString("numero"), rs.getInt("ddi"), rs.getInt("ddd"), rs.getInt("telefone"));
            oficinas.add(oficina);
        }

        return oficinas;

    }

    @Override
    public void updateOficina(Oficina oficina) throws Exception {
        try{
            String sql = "update Modelo  Set nome = ?,email = ?,complemento = ?,logradouro = ?,numero = ?,ddi = ?,ddd = ?,telefone = ? where idOficina = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,oficina.getNome() );
            preparedStatement.setString(2, oficina.getEmail());
            preparedStatement.setString(3, oficina.getComplemento());
            preparedStatement.setString(4, oficina.getLogradouro());
            preparedStatement.setString(5, oficina.getNumero());
            preparedStatement.setInt(6, oficina.getDdi());
            preparedStatement.setInt(7, oficina.getDdd());
            preparedStatement.setInt(8, oficina.getTelefone());
            preparedStatement.setLong(9, oficina.getIdOficina());
            preparedStatement.executeUpdate();
    
            } catch (SQLException erro) {
             //Erro do comando SQL - chave, coluna, nome da tabela, ...
                 throw new Exception("SQL Erro: "+ erro.getMessage());
            } catch(Exception erro){
                 throw new Exception("Incluir Persistencia: " + erro);
            }
    }

    @Override
    public void deleteOficina(Long id) throws Exception {

        try {
            String sql = "delete from Oficina where idOficina = " + id;
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
