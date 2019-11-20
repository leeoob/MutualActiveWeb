/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.controllers;

import app.models.Localidad;
import app.models.Rol;
import app.models.Socio;
import app.models.Usuario;
import app.models.Validaciones;
import java.sql.Date;
import org.javalite.activejdbc.LazyList;
import org.javalite.activeweb.AppController;
import org.javalite.activeweb.annotations.POST;
import app.util.helpers.RecaptchaV3Helper;
import app.util.helpers.TomcatHelper;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;

/**
 *
 * @author crybaby
 */
public class AbmSociosController extends AppController {

    public void index() throws NamingException {

        LazyList lista_socios = Socio.findAll().orderBy("apeynom");
        LazyList lista_localidades = Localidad.findAll();
        view("socios", lista_socios);
        view("localidades", lista_localidades);
        view("recaptcha_public_key", (String) TomcatHelper.getVariable("recaptcha_public_key"));
    }

    public void mostrar() {

        LazyList lista_socios = Socio.findAll();
        view("socios", lista_socios);
        render("mostrar");

    }

    public void modifica() {
        parametrosVista();
        render("modificar");
    }

    @POST
    public void eliminar() {

        if (Integer.valueOf(param("id")) != null) {
            Socio socio = Socio.findById(Integer.valueOf(param("id")));
            Usuario usuario = socio.getUsuario();
            usuario.deleteCascadeShallow();
            socio.deleteCascadeShallow();
            redirect();
        } else {
            render().noLayout();
        }

    }

    @POST
    public void modificar() {
        
        Validaciones.validarSocio(params1st());
        Integer usuario_ingresado = Integer.valueOf(param("dni"));

        if (Socio.first("dni = ? ", usuario_ingresado) == null) { // si me devuelve nulo es que el dni no esta regitrado
            Socio socio = Socio.findById(Integer.parseInt(param("id")));
            socio.set("apeynom", (param("apeynom").toUpperCase()));
            socio.set("dni", Integer.valueOf(param("dni")));
            socio.set("localidad_id", Integer.valueOf(param("localidad")));
            // le dejo la fecha de ingreso que ya tiene
            socio.saveIt();
            Usuario usuario = new Usuario();
            usuario.set("username", param("dni"));
            usuario.set("passwd", param("dni"));
            usuario.saveIt();
            Rol rol = Rol.findFirst("rolename = ? ", "socio");
            usuario.add(rol);
            redirect(AbmSociosController.class, "mostrar");

        } else {
            parametrosVista();
            view("error", "Error ingrese un dni distinto");
            render("mostrar");
        }

    }

    @POST
    public void agregar() {
        try {
            Validaciones.validarSocio(params1st()); // valido que los parametros no esten vacios
            Integer usuario_ingresado = Integer.valueOf(param("dni"));
            if (Socio.first("dni = ? ", usuario_ingresado) == null) { // si me devuelve nulo es que el dni no esta regitrado

                //procedo a crear el socio
                Socio socio = new Socio();
                socio.set("apeynom", String.valueOf(param("apeynom").toUpperCase()));
                socio.set("dni", Integer.valueOf(param("dni")));
                socio.set("localidad_id", Integer.valueOf(param("localidad")));
                socio.set("fecha_alta", Date.valueOf(param("fecha")));
                socio.set("email", String.valueOf(param("email").toLowerCase()));
                socio.save();
                //creo el usuario, el dni ingresado es el usuario y contraseña para loguearse
                Usuario usuario = new Usuario();
                usuario.set("username", param("dni"));
                usuario.set("passwd", param("dni"));
                usuario.saveIt();
                Rol rol = Rol.findFirst("rolename = ? ", param("rol"));
                usuario.add(rol);
                flash("mensaje", "Socio Agregado con exito");
                redirect();
                //            socio.saveIt();
//            if (param("rol").equals("socio")) { // pregunto si
//
//                Usuario usuario = new Usuario();
//                usuario.set("username", param("dni"));
//                usuario.set("passwd", param("dni"));
//                usuario.saveIt();
//                Rol rol = Rol.findFirst("rolename = ? ", "admin_mutual");
//                usuario.add(rol);
//                flash("mensaje", "Datos modificado con exito - Admin");
//                redirect();
//                return;
//                
//            } else { // si no es socio

//                Usuario usuario = new Usuario();
//                usuario.set("username", param("dni"));
//                usuario.set("passwd", param("dni"));
//                usuario.saveIt();
//                Rol rol = Rol.findFirst("rolename = ? ", "admin_mutual");
//                usuario.add(rol);
//                flash("mensaje", "Datos modificado con exito - Socio");
//                redirect();
//                return;
//        }

            }
        } catch (IllegalArgumentException e) {
            parametrosVista();
            view("error", "Error No Se Registro el Usuario");
            render("index");
        }

    }

    @POST
    public void pruebaVerificarRecaptcha() {
        try {
            if (!requestHas("su_email")) {
                throw new IllegalArgumentException("Debe ingresar el correo");
            }

            if (!requestHas("g-recaptcha-response")) {
                throw new IllegalArgumentException("Falta el código captcha para verificar.");
            }

            try {
                new RecaptchaV3Helper().verify(param("g-recaptcha-response"));
            } catch (NamingException ex) {
                logError(ex.getMessage(), ex);
                throw new IllegalArgumentException("Ha fallado la verificación de ReCaptcha. Revise los logs.");
            }

        } catch (IllegalArgumentException ex) {
            flash("error", ex.getMessage());
            redirect();
            return;
        }

        flash("mensaje", "El captcha se verificó correctamente!");
        redirect();
    }

    public void parametrosVista() {

        LazyList lista_localidades = Localidad.findAll();
        LazyList lista_socios = Socio.findAll();
        view("socios", lista_socios);
        view("socio_id", getId());
        view("localidades", lista_localidades);

    }

}
