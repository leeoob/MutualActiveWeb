

<main role="main">
    <div class="jumbotron">
        <div class="container">
            <label class="display-3">Bienvenido : ${socio.apeynom}</label>           

            <br><br>

            <div class="row">
                <div class="col">
                    <h2>Dni: ${socio.dni}</h2>
                </div>

                <div class="col">
                    <h2>Socio Desde : ${socio.fecha_alta}</h2>
                    <div class="row">
                    <div>
                    <br><br>
                    <p><a class="btn btn-outline-success my-2 my-sm-0" href="#collapseExample" data-toggle="collapse" role="button">Ver Detalle &raquo;</a></p>
                        <div class="collapse" id="collapseExample">
                            <div class="card card-body">
                                <#if consumos?hasContent >
                                <table class="table mb-auto">   
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
                                <#else >
                                    <div class="alert alert-danger" role="alert">
                                        <p>Usted no tiene Consumos</p>
                                    </div> 
                                </#if>
                                </div>
                            </div>
                        </div>    
                    </div>
                </div>
            </div>


        </div>
    </div>

    <div class="container ">

        <#if  session.is_admin!false >
        <div class="row">
            <div class="col">

                </div>

            <div class="row btn btn-outline-success my-2 my-sm-0">
                <label class="col-form-label">Cambiar Socio</label>
                <form action="${context_path}/inicio" method="get"/>
                <select name="socio" class="custom-radio" onchange="this.form.submit()">                        
                            <#list socios as socios >
                    <option value="${socios.dni}"> ${socios.apeynom}</option>
                            </#list>
                    </select>
                </form>
                </div>
            </div>     

        </#if>


        <div class="card col-4">
            <h5 class="card-header">Estado de cuenta de: ${socio.apeynom}</h5>
            <div class="card-body">
                <h5 class="card-title">Total a pagar : </h5>
                <p class="card-text">$${socio.importeBySocio}</p>
                <a href="" class="btn btn-outline-success my-2 my-sm-0">Pagar</a>
                </div>
            </div>

        </div>  

    </div> 

</main>
<br><br><br>


