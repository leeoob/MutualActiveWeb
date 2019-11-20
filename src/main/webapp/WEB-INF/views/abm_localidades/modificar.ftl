<table class="table mb-auto">
    <tbody>
        <tr>
    <div class="container">
        <form class="form-inline my-2 my-lg" action="${context_path}/abm_localidades/modificar" method="post">
            <label  class="col-sm-2 col-form-label">Descripcion</label>
            <input type="text" name="descripcion" pattern="[A-Za-z]{0-30}" placeholder="Ingrese Descripcion" required></input>                        
            <br>
            <input type="hidden" name="id" value="${localidad_id}">
            <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Confirmar</button>
            </form>
    </div>
    </tr>
</tbody>
</table>
