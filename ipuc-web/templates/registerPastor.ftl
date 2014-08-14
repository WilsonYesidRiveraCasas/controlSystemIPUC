<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Registro Pastor</title>
	<link rel="stylesheet" href="../static/libs/jquery-ui/jquery-ui.theme.min.css">
</head>
<body>
	<table>
		<tbody>
			<tr>
				<td rowspan="4"><textarea></textarea></td>
				<td rowspan="2"><button>Tomar foto</button></td>
				<td><label>Tipo identificación</label></td>
				<td>
					<select name="t_identification" id="t_identification">
						<#list identificationTypes as type>
							<option value = "${type.codeTipoIdenti}">${type.nombreTipoIdenti}</option>
						</#list>
					</select>
				</td>
			</tr>
			<tr>
				<td><label>Número identicación</label></td>
				<td><input type="text" name="n_identification" id = "n_identification"></td>
			</tr>
			<tr>
				<td rowspan="2"><input type="file" name="img"></td>
				<td><label>Primer nombre</label></td>
				<td><input type="text" name="f_name" id = "f_name"></td>
			</tr>
			<tr>
				<td><label>Segundo nombre</label></td>
				<td><input type="text" name="s_name" id = "s_name"></td>
			</tr>
			<tr>
				<td><label>Primer apellido</label></td>
				<td><input type="text" name="p_lastname" id = "p_lastname"></td>
				<td><label>Segundo apellido</label></td>
				<td><input type="text" name="s_lastname" id = "s_lastname"></td>
			</tr>
			<tr>
				<td><label>Fecha nacimiento</label></td>
				<td> <input type="date" name="d_birth" id = "d_birth"></td>
				<td><label>Lugar nacimiento</label></td>
				<td><input type="text" name="p_birth" id = "p_birth"></td>
			</tr>
			<tr>
				<td><label>Estado civil</label></td>
				<td>
					<select name="m_status" id="m_status">
						<#list civilStates as state>
							<option value = "${state.codeStateCivil}">${state.stateCivil}</option>
						</#list>
					</select>
				</td>
				<td><label>Teléfono</label></td>
				<td><input type="text" name="n_phone" id = "n_phone"></td>
			</tr>
			<tr>
				<td><label>Correo electrónico</label></td>
				<td><input type="text" name="mail" id = "mail"></td>
				<td><label>Contraseña</label></td>
				<td><input type="password" name="pass" id = "pass"></td>
			</tr>
			<tr>
				<td><label>Estado</label></td>
				<td>
					<select name = "state" id = "state">
						<#list ministerStates as stateMinister>
							<option value = "${stateMinister.codeState}">${stateMinister.state}</option>
						</#list> 
					</select>
				</td>
				<td><label>Fecha nombramiento:</label></td>
				<td><input type="date" name="n_date" id = "n_date"></td>
			</tr>
		</tbody>
	</table>
	<button name="register" id="b_register">Registrar</button>
</body>
<script src="../static/libs/jquery-1.11.1.min.js"></script>
<script src="../static/libs/jquery-ui/jquery-ui.js"></script>
<script type="text/javascript" src="../static/js/registerPastor.js"></script>
<script type="text/javascript">
</script>
</html>