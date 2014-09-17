$(function() { 
	$('#register').click(function () {
		executeLogin();
	});

	$('#reg_congre').change(function () {

		var region = $(this).val();
		$('#muni_congre').find( "option" ).remove();
		$('#muni_congre').attr('disabled', true);

		$.ajax({
			type: "GET",
			url: "/municipios/" + region,
			dataType : 'json',
			beforeSend : function(xhr) {
                xhr.setRequestHeader("Accept", "application/json");
                xhr.setRequestHeader("Content-Type", "application/json");
            }			
		}).done(function(obj) {
			buildMuinicipiosOptions(obj);
		}). fail(function(error) {
			switch(err.status) {
                case 409 : errorNotificaction('El pastor ya existe');;
                           break;
            }
		});
	});

	$('#loadPastor').click(function () {

		$('#pastor_congre').find( "option" ).remove();
		$('#pastor_congre').attr('disabled', true);

		$.ajax({
			type: "GET",
			url: "/pastoresSinCongregacion",
			dataType : 'json',
			beforeSend : function(xhr) {
                xhr.setRequestHeader("Accept", "application/json");
                xhr.setRequestHeader("Content-Type", "application/json");
            }			
		}).done(function(obj) {
			buildPastoresOption(obj);
		}). fail(function(error) {
			switch(err.status) {
                case 409 : errorNotificaction('El pastor ya existe');;
                           break;
            }
		});
	});

	$(this).keypress(function(e) {
        if(e.which == 13) {
        	executeLogin();
        }
    });

    $("#num_identificacion").keydown(function(e) {
        elementValidate($(this));
    });

    $('#correo').keydown(function(e) {
    	elementValidate($(this));
    });

	function executeLogin () {
		if(!formValidate()) {

			$.ajax({
				type: "POST",
				url: "/registerPastor",
				contentType: 'application/json',
				data: JSON.stringify(getData()),
				statusCode: {
					409 : function() {
						errorNotificaction('El pastor ya existe');
				    }
				}
			}).done(function( msg ) {
				sucessNotificaction();
			    resetForm();
			});
		}
	};

	function sucessNotificaction() {
		new PNotify({
			title: 'Registro Exitoso',
			text: 'Podrás seguir registrando.',
			type: 'success'
		});
	}

	function errorNotificaction(msj) {
		new PNotify({
			title : 'Error registrando',
			text : msj,
			type : 'error'
		});
	}

	function getData() {
		var data = {
			tipo_identi : $('#tipo_identi').val(),
			num_identi : $('#num_identificacion').val(),
			correo : $('#correo').val()
		};

		return data;
	}

	function formValidate() {

		var num_identi = elementValidate($('#num_identificacion')); 
		var correo = elementValidate($('#correo'));
		var tipo_identi = elementValidate($('#tipo_identi'));

		return num_identi && tipo_identi && correo;
	}

	function elementValidate(element) {
		var validate = $(element).val().length == 0;
		if(validate) {
			$(element).closest('.form-group').removeClass('has-success').addClass('has-error');			
		} else {
			$(element).closest('.form-group').removeClass('has-error');
		}
		return validate;
	}

	function resetForm () {
		$('#num_identificacion').val("");
		$('#tipo_identi').val("CC");
		$('#correo').val("");
		var tipo_identi = elementValidate();
	}

	function buildMuinicipiosOptions(arrayMunicipios) {
		var options = "";
		for( var i in arrayMunicipios) {
			var municipio = arrayMunicipios[i];
			options += "<option value = '" + municipio.codMunicipio + "'>" + municipio.nombre + "</option>\n";
		}

		$('#muni_congre').find( "optgroup" ).append(options);
		$('#muni_congre').removeAttr("disabled");
    }
	
	function buildPastoresOption (arrayPastores) {
		var options = "";
		for(var i in arrayPastores) {
			var pastor = arrayPastores[i];
			options += "<option value = '" + pastor.num_identi + "'>" + pastor.nombre + "</option>\n";
		}
		$('#pastor_congre').find( "optgroup" ).append(options);
		$('#pastor_congre').removeAttr("disabled");
	}

});