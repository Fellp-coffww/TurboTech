package org.com.br.Application.Desktop.Controller;

import org.com.br.Application.Desktop.Services.OrdemServicoService;
import org.com.br.Core.Domain.Models.OrdemServico;
import org.com.br.Core.Domain.Models.Veiculo;
import org.com.br.Infra.Interfaces.IOrdemServico;
import org.com.br.Infra.Repository.OrdemServicoRepository;
import org.com.br.Infra.Repository.VeiculoRepository;

import java.util.List;

public class OrdemServicoController {

    static OrdemServicoService ordemServicoService;
    {
        try {
            ordemServicoService = new OrdemServicoService(new VeiculoRepository());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public List<Veiculo> getVeiculos(){
        try {
            return ordemServicoService.getAllVeiculos();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static List<OrdemServico> getListOrdemServico() throws Exception {

        return  new OrdemServicoRepository().getOrdemServicoByState();

    }

    public void updateOrdemServico(OrdemServico ordemServico)  {
        try {
            ordemServicoService.setOrdemServico(ordemServico);
            ordemServicoService.updateOrdemServico();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void createOrdemServico(OrdemServico ordemServico){
        try {
            new OrdemServicoRepository().createOrdemServico(ordemServico);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }




}
