package org.com.br.Application.Desktop.Controller;

import org.com.br.Core.Domain.Models.OrdemServico;
import org.com.br.Infra.Repository.OrdemServicoRepository;

import java.util.List;

public class OrdemServicoController {

    static OrdemServicoRepository ordemServicoService;

    {
        try {
            ordemServicoService = new OrdemServicoRepository();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public static List<OrdemServico> getListOrdemServico() throws Exception {

        return  ordemServicoService.getOrdemServico();

    }




}
