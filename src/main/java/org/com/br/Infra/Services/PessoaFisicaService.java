package org.com.br.Infra.Services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.com.br.Core.Domain.Models.PessoaFisica;
import org.com.br.Core.Domain.Rules.ValidaCPF;
import org.com.br.Infra.Configuration.DbConnection;
import org.com.br.Infra.Repositories.PessoaFisicaRepository;

public class PessoaFisicaService implements PessoaFisicaRepository {

    private Connection connection = null;

    public PessoaFisicaService() throws Exception {
        connection = DbConnection.getConnection();
        if (connection == null)
            throw new Exception("ERRO DE CONEXAO");
    }

    @Override
    public void createPessoaFisica(PessoaFisica pessoaFisica) throws Exception {
        if(ValidaCPF.isCPFValid(pessoaFisica.getCpf())){
        try {
            String sql = "INSERT INTO PessoaFisica (cpf, nome, email, ddi1, ddd1, numero1, ddi2, ddd2, numero2, logradouro, complemento, numeroEnd)"
                    + " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, pessoaFisica.getCpf());
            preparedStatement.setString(2, pessoaFisica.getNome());
            preparedStatement.setString(3, pessoaFisica.getEmail());
            preparedStatement.setString(4, pessoaFisica.getDdi1());
            preparedStatement.setString(5, pessoaFisica.getDdd1());
            preparedStatement.setString(6, pessoaFisica.getTelefone1());
            preparedStatement.setString(7, pessoaFisica.getDdi2());
            preparedStatement.setString(8, pessoaFisica.getDdd2());
            preparedStatement.setString(9, pessoaFisica.getTelefone2());
            preparedStatement.setString(10, pessoaFisica.getLogradouro());
            preparedStatement.setString(11, pessoaFisica.getComplemento());
            preparedStatement.setString(12, pessoaFisica.getNumeroEnd());
            preparedStatement.executeUpdate();
        } catch (SQLException erro) {
            throw new Exception("SQL Erro: " + erro.getMessage());
        } catch (Exception erro) {
            throw new Exception("Erro ao inserir: " + erro);
        }
        }
    }

    @Override
    public PessoaFisica getPessoaFisicaByCpf(String cpf) throws Exception {
        String sql = "SELECT * FROM PessoaFisica WHERE cpf = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, cpf);
        ResultSet rs = preparedStatement.executeQuery();

        if (rs.next()) {
            return new PessoaFisica(
                    rs.getString("cpf"),
                    rs.getString("nome"),
                    rs.getString("email"),
                    rs.getString("ddi1"),
                    rs.getString("ddd1"),
                    rs.getString("numero1"),
                    rs.getString("ddi2"),
                    rs.getString("ddd2"),
                    rs.getString("numero2"),
                    rs.getString("logradouro"),
                    rs.getString("complemento"),
                    rs.getString("numeroEnd")
            );
        }
        return null; // Retorna null se o CPF não for encontrado
    }

    @Override
    public List<PessoaFisica> getPessoaFisica() throws Exception {
        String sql = "SELECT * FROM PessoaFisica";
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(sql);
        List<PessoaFisica> pessoasFisicas = new ArrayList<>();

        while (rs.next()) {
            PessoaFisica pessoaFisica = new PessoaFisica(
                    rs.getString("cpf"),
                    rs.getString("nome"),
                    rs.getString("email"),
                    rs.getString("ddi1"),
                    rs.getString("ddd1"),
                    rs.getString("numero1"),
                    rs.getString("ddi2"),
                    rs.getString("ddd2"),
                    rs.getString("numero2"),
                    rs.getString("logradouro"),
                    rs.getString("complemento"),
                    rs.getString("numeroEnd")
            );
            pessoasFisicas.add(pessoaFisica);
        }

        return pessoasFisicas;
    }

    @Override
    public void updatePessoaFisica(PessoaFisica pessoaFisica) throws Exception {
        try {
            String sql = "UPDATE PessoaFisica SET nome = ?, email = ?, ddi1 = ?, ddd1 = ?, numero1 = ?, ddi2 = ?, ddd2 = ?, numero2 = ?, logradouro = ?, complemento = ?, numeroEnd = ? WHERE cpf = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, pessoaFisica.getNome());
            preparedStatement.setString(2, pessoaFisica.getEmail());
            preparedStatement.setString(3, pessoaFisica.getDdi1());
            preparedStatement.setString(4, pessoaFisica.getDdd1());
            preparedStatement.setString(5, pessoaFisica.getTelefone1());
            preparedStatement.setString(6, pessoaFisica.getDdi2());
            preparedStatement.setString(7, pessoaFisica.getDdd2());
            preparedStatement.setString(8, pessoaFisica.getTelefone2());
            preparedStatement.setString(9, pessoaFisica.getLogradouro());
            preparedStatement.setString(10, pessoaFisica.getComplemento());
            preparedStatement.setString(11, pessoaFisica.getNumeroEnd());
            preparedStatement.setString(12, pessoaFisica.getCpf());
            preparedStatement.executeUpdate();
        } catch (SQLException erro) {
            throw new Exception("SQL Erro: " + erro.getMessage());
        } catch (Exception erro) {
            throw new Exception("Erro ao atualizar: " + erro);
        }
    }

    @Override
    public void deletePessoaFisica(String cpf) throws Exception {
        try {
            String sql = "DELETE FROM PessoaFisica WHERE cpf = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, cpf);
            preparedStatement.executeUpdate();
        } catch (SQLException erro) {
            throw new Exception("SQL Erro: " + erro.getMessage());
        } catch (Exception erro) {
            throw new Exception("Erro ao deletar: " + erro);
        }
    }
}
