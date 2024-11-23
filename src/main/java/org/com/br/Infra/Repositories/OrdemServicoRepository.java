package org.com.br.Infra.Repositories;

import org.com.br.Core.Domain.Models.OrdemServico;

import java.util.List;

public interface OrdemServicoRepository {

    public void createOrdemServico(OrdemServico ordemServico) throws Exception;

    public OrdemServico getOrdemServicoById(Long id) throws Exception;

    public List<OrdemServico> getOrdemServico() throws Exception;

    public void updateOrdemServico(OrdemServico ordemServico) throws Exception;

    public void deleteOrdemServico(Long id) throws Exception;

}