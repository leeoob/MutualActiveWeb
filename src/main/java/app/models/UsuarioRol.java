/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.models;

import org.javalite.activejdbc.Model;
import org.javalite.activejdbc.annotations.CompositePK;
import org.javalite.activejdbc.annotations.Table;

/**
 *
 * @author crybaby
 */
@Table("public.usuario_rol")
@CompositePK({"usuario_id", "rol_id"})
public class UsuarioRol extends Model {
    
    public Rol getRol(){
        return parent(Rol.class);    
    }
}
