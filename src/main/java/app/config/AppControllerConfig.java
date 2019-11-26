package app.config;

import app.controllers.CambiarMailController;
import app.controllers.LogoutController;
import app.controllers.LogueoFilter;
import org.javalite.activeweb.AbstractControllerConfig;
import org.javalite.activeweb.AppContext;
import org.javalite.activeweb.controller_filters.DBConnectionFilter;

public class AppControllerConfig extends AbstractControllerConfig {

    @Override
    public void init(AppContext context) {
        //Para la conexión a la base de datos
        add(new DBConnectionFilter("default", true), new LogueoFilter());
        add(new SocioFilter());
        add(new MailFilter()).exceptFor(CambiarMailController.class, LogoutController.class);
        add(new ParamValidationFilter());
    }
}
