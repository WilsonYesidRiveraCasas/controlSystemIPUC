<#import "base_modico.ftl" as layout>

<#macro stylesheets>
  <link rel="stylesheet" href="../static/assets/vendor/pnotify/pnotify.custom.css" />
</#macro>


<@layout.layout_base_modico stylesheets=stylesheets javascripts=javascripts title="Registro de Creyentes" section="Registro de Creyentes" sectionmin="registercreyente">

<section class="panel">
	<header class="panel-heading">

		<h2 class="panel-title">Datos personales</h2>
	</header>
	<div class="panel-body">
		<div class="form-group">
			<label class="col-md-2 control-label">Tipo de identificación <span class="required">*</span></label>
			<div class="col-md-4">
				<select data-plugin-selectTwo class="form-control populate" id = "tipo_identi" name="tipo_identi" title="Por favor seleccione un tipo de identificación" required>
					<optgroup label="Seleccione un tipo">
						<#list identificationTypes as type>
							<option value = "${type.codeTipoIdenti}">${type.nombreTipoIdenti}</option>
						</#list>
					</optgroup>						
				</select>
			</div>
			<label class="col-md-2 control-label" for="num_identificacion">Número de identificación <span class="required">*</span></label>
			<div class="col-md-4">
				<input type="text" class="form-control" id="num_identificacion" required/>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label">Primer nombre <span class="required">*</span></label>
			<div class="col-md-4">
				<input type="text" class="form-control" placeholder="primer nombre" id="f_name" required/>
			</div>
			<label class="col-md-2 control-label">Segundo nombre</label>
			<div class="col-md-4">
				<input type="text" class="form-control" placeholder="segundo nombre" id="s_name"/>
			</div>
		</div>	

		<div class="form-group">
			<label class="col-md-2 control-label">Primer apellido <span class="required">*</span></label>
			<div class="col-md-4">
				<input type="text" class="form-control" placeholder="primer apellido" id="f_apellido" required/>
			</div>
			<label class="col-md-2 control-label">Segundo apellido</label>
			<div class="col-md-4">
				<input type="text" class="form-control" placeholder="segundo apellido" id="s_apellido"/>
			</div>
		</div>

		<div class="form-group">
			<label class="col-md-2 control-label">Sexo <span class="required">*</span></label>
			<div class="col-md-4">
				<select data-plugin-selectTwo class="form-control populate" id = "sexo" name="sexo" title="Por favor seleccione el sexo" required>
					<optgroup label="Seleccione un tipo">
						<option value = "M">Masculino</option>
						<option value = "F">Femenino</option>
					</optgroup>						
				</select>
			</div>
			<label class="col-md-2 control-label" for="num_identificacion">Estado civil <span class="required">*</span></label>
			<div class="col-md-4">
				<select data-plugin-selectTwo class="form-control populate" id = "s_civil" name="estado civil" title="Por favor seleccione el estado civil" required>
					<optgroup label="Seleccione un estado">
						<#list statesTypes as type>
							<option value = "${type.codeStateCivil}">${type.stateCivil}</option>
						</#list>
					</optgroup>						
				</select>
			</div>
		</div>

		<div class="form-group">
			<label class="col-md-2 control-label">Fecha nacimiento <span class="required">*</span></label>
			<div class="col-md-4">
				<div class="input-group">
					<span class="input-group-addon">
						<i class="fa fa-calendar"></i>
					</span>
					<input type="text" data-plugin-datepicker class="form-control" id="date_naci">
				</div>
			</div>
			<label class="col-md-2 control-label">Lugar de nacimiento</label>
			<div class="col-md-4">
				<input type="text" class="form-control" placeholder="Lugar de nacimiento" id="l_naci"/>
			</div>
		</div>

		<div class="form-group">
			<label class="col-md-2 control-label">Teléfono </label>
			<div class="col-md-4">
				<input type="text" class="form-control" placeholder="Teléfono o celular" id="tele"/>
			</div>
			<label class="col-md-2 control-label">Correo </label>
			<div class="col-md-4">
				<div class="input-group input-group-icon">
					<span class="input-group-addon">
						<span class="icon"><i class="fa fa-envelope"></i></span>
					</span>
					<input type="text" class="form-control" placeholder="creyente@dominio.com" id="correo" required/>
				</div>
			</div>
		</div>

		<div class="form-group">
			<label class="col-md-2 control-label">Nombre del Padre </label>
			<div class="col-md-4">
				<input type="text" class="form-control" placeholder="Nombre del padre" id="name_papa"/>
			</div>
			<label class="col-md-2 control-label">Nombre de la madre </label>
			<div class="col-md-4">
				<input type="text" class="form-control" placeholder="Nombre de la madre" id="name_madre"/>
			</div>
		</div>

		<div class="form-group">
			<label class="col-md-2 control-label">Fecha recepción del Espíritu Santo </label>
			<div class="col-md-4">
				<div class="input-group">
					<span class="input-group-addon">
						<i class="fa fa-calendar"></i>
					</span>
					<input type="text" data-plugin-datepicker class="form-control" id="date_e_s">
				</div>
			</div>			
		</div>
	</div>
</section>
<section class="panel">
	<header class="panel-heading">
		<h2 class="panel-title">Datos bautismo</h2>
	</header>
	<div class="panel-body">
		<div class="form-group">
			<label class="col-md-2 control-label">Fecha bautizo </label>
			<div class="col-md-4">
				<div class="input-group">
					<span class="input-group-addon">
						<i class="fa fa-calendar"></i>
					</span>
					<input type="text" data-plugin-datepicker class="form-control" id="date_bauti">
				</div>
			</div>
			<label class="col-md-2 control-label">Lugar ceremonia</label>
			<div class="col-md-4">
				<input type="text" class="form-control" placeholder="Lugar de ceremonia" id="l_cere"/>
			</div>
		</div>

		<div class="form-group">
			<label class="col-md-2 control-label">Pastor oficiante</label>
			<div class="col-md-4">
				<select data-plugin-selectTwo class="form-control populate" id = "pastor_ofi" name="pastor_oficiante" title="Por favor seleccione un pastor" disabled="">
					<optgroup label="Seleccione un pastor">
					</optgroup>						
				</select>
			</div>
			<div class="col-md-2">
				<button id = "loadPastor" type="button" class="mb-xs mt-xs mr-xs btn btn-default"><i class="fa fa-refresh"></i> Cargar</button>
			</div>
		</div>
	</div>

</section>

<section>
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