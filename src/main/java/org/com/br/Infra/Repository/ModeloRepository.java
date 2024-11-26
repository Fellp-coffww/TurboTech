package org.com.br.Infra.Repository;

import org.com.br.Core.Domain.Models.Modelo;
import org.com.br.Infra.Configuration.DbConnection;
import org.com.br.Infra.Interfaces.IModelo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ModeloRepository implements IModelo {

    private Connection connection = null;
    public ModeloRepository()throws Exception{
        connection = DbConnection.getConnection();
        if(connection == null) throw new Exception("ERRO DE CONEXAO");
    }
    @Override
    public void createModelo(Modelo modelo) throws Exception {
        try {
            String sql =  "insert into Modelo(descricao,idMarca)"
                    +     "values(?,?);";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, modelo.getDescricao());
            preparedStatement.setLong(2, modelo.getIdMarca());
            preparedStatement.executeUpdate();
        } catch (SQLException erro) {
            //Erro do comando SQL - chave, coluna, nome da tabela, ...
            throw new Exception("SQL Erro: "+ erro.getMessage());
        } catch(Exception erro){
            throw new Exception("Incluir Persistencia: " + erro);
        }
    }

    @Override
    public Modelo getModeloById(Long id) throws Exception {
        String sql = "select * from Modelo where idModelo = " + id;
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(sql);
        rs.next();
        Modelo modelo = new Modelo(rs.getLong("idModelo"), rs.getString("descricao"), rs.getLong("idMarca"));
        return modelo;
    }

    @Override
    public List<Modelo> getModelo() throws Exception {

        String sql = "select * from Modelo";
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(sql);
        List<Modelo> modelos = new ArrayList<Modelo>();

        while(rs.next()){
            Modelo modelo = new Modelo(rs.getLong("idModelo"), rs.getString("descricao"), rs.getLong("idMarca"));
            modelos.add(modelo);
        }

        return modelos;
    }

    @Override
    public void updateModelo(Modelo modelo) throws Exception {
     try{
        String sql = "update Modelo  Set descricao = ?,idMarca = ? where idModelo = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, modelo.getDescricao());
        preparedStatement.setLong(2, modelo.getIdMarca());
        preparedStatement.setLong(3, modelo.getIdModelo());
        preparedStatement.executeUpdate();

        } catch (SQLException erro) {
         //Erro do comando SQL - chave, coluna, nome da tabela, ...
             throw new Exception("SQL Erro: "+ erro.getMessage());
        } catch(Exception erro){
             throw new Exception("Incluir Persistencia: " + erro);
        }
}

    @Override
    public void deleteModelo(Long id) throws Exception {

        try {
            String sql = "delete from Modelo where idModelo = " + id;
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
        } catch (SQLException erro) {
            //Erro do comando SQL - chave, coluna, nome da tabela, ...
            throw new Exception("SQL Erro: "+ erro.getMessage());
        } catch(Exception erro){
            throw new Exception("Incluir Persistencia: " + erro);
        }

    }

    @Override
    public List<Modelo> getModelosByMarcaId(Long id) throws Exception {

        String sql = "select * from Modelo where idMarca = " + id;
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(sql);
        List<Modelo> modelos = new ArrayList<Modelo>();

        while(rs.next()){
            Modelo modelo = new Modelo(rs.getString("descricao"), rs.getLong("idMarca"));
            modelos.add(modelo);
        }

        return modelos;
    }
}