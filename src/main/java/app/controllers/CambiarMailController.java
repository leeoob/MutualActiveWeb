/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.controllers;

import app.models.Socio;
import ar.mppfiles.utils.validation.simple.ParamValidationException;
import ar.mppfiles.utils.validation.simple.ParamValidator;
import org.javalite.activeweb.AppController;
import org.javalite.activeweb.annotations.POST;

/**
 *
 * @author c1yb4by
 */
public class CambiarMailController extends AppController {

    public void index() {

    }

    @POST
    public void cambiarMailPost() throws ParamValidationException {

        ParamValidator parametros = validar();

        if (Socio.first("email = ? ", parametros.getString("mail")) == null) {
            Socio socio = Socio.findById(parametros.getInteger("id"));
            socio.set("email", parametros.getString("mail"));
            socio.saveIt();
            flash("mensaje", "El mail se cambio correctamente");
            redirect(InicioController.class, "index");
        } else {
            flash("error", "El mail se cambio correctamente");
            redirect(InicioController.class, "index");

        }
    }

    public ParamValidator validar() throws ParamValidationException {
        ParamValidator validador = new ParamValidator().fromMap(params1st());
        validador.con("mail").requerido().check();
        return validador;
    }
}
