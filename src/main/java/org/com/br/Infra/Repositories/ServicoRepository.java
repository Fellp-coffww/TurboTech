package org.com.br.Infra.Repositories;
import org.com.br.Core.Domain.Models.Servico;

import java.util.List;


public interface ServicoRepository {
    
    public void createServico(Servico servico) throws Exception;

    public Servico getServicoById(Long id) throws Exception;

    public List<Servico> getServicos() throws Exception;

    public void updateServico(Servico servico) throws Exception;

    public void deleteServico(Long id) throws Exception;

}
