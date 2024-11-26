package org.com.br.Infra.Interfaces;

import org.com.br.Core.Domain.Models.PessoaJuridica;

import java.util.List;

public interface IPessoaJuridica {

    public void createPessoaJuridica(PessoaJuridica PessoaJuridica) throws Exception;

    public PessoaJuridica getPessoaJuridicaByCnpj(String cpnj) throws Exception;

    public List<PessoaJuridica> getPessoaJuridica() throws Exception;

    public void updatePessoaJuridica(PessoaJuridica PessoaJuridica) throws Exception;

    public void deletePessoaJuridica(String cpnj) throws Exception;

}
