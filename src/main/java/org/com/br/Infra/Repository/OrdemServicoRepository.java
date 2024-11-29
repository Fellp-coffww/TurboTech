package org.com.br.Infra.Repository;

import org.com.br.Core.Domain.Models.OrdemServico;
import org.com.br.Infra.Configuration.DbConnection;
import org.com.br.Infra.Interfaces.IOrdemServico;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrdemServicoRepository implements IOrdemServico {

    private Connection connection = null;
    public OrdemServicoRepository()throws Exception{
        connection = DbConnection.getConnection();
        if(connection == null) throw new Exception("ERRO DE CONEXAO");
    }
    @Override
    public void createOrdemServico(OrdemServico ordemServico) throws Exception {
        try {
            String sql =  "insert into OrdemServico(dataos,statusos,precoTotal,precoPago, placa)"
                    +     "values(?,?,?,?,?);";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setDate(1, ordemServico.getData());
            preparedStatement.setString(2, ordemServico.getStatusOS());
            preparedStatement.setDouble(3, ordemServico.getPrecoTotal());
            preparedStatement.setDouble(4, ordemServico.getPrecoPago());
            preparedStatement.setString(5, ordemServico.getPlaca());
            preparedStatement.executeUpdate();
        } catch (SQLException erro) {
            //Erro do comando SQL - chave, coluna, nome da tabela, ...
            throw new Exception("SQL Erro: "+ erro.getMessage());
        } catch(Exception erro){
            throw new Exception("Incluir Persistencia: " + erro);
        }
    }

    @Override
    public OrdemServico getOrdemServicoById(long id) throws Exception {
        String sql = "select * from OrdemServico where idos = " + id;
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(sql);
        rs.next();
        OrdemServico ordemServico = new OrdemServico(rs.getLong("idos"), rs.getString("StatusOS"),rs.getDate("dataos"), rs.getDouble("precoTotal"), rs.getDouble("precoPago"), rs.getString("placa"));
        return ordemServico;
    }

    @Override
    public List<OrdemServico> getOrdemServico() throws Exception {

        String sql = "select * from OrdemServico";
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(sql);
        List<OrdemServico> ordensServicos = new ArrayList<OrdemServico>();

        while(rs.next()){
            OrdemServico ordemServico = new OrdemServico(rs.getLong("idos"), rs.getString("StatusOS"),rs.getDate("dataos"), rs.getDouble("precoTotal"), rs.getDouble("precoPago"), rs.getString("placa"));
            ordensServicos.add(ordemServico);
        }

        return ordensServicos;
    }

    @Override
    public void updateOrdemServico(OrdemServico ordemServico) throws Exception {
     try{
        String sql = "update OrdemServico  Set dataos = ?,statusos = ?,precoTotal = ?,precopago = ? where idos = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setDate(1, ordemServico.getData());
        preparedStatement.setString(2, ordemServico.getStatusOS());
        preparedStatement.setDouble(3, ordemServico.getPrecoTotal());
        preparedStatement.setDouble(4, ordemServico.getPrecoPago());
        preparedStatement.setLong(5, ordemServico.getIdOrdemServico());
        preparedStatement.executeUpdate();

        } catch (SQLException erro) {
         //Erro do comando SQL - chave, coluna, nome da tabela, ...
             throw new Exception("SQL Erro: "+ erro.getMessage());
        } catch(Exception erro){
             throw new Exception("Incluir Persistencia: " + erro);
        }
}

    @Override
    public void deleteOrdemServico(long id) throws Exception {

        try {
            String sql = "delete from OrdemServico where idos = " + id;
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
    public List<OrdemServico> getOrdemServicoByState() throws Exception {
        // SQL com filtro para status diferente de "Paga"
        String sql = "SELECT * FROM OrdemServico WHERE StatusOS != 'Paga'";

        // Criação do statement e execução da consulta
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(sql);

        // Lista para armazenar as ordens de serviço
        List<OrdemServico> ordensServicos = new ArrayList<OrdemServico>();

        // Iterando pelo resultado
        while (rs.next()) {
            // Criando um objeto OrdemServico para cada linha do ResultSet
            OrdemServico ordemServico = new OrdemServico(
                    rs.getLong("idos"), // ID da ordem de serviço
                    rs.getString("StatusOS"), // Status da ordem de serviço
                    rs.getDate("dataos"), // Data da ordem de serviço
                    rs.getDouble("precoTotal"), // Preço total
                    rs.getDouble("precoPago"), // Preço pago
                    rs.getString("placa") // Placa do veículo
            );

            // Adicionando o objeto à lista
            ordensServicos.add(ordemServico);
        }

        // Retornando a lista com as ordens de serviço que têm status diferente de "Paga"
        return ordensServicos;
    }

}