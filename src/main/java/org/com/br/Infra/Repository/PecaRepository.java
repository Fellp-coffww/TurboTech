package org.com.br.Infra.Repository;


import org.com.br.Core.Domain.Models.Peca;
import org.com.br.Infra.Configuration.DbConnection;
import org.com.br.Infra.Interfaces.IPeca;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PecaRepository implements IPeca {

    private Connection connection = null;
    public PecaRepository()throws Exception{
        connection = DbConnection.getConnection();
        if(connection == null) throw new Exception("ERRO DE CONEXAO");
    }
    @Override
    public void createPeca(Peca peca) throws Exception {
        try {
            String sql =  "insert into Pecas(descricao,quantidade,valorUnitario,codigo)"
                    +     "values(?,?,?,?);";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, peca.getDescricao());
            preparedStatement.setInt(2, peca.getQuantidade());
            preparedStatement.setDouble(3, peca.getValorUnitario());
            preparedStatement.setString(4, peca.getCodigo());
            preparedStatement.executeUpdate();
        } catch (SQLException erro) {
            //Erro do comando SQL - chave, coluna, nome da tabela, ...
            throw new Exception("SQL Erro: "+ erro.getMessage());
        } catch(Exception erro){
            throw new Exception("Incluir Persistencia: " + erro);
        }
    }


    @Override
    public Peca getPecaById(long id) throws Exception {
        String sql = "select * from pecas where idpecas =" + id;
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(sql);
        rs.next();
        Peca peca = new Peca(rs.getLong("idPecas"), rs.getString("descricao"), rs.getInt("quantidade"), rs.getDouble("valorUnitario"), rs.getString("codigo"));
        return peca;
    }

    @Override
    public List<Peca> getPecas() throws Exception {
        String sql = "select * from Pecas";
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(sql);
        List<Peca> pecas = new ArrayList<Peca>();

        while(rs.next()){
            Peca peca = new Peca(rs.getLong("idPecas"), rs.getString("descricao"), rs.getInt("quantidade"), rs.getDouble("valorUnitario"), rs.getString("codigo"));
            pecas.add(peca);
        }

        return pecas;
    }

    @Override
    public void updatePeca(Peca peca) throws Exception {
     try{
        String sql = "update Pecas  Set descricao = ?,quantidade = ?,valorUnitario = ?,codigo = ? where idPeca = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, peca.getDescricao());
        preparedStatement.setInt(2, peca.getQuantidade());
        preparedStatement.setDouble(3, peca.getValorUnitario());
        preparedStatement.setString(4, peca.getCodigo());
        preparedStatement.executeUpdate();

        } catch (SQLException erro) {
         //Erro do comando SQL - chave, coluna, nome da tabela, ...
             throw new Exception("SQL Erro: "+ erro.getMessage());
        } catch(Exception erro){
             throw new Exception("Incluir Persistencia: " + erro);
        }
}

    @Override
    public void deletePeca(long id) throws Exception {

        try {
            String sql = "delete from Pecas where idPecas = " + id;
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
    public List<Peca> getPecaBydescricao(String descricao) throws Exception {

        String sql = "select * from Pecas where column descricao = " + descricao;
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(sql);
        List<Peca> pecas = new ArrayList<Peca>();

        while(rs.next()){
            Peca peca = new Peca(rs.getLong("idPecas"), rs.getString("descricao"), rs.getInt("quantidade"), rs.getDouble("valorUnitario"), rs.getString("codigo"));
            pecas.add(peca);
        }

        return pecas;
    }

    @Override
    public List<Peca> getPecaBycodigo(String codigo) throws Exception {

        String sql = "select * from Pecas where  codigo = " + codigo;
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(sql);
        List<Peca> pecas = new ArrayList<Peca>();

        while(rs.next()){
            Peca peca = new Peca(rs.getLong("idPecas"), rs.getString("descricao"), rs.getInt("quantidade"), rs.getDouble("valorUnitario"), rs.getString("codigo"));
            pecas.add(peca);
        }

        return pecas;
    }
}