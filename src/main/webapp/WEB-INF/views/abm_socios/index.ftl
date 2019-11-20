<div class="row">
        <div class="col">
        </div>
        <div class="col">                   
            <input type="text" id="myInput" onkeyup="funcionFiltrado()" placeholder="Ingrese su busqueda">   
        </div>
       <@render partial="tablaSocios" />
<!--         -- Boton y tabla agregar Socio-->
        <a class="btn btn-outline-success my-2 my-sm-0" href="#agregarSocio" data-toggle="collapse" role="button">Agregar Socio</a>

        <table class="table mb-auto">
            <tbody>
                <tr>
            <div class="collapse" id="agregarSocio">
                <form class="form-inline my-2 my-lg" action="${context_path}/abm_socios/agregar" onsubmit="return validarFormulario()" method="post">
                    <div class="form-group row">
                        <div class="col-sm-10">
                            <label  class="col-sm-2 col-form-label">Apellido y Nombre</label>
                            <input type="text" name="apeynom" id="apeynom" pattern="[A-Za-z]{0-30}" placeholder="Ingrese Apellido y Nombre" onkeypress="return permitidos(event, 'caracteres')" required>
                            </div>
                        </div>
                    <div class="form-group row">
                        <div class="col-sm-10">
                            <label class="col-sm-2 col-form-label">Dni</label>
                            <input type="number"  class="form-control" name="dni"  id="dni" placeholder="Ingrese Dni" required>
                            </div>
                        </div>
                    <div class="form-group row">
                        <div class="col-sm-10">                    
                            <label class="col-sm-2 col-form-label">Localidad</label>
                            <select class="custom-select" name="localidad" id="localidad" required>
                            <#list  localidades as localidades>
                                <option value="${localidades.id}">${localidades.descripcion}</option>
                            </#list>                
                                </select>
                            <div class="invalid-feedback">Elija una Localidad</div>
                            </div>
                        </div>
                    <div class="form-group row">
                        <div class="col-sm-10">  
                            <label  class="col-sm-2 col-form-label">Fecha</label>
                            <input type="date" class="form-control" name="fecha" id="fecha" required>
                            </div>
                        </div>
                    <div class="form-group row">
                        <div class="col-sm-10">  
                            <label  class="col-sm-2 col-form-label">E-mail</label>
                            <input type="email" class="form-control" name="email" id="email" required>
                            </div>
                        </div>
                    <fieldset class="form-group">
                        <div class="row">
                            <legend class="col-form-label col-sm-2 pt-0">Seleccione el Rol</legend>
                            <div class="col-sm-10">
                                <div class="form-check">
                                    <input class="form-check-input" type="radio" name="rol" id="gridRadios1" value="admin_mutual">
                                    <label class="form-check-label" for="gridRadios1">Administrador</label>
                                    </div>
                                <div class="form-check">
                                    <input class="form-check-input" type="radio" name="rol" id="gridRadios2" value="socio">
                                    <label class="form-check-label" for="gridRadios2">Socio</label>
                                    </div>
                                </div>
                            </div>
                        </fieldset>
                    <div class="form-group row">
                        <div class="col-sm-10">
                            <@render partial="/captcha/recaptcha" />
                            <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Confirmar</button>
                            </div>
                        </div>
                    </form>
                </div>
            </tr>
            </tbody>
            </table>
        </div>
    <br><br>

