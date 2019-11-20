<br><br>
<div class="container">  <div class="row">
        <div class="col">
            
        </div>
        <div class="row btn btn-outline-success my-2 my-sm-0">                   
            <input type="text" id="myInput" onkeyup="funcionFiltrado()" placeholder="Ingrese su busqueda">   
        </div>
    <table class="table mb-auto" id="myTable">   
        <thead class="thead-dark">
            <tr>
                <th>Descripcion</th>
            <tr>
        </thead>
        <tbody>
            <#list  localidades as localidades>
            <tr>
                <td>${localidades.descripcion}</td>
                </tr>            
            </#list>  
            </tbody>
        </table>
    <br><br>
