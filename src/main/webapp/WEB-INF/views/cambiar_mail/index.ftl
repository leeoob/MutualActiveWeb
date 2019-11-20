<form action="${context_path}/cambiar_mail/cambiar_mail_post/${session.socio.id}" onsubmit="return validarFormulario()" method="post">
            <div class="row justify-content-center">
                <div class="col-sm-12">
                        <h2>Para continuar por favor Cambie su Mail</h2>
                </div>
            </div>
            <div class="row">
                <div class="col-sm-6">
                    <label  class="col-sm-2 col-form-label">Nuevo Mail</label>
                    <input type="text" name="mail" id="mail"  placeholder="Ingrese un nuevo Mail de Contacto" required>
                    </div>
                </div>    
                <div class="row">
                <div class="col-sm-12">
                    <@render partial="/captcha/recaptcha" />
                    <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Confirmar</button>
                </div>
                </div>
            </form>    
        </div>
    </div>
</div>