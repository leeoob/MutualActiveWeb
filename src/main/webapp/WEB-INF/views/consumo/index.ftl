

<br><br><br>

<div class="container">
    
    <#if consumos?hasContent>    
    <div class="row">
        <div class="col">
            <h1>Consumo Detallado de: ${socio.apeynom}</h1>
        </div>
        <div class="row btn btn-outline-success my-2 my-sm-0">                   
            <input type="text" id="myInput" onkeyup="funcionFiltrado()" placeholder="Ingrese su busqueda">   
        </div>
    </div>
</div>
<br><br>
<div class="container">
    <div class="card">
        <table class="table mb-auto" id="myTable">   
            <thead class="thead-dark">
                <tr>
                    <th>Fecha</th>
                    <th>Comercio</th>
                    <th>Importe</th>
                    <th>Codigo Transaccion</th>
                </tr>
            </thead>

            <tbody id="tablafecha">                
                <#list  consumos as consumo>
                    <tr>
                        <td>${consumo.fecha}</td>
                        <td>${consumo.comercio.razon_social}</td>
                        <td>${consumo.importe}</td>
                        <td>${consumo.codigo_transaccion}</td>
                    </tr>
                </#list>
            </tbody>
        </table>
    </div>
    <#else >
        <div class="alert alert-danger" role="alert">
            <p>Usted no tiene Consumos</p>
        </div> 
    </#if>
</div>
<br><br><br>

