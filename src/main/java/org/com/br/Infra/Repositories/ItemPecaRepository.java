package org.com.br.Infra.Repositories;

import java.util.List;

import org.com.br.Core.Domain.Models.ItemPeca;

public interface ItemPecaRepository {

    public void createItemPeca (ItemPeca itemPeca) throws Exception;

    public ItemPeca getItemPecaById(Long id) throws Exception;

    public List<ItemPeca> getItemPeca() throws Exception;

    public void updateItemPeca(ItemPeca itemPeca) throws Exception;

    public void deleteItemPeca(Long id) throws Exception;

}