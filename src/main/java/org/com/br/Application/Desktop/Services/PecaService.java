package org.com.br.Application.Desktop.Services;

import javax.swing.JOptionPane;

import org.com.br.Core.Domain.Models.Peca;
import org.com.br.Infra.Interfaces.IPeca;
import org.com.br.Infra.Repository.PecaRepository;

public class PecaService {
    
    private IPeca pecaRepository;

    public PecaService(PecaRepository pecaRepository) {
        this.pecaRepository = pecaRepository;
    }

    public void criarPeca(String descricao, int quantidade, double valorUnitario, String codigo) throws Exception {
        // Criar uma nova peça
        Peca peca = new Peca(descricao, quantidade, valorUnitario, codigo);
    
        // Validar os campos antes de salvar
        if (descricao == null || descricao.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "O campo descrição é obrigatório!", "Erro", JOptionPane.ERROR_MESSAGE);
        } else if (quantidade <= 0) {
            JOptionPane.showMessageDialog(null, "A quantidade deve ser maior que zero!", "Erro", JOptionPane.ERROR_MESSAGE);
        } else if (valorUnitario <= 0) {
            JOptionPane.showMessageDialog(null, "O valor unitário deve ser maior que zero!", "Erro", JOptionPane.ERROR_MESSAGE);
        } else if (codigo == null || codigo.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "O campo código é obrigatório!", "Erro", JOptionPane.ERROR_MESSAGE);
        } else {
            // Se todas as validações forem aprovadas, salvar a peça
            pecaRepository.createPeca(peca); // Salvar no repositório
            JOptionPane.showMessageDialog(null, "Peça salva com sucesso!");
        }
    }
    

}
