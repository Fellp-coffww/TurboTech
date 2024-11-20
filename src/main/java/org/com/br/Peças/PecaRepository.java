package org.com.br.Peca;

import java.util.List;

public interface PecaRepository {

    public void createPeca(Peca peca) throws Exception;

    public Peca getPecaById(Long id) throws Exception;

    public List<Peca> getPecas() throws Exception;

    public void updatePeca(Peca peca) throws Exception;

    public void deletePeca(Long id) throws Exception;

    public List<Peca> getPecaBydescricao(String descricao) throws Exception;

    public List<Peca> getPecaBycodigo(String codigo) throws Exception;
}