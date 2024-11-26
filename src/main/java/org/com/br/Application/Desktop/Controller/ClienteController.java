package org.com.br.Application.Desktop.Controller;

import org.com.br.Application.Desktop.Services.ClienteService;
import org.com.br.Core.Domain.Exceptions.InvalidCPFException;
import org.com.br.Core.Domain.Exceptions.InvalidCPNJException;
import org.com.br.Core.Domain.Exceptions.InvalidTelefoneException;
import org.com.br.Core.Domain.Models.PessoaFisica;
import org.com.br.Core.Domain.Models.PessoaJuridica;
import org.com.br.Infra.Repository.PessoaFisicaRepository;
import org.com.br.Infra.Repository.PessoaJuridicaRepository;

import javax.swing.*;

public class ClienteController {

    private ClienteService clienteService = new ClienteService(new PessoaFisicaRepository(), new PessoaJuridicaRepository());

    private  JFrame frame;

    public ClienteController(JFrame frame) throws Exception {

        this.frame = frame;

    }

    public void criarPessoa(String tipo,
                              String nome,
                              String email,
                              String telefone,
                              String endereco,
                              String complemento,
                              String numero,
                              String cpfCnpj,
                              String inscricaoEstadual,
                              String contato,
                              String razaoSocial){

        try{
            clienteService.criarPessoa(tipo, nome, email, telefone, endereco, complemento, numero, cpfCnpj, inscricaoEstadual, contato, razaoSocial);
            JOptionPane.showMessageDialog(frame, "Dados salvos com sucesso!");

        }catch (Exception e){
            JOptionPane.showMessageDialog(frame, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }

    }


}


