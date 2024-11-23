package org.com.br.Propriedade;

import org.com.br.SGBD.DbConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PropriedadeService implements PropriedadeRepository{

    private Connection connection = null;
    public PropriedadeService()throws Exception{
        connection = DbConnection.getConnection();
        if(connection == null) throw new Exception("ERRO DE CONEXAO");
    }
    @Override
    public void createPropriedade(Propriedade propriedade) throws Exception {
        try {
            String sql =  "insert into Propriedade(dataInicio, dataFim, idCliente, placa)"
                    +     "values(?,?,?,?);";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setDate(1, propriedade.getdataInicio());
            preparedStatement.setDate(2, propriedade.getdataFim());
            preparedStatement.setLong(3, propriedade.getidCliente());
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
    public Modelo getPessoaPropriedadeById(Long id) throws Exception {
        String sql = "select * from Propriedade where idPropriedade = " + id;
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(sql);
        rs.next();
        Propriedade propriedade = new Propriedade(rs.getDate("dataInicio"), rs.getDate("dataFim"), rs.getLong("idCliente"), rs.getString("placa"));
        return propriedade;
    }

    @Override
    public List<Propriedade> getPropriedade() throws Exception {

        String sql = "select * from Propriedade";
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(sql);
        List<Propriedade> propriedades = new ArrayList<Propriedade>();

        while(rs.next()){
            Propriedade propriedade = new Propriedade(rs.getDate("dataInicio"), rs.getDate("dataFim"), rs.getLong("idCliente"), rs.getString("placa"));
            propriedades.add(propriedade);
        }

        return propriedades;
    }

    @Override
    public void updatePropriedade(Propriedade propriedade) throws Exception {
     try{
        String sql = "update Propriedade  Set dataInico = ?,dataFim = ?,idCliente = ?, placa = ? where idPropriedade = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setDate(1, propriedade.getdataInicio());
        preparedStatement.setDate(2, propriedade.getdataFim());
        preparedStatement.setLong(3, propriedade.getidCliente());
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

    @Override
    public List<Propriedade> getPropriedadeByplaca(Long id) throws Exception {

        String sql = "select * from Propriedade where column idPropriedade = " + id;
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(sql);
        List<Propriedade> propriedades = new ArrayList<Propriedade>();

        while(rs.next()){
            Propriedade propriedade = new Modelo(rs.getDate("dataInicio"), rs.getDate("dataFim"), rs.getLong("idCliente"), rs.getString("placa"));
            propriedades.add(propriedade);
        }

        return propriedades;
    }
}