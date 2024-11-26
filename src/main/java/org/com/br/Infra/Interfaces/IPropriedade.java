package org.com.br.Infra.Interfaces;

import org.com.br.Core.Domain.Models.Propriedade;

import java.util.List;

public interface IPropriedade {

    public void createPropriedade(Propriedade Propriedade) throws Exception;

    public Propriedade getPropriedadeById(Long id) throws Exception;

    public List<Propriedade> getPropriedade() throws Exception;

    public void updatePropriedade(Propriedade Propriedade) throws Exception;

    public void deletePropriedade(Long id) throws Exception;

}
