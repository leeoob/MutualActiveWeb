<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="canonical" href="https://getbootstrap.com/docs/4.3/examples/floating-labels/"/>
        <link rel="stylesheet" href="<c:url value="/css/bootstrap.min.css"/>"/>   		
        <script src="<c:url value="/js/bootstrap.min.js"/>"></script> 
        <link href="<c:url value="/css/bootstrap.min.css"/>" rel="stylesheet" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <link href="<c:url value="/css/floating-labels.css"/>" rel="stylesheet">
        <link href="<c:url value="/css/signin.css"/>" rel="stylesheet">
    </head>
    <body class="text-center">
        <form class="form-signin" name="j_security_form" method="post" action="j_security_check">
            <img class="mb-4" src="https://previews.123rf.com/images/iconisa/iconisa1805/iconisa180501308/101247239-mutual-agreement-vector-thin-line-stroke-icon-mutual-agreement-outline-illustration-linear-sign-symb.jpg" alt="" width="72" height="72">
            <div class="form-label-group">
                <label for="usuario">Usuario</label>
                <input type="text" id="usuario" class="form-control" placeholder="Usuario" name="j_username" required autofocus>
            </div>
            <div class="form-label-group">
                <label for="inputPassword">Password</label>

                <input type="password" id="inputPassword" class="form-control" placeholder="Contraseña" name="j_password" required>
            </div>
            <span style="label-link">
                <a href="<c:url value="/inicio/olvide_contrasenia"/>">Olvide mi contraseña</a>
            </span>
            <br>
            <button class="btn btn-lg btn-primary btn-block" type="submit">Ingresar</button>
            <script src="https://www.google.com/recaptcha/api.js?render=${recaptcha_public_key}"></script>
            <script>
                grecaptcha.ready(function () {
                    window.resetRecaptcha = function () {
                        grecaptcha.execute('${recaptcha_public_key}', {action: 'ejemplo_recaptcha_tuds'}).then(function (token) {
                            document.getElementById('g-recaptcha-response').value = token;
                        });
                    };
                    resetRecaptcha();
                });
            </script>
            <input type="hidden" name="g-recaptcha-response" id="g-recaptcha-response" required/>            
        </form>
    </body>

</html>
