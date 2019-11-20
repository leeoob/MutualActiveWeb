<div class="container">
    <div class="row">
        <div class="col">
            
        </div>
        <div class="row btn btn-outline-success my-2 my-sm-0">                   
            <input type="text" id="myInput" onkeyup="funcionFiltrado()" placeholder="Ingrese su busqueda">   
        </div>
        <br><br>
    <table class="table mb-auto" id="myTable">   
        <thead class="thead-dark">
            <tr>
                <th>Apellido y Nombre</th>
                <th>Rol</th>
                <th>Dni</th>
                <th>Fecha Alta</th>
                <th>Localidad</th>
                <th>E-Mail</th>
            <tr>
        </thead>
        <tbody>
            <#list  socios as socios>
            <tr>
                <td>${socios.apeynom}</td>
                <td>${socios.tipoRol}</td>
                <td>${socios.dni}</td>
                <td>${socios.fecha_alta}</td>
                <td>${socios.localidad.descripcion}</td>
                <td>${socios.email}</td>
                </tr>            
            </#list>  
            </tbody>
        </table>
    <br><br>
