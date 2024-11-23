package org.com.br.Propriedade;

import java.util.List;

public interface PropriedadeRepository {

    public void createPropriedade(Propriedade propriedade) throws Exception;

    public PessoaFisica getPessoaPropriedadeById(Long id) throws Exception;

    public List<Propriedade> getPropriedade() throws Exception;

    public void updatePropriedade(Propriedade propriedade) throws Exception;

    public void deletePropriedade(Long id) throws Exception;

    public List<Propriedade> getPropriedadeByplaca(Long id) throws Exception;
}