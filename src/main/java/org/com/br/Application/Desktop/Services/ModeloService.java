package org.com.br.Application.Desktop.Services;

import java.util.List;

import org.com.br.Core.Domain.Models.Modelo;
import org.com.br.Infra.Interfaces.IModelo;

public class ModeloService {
    private IModelo iModelo;

    public ModeloService(IModelo iModelo) {
        this.iModelo = iModelo;
    }

    public List<Modelo> getModelos() throws Exception {
        return iModelo.getModelo();
    }

    public void createModelo(String descricao, long idMarca) throws Exception{
        Modelo modelo = new Modelo(descricao, idMarca);

         // Validar o campo antes de salvar
         if (descricao == null || descricao.trim().isEmpty()) {
            throw new Exception("O campo descrição é obrigatório!");
            //JOptionPane.showMessageDialog(null, "O campo descrição é obrigatório!", "Erro", JOptionPane.ERROR_MESSAGE);
        } else {
            iModelo.createModelo(modelo); // Salvar no repositório
        }
    }

}
