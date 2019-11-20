/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.models;

import org.javalite.activejdbc.Model;
import org.apache.commons.codec.digest.DigestUtils;
import org.javalite.activejdbc.LazyList;
import org.javalite.activejdbc.annotations.Many2Many;
import org.javalite.activejdbc.annotations.Table;
import org.javalite.common.Util;

/**
 *
 * @author crybaby
 */
@Table("public.usuario")
@Many2Many(other = Rol.class, join = "public.usuario_rol", sourceFKName = "usuario_id", targetFKName = "rol_id")
public class Usuario extends Model {

    @Override
    public void beforeSave() {
        set("passwd", encryptPassword());
    }

    private String encryptPassword() {

        return DigestUtils.md5Hex(getString("passwd")).toUpperCase();
    }
    
    public LazyList getRoles(){
    
        return getAll(Rol.class);
    }
    
    public boolean isHasAdmin(){
        
        LazyList rol = get(Rol.class, "rol_id = ? ", 5);
        
        return !Util.empty(rol);
    
    }
    public boolean isHasSocio(){
        
        LazyList rol = get(Rol.class, "rol_id = ? ", 6);
        
        return !Util.empty(rol);
    
    }
}
