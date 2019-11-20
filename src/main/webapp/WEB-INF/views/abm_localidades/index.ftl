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
                <th>Descripcion</th>
                <th>Modificar</th>
                <th>Eliminar</th>
            </tr>
            </thead>

            <tbody>
                    <#list  localidades as localidades>
                <tr>
                    <td>${localidades.descripcion}</td>
                    <td><a class="btn btn-outline-warning my-2 my-sm-0" href="${context_path}/abm_localidades/modifica/${localidades.id}" role="button">Modificar</a></td>
                    <td>
                        <form class="form-inline my-2 my-lg" action="${context_path}/abm_localidades/eliminar" method="post" onsubmit="return confirmarEliminado()">                        
                            <input type="hidden" name="id" value="${localidades.id}"></input>
                            <button class="btn btn-outline-danger" type="submit" >Eliminar</button>
                        </form>
                    </td>
                </tr>            
                    </#list>  
            </tbody>
        </table>
    
        <a class="btn btn-outline-success my-2 my-sm-0" href="#agregarLocalidad" data-toggle="collapse" role="button">Agregar Localidad</a>
    
        <table class="table mb-auto">
            <tbody>
                <tr>
                <div class="collapse" id="agregarLocalidad">
                    <form class="form-inline my-2 my-lg" action="${context_path}/abm_localidades/agregar" method="post">
                        <label  class="col-sm-2 col-form-label">Descripcion</label>
                        <input type="text" name="descripcion" pattern="[A-Za-z]{0-30}" placeholder="Ingrese Descripcion" required></input>                        
                        <br>
                        <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Confirmar</button>
                        </form>
                    </div>
                </tr>
            </tbody>
        </table>
    </div>
<br><br>


