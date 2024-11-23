package org.com.br.Infra.Repositories;

import java.util.List;

import org.com.br.Core.Domain.Models.Peca;

public interface PecaRepository {

    public void createPeca(Peca peca) throws Exception;

    public Peca getPecaById(Long id) throws Exception;

    public List<Peca> getPecas() throws Exception;

    public void updatePeca(Peca peca) throws Exception;

    public void deletePeca(Long id) throws Exception;

    public Peca getPecaBydescricao(String descricao) throws Exception;

    public Peca getPecaBycodigo(String codigo) throws Exception;
}