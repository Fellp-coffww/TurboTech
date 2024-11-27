package org.com.br.Application.Desktop.Controller;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.com.br.Application.Desktop.Services.MarcaService;
import org.com.br.Infra.Repository.MarcaRepository;

public class MarcaController {

    private MarcaService marcaService = new MarcaService(new MarcaRepository());

    private JFrame frame;

    public MarcaController(JFrame frame) throws Exception{
        this.frame = frame;
    }

    public void criarMarca(String descricao)throws Exception{
        try{
            marcaService.criarMarca(descricao);
            JOptionPane.showMessageDialog(frame, "Dados salvos com sucesso!");

        }catch (Exception e){
            JOptionPane.showMessageDialog(frame, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

}
