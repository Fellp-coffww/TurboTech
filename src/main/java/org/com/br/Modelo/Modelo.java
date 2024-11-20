package org.com.br.Modelo;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Modelo{

    private long idModelo;

    private String descricao;

    private long idMarca;

}