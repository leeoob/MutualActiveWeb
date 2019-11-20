/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.controllers;

import app.models.Consumo;
import app.models.Socio;
import app.models.Usuario;
import app.util.helpers.RecaptchaV3Helper;
import app.util.helpers.TomcatHelper;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.naming.NamingException;
import org.apache.commons.mail.EmailException;
import org.javalite.activejdbc.LazyList;
import org.javalite.activeweb.AppController;
import org.javalite.activeweb.annotations.GET;
import org.javalite.activeweb.annotations.POST;
import org.javalite.common.Util;
import org.apache.commons.lang3.time.DateUtils;

/**
 *
 * @author crybaby
 */
public class InicioController extends AppController {

    public void index() throws NamingException {

        boolean is_admin = getHttpServletRequest().isUserInRole("admin_mutual");
        boolean is_socio = getHttpServletRequest().isUserInRole("socio");

        if (is_admin == true) { // pregunto si es administrador

            datosAdminIndex();
            session("is_admin", is_admin);

        } else if (is_socio == true) { // sino es socio

            datosSocioIndex();
            session("is_socio", is_socio);
        }
    }

    public void olvideContrasenia() {
        render().noLayout();
    } 
    
    public void restablecerContrasenia() {
        try {
            DecodedJWT token_verificado = verificarJWT(param("token"));
            Integer dni = Integer.parseInt(token_verificado.getSubject());
            Socio socio = Socio.findFirst(" dni = ? ", dni);
            view("socio", socio);
            render().noLayout();

        } catch (NumberFormatException | NamingException ex) {
            logError(ex.getMessage(), ex);
            view("error", "El token no pudo validarse: " + ex.getMessage());
        }

    }

    @POST
    public void enviarMail() {

        // hacervalidaciones
        Map parametros = new HashMap();
        parametros.put("context_path", context());
        String para = param("email");
        String asunto = "Restablecer su contraseña";
        String token = generarJWT(param("dni"));
        parametros.put("token", token);

        String mensaje = merge("/inicio/mail_reset_password", parametros);

        try {
            MailHelper.mandarMail(para, asunto, mensaje);

        } catch (NamingException | EmailException ex) {
            logError(ex.getMessage(), ex);
            view("error", "No se pudo enviar el correo: " + ex.getMessage());
            render("index");
            
        }
        flash("mensaje", "El mail se envió correctamente");
        redirect();

    }

    @POST
    public void cambiarContrasenia() {

        Map parametros = params1st();        
 
        if (!Util.blank(parametros)) {
            // if (parametros.) {
           
            Socio socio = Socio.findById(Integer.parseInt((String) parametros.get("id")));
            Usuario usuario = socio.getUsuario();
            usuario.set("passwd", (String) parametros.get("nuevacontrasenia"));
            usuario.saveIt();            
            flash("mensaje", "La contraseña se cambio exitosamente");
            redirect(context() + "/logout");
        } else {
            flash("error", "Error no se pudo realizar el cambio de Contraseña");
            redirect(InicioController.class, "restablecer_contrasenia");
        }

    }

    public void datosAdminIndex() {

        Socio socio = Socio.first("dni = ? ", Integer.valueOf(getHttpServletRequest().getRemoteUser()));
        LazyList<Consumo> lista_consumos = Consumo.getConsumoByIdSocio(socio.getInteger("id"));
        LazyList lista_socios = Socio.findAll();
        view("socio", socio);
        view("socios", lista_socios);
        view("consumos", lista_consumos);

    }    

    @GET
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
            } catch (Exception ex) {
                logError(ex.getMessage(), ex);
                throw new IllegalArgumentException("Ha fallado la verificación de ReCaptcha. Revise los logs.");
            }

        } catch (Exception ex) {
            flash("error", ex.getMessage());
            redirect();
            return;
        }

        flash("mensaje", "El captcha se verificó correctamente!");
        redirect();
    }

    // https://github.com/auth0/java-jwt
    private String generarJWT(String dni) {
        try {
            String secret = (String) TomcatHelper.getVariable("jwt_private_key");
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer("mutual")
                    .withSubject(dni)                    
                    .withExpiresAt(DateUtils.addMinutes(new Date(), 2)) // expira en 2 minutos
                    .sign(algorithm);
        } catch (JWTCreationException | IllegalArgumentException | NamingException ex) {
            throw new RuntimeException("No se pudo generar el token de prueba.", ex);
        }
    }

    // https://github.com/auth0/java-jwt
    private DecodedJWT verificarJWT(String token) throws NamingException {
        String secret = (String) TomcatHelper.getVariable("jwt_private_key");
        Algorithm algorithm = Algorithm.HMAC256(secret);
        JWTVerifier verifier = JWT.require(algorithm)
                .withIssuer("mutual")
                .acceptLeeway(2 * 60) // 2 minutos de "diferencia"
                .build();
        DecodedJWT jwt = verifier.verify(token);
        return jwt;
    }

    public void datosSocioIndex() throws NamingException {

        Socio socio = null;

        if (!sessionHas("socio")) {
            socio = Socio.first("dni = ? ", Integer.valueOf(getHttpServletRequest().getRemoteUser()));
        } else {
            socio = (Socio) session("socio");
        }
        LazyList<Consumo> lista_consumos = Consumo.getConsumoByIdSocio(socio.getInteger("id"));
        view("socio", socio);
        view("consumos", lista_consumos);
        session("socio", socio);
        

    }

}
