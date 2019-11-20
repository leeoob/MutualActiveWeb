<form action="${context_path}/datos_personales/modificar/${socio.id}" onsubmit="return validarFormulario()" method="post">
    <div class="row justify-content-center">
        <div class="col-sm-12">
                <h2>Modificar Datos Personales</h2>
        </div>
    </div>
    <div class="row">
        <div class="col-sm-6">
            <label  class="col-sm-2 col-form-label">Apellido y Nombre</label>
            <input type="text" name="apeynom" id="apeynom" pattern="[A-Za-z]{0-30}" placeholder="${socio.apeynom}" onkeypress="return permitidos(event, 'caracteres')" required>
        </div>
    </div>
    <div class="row">
        <div class="col-sm-6">
            <label class="col-sm-2 col-form-label">Dni</label>
            <input type="number"  class="form-control" name="dni"  id="dni" placeholder="${socio.dni}" required>
            </div>
        </div>
    <div class="row">
        <div class="col-sm-6">                    
            <label class="col-sm-2 col-form-label">Localidad</label>
            <select class="custom-select" name="localidad" id="localidad" required>
                <#list  localidades as localidades>
                    <option value="${localidades.id}">${localidades.descripcion}</option>
                </#list>                
                </select>
        <div class="invalid-feedback">Elija una Localidad</div>
    </div>
    </div>
    <div class="row">
        <div class="col-sm-12">  
            <label  class="col-sm-2 col-form-label">E-mail</label>
            <input type="email" class="form-control" name="email" id="email" placeholder="${socio.email}" required>
         </div>
    </div>    
    <div class="row">
    <div class="col-sm-12">
        <@render partial="/captcha/recaptcha" />
        <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Confirmar</button>
    </div>
    </div>
</form>    
