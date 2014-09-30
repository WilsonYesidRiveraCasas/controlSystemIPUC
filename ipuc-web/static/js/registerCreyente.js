$(function() { 
	$('#register').click(function () {
		execute();
	});	

    $('#loadPastor').click(function () {

		$('#pastor_ofi').find( "option" ).remove();
		$('#pastor_ofi').attr('disabled', true);

		$.ajax({
			type: "GET",
			url: "/pastores",
			dataType : 'json',
			beforeSend : function(xhr) {
                xhr.setRequestHeader("Accept", "application/json");
                xhr.setRequestHeader("Content-Type", "application/json");
                $.blockUI({ message: '<h3>Un momento por favor ...</h3>' }); 
            },
            statusCode: {
				409 : function() {
					notificacionGenerica('Alerta', obj.responseText, 'error');
			    }
			}		
		}).done(function(obj) {
			buildPastoresOption(obj);
		}).complete(function() {
			$.unblockUI();
		});
	});

	function execute () {
		if(formValidate()) {

			var datos = getData();

			$.ajax({
				type: "POST",
				url: "/registerCreyente",
				contentType: 'application/json;charset=UTF-8',
				data: JSON.stringify(datos),
				statusCode: {
					400 : function(obj) {
						notificacionGenerica('Error', obj.responseText, 'error');
					},
					409 : function(obj) {
						notificacionGenerica('Alerta', obj.responseText, 'error');
				    }
				}, beforeSend : function(xhr) {
	                xhr.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
	                $.blockUI({ message: '<h3>Un momento por favor ...</h3>' }); 
	            },
			}).done(function( msg ) {
				notificacionGenerica('Registro exitoso', 'Podrás continuar registrando creyentes', 'success');
			    resetForm();
			    $('.certificado-downloader').attr('src', '/creyente/' + datos.num_identi + '/report');
			    
			}).complete(function() {
				$.unblockUI();
			});
		} else {
			notificacionGenerica('Error', 'Faltan datos requeridos', 'error');
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
			p_name : $('#f_name').val(),
			s_name : $('#s_name').val(),
			p_apellido : $('#f_apellido').val(),
			s_apellido : $('#s_apellido').val(),
			sexo : $('#sexo').val(),
			s_civil : $('#s_civil').val(),
			date_naci : $('#date_naci').val(),
			l_naci : $('#l_naci').val(),
			tele : $('#tele').val(),
			correo : $('#correo').val(),
			name_papa : $('#name_papa').val(),
			name_madre : $('#name_madre').val(),
			date_e_s : $('#date_e_s').val(),
			date_bauti : $('#date_bauti').val(),
			l_cere : $('#l_cere').val(),
			pastor_ofi : $('#pastor_ofi').val() == null ? "" : $('#pastor_ofi').val()
		};

		return data;
	}

	function formValidate() {

		var tipo_identi = elementValidate($('#tipo_identi'));
		var num_identi = elementValidate($('#num_identificacion')); 
		var p_name = elementValidate($('#f_name'));
		var p_apellido = elementValidate($('#f_apellido'));
		var sexo = elementValidate($('#sexo'));
		var s_civil = elementValidate($('#s_civil'));
		var date_naci = elementValidate($('#date_naci'));
		var date_bauti = elementValidate($('#date_bauti'));
		var pastor_ofi = elementValidate($("#pastor_ofi"));

		return num_identi && tipo_identi && p_name && p_apellido && sexo && s_civil && date_naci && date_bauti && pastor_ofi; 
	}

	function elementValidate(element) {
		var value = "";
		if($(element).val() != null) {
			value = $(element).val()
		}
		var validate = value.length > 0;
		if(!validate) {
			$(element).closest('campo').removeClass('has-success').addClass('has-error');			
		} else {
			$(element).closest('campo').removeClass('has-error');
		}
		return validate;
	}

	function resetForm () {
		$('#tipo_identi').val('CC');
		$('#num_identificacion').val('');
		$('#f_name').val('');
		$('#s_name').val('');
		$('#f_apellido').val('');
		$('#s_apellido').val('');
		$('#sexo').val('M');
		$('#s_civil').val('C');
		$('#date_naci').val('');
		$('#l_naci').val('');
		$('#tele').val('');
		$('#correo').val('');
		$('#name_papa').val('');
		$('#name_madre').val('');
		$('#date_e_s').val('');
		$('#date_bauti').val('');
		$('#l_cere').val('');
		$('#pastor_ofi').find( "option" ).remove();
		$('#pastor_ofi').attr('disabled', true);
	}

	function buildPastoresOption (arrayPastores) {
		var options = "";
		for(var i in arrayPastores) {
			var pastor = arrayPastores[i];
			options += "<option value = '" + pastor.num_identi + "'>" + pastor.nombre + "</option>\n";
		}
		$('#pastor_ofi').find( "optgroup" ).append(options);
		$('#pastor_ofi').removeAttr("disabled");
	}


	/******* Key events *****/

	$(this).keypress(function(e) {
        if(e.which == 13) {
        	execute();
        }
    });

	$('select:required').change(function(e){
		elementValidate($(this));
	});

    $('input:required').focusout(function(e) {
    	elementValidate($(this));
    });
});