package org.com.br.Infra.Repository;

import org.com.br.Core.Domain.Models.Marca;
import org.com.br.Infra.Configuration.DbConnection;
import org.com.br.Infra.Interfaces.IMarca;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MarcaRepository implements IMarca {

    private Connection connection = null;
    public MarcaRepository()throws Exception{
        connection = DbConnection.getConnection();
        if(connection == null) throw new Exception("ERRO DE CONEXAO");
    }
    @Override
    public void createMarca(Marca marca) throws Exception {
        try {
            String sql =  "insert into Marca(descricao)"
                    +     "values(?);";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, marca.getDescricao());
            preparedStatement.executeUpdate();
        } catch (SQLException erro) {
            //Erro do comando SQL - chave, coluna, nome da tabela, ...
            throw new Exception("SQL Erro: "+ erro.getMessage());
        } catch(Exception erro){
            throw new Exception("Incluir Persistencia: " + erro);
        }
    }

    @Override
    public Marca getMarcaById(Long id) throws Exception {
        String sql = "select * from Marca where idMarca = " + id;
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(sql);
        rs.next();
        Marca marca = new Marca(rs.getLong("idMarca"),rs.getString("descrição"));
        return marca;
    }

    @Override
    public List<Marca> getMarcas() throws Exception {

        String sql = "select * from Marca";
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(sql);
        List<Marca> marcas = new ArrayList<Marca>();

        while(rs.next()){
            Marca marca = new Marca(rs.getLong("idMarca"), rs.getString("descricao"));
            marcas.add(marca);
        }

        return marcas;
    }

    
        @Override
        public int getMarcaId(Marca marca) throws Exception {
        int id = -1;  // Valor padrão para caso não encontre a marca
        try {
            String sql = "SELECT idMarca FROM Marca WHERE descricao = ?";  // Usei idMarca no SELECT
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, marca.getDescricao());  // Parâmetro de entrada para o nome da marca
            
            ResultSet resultSet = preparedStatement.executeQuery();  // Execute a consulta SQL
        
            if (resultSet.next()) {  // Verifica se há algum resultado na consulta
                id = resultSet.getInt("idMarca");  // Obtém o id da marca
            }
        } catch (SQLException erro) {
            throw new Exception("SQL Erro: " + erro.getMessage());
        } catch (Exception erro) {
            throw new Exception("Erro ao obter ID da Marca: " + erro);
        }
        return id;  // Retorna o ID encontrado ou -1 caso não encontre
    }
    

    @Override
    public void updateMarca(Marca marca) throws Exception {
     try{
        String sql = "update Marca  Set descricao = ? where idMarca = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, marca.getDescricao());
        preparedStatement.setLong(2, marca.getIdMarca());
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



