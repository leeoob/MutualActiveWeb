/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.controllers;

import app.models.Comercio;
import app.models.Consumo;
import app.models.Socio;
import ar.mppfiles.utils.validation.simple.ParamValidationException;
import ar.mppfiles.utils.validation.simple.ParamValidator;
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
    public void pepito() throws ParamValidationException {

        ParamValidator parametros = validar();
        if (parametros.isOK()) {
            Consumo consumo = new Consumo();
            consumo.set("socio_id", parametros.getInteger("socio"));
            consumo.set("comercio_id", parametros.getInteger("comercio"));
            consumo.set("fecha", parametros.getDate("fecha"));
            consumo.set("importe", parametros.getBigDecimal("importe"));
            consumo.saveIt();
            flash("mensaje", "exito");
            redirect();
        }else {        
            view("error", "error");
            render();
        }

    }

    public ParamValidator validar() throws ParamValidationException {
        ParamValidator validador = new ParamValidator().fromMap(params1st());
        validador.con("socio_id").entero().min(1).requerido()
                .con("comercio_id").entero().min(1).requerido()
                .con("fecha").fecha().requerido()
                .con("importe").decimal().requerido()
                .check();
        return validador;
    }

}
