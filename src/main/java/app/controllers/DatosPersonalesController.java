/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.controllers;

import app.models.Localidad;
import app.models.Socio;
import app.models.Usuario;
import ar.mppfiles.utils.validation.simple.ParamValidationException;
import ar.mppfiles.utils.validation.simple.ParamValidator;
import javax.naming.NamingException;
import org.javalite.activejdbc.LazyList;
import org.javalite.activeweb.AppController;
import org.javalite.activeweb.annotations.POST;

/**
 *
 * @author c1yb4by
 */
public class DatosPersonalesController extends AppController {

    public void index() throws NamingException {
        datosIndex();
    }

    @POST
    public void modificar() throws ParamValidationException {

        ParamValidator parametros = validar();

        Integer usuario_ingresado = parametros.getInteger("dni");
        Integer id = Integer.parseInt(getId());
        Socio socio_actual = (Socio) session("socio");
        Integer dni_actual = socio_actual.getInteger("dni");

        if (Socio.first("dni = ? ", usuario_ingresado) == null) { // si me devuelve nulo es que el dni no esta regitrado
            Socio socio = Socio.findById(parametros.getInteger("id"));
            socio.set("apeynom", (parametros.getString("apeynom").toUpperCase()));
            socio.set("dni", parametros.getInteger("dni"));
            socio.set("localidad_id", parametros.getInteger("localidad"));
            socio.set("email", parametros.getString("email").toLowerCase());
            // le dejo la fecha de ingreso que ya tiene
            socio.saveIt();
            Usuario usuario = Usuario.first("username = ? ", String.valueOf(dni_actual));
            usuario.set("username", parametros.getInteger("dni"));
            usuario.set("passwd", parametros.getInteger("dni"));
            usuario.saveIt();
            flash("mensaje", "Datos modificado con exito - Socio");
            session("socio", socio);
            redirect();
        } else {
            datosIndex();
            view("error", "Error ingrese un dni distinto");
            render("index");
        }

    }

    public void datosIndex() {

        Socio socio = (Socio) session("socio");
        LazyList lista_localidades = Localidad.findAll();
        view("socio", socio);
        view("localidades", lista_localidades);
        view("recaptcha_public_key", appContext().get("recaptcha_public_key"));

    }

    public ParamValidator validar() throws ParamValidationException {

        ParamValidator validador = new ParamValidator().fromMap(params1st());
        validador.con("apeynom").letrasConEspacios().minLargo(5).requerido()
                .con("dni").entero().requerido()
                .con("localidad_id").entero().min(1).requerido()
                .con("email").texto().requerido()
                .check();
        return validador;

    }

}
