<table class="table mb-auto" id="myTable">   
    <thead class="thead-dark">
        <tr>
            <th>Apellido y Nombre</th>
            <th>Rol</th>
            <th>Dni</th>
            <th>Fecha Alta</th>
            <th>Localidad</th>
            <th>E-Mail</th>
            <th>Modificar</th>
            <th>Eliminar</th>
            </tr>
        </thead>

    <tbody id="tablafecha">
        <#list  socios as socios>
        <tr>
            <td>${socios.apeynom}</td>
            <td>${socios.tipoRol}</td>
            <td>${socios.dni}</td>
            <td>${socios.fecha_alta}</td>
            <td>${socios.localidad.descripcion}</td>
            <td>${socios.email}</td>
            <td><a class="btn btn-outline-warning my-2 my-sm-0" href="${context_path}/abm_socios/modifica/${socios.id}" role="button"> Modificar</a></td>
            <td>
                <form class="form-inline my-2 my-lg" action="${context_path}/abm_socios/eliminar" method="post" onsubmit="return confirm(\"¿Esta seguro?\")">                        
                    <input type="hidden" name="id" value="${socios.id}">
                    <@render partial="recaptcha" />
                    <button class="btn btn-outline-danger" type="submit" >Eliminar</button>
                </form>
            </td>
            </tr>            
        </#list>  
        </tbody>
    </table>