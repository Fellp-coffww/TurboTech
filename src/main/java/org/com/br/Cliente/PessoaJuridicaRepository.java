package org.com.br.Cliente;

import java.util.List;

public interface PessoaJuridicaRepository {

    public void createPessoaJuridica(PessoaJuridica PessoaJuridica) throws Exception;

    public PessoaJuridica getPessoaJuridicaById(Long id) throws Exception;

    public List<PessoaJuridica> getPessoaJuridica() throws Exception;

    public void updatePessoaJuridica(PessoaJuridica PessoaJuridica) throws Exception;

    public void deletePessoaJuridica(Long id) throws Exception;

}
