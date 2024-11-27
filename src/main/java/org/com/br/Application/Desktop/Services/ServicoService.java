package org.com.br.Application.Desktop.Services;

import javax.swing.JOptionPane;

import org.com.br.Core.Domain.Models.Servico;
import org.com.br.Infra.Interfaces.IServico;
import org.com.br.Infra.Repository.ServicoRepository;

public class ServicoService {
    
    private IServico servicoRepository;

    public ServicoService(ServicoRepository servicoRepository) {
        this.servicoRepository = servicoRepository;
    }

    public void criarServico(String descricao, double valorUnitario) throws Exception {
        // Criar uma nova peça
        Servico servico = new Servico(descricao, valorUnitario);
    
        // Validar os campos antes de salvar
        if (descricao == null || descricao.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "O campo descrição é obrigatório!", "Erro", JOptionPane.ERROR_MESSAGE);
        } else  if (valorUnitario <= 0) {
            JOptionPane.showMessageDialog(null, "O valor unitário deve ser maior que zero!", "Erro", JOptionPane.ERROR_MESSAGE);
        } else 
            // Se todas as validações forem aprovadas, salvar a peça
            servicoRepository.createServico(servico); // Salvar no repositório
            JOptionPane.showMessageDialog(null, "Peça salva com sucesso!");
    }
}


