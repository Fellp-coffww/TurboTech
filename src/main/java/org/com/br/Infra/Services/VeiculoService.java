package org.com.br.Infra.Services;

import org.com.br.Core.Domain.Models.Veiculo;
import org.com.br.Infra.Configuration.DbConnection;
import org.com.br.Infra.Repositories.VeiculoRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VeiculoService implements VeiculoRepository {

    private Connection connection = null;
    public VeiculoService()throws Exception{
        connection = DbConnection.getConnection();
        if(connection == null) throw new Exception("ERRO DE CONEXAO");
    }
    @Override
    public void createVeiculo(Veiculo veiculo) throws Exception{
        try {
            String sql =  "insert into Veiculo(placa,chassi,kilometragem,numpatrimonio,ano, idmodelo)"
                    +     "values(?,?,?,?,?,?);";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, veiculo.getPlaca());
            preparedStatement.setString(2, veiculo.getChassi());
            preparedStatement.setString(3, veiculo.getKilometragem());
            preparedStatement.setLong(4, veiculo.getnPropriedade());
            preparedStatement.setLong(5, veiculo.getAno());
            preparedStatement.setLong(6, veiculo.getIdModelo());
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
        String sql = "select * from Veiculo where placa = ? ";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, placa);
        ResultSet rs = statement.executeQuery();
        rs.next();
        Veiculo veiculo = new Veiculo(rs.getString("placa"),rs.getString("chassi"), rs.getString("Kilometragem"), rs.getLong("idModelo"), rs.getInt("ano"), rs.getInt("numPatrimonio"));
        return veiculo;
    }

    public List<Veiculo> getVeiculo() throws Exception{
        String sql = "select * from Veiculo";
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(sql);
        List<Veiculo> veiculos = new ArrayList<Veiculo>();

        while(rs.next()){
            Veiculo veiculo = new Veiculo(rs.getString("placa"),rs.getString("chassi"), rs.getString("Kilometragem"), rs.getLong("idModelo"), rs.getInt("ano"), rs.getInt("numPatrimonio"));
            veiculos.add(veiculo);
        }

        return veiculos;
    }
    @Override
    public void updateVeiculo(Veiculo veiculo) throws Exception{

        try{
            String sql = "update Veiculo  Set chassi = ?,kilometragem = ?, numpatrimonio = ?,ano = ?,idmodelo= ? where placa = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, veiculo.getPlaca());
            preparedStatement.setString(2, veiculo.getChassi());
            preparedStatement.setString(3, veiculo.getKilometragem());
            preparedStatement.setLong(4, veiculo.getnPropriedade());
            preparedStatement.setLong(5, veiculo.getAno());
            preparedStatement.setLong(6, veiculo.getIdModelo());
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
            String sql = "delete from Veiculo where placa = ? ";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, placa);
            preparedStatement.executeUpdate();
        } catch (SQLException erro) {
            //Erro do comando SQL - chave, coluna, nome da tabela, ...
            throw new Exception("SQL Erro: "+ erro.getMessage());
        } catch(Exception erro){
            throw new Exception("Incluir Persistencia: " + erro);
        }
    }

}
