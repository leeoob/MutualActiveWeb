/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.config;

import app.models.Socio;
import app.util.helpers.TomcatHelper;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import org.javalite.activeweb.controller_filters.HttpSupportFilter;

/**
 *
 * @author c1yb4by
 */
public class SocioFilter extends HttpSupportFilter{

    @Override
    public void before() {
        
        Socio socio;
        if (!sessionHas("socio")) {
            try {
                socio = Socio.first("dni = ? ", Integer.valueOf(getHttpServletRequest().getRemoteUser()));
                session("socio", socio);
                session("recaptcha_public_key", (String) TomcatHelper.getVariable("recaptcha_public_key"));
            } catch (NamingException ex) {
                Logger.getLogger(SocioFilter.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    
    
}
