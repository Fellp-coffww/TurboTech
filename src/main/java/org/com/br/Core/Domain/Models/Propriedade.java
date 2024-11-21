package org.com.br.Core.Domain.Models;

import java.util.Date;

public class Propriedade {

    private long idPropriedade;

    private Date dataInicio;

    private Date dataFim;


    public Propriedade(long idPropriedade, Date dataInicio, Date dataFim) {
        this.idPropriedade = idPropriedade;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
    }

    public Propriedade() {
    }

    public long getIdPropriedade() {
        return idPropriedade;
    }

    public void setIdPropriedade(long idPropriedade) {
        this.idPropriedade = idPropriedade;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }
}
