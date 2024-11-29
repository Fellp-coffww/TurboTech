package org.com.br.Application.Desktop.Controller;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.com.br.Application.Desktop.Services.MarcaService;
import org.com.br.Application.Desktop.Services.ModeloService;
import org.com.br.Infra.Repository.MarcaRepository;
import org.com.br.Infra.Repository.ModeloRepository;

public class ModeloController {
     private ModeloService modeloService = new ModeloService(new ModeloRepository());

    private JFrame frame;

    public ModeloController(JFrame frame) throws Exception{
        this.frame = frame;
    }

    public void createModelo(String descricao, long idMarca){
        try {
            modeloService.createModelo(descricao, idMarca);
            JOptionPane.showMessageDialog(frame, "Dados salvos com sucesso!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(frame, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}