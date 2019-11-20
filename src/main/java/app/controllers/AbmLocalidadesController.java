/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.controllers;

import app.models.Localidad;
import app.models.Usuario;
import app.models.UsuarioRol;
import java.sql.Date;
import org.javalite.activejdbc.LazyList;
import org.javalite.activeweb.AppController;
import org.javalite.activeweb.annotations.POST;

/**
 *
 * @author crybaby
 */
public class AbmLocalidadesController extends AppController {

    public void index() {

        LazyList lista_localidades = Localidad.findAll();
        view("localidades", lista_localidades);
    }

    public void mostrar() {
        darComerMostrar();
        render("mostrar");

    }

    public void modifica() {

        view("localidad_id", getId());
        render("modificar");
    }

    @POST
    public void eliminar() {

        Localidad localidad = Localidad.findById(Integer.valueOf(param("id")));
        localidad.deleteCascade();
        redirect();

    }

    @POST
    public void modificar() {
        Integer id = Integer.valueOf(param("id"));
        if (Localidad.exists(id)) {
            Localidad localidad = Localidad.findById(id);
            localidad.set("descripcion", String.valueOf(param("descripcion").toUpperCase()));
            localidad.saveIt();
            darComerMostrar();
            render("mostrar");

        }

    }

    @POST
    public void agregar() {

        Localidad localidad = new Localidad();
        localidad.set("descripcion", String.valueOf(param("descripcion").toUpperCase()));
        localidad.saveIt();
        redirect();

    }

    public void darComerMostrar() {

        LazyList lista_localidades = Localidad.findAll();
        view("localidades", lista_localidades);
    }
}
