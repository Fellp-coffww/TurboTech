package org.com.br.Infra.Services;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import org.com.br.Core.Domain.Models.Acessorios;
import org.com.br.Infra.Configuration.DbConnection;
import org.com.br.Infra.Repositories.AcessoriosRepository;

public class AcessoriosService implements AcessoriosRepository {

    private Connection connection = null;
    public AcessoriosService()throws Exception{
        connection = DbConnection.getConnection();
        if(connection == null) throw new Exception("ERRO DE CONEXAO");
    }

    @Override
    public void createAcessorios(Acessorios acessorios) throws Exception {
        try {
            String sql =  "insert into Acessorios(descricao,placa)"
                    +     "values(?,?);";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, acessorios.getDescricao());
            preparedStatement.setString(2, acessorios.getPlaca());
            preparedStatement.executeUpdate();
        } catch (SQLException erro) {
            //Erro do comando SQL - chave, coluna, nome da tabela, ...
            throw new Exception("SQL Erro: "+ erro.getMessage());
        } catch(Exception erro){
            throw new Exception("Incluir Persistencia: " + erro);
        }
    }

    @Override
    public Acessorios getAcessoriosById(Long id) throws Exception {
        String sql = "select * from Acessorios where idAcessorio = " + id;
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(sql);
        rs.next();
        Acessorios acessorios = new Acessorios(rs.getLong("idAcessorios"), rs.getString("descricao"), rs.getString("placa"));
        return acessorios;
    }

    @Override
    public List<Acessorios> getAcessorios() throws Exception {
        String sql = "select * from Acessorios";
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(sql);
        List<Acessorios> acessorios = new ArrayList<Acessorios>();

        while(rs.next()){
            Acessorios acessorio = new Acessorios(rs.getLong("idAcessorios"), rs.getString("descricao"), rs.getString("placa"));
            acessorios.add(acessorio);
        }

        return acessorios;
    }

    @Override
    public void updateAcessorios(Acessorios acessorios) throws Exception {
        try{
            String sql = "update Acessorios  Set descricao = ?,placa = ? where idAcessorios = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, acessorios.getDescricao());
            preparedStatement.setString(2, acessorios.getPlaca());
            preparedStatement.setLong(3, acessorios.getIdAcessorios());
            preparedStatement.executeUpdate();
    
            } catch (SQLException erro) {
             //Erro do comando SQL - chave, coluna, nome da tabela, ...
                 throw new Exception("SQL Erro: "+ erro.getMessage());
            } catch(Exception erro){
                 throw new Exception("Incluir Persistencia: " + erro);
            }
    }

    @Override
    public void deleteAcessorios(Long id) throws Exception {
        try {
            String sql = "delete from Acessorios where idAcessorio = " + id;
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
