package org.com.br.Application.Desktop.Services;

import org.com.br.Core.Domain.Models.Peca;
import org.com.br.Infra.Interfaces.IPeca;

public class PecaService {
    
    private IPeca pecaRepository;

    
    public PecaService(IPeca pecaRepository) {
        this.pecaRepository = pecaRepository;
    }

    public PecaService() {
    }


    public void criarPeca(String descricao, int quantidade, double valorUnitario, String codigo) throws Exception {
        // Criar uma nova peça
        Peca peca = new Peca(descricao, quantidade, valorUnitario, codigo);
    
        // Validar os campos antes de salvar
        if (descricao == null || descricao.trim().isEmpty()) {
            throw new Exception("O campo descrição é obrigatório!");
        } else if (quantidade <= 0) {
            throw new Exception("A quantidade deve ser maior que zero!");
        } else if (valorUnitario <= 0) {
            throw new Exception("O valor unitário deve ser maior que zero!");
        } else if (codigo == null || codigo.trim().isEmpty()) {
            throw new Exception("O campo código é obrigatório!");
        } else {
            // Se todas as validações forem aprovadas, salvar a peça
            pecaRepository.createPeca(peca); // Salvar no repositório
        }
    }

    public void deletePeca(long id){
        try {
            pecaRepository.deletePeca(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public void editarPeca(String descricao, int quantidade, double valorUnitario, String codigo, long idPeca) throws Exception{
        Peca peca = new Peca(descricao, quantidade, valorUnitario, codigo, idPeca);
        pecaRepository.updatePeca(peca);
    }

}
