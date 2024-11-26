package org.com.br.Infra.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.com.br.Core.Domain.Models.Propriedade;
import org.com.br.Infra.Configuration.DbConnection;
import org.com.br.Infra.Interfaces.IPropriedade;

public class PropriedadeRepository implements IPropriedade {

    private Connection connection = null;
    public PropriedadeRepository()throws Exception{
        connection = DbConnection.getConnection();
        if(connection == null) throw new Exception("ERRO DE CONEXAO");
    }

    @Override
    public void createPropriedade(Propriedade propriedade) throws Exception {
        try {
            String sql =  "insert into Propriedade(dataInicio, dataFim, idCliente, placa)"
                    +     "values(?,?,?,?);";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,propriedade.getDataInicio());
            preparedStatement.setInt(2, propriedade.getDataFim());
            preparedStatement.setLong(3, propriedade.getIdCliente());
            preparedStatement.setString(4, propriedade.getPlaca());
            preparedStatement.executeUpdate();
        } catch (SQLException erro) {
            //Erro do comando SQL - chave, coluna, nome da tabela, ...
            throw new Exception("SQL Erro: "+ erro.getMessage());
        } catch(Exception erro){
            throw new Exception("Incluir Persistencia: " + erro);
        }
    }

    @Override
    public Propriedade getPropriedadeById(Long id) throws Exception {
        String sql = "select * from Propriedade where idPropriedade = " + id;
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(sql);
        rs.next();
        Propriedade propriedade = new Propriedade(rs.getLong("idPropriedade"), rs.getInt("dataInicio"), rs.getInt("dataFim"), rs.getLong("idCliente"), rs.getString("placa"));
        return propriedade;
    }

    @Override
    public List<Propriedade> getPropriedade() throws Exception {
        String sql = "select * from Propriedade";
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(sql);
        List<Propriedade> propriedades = new ArrayList<Propriedade>();

        while(rs.next()){
            Propriedade propriedade = new Propriedade(rs.getLong("idPropriedade"), rs.getInt("dataInicio"), rs.getInt("dataFim"), rs.getLong("idCliente"), rs.getString("placa"));
            propriedades.add(propriedade);
        }

        return propriedades;
    }

    @Override
    public void updatePropriedade(Propriedade propriedade) throws Exception {
        try{
            String sql = "update Propriedade  Set dataInicio = ?, dataFim = ?, idCliente = ?, placa = ? where idPropriedade = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,propriedade.getDataInicio() );
            preparedStatement.setInt(2, propriedade.getDataFim());
            preparedStatement.setLong(3, propriedade.getIdCliente());
            preparedStatement.setString(4, propriedade.getPlaca());
            preparedStatement.executeUpdate();
    
            } catch (SQLException erro) {
             //Erro do comando SQL - chave, coluna, nome da tabela, ...
                 throw new Exception("SQL Erro: "+ erro.getMessage());
            } catch(Exception erro){
                 throw new Exception("Incluir Persistencia: " + erro);
            }
    }

    @Override
    public void deletePropriedade(Long id) throws Exception {
        try {
            String sql = "delete from Propriedade where idPropriedade = " + id;
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
