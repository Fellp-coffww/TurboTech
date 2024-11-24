package org.com.br.Infra.Services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.com.br.Core.Domain.Models.PessoaJuridica;
import org.com.br.Infra.Configuration.DbConnection;
import org.com.br.Infra.Repositories.PessoaJuridicaRepository;

public class PessoaJuridicaService implements PessoaJuridicaRepository {

    private Connection connection;

    public PessoaJuridicaService() throws Exception {
        connection = DbConnection.getConnection();
        if (connection == null) throw new Exception("Erro de conexão");
    }

    @Override
    public void createPessoaJuridica(PessoaJuridica pessoaJuridica) throws Exception {
        try {
            String sql = "INSERT INTO PessoaJuridica (cnpj, nome, email, ddi1, ddd1, numero1, ddi2, ddd2, numero2, logradouro, complemento, numeroEnd, inscricaoEstadual, contato, razaoSocial) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, pessoaJuridica.getCnpj());
            preparedStatement.setString(2, pessoaJuridica.getNome());
            preparedStatement.setString(3, pessoaJuridica.getEmail());
            preparedStatement.setString(4, pessoaJuridica.getDdi1());
            preparedStatement.setString(5, pessoaJuridica.getDdd1());
            preparedStatement.setString(6, pessoaJuridica.getTelefone1());
            preparedStatement.setString(7, pessoaJuridica.getDdi2());
            preparedStatement.setString(8, pessoaJuridica.getDdd2());
            preparedStatement.setString(9, pessoaJuridica.getTelefone2());
            preparedStatement.setString(10, pessoaJuridica.getLogradouro());
            preparedStatement.setString(11, pessoaJuridica.getComplemento());
            preparedStatement.setString(12, pessoaJuridica.getNumeroEnd());
            preparedStatement.setString(13, pessoaJuridica.getInscricaoEstadual());
            preparedStatement.setString(14, pessoaJuridica.getContato());
            preparedStatement.setString(15, pessoaJuridica.getRazaoSocial());
            preparedStatement.executeUpdate();
        } catch (SQLException erro) {
            throw new Exception("Erro SQL: " + erro.getMessage());
        }
    }

    @Override
    public PessoaJuridica getPessoaJuridicaByCnpj(String cnpj) throws Exception {
        String sql = "SELECT * FROM PessoaJuridica WHERE cnpj = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, cnpj);
        ResultSet rs = preparedStatement.executeQuery();

        if (rs.next()) {
            return new PessoaJuridica(
                    rs.getString("cnpj"),
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
                    rs.getString("numeroEnd"),
                    rs.getString("inscricaoEstadual"),
                    rs.getString("contato"),
                    rs.getString("razaoSocial")
            );
        }
        return null; // Retorna null caso o CNPJ não seja encontrado
    }

    @Override
    public List<PessoaJuridica> getPessoaJuridica() throws Exception {
        String sql = "SELECT * FROM PessoaJuridica";
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(sql);
        List<PessoaJuridica> pessoasJuridicas = new ArrayList<>();

        while (rs.next()) {
            PessoaJuridica pessoaJuridica = new PessoaJuridica(
                    rs.getString("cnpj"),
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
                    rs.getString("numeroEnd"),
                    rs.getString("inscricaoEstadual"),
                    rs.getString("contato"),
                    rs.getString("razaoSocial")
            );
            pessoasJuridicas.add(pessoaJuridica);
        }
        return pessoasJuridicas;
    }

    @Override
    public void updatePessoaJuridica(PessoaJuridica pessoaJuridica) throws Exception {
        try {
            String sql = "UPDATE PessoaJuridica SET nome = ?, email = ?, ddi1 = ?, ddd1 = ?, numero1 = ?, ddi2 = ?, ddd2 = ?, numero2 = ?, logradouro = ?, complemento = ?, numeroEnd = ?, inscricaoEstadual = ?, contato = ?, razaoSocial = ? WHERE cnpj = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, pessoaJuridica.getNome());
            preparedStatement.setString(2, pessoaJuridica.getEmail());
            preparedStatement.setString(3, pessoaJuridica.getDdi1());
            preparedStatement.setString(4, pessoaJuridica.getDdd1());
            preparedStatement.setString(5, pessoaJuridica.getTelefone1());
            preparedStatement.setString(6, pessoaJuridica.getDdi2());
            preparedStatement.setString(7, pessoaJuridica.getDdd2());
            preparedStatement.setString(8, pessoaJuridica.getTelefone2());
            preparedStatement.setString(9, pessoaJuridica.getLogradouro());
            preparedStatement.setString(10, pessoaJuridica.getComplemento());
            preparedStatement.setString(11, pessoaJuridica.getNumeroEnd());
            preparedStatement.setString(12, pessoaJuridica.getInscricaoEstadual());
            preparedStatement.setString(13, pessoaJuridica.getContato());
            preparedStatement.setString(14, pessoaJuridica.getRazaoSocial());
            preparedStatement.setString(15, pessoaJuridica.getCnpj());
            preparedStatement.executeUpdate();
        } catch (SQLException erro) {
            throw new Exception("Erro SQL: " + erro.getMessage());
        }
    }

    @Override
    public void deletePessoaJuridica(String cnpj) throws Exception {
        try {
            String sql = "DELETE FROM PessoaJuridica WHERE cnpj = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, cnpj);
            preparedStatement.executeUpdate();
        } catch (SQLException erro) {
            throw new Exception("Erro SQL: " + erro.getMessage());
        }
    }
}
