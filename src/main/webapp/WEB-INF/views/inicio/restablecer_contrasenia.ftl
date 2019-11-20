<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Mutual Montotos</title>
        <link rel="stylesheet" href="${context_path}/css/bootstrap.min.css"> 
        <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha256-pasqAKBDmFT4eHoN2ndd6lN370kFiGUFyTiUHWhU7k8=" crossorigin="anonymous"></script>
        <script src="${context_path}/js/bootstrap.min.js"></script>       
        </head>
    <body class="text-center">

        <form  action="${context_path}/inicio/cambiar_contrasenia" method="post">
            <div class="row justify-content-center">
                <div class="col-sm-12">
                    <h2>Modificar Su Contraseña</h2>
                    </div>
                </div>
            <div class="row">
                <div class="col-sm-6">
                    <label for="inputPassword">Ingrese su nueva Contraseña</label>
                    <input type="password" class="form-control" placeholder="Nueva Contraseña" name="nuevacontrasenia" required>
                    </div>
                </div>
            <div class="row">
                <div class="col-sm-6">
                    <label for="inputPassword">Confirme su nueva contrasenia</label>
                    <input type="password" class="form-control" placeholder="Confirme su Nueva Contraseña" name="confirnuevacontrasenia" required>
                    </div>
                </div>
            <input type="hidden" name="id" value="${socio.id}">
            <button class="btn btn-lg btn-primary btn-block" type="submit">Cambiar Contraseña</button>
            </form>
        </body>
    </html>
