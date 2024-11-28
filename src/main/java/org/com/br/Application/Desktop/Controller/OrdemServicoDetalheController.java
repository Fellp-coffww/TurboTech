package org.com.br.Application.Desktop.Controller;

import org.com.br.Application.Desktop.Services.ClienteService;
import org.com.br.Application.Desktop.Services.OrdemServicoService;
import org.com.br.Core.Domain.Models.*;
import org.com.br.Infra.Repository.*;

import javax.swing.*;
import java.util.List;

public class OrdemServicoDetalheController {



    private OrdemServicoService ordemServicoService;

    private JFrame frame;

    private OrdemServico ordemServico;

    private Veiculo veiculo;

    private List<ItemPeca> itemPecaList;

    private List<ItemServico> itemServicoList;

    private List<Peca> pecaList;

    private List<Servico> servicoList;

    private Peca peca;

    private Servico servico;

    public OrdemServicoDetalheController(JFrame frame){

        try {
            ordemServicoService = new OrdemServicoService(new VeiculoRepository(), new OrdemServicoRepository(),
                    new ItemServicoRepository(), new ItemPecaRepository(), new PecaRepository(), new ServicoRepository());
            this.frame = frame;
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public void ordemServiceContrucut(long id) throws Exception {
        ordemServico = ordemServicoService.getOrdemServicoById(id);
        itemPecaList = ordemServicoService.getItemPecasFromOS();
        itemServicoList = ordemServicoService.getItemServicoFromOS();
        veiculo = ordemServicoService.getVeiculoFromOS();
        pecaList = ordemServicoService.getListPeca();
        servicoList = ordemServicoService.getListServico();

    }

    public void setOrdemServico(OrdemServico ordemServico) {
        this.ordemServico = ordemServico;
    }

    public OrdemServico getOrdemServico() {
        return ordemServico;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public List<ItemPeca> getItemPecaList() {
        return itemPecaList;
    }

    public List<ItemServico> getItemServicoList() {
        return itemServicoList;
    }

    public List<Peca> getPecaList() {
        return pecaList;
    }

    public List<Servico> getServicoList() {
        return servicoList;
    }

    public void addItemPeca(String peca, int quantidade){
        try {
            this.peca = ordemServicoService.getPecaByDescricao(peca);
            ordemServicoService.addItemPeca(this.peca, quantidade, this.ordemServico);
            JOptionPane.showMessageDialog(frame, "Dados salvos com sucesso!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(frame, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            throw new RuntimeException(e);
        }
    }

    public void addItemServico(String servico, int quantidade){
        try {
            this.servico = ordemServicoService.getServicoByDescricao(servico);
            ordemServicoService.addItemServico(this.servico, quantidade, this.ordemServico);
            JOptionPane.showMessageDialog(frame, "Dados salvos com sucesso!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(frame, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            throw new RuntimeException(e);
        }
    }
}
