<div class="container-fluid">
    <div class="container">
        <div class="formBox">
    <@flash name="mensaje">        
        <div class="alert alert-success" role="alert">
            <@flash name="mensaje"/>
        </div> 
    </@flash>
    <@flash name="error">        
        <div class="alert alert-danger" role="alert">
            <@flash name="error"/>
        </div> 
    </@flash>
    <#if error?hasContent >
        <div class="alert alert-danger" role="alert">
            <a class="danger">${error}</a>
        </div>        
    </#if>        
