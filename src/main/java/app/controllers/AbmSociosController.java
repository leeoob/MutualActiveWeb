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
import org.javalite.activejdbc.LazyList;
import org.javalite.activeweb.AppController;
import org.javalite.activeweb.annotations.POST;
import app.util.helpers.RecaptchaV3Helper;
import ar.mppfiles.utils.validation.simple.ParamValidationException;
import ar.mppfiles.utils.validation.simple.ParamValidator;
import javax.naming.NamingException;

/**
 *
 * @author crybaby
 */
public class AbmSociosController extends AppController {

    public void index() {

        LazyList lista_socios = Socio.findAll().orderBy("apeynom");
        LazyList lista_localidades = Localidad.findAll();
        view("socios", lista_socios);
        view("localidades", lista_localidades);
        view("recaptcha_public_key", (String) appContext().get("recaptcha_public_key"));
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
    public void eliminar() throws ParamValidationException {

        ParamValidator parametros = new ParamValidator().fromMap(params1st());
        parametros.con("id").entero().min(1).requerido()
                .check();

        if (parametros.getInteger("id") != null) {
            Socio socio = Socio.findById(parametros.getInteger("id"));
            Usuario usuario = socio.getUsuario();
            usuario.deleteCascadeShallow();
            socio.deleteCascadeShallow();
            redirect();
        } else {
            render().noLayout();
        }

    }

    @POST
    public void modificar() throws ParamValidationException {

        ParamValidator parametros = validar();
        Integer usuario_ingresado = parametros.getInteger("dni");

        if (Socio.first("dni = ? ", usuario_ingresado) == null) { // si me devuelve nulo es que el dni no esta regitrado
            Socio socio = Socio.findById(parametros.getInteger("id"));
            socio.set("apeynom", parametros.getString("apeynom").toUpperCase());
            socio.set("dni", parametros.getInteger("dni"));
            socio.set("localidad_id", parametros.getInteger("localidad"));
            // le dejo la fecha de ingreso que ya tiene
            socio.saveIt();
            Usuario usuario = new Usuario();
            usuario.set("username", parametros.getInteger("dni"));
            usuario.set("passwd", parametros.getInteger("dni"));
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
    public void agregar() throws ParamValidationException {
        try {
            ParamValidator parametros = validar();
            Integer usuario_ingresado = (parametros.getInteger("dni"));
            if (Socio.first("dni = ? ", usuario_ingresado) == null) { // si me devuelve nulo es que el dni no esta regitrado
                //procedo a crear el socio
                Socio socio = new Socio();
                socio.set("apeynom", parametros.getString("apeynom").toUpperCase());
                socio.set("dni", parametros.getInteger("dni"));
                socio.set("localidad_id", parametros.getInteger("localidad"));
                socio.set("fecha_alta", parametros.getDate("fecha"));
                socio.set("email", parametros.getString("email").toLowerCase());
                socio.saveIt();
                //creo el usuario, el dni ingresado es el usuario y contraseña para loguearse          
                if (param("rol").equals("socio")) { // pregunto si el rol agregado es Admin
                    Usuario usuario = new Usuario();
                    usuario.set("username", parametros.getInteger("dni"));
                    usuario.set("passwd", parametros.getInteger("dni"));
                    usuario.saveIt();
                    Rol rol = Rol.findFirst("rolename = ? ", "socio");
                    usuario.add(rol);
                    flash("mensaje", "Datos modificado con exito - Socio");
                    redirect();

                } else { // si no es admin
                    Usuario usuario = new Usuario();
                    usuario.set("username", parametros.getInteger("dni"));
                    usuario.set("passwd", parametros.getInteger("dni"));
                    usuario.saveIt();
                    Rol rol = Rol.findFirst("rolename = ? ", "admin_mutual");
                    usuario.add(rol);
                    flash("mensaje", "Datos modificado con exito - Admin");
                    redirect();
                }

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

    public ParamValidator validar() throws ParamValidationException {
        ParamValidator validador = new ParamValidator().fromMap(params1st());
        validador.con("apeynom").letrasConEspacios().sinNumeros().minLargo(5).requerido()
                .con("dni").entero().requerido()
                .con("localidad_id").entero().min(1).requerido().check();
        if (getRoute().getActionName().equals("agregar")) {
            validador.con("fecha_alta").requerido();
        }
        validador.con("email").texto().requerido()
                .check();
        return validador;
    }
}
