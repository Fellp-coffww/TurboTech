package org.com.br.Application.Desktop.Services;

import javax.swing.JOptionPane;

import org.com.br.Core.Domain.Models.Servico;
import org.com.br.Infra.Interfaces.IServico;

public class ServicoService {
    
    private IServico servicoRepository;

    public ServicoService(IServico servicoRepository) {
        this.servicoRepository = servicoRepository;
    }

    public ServicoService() {
    }
    

    public void criarServico(String descricao, double valorUnitario) throws Exception {
        // Criar uma nova peça
        Servico servico = new Servico(descricao, valorUnitario);
    
        // Validar os campos antes de salvar
        if (descricao == null || descricao.trim().isEmpty()) {
            throw new Exception("O campo descrição é obrigatório!");
        } else  if (valorUnitario <= 0) {
            throw new Exception("O valor unitário deve ser maior que zero!");
        } else 
            // Se todas as validações forem aprovadas, salvar a peça
            servicoRepository.createServico(servico); // Salvar no repositório
            JOptionPane.showMessageDialog(null, "Peça salva com sucesso!");

    }

    public void deleteServico(long id) throws Exception {
        servicoRepository.deleteServico(id);
    }

    public void editarServiço(String descricao, double  valorUnitario, long id) throws Exception {
        Servico servico = new Servico(descricao, valorUnitario, id);
        servicoRepository.updateServico(servico);
    }

}


