<div class="container">
    <table class="table mb-auto">
        <form class="form-inline my-2 my-lg" action="${context_path}/cargar_consumo/pepito" method="post">
            <tr>
            <label class="col-sm-2 col-form-label">Usuario</label>
            <select class="custom-select" name="socio" required>
                    <#list socios as socios >
                <option value="${socios.id}" >${socios.apeynom}</option>
                    </#list>
                </select>
            <div class="invalid-feedback">Elija un Usuario</div>
            <label class="col-sm-2 col-form-label">Comercio</label>
            <select class="custom-select" name="comercio" required>
                    <#list  comercios as comercios>
                <option value="${comercios.id}">${comercios.razon_social}</option>
                    </#list>                
                </select>
            <div class="invalid-feedback">Elija un Comercio</div>
            <label  class="col-sm-2 col-form-label">Fecha</label>               
            <input type="date" class="form-control" name="fecha"required>                
            <label class="col-sm-2 col-form-label">Importe</label>                
            <input type="numeric" class="form-control" name="importe" placeholder="Ingrese Importe" required>

            <br>
            <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Cargar Consumo</button>
            </tr>
            </form>
        </table>
    </div>

