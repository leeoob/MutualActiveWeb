package app.controllers;

import org.javalite.activeweb.controller_filters.HttpSupportFilter;

/**
 *
 * @author crybaby
 */
public class LogueoFilter extends HttpSupportFilter {

    @Override
    public void after() {
        logDebug("Estamos saliendo de la accion : " + getRoute().getActionName());
    }

}
