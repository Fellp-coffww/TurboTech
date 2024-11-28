package org.com.br.Infra.Repository;

import org.com.br.Core.Domain.Models.ItemPeca;
import org.com.br.Infra.Configuration.DbConnection;
import org.com.br.Infra.Interfaces.IItemPeca;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ItemPecaRepository implements IItemPeca {

    private Connection connection = null;
    public ItemPecaRepository()throws Exception{
        connection = DbConnection.getConnection();
        if(connection == null) throw new Exception("ERRO DE CONEXAO");
    }
    @Override
    public void createItemPeca(ItemPeca itemPeca) throws Exception {
        try {
            String sql =  "insert into itempeca(idos,idPeca,quantidade,valorUnitario,valorTotal)"
                    +     "values(?,?,?,?,?);";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, itemPeca.getIdOrdemServico());
            preparedStatement.setLong(2, itemPeca.getIdPeca());
            preparedStatement.setInt(3, itemPeca.getQuantidade());
            preparedStatement.setDouble(4, itemPeca.getValorUnitario());
            preparedStatement.setDouble(5, itemPeca.getValorTotal());
            preparedStatement.executeUpdate();
        } catch (SQLException erro) {
            //Erro do comando SQL - chave, coluna, nome da tabela, ...
            throw new Exception("SQL Erro: "+ erro.getMessage());
        } catch(Exception erro){
            throw new Exception("Incluir Persistencia: " + erro);
        }
    }

    @Override
    public ItemPeca getItemPecaById(long id) throws Exception {
        String sql = "select * from ItemPeca where idItemPeca = " + id;
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(sql);
        rs.next();
        ItemPeca itemPeca = new ItemPeca(rs.getLong("idItemPeca"),rs.getLong("idOs"), rs.getLong("IdPeca"), rs.getInt("quantidade"), rs.getDouble("valorTotal"), rs.getDouble("ValorUnitario"));
        return itemPeca;
    }

    @Override
    public List<ItemPeca> getItemPeca() throws Exception {

        String sql = "select * from ItemPeca";
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(sql);
        List<ItemPeca> itemPecas = new ArrayList<ItemPeca>();

        while(rs.next()){
            ItemPeca itemPeca = new ItemPeca(rs.getLong("idItemPeca"),rs.getLong("idOs"), rs.getLong("IdPeca"), rs.getInt("quantidade"), rs.getDouble("valorTotal"), rs.getDouble("ValorUnitario"));
            itemPecas.add(itemPeca);
        }

        return itemPecas;
    }

    @Override
    public void updateItemPeca(ItemPeca itemPeca) throws Exception {
     try{
        String sql = "update ItemPeca  Set idOrdemServico = ?,idPeca = ?,quantidade = ?,valorUnitario = ?,valorTotal = ? where idItemPeca = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setLong(1, itemPeca.getIdOrdemServico());
        preparedStatement.setLong(2, itemPeca.getIdPeca());
        preparedStatement.setInt(3, itemPeca.getQuantidade());
        preparedStatement.setDouble(4, itemPeca.getValorUnitario());
        preparedStatement.setDouble(5, itemPeca.getValorTotal());
        preparedStatement.setLong(6, itemPeca.getIdItemPeca());
        preparedStatement.executeUpdate();

    } catch (SQLException erro) {
        //Erro do comando SQL - chave, coluna, nome da tabela, ...
            throw new Exception("SQL Erro: "+ erro.getMessage());
       } catch(Exception erro){
            throw new Exception("Incluir Persistencia: " + erro);
       }
}

    @Override
    public void deleteItemPeca(long id) throws Exception {

        try {
            String sql = "delete from ItemPeca where idItemPeca = " + id;
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
    public List<ItemPeca> getItemPecaByOrdemServicoId(long id) throws Exception {

        String sql = "select * from ItemPeca where idos = " + id;
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(sql);
        List<ItemPeca> itemPecas = new ArrayList<ItemPeca>();

        while(rs.next()){
            ItemPeca itemPeca = new ItemPeca(rs.getLong("idItemPeca"),rs.getLong("idOs"), rs.getLong("IdPeca"), rs.getInt("quantidade"), rs.getDouble("valorTotal"), rs.getDouble("ValorUnitario"));
            itemPecas.add(itemPeca);
        }

        return itemPecas;



    }


}
