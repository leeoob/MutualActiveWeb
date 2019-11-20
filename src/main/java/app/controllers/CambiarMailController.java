/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.controllers;

import app.models.Socio;
import java.util.Map;
import org.javalite.activeweb.AppController;
import org.javalite.activeweb.annotations.POST;
import org.javalite.common.Convert;
import org.javalite.common.Util;

/**
 *
 * @author c1yb4by
 */
public class CambiarMailController extends AppController {

    public void index() {

    }

    @POST
    public void cambiarMailPost() {

        Map parametros = params();

        if (!Util.blank(parametros)) {
            if (Socio.first("email = ? ", Convert.toString(parametros.get("mail"))) == null) {
                Socio socio = Socio.findById(Convert.toInteger(parametros.get("id")));
                socio.set("email", Convert.toString(parametros.get("mail")));
                socio.saveIt();
                flash("mensaje", "El mail se cambio correctamente");
                redirect(InicioController.class, "index");
            }
        } else {
            view("error", "Ingrese un Email Valido");
            render();

        }
    }
}
