package app.models;
import org.javalite.common.Convert;
import org.javalite.common.Util;
import java.util.Map;

public class Validaciones {

    public static void validarSocio(Map parametros){
        
        if (Util.blank(parametros.get("apeynom"))) {
            throw new IllegalArgumentException("El Apellido y/o nombre no es válido.");            
        }
        if (Util.blank(parametros.get("dni"))) {
            throw new IllegalArgumentException("El Dni no es válido.");            
        }
        if (Util.blank(parametros.get("localidad"))) {
            throw new IllegalArgumentException("La localidad no es válida.");            
        }
        if (Util.blank(parametros.get("fecha"))) {
            throw new IllegalArgumentException("La fecha no es válida.");            
        }
        if (Util.blank(parametros.get("email"))) {
            throw new IllegalArgumentException("El email no es válido.");            
        }
    }
    
    public static void validarSocioSinFecha(Map parametros){
        
        if (Util.blank(parametros.get("apeynom"))) {
            throw new IllegalArgumentException("El Apellido y/o nombre no es válido.");            
        }
        if (Util.blank(parametros.get("dni"))) {
            throw new IllegalArgumentException("El Dni no es válido.");            
        }
        if (Util.blank(parametros.get("localidad"))) {
            throw new IllegalArgumentException("La localidad no es válida.");            
        }
        if (Util.blank(parametros.get("email"))) {
            throw new IllegalArgumentException("El email no es válido.");            
        }
    }
   
    public static void validarComercio(Map parametros) {
        if(Util.blank(parametros.get("razon_social"))){
            throw new IllegalArgumentException("Falta el razon social");
        }
        if(Util.blank(parametros.get("fecha_alta"))){
            throw new IllegalArgumentException("Falta el fecha");
        }
        if(Util.blank(parametros.get("localidad_id"))){
            throw new IllegalArgumentException("Falta el localidad");
        }
        if(Util.blank(parametros.get("domicilio"))){
            throw new IllegalArgumentException("Falta el domicilio");
        }

    }
   
   
   
    public static Comercio eliminar(String param){
        Comercio c = null;
        try{
            if(Util.blank(param)){
                throw new IllegalArgumentException("Falta el id");
            }
            Integer id = Convert.toInteger(param);
            if(id < 1){
                throw new IllegalArgumentException("El id no es valido");
            }

            c = Comercio.findById(id);

            if(c == null){
                throw new IllegalArgumentException("El id no es valido");
            }

        }catch(IllegalArgumentException ex){
            //view("error", ex.getMessage());
            //return;
        }
        return c;
    }
    public static Socio eliminarSocio(String param){
        Socio s = null;
        try{
            if(Util.blank(param)){
                throw new IllegalArgumentException("Falta el id");
            }
            Integer id = Convert.toInteger(param);
            if(id < 1){
                throw new IllegalArgumentException("El id no es valido");
            }

            s = Socio.findById(id);

            if(s == null){
                throw new IllegalArgumentException("El id no es valido");
            }

        }catch(IllegalArgumentException ex){
            //view("error", ex.getMessage());
            //return;
        }
        return s;
    }
       
       
       
       
       
    /*    
    public static Date fechas(String fecha){
        Date hoy;
        if(Util.blank(fecha)){
            throw new IllegalArgumentException("Falta la fecha");
        }
        Date fecha_alta = Date.valueOf(fecha);
        if(fecha_alta.before(hoy)){
            throw new IllegalArgumentException("");
        }
        return fecha_alta;
    }*/

    public static void crearLocalidad(String param) {
        if(Util.blank(param)){
            throw new IllegalArgumentException("Falta el nombre de la localidad");
        }
    }
   
    
}