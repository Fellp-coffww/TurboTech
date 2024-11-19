package org.com.br.Modelo;

import org.com.br.SGBD.DbConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ModeloService implements ModeloRepository{

    private Connection connection = null;
    public ModeloService()throws Exception{
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
            preparedStatement.setString(1, modelo.getIdMarca());
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
        Modelo modelo = new Modelo(rs.getLong("idModelo"), rs.getString("descricao"));
        return marca;
    }

    @Override
    public List<Modelo> getMModelo() throws Exception {

        String sql = "select * from Modelo";
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(sql);
        List<Modelo> modelos = new ArrayList<Modelo>();

        while(rs.next()){
            Modelo modelo = new Modelo(rs.getLong("idMarca"), rs.getString("descricao"));
            marcas.add(modelo);
        }

        return modelos;
    }

    @Override
    public void updateModelo(Modelo modelo) throws Exception {
     try{
        String sql = "update Modelo  Set descricao = ?,idMarca = ? where idModelo = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, modelo.getDescricao());
        preparedStatement.setString(2, modelo.getIdMarca());
        preparedStatement.setLong(3, modelo.getIdMarca());
        preparedStatement.executeUpdate();

        } catch (SQLException erro) {
         //Erro do comando SQL - chave, coluna, nome da tabela, ...
             throw new Exception("SQL Erro: "+ erro.getMessage());
        } catch(Exception erro){
             throw new Exception("Incluir Persistencia: " + erro);
        }
}

    @Override
    public void deleteMarca(Long id) throws Exception {

        try {
            String sql = "delete from Marca where idMarca = " + id;
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