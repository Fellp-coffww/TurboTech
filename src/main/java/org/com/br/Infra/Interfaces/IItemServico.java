package org.com.br.Infra.Interfaces;

import java.util.List;

import org.com.br.Core.Domain.Models.ItemServico;

public interface IItemServico {

    public void createItemServico (ItemServico itemServico) throws Exception;

    public ItemServico getItemServicoById(long id) throws Exception;

    public List<ItemServico> getItemServico() throws Exception;

    public void updateItemServico(ItemServico itemServico) throws Exception;

    public void deleteItemServico(long id) throws Exception;

    public List<ItemServico> getItemServicoByOrdemServicoId(long id) throws Exception;


}