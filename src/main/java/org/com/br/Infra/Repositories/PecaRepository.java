package org.com.br.Infra.Repositories;

import org.com.br.Core.Domain.Models.Peca;

import java.util.List;

public interface PecaRepository {

    public void createPeca(Peca peca) throws Exception;

    public Peca getPecaById(Long id) throws Exception;

    public List<Peca> getPecas() throws Exception;

    public void updatePeca(Peca peca) throws Exception;

    public void deletePeca(Long id) throws Exception;


}