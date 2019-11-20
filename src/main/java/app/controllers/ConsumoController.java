/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.controllers;

import app.models.Consumo;
import app.models.Socio;
import org.javalite.activejdbc.LazyList;
import org.javalite.activeweb.AppController;

/**
 *
 * @author crybaby
 */
public class ConsumoController extends AppController {

    public void index() {
        
        Socio socio = (Socio) session("socio");
        LazyList<Consumo> lista_consumos = Consumo.getConsumoByIdSocio(socio.getInteger("id"));
        view("socio", socio);
        view("consumos", lista_consumos);

    }
}
