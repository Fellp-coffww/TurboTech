package org.com.br.Infra.Services;

import org.com.br.Core.Domain.Models.Veiculo;
import org.com.br.Infra.Configuration.DbConnection;
import org.com.br.Infra.Repositories.VeiculoRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class veiculoService implements VeiculoRepository {

    private Connection connection = null;
    public veiculoService()throws Exception{
        connection = DbConnection.getConnection();
        if(connection == null) throw new Exception("ERRO DE CONEXAO");
    }
    @Override
    public void createVeiculo(Veiculo veiculo) throws Exception{
        try {
            String sql =  "insert into Acessorios(placa,chassi,kilometragem,modelo,propriedade,ano,numPropriedade)"
                    +     "values(?,?,?,?,?,?,?);";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, veiculo.getPlaca());
            preparedStatement.setString(2, veiculo.getChassi());
            preparedStatement.setString(3, veiculo.getKilometragem());
            preparedStatement.setLong(4, veiculo.getIdModelo());
            preparedStatement.setLong(5, veiculo.getIdPropriedade());
            preparedStatement.setInt(6, veiculo.getAno());
            preparedStatement.setInt(7, veiculo.getnPropriedade());
            preparedStatement.executeUpdate();
        } catch (SQLException erro) {
            //Erro do comando SQL - chave, coluna, nome da tabela, ...
            throw new Exception("SQL Erro: "+ erro.getMessage());
        } catch(Exception erro){
            throw new Exception("Incluir Persistencia: " + erro);
        }
    }
    @Override
    public Veiculo getVeiculoById(String placa) throws Exception{
        String sql = "select * from Veiculo where placa = " + placa;
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(sql);
        rs.next();
        Veiculo veiculo = new Veiculo(rs.getString("placa"),rs.getString("chassi"), rs.getString("Kilometragem"), rs.getLong("idModelo"), rs.getLong("idPropriedade"), rs.getInt("ano"), rs.getInt("nPropriedade"));
        return veiculo;
    }

    public List<Veiculo> getVeiculo() throws Exception{
        String sql = "select * from Veiculo";
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(sql);
        List<Veiculo> veiculos = new ArrayList<Veiculo>();

        while(rs.next()){
            Veiculo veiculo = new Veiculo(rs.getString("placa"),rs.getString("chassi"), rs.getString("Kilometragem"), rs.getLong("idModelo"), rs.getLong("idPropriedade"), rs.getInt("ano"), rs.getInt("nPropriedade"));
            veiculos.add(veiculo);
        }

        return veiculos;
    }
    @Override
    public void updateVeiculo(Veiculo veiculo) throws Exception{
        try{
            String sql = "update Veiculo  Set chassi = ?,kilometragem = ?,modelo = ?,propriedade = ?,ano = ?,numPropriedade = ? where placa = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, veiculo.getChassi());
            preparedStatement.setString(2, veiculo.getKilometragem());
            preparedStatement.setLong(3, veiculo.getIdModelo());
            preparedStatement.setLong(4, veiculo.getIdPropriedade());
            preparedStatement.setInt(5, veiculo.getAno());
            preparedStatement.setInt(6, veiculo.getnPropriedade());
            preparedStatement.setString(7, veiculo.getPlaca());
            preparedStatement.executeUpdate();
    
            } catch (SQLException erro) {
             //Erro do comando SQL - chave, coluna, nome da tabela, ...
                 throw new Exception("SQL Erro: "+ erro.getMessage());
            } catch(Exception erro){
                 throw new Exception("Incluir Persistencia: " + erro);
            }
    }
    @Override
    public void deleteVeiculo(String placa) throws Exception{
        try {
            String sql = "delete from Veiculo where placa = " + placa;
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
