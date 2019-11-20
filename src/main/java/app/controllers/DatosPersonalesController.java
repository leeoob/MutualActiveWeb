/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.controllers;

import app.models.Localidad;
import app.models.Socio;
import app.models.Usuario;
import app.models.Validaciones;
import app.util.helpers.TomcatHelper;
import javax.naming.NamingException;
import org.javalite.activejdbc.LazyList;
import org.javalite.activeweb.AppController;
import org.javalite.activeweb.annotations.POST;
import org.javalite.common.Util;

/**
 *
 * @author c1yb4by
 */
public class DatosPersonalesController extends AppController {

    public void index() throws NamingException {        
        datosIndex();
    }

    @POST
    public void modificar() throws NamingException {

        try {
            Integer usuario_ingresado = Integer.valueOf(param("dni"));
            Integer id = Integer.parseInt(getId());
            Socio socio_actual = (Socio) session("socio");
            Integer dni_actual = socio_actual.getInteger("dni");
            Validaciones.validarSocioSinFecha(params1st());
            if (!Util.blank(id)) {
                if (Socio.first("dni = ? ", usuario_ingresado) == null) { // si me devuelve nulo es que el dni no esta regitrado
                    Socio socio = Socio.findById(Integer.parseInt(param("id")));
                    socio.set("apeynom", (param("apeynom").toUpperCase()));
                    socio.set("dni", Integer.valueOf(param("dni")));
                    socio.set("localidad_id", Integer.valueOf(param("localidad")));
                    socio.set("email", param("email").toLowerCase());
                    // le dejo la fecha de ingreso que ya tiene
                    socio.saveIt();
                    Usuario usuario = Usuario.first("username = ? ", String.valueOf(dni_actual));
                    usuario.set("username", param("dni"));
                    usuario.set("passwd", param("dni"));
                    usuario.saveIt();
                    flash("mensaje", "Datos modificado con exito - Socio");
                    session("socio", socio);
                    redirect();
                }else {
                    flash("error", "Elija un Dni distinto del que a tiene");
                    redirect();
                }
            }

        } catch (IllegalArgumentException e) {
            datosIndex();
            view("error", "Error ingrese un dni distinto");
            render("index");
        }
    }

    public void datosIndex() throws NamingException {
        
        Socio socio = (Socio) session("socio");
        LazyList lista_localidades = Localidad.findAll();
        view("socio", socio);
        view("localidades", lista_localidades);
        view("recaptcha_public_key", (String) TomcatHelper.getVariable("recaptcha_public_key"));

    }

}
