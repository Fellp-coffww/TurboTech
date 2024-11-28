package org.com.br.Application.Desktop.Services;


import java.util.List;

import javax.swing.JOptionPane;

import org.com.br.Core.Domain.Models.Marca;
import org.com.br.Core.Domain.Models.Modelo;
import org.com.br.Infra.Interfaces.IMarca;
import org.com.br.Infra.Repository.MarcaRepository;

public class MarcaService {

    private IMarca marcaRepository;

    public MarcaService(MarcaRepository marcarepository) {
        this.marcaRepository = marcarepository;
    }

    public void criarMarca(String descricao) throws Exception {
        // Criar uma nova marca
        Marca marca = new Marca(descricao);
    
        // Validar o campo antes de salvar
        if (descricao == null || descricao.trim().isEmpty()) {
            throw new Exception("O campo descrição é obrigatório!");
            //JOptionPane.showMessageDialog(null, "O campo descrição é obrigatório!", "Erro", JOptionPane.ERROR_MESSAGE);
        } else {
            marcaRepository.createMarca(marca); // Salvar no repositório
        }
    }

     public List<Marca> getMarcas() throws Exception {
        return marcaRepository.getMarcas();
    }


}
