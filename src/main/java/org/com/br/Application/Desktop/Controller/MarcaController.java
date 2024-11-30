package org.com.br.Application.Desktop.Controller;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.com.br.Application.Desktop.Services.MarcaService;
import org.com.br.Core.Domain.Models.Marca;
import org.com.br.Infra.Repository.MarcaRepository;

public class MarcaController {

    private MarcaService marcaService;  // MarcaService
    private JFrame frame;

    // Construtor
    public MarcaController(JFrame frame) throws Exception{
        this.frame = frame;
        this.marcaService = new MarcaService(new MarcaRepository());  // Instancia o serviço com o repositório
    }

    // Método para criar marca
    public void criarMarca(String descricao) throws Exception {
        try {
            marcaService.criarMarca(descricao);
            JOptionPane.showMessageDialog(frame, "Dados salvos com sucesso!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(frame, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Método para editar marca
    public void editarMarca(long id, String descricao) throws Exception {
        try {
            marcaService.editMarca(id, descricao);
            JOptionPane.showMessageDialog(frame, "Dados salvos com sucesso!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(frame, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Novo método para buscar o ID da marca pelo nome
    public int getMarcaId(Marca marca) throws Exception {
        try {
            return marcaService.getMarcaId(marca);  // Chama o serviço para buscar o ID
        } catch (Exception e) {
            throw new Exception("Erro ao obter ID da Marca: " + e.getMessage());
        }
    }

    public void deletarMarca(long id) throws Exception {

        try{

            marcaService.deleteMarca(id);
            JOptionPane.showMessageDialog(frame, "Dados salvos com sucesso!");
        }catch (Exception e){
            JOptionPane.showMessageDialog(frame, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

}
