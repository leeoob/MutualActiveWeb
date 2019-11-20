/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.config;

import app.controllers.CambiarMailController;
import app.controllers.InicioController;
import app.models.Socio;
import org.javalite.activeweb.controller_filters.HttpSupportFilter;

/**
 *
 * @author c1yb4by
 */
public class MailFilter extends HttpSupportFilter {

    @Override
    public void before() {
        
        Socio socio = (Socio) session("socio");        
        if (socio.get("email").equals("socio@example.com")) {
            redirect(CambiarMailController.class);
        }

    }

}
