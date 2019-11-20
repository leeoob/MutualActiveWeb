<br><br>
<div class="container"> 
    <div class="row">
        <div class="col">
            
        </div>
        <div class="row btn btn-outline-success my-2 my-sm-0">                   
            <input type="text" id="myInput" onkeyup="funcionFiltrado()" placeholder="Ingrese su busqueda">   
        </div>
    <table class="table mb-auto" id="myTable">   
        <thead class="thead-dark">
            <tr>
                <th>Razon Social</th>
                <th>Fecha Alta</th>
                <th>Domicilio</th>
                <th>Localidad</th>
                <th>Modificar</th>
                <th>Eliminar</th>
                </tr>
            </thead>

            <tbody>
                    <#list  comercios as comercios>
                <tr>
                    <td>${comercios.razon_social}</td>
                    <td>${comercios.fecha_alta}</td>
                    <td>${comercios.domicilio!}</td>
                    <td>${comercios.localidad.descripcion}</td>
                    <td><a class="btn btn-outline-warning my-2 my-sm-0" href="${context_path}/abm_comercios/modifica/${comercios.id}"  role="button"> Modificar</a></td>
                    <td>
                        <form class="form-inline my-2 my-lg" action="${context_path}/abm_comercios/eliminar" method="post" onsubmit="return confirmarEliminado()">                        
                            <input type="hidden" name="id" value="${comercios.id}"></input>
                            <button class="btn btn-outline-danger" type="submit" >Eliminar</button>
                        </form>
                    </td>
                </tr>            
                    </#list>  
            </tbody>
        </table>
<!--        Tabla modificar comercio-->
        
    
        <a class="btn btn-outline-success my-2 my-sm-0" href="#agregarComercio" data-toggle="collapse" role="button">Agregar Comercio</a>
    
        <table class="table mb-auto">
            <tbody>
                <tr>
                <div class="collapse" id="agregarComercio">
                    <form class="form-inline my-2 my-lg" action="${context_path}/abm_comercios/agregar" method="post">
                        <label  class="col-sm-2 col-form-label">Razon Social</label>
                        <input type="text" name="razon_social" pattern="[A-Za-z]{0-30}" placeholder="Ingrese Razon Social" required></input>
                        <label  class="col-sm-2 col-form-label">Fecha</label>
                        <input type="date" class="form-control" name="fecha"required>
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
                        <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Confirmar</button>
                        </form>
                    </div>
                </tr>
            </tbody>
        </table>
    </div>
<br><br>

