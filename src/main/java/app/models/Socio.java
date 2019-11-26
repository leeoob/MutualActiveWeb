/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.models;

import java.math.BigDecimal;
import org.javalite.activejdbc.Base;
import org.javalite.activejdbc.Model;
import org.javalite.activejdbc.annotations.BelongsTo;
import org.javalite.activejdbc.annotations.Table;
import org.javalite.common.Util;

/**
 *
 * @author crybaby
 */
@Table("mutual.socios")
@BelongsTo(foreignKeyName = "localidad_id", parent = Localidad.class)

public class Socio extends Model {

    public BigDecimal getImporteBySocio() {

        String sql = "SELECT COALESCE(SUM(importe) , 0) as importe "
                + "FROM mutual.consumos "
                + "WHERE mutual.consumos.socio_id = ? ;";
        BigDecimal importe = (BigDecimal) Base.firstCell(sql, getId());

        return importe;
    }

    public Localidad getLocalidad() {

        return parent(Localidad.class);

    }

    public Usuario getUsuario() {

        return Usuario.first("username = ? ", String.valueOf(getInteger("dni")));
    }

    public String getTipoRol() {
        if (!isTieneUsuario()) {
            return null;
        }
        if (getUsuario().isHasAdmin()) {
            return "Administrador";
        } else if (getUsuario().isHasSocio()) {
            return "Socio";
        } else {
            return null;
        }

    }

    public boolean isTieneUsuario() {
        return !Util.blank(getUsuario());
    }
    
    

}
