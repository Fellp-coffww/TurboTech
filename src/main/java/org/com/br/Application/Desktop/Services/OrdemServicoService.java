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


    public OrdemServicoService(IVeiculo veiculoRepository, IOrdemServico ordemServicoRepository,
                               IItemServico itemServicoRepository, IItemPeca iItemPecaRepository, IPeca ipecaRepository, IServico servicoRepository) {
        this.veiculoRepository = veiculoRepository;
        this.ordemServicoRepository = ordemServicoRepository;
        this.itemServicoRepository = itemServicoRepository;
        this.iItemPecaRepository = iItemPecaRepository;
        this.pecaRepository = ipecaRepository;
        this.servicoRepository = servicoRepository;

    }

    public OrdemServicoService(IPeca pecaRepository) {
        this.pecaRepository = pecaRepository;
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

    public Peca getPecaByDescricao(String descricao) throws Exception {
        return pecaRepository.getPecaBydescricao(descricao);
    }
    public Servico getServicoByDescricao(String descricao) throws Exception {
        return servicoRepository.getServicoByDescricao(descricao);
    }

    public void addItemPeca(Peca peca, int quantidade, OrdemServico ordemServico) throws Exception {
        if(peca == null){
            throw new Exception("Peça inválida! Checar seleção.");
        } else if (quantidade <= 0) {
            throw new Exception("Quantidade deve ser maior que zero!");
        }else if(!ordemServico.getStatusOS().equals("Orçamento")){
            throw new Exception("Não é possível adicionar produtos em ordem de serviço fora do status de orçamento!");
        } else {
            iItemPecaRepository.createItemPeca(new ItemPeca(ordemServico.getIdOrdemServico(), peca.getIdPeca(), quantidade, peca.getValorUnitario() * quantidade, peca.getValorUnitario()));
        }
        }

    public void addItemServico(Servico servico, int quantidade, OrdemServico ordemServico) throws Exception {
        if(servico == null){
            throw new Exception("Peça inválida! Checar seleção.");
        } else if (quantidade <= 0) {
            throw new Exception("Quantidade deve ser maior que zero!");
        }else if(!ordemServico.getStatusOS().equals("Orçamento")){
            throw new Exception("Não é possível adicionar produtos em ordem de serviço fora do status de orçamento!");
        } else {
            iItemPecaRepository.createItemPeca(new ItemPeca(ordemServico.getIdOrdemServico(), servico.getIdServico(), quantidade, servico.getValorUnitario() * quantidade, servico.getValorUnitario()));
        }
    }
}
