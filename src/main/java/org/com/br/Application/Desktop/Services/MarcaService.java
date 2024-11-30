package org.com.br.Application.Desktop.Services;

import java.util.List;

import org.com.br.Core.Domain.Models.Marca;
import org.com.br.Infra.Interfaces.IMarca;
import org.com.br.Infra.Interfaces.IModelo;
import org.com.br.Infra.Repository.MarcaRepository;
import org.com.br.Infra.Repository.ModeloRepository;

public class MarcaService {

    private IMarca marcaRepository;

    // Construtor que recebe o repositório de marcas
    public MarcaService(MarcaRepository marcaRepository) {
        this.marcaRepository = marcaRepository;
    }

    // Método para criar uma nova marca
    public void criarMarca(String descricao) throws Exception {
        // Criar uma nova marca
        Marca marca = new Marca(descricao);

        // Validar o campo antes de salvar
        if (descricao == null || descricao.trim().isEmpty()) {
            throw new Exception("O campo descrição é obrigatório!");
        } else {
            marcaRepository.createMarca(marca); // Salvar no repositório
        }
    }

    // Método para editar uma marca existente
    public void editMarca(String descricao, long id) throws Exception {
        Marca marca = new Marca(descricao, id);

        // Validar o campo antes de salvar
        if (descricao == null || descricao.trim().isEmpty()) {
            throw new Exception("O campo descrição é obrigatório!");
        } else {
            marcaRepository.updateMarca(marca); // Salvar no repositório
        }
    }

    // Método para listar todas as marcas
    public List<Marca> getMarcas() throws Exception {
        return marcaRepository.getMarcas();
    }

    public void deleteMarca(Long id) throws Exception {
        IModelo modelo = new ModeloRepository();
        if(modelo.getModelosByMarcaId(id).size() > 0) {
            throw new Exception("Não deletar marcas com modelos associados!");
        }else {
            marcaRepository.deleteMarca(id);
        }
    }

}
