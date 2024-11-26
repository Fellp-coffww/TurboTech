package org.com.br.Infra.Interfaces;

import java.util.List;

import org.com.br.Core.Domain.Models.Peca;

public interface IPeca {

    public void createPeca(Peca peca) throws Exception;

    public Peca getPecaById(long id) throws Exception;

    public List<Peca> getPecas() throws Exception;

    public void updatePeca(Peca peca) throws Exception;

    public void deletePeca(long id) throws Exception;

    public List<Peca> getPecaBydescricao(String descricao) throws Exception;

    public List<Peca> getPecaBycodigo(String codigo) throws Exception;
}