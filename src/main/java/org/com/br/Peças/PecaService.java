package org.com.br.Peca;

import org.com.br.SGBD.DbConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PecaService implements PecaRepository{

    private Connection connection = null;
    public PecaService()throws Exception{
        connection = DbConnection.getConnection();
        if(connection == null) throw new Exception("ERRO DE CONEXAO");
    }
    @Override
    public void createPeca(Peca peca) throws Exception {
        try {
            String sql =  "insert into Peca(descricao,quantidade,valorUnitario,codigo)"
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
    public Peca getPecaByIdById(Long id) throws Exception {
        String sql = "select * from Peca where idModelo = " + id;
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(sql);
        rs.next();
        Peca peca = new Peca(rs.getLong("idPeca"), rs.getString("descricao"), rs.getInt("quantidade"), rs.getDouble("valorUnitario"), rs.getString("codigo"));
        return peca;
    }

    @Override
    public List<Modelo> getPeca() throws Exception {

        String sql = "select * from Peca";
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(sql);
        List<Peca> pecas = new ArrayList<Peca>();

        while(rs.next()){
            Peca peca = new Peca(rs.getLong("idPeca"), rs.getString("descricao"), rs.getInt("quantidade"), rs.getDouble("valorUnitario"), rs.getString("codigo"));
            Peca.add(peca);
        }

        return pecas;
    }

    @Override
    public void updatePeca(Peca peca) throws Exception {
     try{
        String sql = "update Peca  Set descricao = ?,quantidade = ?,valorUnitario = ?,codigo = ? where idPeca = ?";
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
    public void deletePeca(Long id) throws Exception {

        try {
            String sql = "delete from Peca where idPeca = " + id;
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

        String sql = "select * from Peca where column descricao = " + descricao;
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(sql);
        List<Peca> pecas = new ArrayList<Peca>();

        while(rs.next()){
            Peca peca = new Peca(rs.getLong("idPeca"), rs.getString("descricao"), rs.getInt("quantidade"), rs.getDouble("valorUnitario"), rs.getString("codigo"));
            pecas.add(peca);
        }

        return pecas;
    }

    @Override
    public List<Peca> getPecaBycodigo(String codigo) throws Exception {

        String sql = "select * from Peca where column descricao = " + codigo;
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(sql);
        List<Peca> pecas = new ArrayList<Peca>();

        while(rs.next()){
            Peca peca = new Peca(rs.getLong("idPeca"), rs.getString("descricao"), rs.getInt("quantidade"), rs.getDouble("valorUnitario"), rs.getString("codigo"));
            pecas.add(peca);
        }

        return pecas;
    }
}