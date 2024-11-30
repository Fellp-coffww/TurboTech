package org.com.br.Application.Desktop.Controller;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.com.br.Application.Desktop.Services.MarcaService;
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
    public void editarMarca(String descricao, long id) throws Exception {
        try {
            marcaService.editMarca(descricao, id);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(frame, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
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
