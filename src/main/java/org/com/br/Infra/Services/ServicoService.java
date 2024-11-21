package org.com.br.Infra.Services;

import org.com.br.Core.Domain.Models.Servico;
import org.com.br.Infra.Configuration.DbConnection;
import org.com.br.Infra.Repositories.ServicoRepository;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServicoService implements ServicoRepository {

    private Connection connection = null;
    public ServicoService()throws Exception{
        connection = DbConnection.getConnection();
        if(connection == null) throw new Exception("ERRO DE CONEXAO");
    }
    @Override
    public void createServico(Servico servico) throws Exception {
        try {
            String sql =  "insert into Servico(idServico, descricao, valorUnitario)"
                    +     "values(? , ? , ?);";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, servico.getIdServico());
            preparedStatement.setString(2, servico.getDescricao());
            preparedStatement.setDouble(3, servico.getValorUnitario());
            preparedStatement.executeUpdate();
        } catch (SQLException erro) {
            //Erro do comando SQL - chave, coluna, nome da tabela, ...
            throw new Exception("SQL Erro: "+ erro.getMessage());
        } catch(Exception erro){
            throw new Exception("Incluir Persistencia: " + erro);
        }
    }

    @Override
    public Servico getServicoById(Long id) throws Exception {
        String sql = "select * from Servico where idServico = " + id;
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(sql);
        rs.next();
        Servico servico = new Servico(rs.getLong("idServico"), rs.getString("descricao"), rs.getDouble("valorUnitario"));
        return servico;
    }

    @Override
    public List<Servico> getServicos() throws Exception {

        String sql = "select * from Servico";
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
        String sql = "update Servico  Set descricao = ?, valorUnitario = ? where idServico = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, servico.getDescricao());
        preparedStatement.setLong(3, servico.getIdServico());
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
    public void deleteServico(Long id) throws Exception {

        try {
            String sql = "delete from Servico where idServico = " + id;
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
