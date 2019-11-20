/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.controllers;

import app.models.Comercio;
import app.models.Localidad;
import app.models.Socio;
import java.sql.Date;
import org.javalite.activejdbc.LazyList;
import org.javalite.activeweb.AppController;
import org.javalite.activeweb.annotations.POST;

/**
 *
 * @author crybaby
 */
public class AbmComerciosController extends AppController {

    public void index() {

        LazyList lista_comercios = Comercio.findAll();
        LazyList lista_localidades = Localidad.findAll();
        view("comercios", lista_comercios);
        view("localidades", lista_localidades);
    }

    public void mostrar() {

        darComerMostrar();
        render("mostrar");

    }

    public void modifica() {
        LazyList lista_localidades = Localidad.findAll();
        view("localidades", lista_localidades);
        view("comercio_id", getId());
        render("modificar");
    }

    @POST
    public void eliminar() {

        Integer id = Integer.valueOf(param("id"));
        if (Comercio.exists(id)) {
            Comercio comercio = Comercio.findById(id);
            comercio.deleteCascade();
            redirect();
        }

    }

    @POST
    public void modificar() {
        Integer id = Integer.valueOf(param("id"));
        if (Comercio.exists(id)) {
            Comercio comercio = Comercio.findById(id);
            comercio.set("razon_social", String.valueOf(param("razon_social").toUpperCase()));
            comercio.set("domicilio", String.valueOf(param("domicilio").toUpperCase()));
            comercio.set("localidad_id", Integer.valueOf(param("localidad")));
            comercio.saveIt();
            darComerMostrar();
            render("mostrar");
        }

    }

    @POST
    public void agregar() {

        Comercio comercio = new Comercio();
        comercio.set("razon_social", String.valueOf(param("razon_social").toUpperCase()));
        comercio.set("fecha_alta", Date.valueOf(param("fecha")));
        comercio.set("domicilio", String.valueOf(param("domicilio").toUpperCase()));
        comercio.set("localidad_id", Integer.valueOf(param("localidad")));
        comercio.saveIt();
        redirect();

    }

    public void darComerMostrar() {

        LazyList lista_comercios = Comercio.findAll();
        view("comercios", lista_comercios);

    }
}
