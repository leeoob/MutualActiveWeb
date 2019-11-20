<table class="table mb-auto">
            <tbody>
                <tr>
                <div class="container">
                    <form class="form-inline my-2 my-lg" action="${context_path}/abm_comercios/modificar" method="post">
                        <label  class="col-sm-2 col-form-label">Razon Social</label>
                        <input type="text" name="razon_social" pattern="[A-Za-z]{0-30}" placeholder="Ingrese Razon Social" required></input>
                        <label  class="col-sm-2 col-form-label">Domicilio</label>
                        <input type="text" name="domicilio" pattern="[A-Za-z]{0-30}" placeholder="Ingrese Domicilio" required></input>
                        <label class="col-sm-2 col-form-label">Localidad</label>
                        <select class="custom-select" name="localidad" required>
                            <#list  localidades as localidades>
                                <option value="${localidades.id}">${localidades.descripcion}</option>
                            </#list>                
                        </select>
                        <div class="invalid-feedback">Elija una Localidad</div>                        
                        <br>
                        <input type="hidden" name="id" value="${comercio_id}">
                        <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Confirmar</button>
                        </form>
                    </div>
                </tr>
            </tbody>
        </table>
