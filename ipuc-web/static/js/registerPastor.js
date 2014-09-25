$(function() { 
	$('#register').click(function () {
		executeLogin();
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
					400 : function(obj) {
						notificacionGenerica('Error', obj.responseText, 'error');
					},
					409 : function(obj) {
						notificacionGenerica('Alerta', obj.responseText, 'error');
				    }
				},
				beforeSend : function() {
					$.blockUI({ message: '<h3>Un momento por favor ...</h3>' });
				}
			}).done(function( msg ) {
				notificacionGenerica('Registro exitoso', 'Podrás continuar registrando', 'success');
			    resetForm();
			}).complete(function() {
				$.unblockUI();
			});
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
});