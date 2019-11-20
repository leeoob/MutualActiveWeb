/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.controllers;

import app.models.Comercio;
import app.models.Consumo;
import app.models.Socio;
import java.sql.Date;
import org.javalite.activejdbc.LazyList;
import org.javalite.activeweb.AppController;
import org.javalite.activeweb.annotations.POST;

/**
 *
 * @author crybaby
 */
public class CargarConsumoController extends AppController {

    public void index() {

        LazyList lista_socios = Socio.findAll();
        LazyList lista_comercios = Comercio.findAll();

        view("socios", lista_socios);
        view("comercios", lista_comercios);

    }

    @POST
    public void pepito() {

        Consumo consumo = new Consumo();
        consumo.set("socio_id", Integer.valueOf(param("socio")));
        consumo.set("comercio_id", Integer.valueOf(param("comercio")));
        consumo.set("fecha", Date.valueOf(param("fecha")));
        consumo.set("importe", Double.parseDouble(param("importe")));
        consumo.saveIt();
        redirect();

    }

}
