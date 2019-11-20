<div class="container">
    <table class="table mb-auto">
        <tbody>
        <tr>    
        <label>Datos a modificar del Socio : </label>
        <form class="form-inline my-2 my-lg" action="${context_path}/abm_socios/modificar" onsubmit="return validarFormulario()" method="post">
            <label  class="col-sm-2 col-form-label">Apellido y Nombre</label>
            <input type="text" name="apeynom" id="apeynom" pattern="[A-Za-z]{0-30}" placeholder="Ingrese Nuevo Apellido y Nombre" onkeypress="return permitidos(event, 'caracteres')" required>
            <label class="col-sm-2 col-form-label">Dni</label>
            <input type="number"  class="form-control" name="dni"  id="dni" placeholder="Ingrese Nuevo Dni" required>
            <label class="col-sm-2 col-form-label">Localidad</label>
            <select class="custom-select" name="localidad" id="localidad" required>
                <#list  localidades as localidades>
                    <option value="${localidades.id}">${localidades.descripcion}</option>
                </#list>                
            </select>
            <div class="invalid-feedback">Elija una Localidad</div>
            <br>
            <input type="hidden" name="id" value="${socio_id}">
            <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Confirmar</button>
            </form>
        </tr>
        </tbody>
        </table>
    </div>

