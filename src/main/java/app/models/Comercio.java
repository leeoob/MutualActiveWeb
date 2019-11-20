/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.models;

import org.javalite.activejdbc.Model;
import org.javalite.activejdbc.annotations.BelongsTo;
import org.javalite.activejdbc.annotations.Table;

/**
 *
 * @author crybaby
 */
@Table("mutual.comercios")
@BelongsTo(foreignKeyName = "localidad_id", parent = Localidad.class)
public class Comercio extends Model {

    public Localidad getLocalidad() {

        return parent(Localidad.class);

    }
}
