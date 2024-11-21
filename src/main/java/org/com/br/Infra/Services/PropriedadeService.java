package org.com.br.Infra.Services;

import org.com.br.Core.Domain.Models.Propriedade;
import org.com.br.Infra.Configuration.DbConnection;
import org.com.br.Infra.Repositories.PropriedadeRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class PropriedadeService implements PropriedadeRepository {

    private Connection connection = null;
    public PropriedadeService()throws Exception{
        connection = DbConnection.getConnection();
        if(connection == null) throw new Exception("ERRO DE CONEXAO");
    }

    @Override
    public void createPropriedade(Propriedade propriedade) throws Exception {

    }

    @Override
    public Propriedade getPropriedadeById(Long id) throws Exception {
        return null;
    }

    @Override
    public List<Propriedade> getPropriedade() throws Exception {
        return List.of();
    }

    @Override
    public void updatePropriedade(Propriedade Propriedade) throws Exception {

    }

    @Override
    public void deletePropriedade(Long id) throws Exception {

    }
}
