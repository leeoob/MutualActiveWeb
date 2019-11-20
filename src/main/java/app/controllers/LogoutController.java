/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.controllers;

import org.javalite.activeweb.AppController;

/**
 *
 * @author crybaby
 */
public class LogoutController extends AppController {

    public void index() {
        getHttpServletRequest().getSession().invalidate();
        redirect(context());
    }

}
