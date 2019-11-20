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
            <tr>
        </thead>
        <tbody>
            <#list  comercios as comercios>
            <tr>
                <td>${comercios.razon_social}</td>
                <td>${comercios.fecha_alta}</td>
                <td>${comercios.domicilio!}</td>
                <td>${comercios.localidad.descripcion}</td>
                </tr>            
            </#list>  
            </tbody>
        </table>
    <br><br>
