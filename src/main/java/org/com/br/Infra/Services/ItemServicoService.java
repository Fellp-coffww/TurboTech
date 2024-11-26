package org.com.br.Infra.Services;


import org.com.br.Core.Domain.Models.ItemServico;
import org.com.br.Infra.Configuration.DbConnection;
import org.com.br.Infra.Repositories.ItemServicoRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ItemServicoService implements ItemServicoRepository {

    private Connection connection = null;
    public ItemServicoService()throws Exception{
        connection = DbConnection.getConnection();
        if(connection == null) throw new Exception("ERRO DE CONEXAO");
    }
    @Override
    public void createItemServico (ItemServico itemServico) throws Exception {
        try {
            String sql =  "insert into ItemServico(idOs,idServico,cpf,quantidade,valorUnitario,valorTotal)"
                    +     "values(?,?,?,?,?,?);";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, itemServico.getIdOrdemServico());
            preparedStatement.setLong(2, itemServico.getIdServico());
            preparedStatement.setString(3, itemServico.getCpf());
            preparedStatement.setInt(4, itemServico.getQuantidade());
            preparedStatement.setDouble(5, itemServico.getValorUnitario());
            preparedStatement.setDouble(6, itemServico.getValorTotal());
            preparedStatement.executeUpdate();
        } catch (SQLException erro) {
            //Erro do comando SQL - chave, coluna, nome da tabela, ...
            throw new Exception("SQL Erro: "+ erro.getMessage());
        } catch(Exception erro){
            throw new Exception("Incluir Persistencia: " + erro);
        }
    }

    @Override
    public ItemServico getItemServicoById(long id) throws Exception {
        String sql = "select * from ItemServico where idItemServico = " + id;
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(sql);
        rs.next();
        ItemServico itemServico = new ItemServico(rs.getLong("idItemServico"),rs.getLong("idOs"), rs.getLong("idServico"), rs.getString("cpf"), rs.getInt("quantidade"), rs.getDouble("ValorUnitario"), rs.getDouble("valorTotal"));
        return itemServico;
    }

    @Override
    public List<ItemServico> getItemServico() throws Exception {

        String sql = "select * from ItemServico";
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(sql);
        List<ItemServico> itemServicos = new ArrayList<ItemServico>();

        while(rs.next()){
            ItemServico itemServico = new ItemServico(rs.getLong("idItemServico"),rs.getLong("idOs"), rs.getLong("idServico"), rs.getString("cpf"), rs.getInt("quantidade"), rs.getDouble("ValorUnitario"), rs.getDouble("valorTotal"));
            itemServicos.add(itemServico);
        }

        return itemServicos;
    }

    @Override
    public void updateItemServico(ItemServico itemServico) throws Exception {
        try{
            String sql = "update ItemServico  Set idOs = ?,idServico = ?, cpf = ?,quantidade = ?,valorUnitario = ?,valorTotal = ? where idItemServico = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, itemServico.getIdOrdemServico());
            preparedStatement.setLong(2, itemServico.getIdServico());
            preparedStatement.setString(3, itemServico.getCpf());
            preparedStatement.setInt(4, itemServico.getQuantidade());
            preparedStatement.setDouble(5, itemServico.getValorUnitario());
            preparedStatement.setDouble(6, itemServico.getValorTotal());
            preparedStatement.setLong(7, itemServico.getIdItemServico());
            preparedStatement.executeUpdate();

        } catch (SQLException erro) {
            //Erro do comando SQL - chave, coluna, nome da tabela, ...
            throw new Exception("SQL Erro: "+ erro.getMessage());
        } catch(Exception erro){
            throw new Exception("Incluir Persistencia: " + erro);
        }
    }

    @Override
    public void deleteItemServico(long id) throws Exception {

        try {
            String sql = "delete from ItemServico where idItemServico = " + id;
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
    public List<ItemServico> getItemServicoByOrdemServicoId(long id) throws Exception {
        return List.of();
    }


}
