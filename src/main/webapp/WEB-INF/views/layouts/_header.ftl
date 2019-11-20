<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Mutual Montotos</title>
        <link rel="stylesheet" href="${context_path}/css/bootstrap.min.css"> 
        <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha256-pasqAKBDmFT4eHoN2ndd6lN370kFiGUFyTiUHWhU7k8=" crossorigin="anonymous"></script>
        <script src="${context_path}/js/bootstrap.min.js"></script>
        <script src="${context_path}/js/validarFormulario.js"></script>
        <script src="${context_path}/js/funcionFiltrado.js"></script>
        <script src="${context_path}/js/confirmarEliminado.js"></script>
        </head>
    <body>
        <nav class="navbar navbar-expand-md navbar-dark bg-dark ">
            <h1 class="navbar-brand btn btn-outline-success my-2 my-sm-0">Mutual Montotos</h1>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExampleDefault" aria-controls="navbarsExampleDefault" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
                </button>

            <div class="collapse navbar-collapse" id="navbarsExampleDefault">
                <ul class="navbar-nav mr-auto">
                    <li class="nav-item active">
                        <a class="nav-link btn-outline-success" href="${context_path}/inicio">Inicio <span class="sr-only">(current)</span></a>
                        </li>
                    <li class="nav-item btn-outline-success">
                        <a class="nav-link active"  href="${context_path}/consumo" name="consumo" >Consumos<span class="sr-only">(current)</span></a>
                        </li>
                    <#if session.is_admin!false >
                    <li class="nav-item  dropdown btn-outline-success">
                        <a class="nav-link active" href="${context_path}/cargar_consumo">Cargar Consumo</a>
                    </li>       
                    <div class="btn-group btn-outline-success">
                        <button type="button" class="btn btn-dark">Socios</button>
                        <button type="button" class="btn btn-dark dropdown-toggle dropdown-toggle-split" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            <span class="sr-only">Socios</span>
                        </button>
                        <div class="dropdown-menu">
                            <a class="dropdown-item" href="${context_path}/abm_socios">Alta-Baja-Modificacion</a>
                            <div class="dropdown-divider"></div>
                                <a class="dropdown-item" href="${context_path}/abm_socios/mostrar">Lista Socios</a>
                            </div>
                        </div>
                        <div class="btn-group btn-outline-success">
                        <button type="button" class="btn btn-dark">Comercios</button>
                        <button type="button" class="btn btn-dark dropdown-toggle dropdown-toggle-split" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            <span class="sr-only">Comercios</span>
                        </button>
                        <div class="dropdown-menu">
                            <a class="dropdown-item " href="${context_path}/abm_comercios">Alta-Baja-Modificacion</a>
                            <div class="dropdown-divider"></div>
                                <a class="dropdown-item" href="${context_path}/abm_comercios/mostrar">Lista Comercios</a>
                            </div>
                        </div>
                        <div class="btn-group btn-outline-success">
                        <button type="button" class="btn btn-dark">Localidades</button>
                        <button type="button" class="btn btn-dark dropdown-toggle dropdown-toggle-split" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            <span class="sr-only">Localidades</span>
                        </button>
                        <div class="dropdown-menu">
                            <a class="dropdown-item " href="${context_path}/abm_localidades">Alta-Baja-Modificacion</a>
                            <div class="dropdown-divider"></div>
                                <a class="dropdown-item" href="${context_path}/abm_localidades/mostrar">Lista Localidades</a>
                            </div>
                        </div>
                    </#if>
                    <#if session.is_socio!false >
                        <li class="nav-item  dropdown btn-outline-success">
                            <a class="nav-link active" href="${context_path}/datos_personales">Datos Personales</a>
                        </li>
                    </#if>
                    </ul>
                <form class="form-inline my-2 my-lg" action="${context_path}/logout">
                    <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Salir</button>
                    </form>
                </div>
            </nav>


