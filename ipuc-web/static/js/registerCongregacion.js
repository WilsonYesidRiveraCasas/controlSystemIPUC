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
                $.blockUI({ message: '<h3>Un momento por favor ...</h3>' });
            }			
		}).done(function(obj) {
			buildMuinicipiosOptions(obj);
		}). fail(function(error) {
			switch(err.status) {
                case 409 : notificacionGenerica('Alerta', 'No hay municipios disponibles', 'error');
                           break;
            }
		}).complete(function() {
			$.unblockUI();
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
                $.blockUI({ message: '<h3>Un momento por favor ...</h3>' });
            }		
		}).done(function(obj) {
			buildPastoresOption(obj);
		}). fail(function(error) {
			switch(error.status) {
                case 409 : notificacionGenerica('Alerta', 'No hay pastores disponibles', 'error');
                           break;
            }
		}).complete(function() {
			$.unblockUI();
		});
	});

	$(this).keypress(function(e) {
        if(e.which == 13) {
        	executeLogin();
        }
    });

	$("#name_congre").keydown(function(e) {
        elementValidate($(this));
    });

    $("#dir_congre").keydown(function(e) {
        elementValidate($(this));
    });

    $("#date_congre").focusout(function(e) {
        elementValidate($(this));
    });

    $("#reg_congre").focusout(function(e) {
        elementValidate($(this));
    });

    $('#muni_congre').focusout(function(e) {
    	elementValidate($(this));
    });

	function executeLogin () {
		if(!formValidate()) {

			$.ajax({
				type: "POST",
				url: "/registerCongregacion",
				contentType: 'application/json',
				data: JSON.stringify(getData()),
				statusCode: {
					400 : function(obj) {
						notificacionGenerica('Error', obj.responseText, 'error');
					},
					409 : function() {
						notificacionGenerica('Alerta', obj.responseText, 'error');
				    }
				},
				beforeSend : function() {
					$.blockUI({ message: '<h3>Un momento por favor ...</h3>' });
				}
			}).done(function( msg ) {
				notificacionGenerica('Registro exitoso', 'Podrás continuar registrando congregaciones', 'sucess');
			    resetForm();
			}).complete(function() {
				$.unblockUI();
			});
		} else {
			notificacionGenerica('Datos incompletos', 'Se han señalado los campos que se requieren' , 'error');
		}
	};

	function notificacionGenerica(titulo, texto, tipo) {
		new PNotify({
			title : titulo,
			text : texto,
			type : tipo,
			icon: false
		});
	}

	function getData() {
		var data = {
			nombre : $('#name_congre').val(),
			direccion : $('#dir_congre').val(),
			telefono : $('#tel_congre').val(),
			fecha_apertura : $('#date_congre').val(),
			municipio : $('#muni_congre').val(),
			pastor : $('#pastor_congre').val()
		};

		return data;
	}

	function formValidate() {

		var nombre = elementValidate($('#name_congre')); 
		var direccion = elementValidate($('#dir_congre')); 
		var fecha_apertura = elementValidate($('#date_congre'));
		var region = elementValidate($('#reg_congre'));
		var municipio = elementValidate($('#muni_congre'));

		return nombre && direccion && fecha_apertura && region && municipio;
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
		$('#name_congre').val("");
		$('#dir_congre').val("");
		$('#date_congre').val("");
		$('#reg_congre').val("");
		$('#muni_congre').find( "option" ).remove();
		$('#muni_congre').attr('disabled', true);
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