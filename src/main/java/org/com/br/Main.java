package org.com.br;

import org.com.br.Marca.Marca;
import org.com.br.Marca.MarcaService;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {

        MarcaService marcaService = new MarcaService();

        List<Marca> marcas = marcaService.getMarcas();

        for (Marca marca1 : marcas) {
            System.out.println(marca1.getDescricao());
        }

        marcaService.deleteMarca(Long.valueOf(7));


    }



}