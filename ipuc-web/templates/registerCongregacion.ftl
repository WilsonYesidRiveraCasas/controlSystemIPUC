<#import "base_modico.ftl" as layout>

<#macro stylesheets>
  <link rel="stylesheet" href="../static/assets/vendor/pnotify/pnotify.custom.css" />
</#macro>


<@layout.layout_base_modico stylesheets=stylesheets javascripts=javascripts title="Registro de Congregaciones" section="Registro de Congregaciones" sectionmin="registercongre">

<section class="panel">
	<header class="panel-heading">

		<h2 class="panel-title">Congregación</h2>
	</header>
	<div class="panel-body">
		<div class="form-group">
			<label class="col-md-3 control-label">Tipo de documento <span class="required">*</span></label>
			<div class="col-md-6">
				<select data-plugin-selectTwo class="form-control populate" id = "tipo_identi" name="tipo_identi" title="Por favor seleccione un tipo de identificación" required>
					<optgroup label="Seleccione un tipo">
						<#list identificationTypes as type>
							<option value = "${type.codeTipoIdenti}">${type.nombreTipoIdenti}</option>
						</#list>
					</optgroup>						
				</select>
				<label class="error" for="tipo_identi"></label>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-3 control-label" for="num_identificacion">Número de identificación <span class="required">*</span></label>
			<div class="col-md-6">
				<input type="text" class="form-control" id="num_identificacion" required/>
			</div>
		</div>

		<div class="form-group">
			<label class="col-md-3 control-label">Correo <span class="required">*</span></label>
			<div class="col-md-6">
				<div class="input-group input-group-icon">
					<span class="input-group-addon">
						<span class="icon"><i class="fa fa-envelope"></i></span>
					</span>
					<input type="text" class="form-control" placeholder="pastor@ipuc.com" id="correo" required/>
				</div>
			</div>
		</div>		
	</div>
	<footer class="panel-footer">
		<div class="row">
			<div class="col-sm-9 col-sm-offset-3">
				<button id = "register" class="btn btn-primary">Registrar</button>
			</div>
		</div>
	</footer>
	
</section>

<#macro javascripts>
	<script src="../static/js/registerPastor.js" /></script>
	<script src="../static/assets/vendor/pnotify/pnotify.custom.js"></script>
</#macro>
</@layout.layout_base_modico>