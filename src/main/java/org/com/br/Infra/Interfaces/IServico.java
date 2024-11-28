package org.com.br.Infra.Interfaces;
import org.com.br.Core.Domain.Models.Servico;

import java.util.List;


public interface IServico {
    
    public void createServico(Servico servico) throws Exception;

    public Servico getServicoById(long id) throws Exception;

    public List<Servico> getServicos() throws Exception;

    public void updateServico(Servico servico) throws Exception;

    public void deleteServico(long id) throws Exception;

    public Servico getServicoByDescricao(String decricao) throws Exception;

}
