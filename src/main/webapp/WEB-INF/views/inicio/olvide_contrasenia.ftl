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
        <div id="start-of-content" class="show-on-focus"></div>
        <div class="application-main " data-commit-hovercards-enabled="">
            <main id="js-pjax-container" data-pjax-container="">
                <div class="auth-form px-3">
                    <form id="forgot_password_form" action="${context_path}/inicio/enviar_mail" method="post"> 
                        <div class="auth-form-header p-0">
                            <h1>Restablezca su contraseña</h1>
                            </div>
                        <div id="js-flash-container">

                            </div>

                        <div class="auth-form-body mt-3">
                            <label>Ingrese su Correo Electronico para que se le envie un link para restablecer su contraseña</label>
                            <input type="email" name="email" class="form-control input-block" autofocus="autofocus" placeholder="Ingrese su correo electronico" required>
                            <label>Ingrese su Dni para poder verificar</label>
                            <input type="numeric" name="dni" class="form-control input-block" autofocus="autofocus" placeholder="Ingrese su Dni" required>
                            <input name="commit" type="submit" value="Send password reset email" class="btn btn-primary btn-block">
                            </div>
                        </form>
                    </div>
                </main>
            </div>  
        </body>
    </html>
