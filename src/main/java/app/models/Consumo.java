/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.models;

import java.util.LinkedList;
import org.javalite.activejdbc.LazyList;
import org.javalite.activejdbc.Model;
import org.javalite.activejdbc.annotations.BelongsTo;
import org.javalite.activejdbc.annotations.BelongsToParents;
import org.javalite.activejdbc.annotations.Table;

/**
 *
 * @author crybaby
 */
@Table("mutual.consumos")
@BelongsToParents({
    @BelongsTo(parent = Comercio.class, foreignKeyName = "comercio_id")
    , @BelongsTo(parent = Socio.class, foreignKeyName = "socio_id")})

public class Consumo extends Model {

    public static LazyList getConsumoByIdSocio(Integer id) {
        LazyList lista_consumos;
        lista_consumos = Consumo.where("socio_id = ? ", id);

        return lista_consumos;
    }

    public static LazyList getConsumoBySocio(Integer id) {
        return where("socio_id = ?", id).include(Comercio.class);
    }

    public Comercio getComercio() {
        return parent(Comercio.class);
    }
}
