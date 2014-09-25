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
			<label class="col-md-3 control-label">Nombre <span class="required">*</span></label>
			<div class="col-md-6">
				<input type="text" class="form-control" id="name_congre" maxlength="50" required/>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-3 control-label">Dirección <span class="required">*</span></label>
			<div class="col-md-6">
				<input type="text" class="form-control" id="dir_congre" maxlength="200" required/>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-3 control-label">Teléfono</label>
			<div class="col-md-6">
				<div class="input-group input-group-icon">
					<span class="input-group-addon">
						<span class="icon"><i class="fa fa-phone"></i></span>
					</span>
					<input type="text" class="form-control" placeholder="Teléfono o celular" id="tel_congre" maxlength="50">
				</div>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-3 control-label">Fecha apertura <span class="required">*</span></label>
			<div class="col-md-6">
				<div class="input-group">
					<span class="input-group-addon">
						<i class="fa fa-calendar"></i>
					</span>
					<input type="text" data-plugin-datepicker class="form-control" id="date_congre">
				</div>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-3 control-label">Departamento o región <span class="required">*</span></label>
			<div class="col-md-6">
				<select data-plugin-selectTwo class="form-control populate" id = "reg_congre" name="reg_congre" title="Por favor seleccione una región del páis" required>
					<optgroup label="Seleccione una región">
							<option value = "" selected>Sin asignar</option>
						<#list regiones as region>
							<option value = "${region.idRegion}">${region.nombre}</option>
						</#list>
					</optgroup>						
				</select>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-3 control-label">Municipio <span class="required">*</span></label>
			<div class="col-md-6">
				<select data-plugin-selectTwo class="form-control populate" id = "muni_congre" name="muni_congre" title="Por favor seleccione un municipio" disabled="" required>
					<optgroup label="Seleccione un municipio">
					</optgroup>						
				</select>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-3 control-label">Pastor</label>
			<div class="col-md-6">
				<select data-plugin-selectTwo class="form-control populate" id = "pastor_congre" name="pastor_congre" title="Por favor seleccione un pastor" disabled="" required>
					<optgroup label="Seleccione un pastor">
					</optgroup>						
				</select>
			</div>
			<div class="col-md-2">
				<button id = "loadPastor" type="button" class="mb-xs mt-xs mr-xs btn btn-default"><i class="fa fa-refresh"></i> Cargar</button>
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
	<script src = "../static/plugins/jquery.blockUI.js"></script>
	<script src="../static/js/registerCongregacion.js" /></script>
	<script src="../static/assets/vendor/pnotify/pnotify.custom.js"></script>
</#macro>
</@layout.layout_base_modico>