package org.com.br.Application.Desktop.Services;

import org.com.br.Core.Domain.Models.*;
import org.com.br.Infra.Interfaces.*;
import org.com.br.Infra.Repository.ItemPecaRepository;
import org.com.br.Infra.Repository.ItemServicoRepository;
import org.com.br.Infra.Repository.OrdemServicoRepository;
import org.com.br.Infra.Repository.VeiculoRepository;

import java.util.List;
import java.util.ListIterator;

public class OrdemServicoService {

    private IVeiculo veiculoRepository;

    private IOrdemServico ordemServicoRepository;

    private IItemServico itemServicoRepository;

    private IItemPeca iItemPecaRepository;

    private IPeca pecaRepository;

    private IServico servicoRepository;

    private OrdemServico ordemServico;


    public OrdemServicoService(VeiculoRepository veiculoRepository, OrdemServicoRepository ordemServicoRepository,
                               ItemServicoRepository itemServicoRepository, ItemPecaRepository iItemPecaRepository, IPeca ipecaRepository, IServico servicoRepository) {
        this.veiculoRepository = veiculoRepository;
        this.ordemServicoRepository = ordemServicoRepository;
        this.itemServicoRepository = itemServicoRepository;
        this.iItemPecaRepository = iItemPecaRepository;
        this.pecaRepository = ipecaRepository;
        this.servicoRepository = servicoRepository;

    }

    public OrdemServico getOrdemServicoById(long id) throws Exception {
        ordemServico = ordemServicoRepository.getOrdemServicoById(id);
        return ordemServico;
    }

    public Veiculo getVeiculoFromOS() throws Exception {
        return  veiculoRepository.getVeiculoByPlaca(ordemServico.getPlaca());
    }

    public List<ItemPeca> getItemPecasFromOS() throws Exception {
        return iItemPecaRepository.getItemPecaByOrdemServicoId(ordemServico.getIdOrdemServico());
    }

    public List<ItemServico> getItemServicoFromOS() throws Exception {
        return itemServicoRepository.getItemServicoByOrdemServicoId(ordemServico.getIdOrdemServico());
    }

    public List<Peca> getListPeca() throws Exception {
        return pecaRepository.getPecas();
    }

    public List<Servico> getListServico() throws Exception {
        return servicoRepository.getServicos();

    }

}
