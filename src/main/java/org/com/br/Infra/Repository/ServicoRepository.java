package org.com.br.Infra.Repository;

import org.com.br.Core.Domain.Models.Servico;
import org.com.br.Infra.Configuration.DbConnection;
import org.com.br.Infra.Interfaces.IServico;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServicoRepository implements IServico {

    private Connection connection = null;
    public ServicoRepository()throws Exception{
        connection = DbConnection.getConnection();
        if(connection == null) throw new Exception("ERRO DE CONEXAO");
    }
    @Override
    public void createServico(Servico servico) throws Exception {
        try {
            String sql =  "insert into Serviço(descricao, valorUnitario)"
                    +     "values(? , ?);";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, servico.getDescricao());
            preparedStatement.setDouble(2, servico.getValorUnitario());
            preparedStatement.executeUpdate();
        } catch (SQLException erro) {
            //Erro do comando SQL - chave, coluna, nome da tabela, ...
            throw new Exception("SQL Erro: "+ erro.getMessage());
        } catch(Exception erro){
            throw new Exception("Incluir Persistencia: " + erro);
        }
    }

    @Override
    public Servico getServicoById(long id) throws Exception {
        String sql = "select * from Serviço where idServico = " + id;
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(sql);
        rs.next();
        Servico servico = new Servico(rs.getLong("idServico"), rs.getString("descricao"), rs.getDouble("valorUnitario"));
        return servico;
    }

    @Override
    public List<Servico> getServicos() throws Exception {

        String sql = "select * from Serviço";
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(sql);
        List<Servico> servicos = new ArrayList<Servico>();

        while(rs.next()){
            Servico servico = new Servico(rs.getLong("idServico"), rs.getString("descricao"), rs.getDouble("valorUnitario"));
            servicos.add(servico);
        }

        return servicos;
    }

    @Override
    public void updateServico(Servico servico) throws Exception {
     try{
        String sql = "update Serviço  Set descricao = ?, valorUnitario = ? where idServico = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, servico.getDescricao());
        preparedStatement.setDouble(2, servico.getValorUnitario());
        preparedStatement.setLong(3, servico.getIdServico());
        preparedStatement.executeUpdate();

        } catch (SQLException erro) {
         //Erro do comando SQL - chave, coluna, nome da tabela, ...
             throw new Exception("SQL Erro: "+ erro.getMessage());
        } catch(Exception erro){
             throw new Exception("Incluir Persistencia: " + erro);
        }
}

    @Override
    public void deleteServico(long id) throws Exception {

        try {
            String sql = "delete from Serviço where idServico = " + id;
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
    public Servico getServicoByDescricao(String decricao) throws Exception {
        String sql = "select * from Serviço where descricao = ?" ;
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, decricao);
        ResultSet rs = preparedStatement.executeQuery();
        rs.next();
        Servico servico = new Servico(rs.getLong("idServico"), rs.getString("descricao"), rs.getDouble("valorUnitario"));
        return servico;
    }
}
