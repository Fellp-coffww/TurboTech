package org.com.br.Infra.Interfaces;

import org.com.br.Core.Domain.Models.OrdemServico;

import java.util.List;

public interface IOrdemServico {

    public void createOrdemServico(OrdemServico ordemServico) throws Exception;

    public OrdemServico getOrdemServicoById(long id) throws Exception;

    public List<OrdemServico> getOrdemServico() throws Exception;

    public void updateOrdemServico(OrdemServico ordemServico) throws Exception;

    public void deleteOrdemServico(long id) throws Exception;

    public List<OrdemServico> getOrdemServicoByState() throws Exception;


}