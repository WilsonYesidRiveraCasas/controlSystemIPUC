$(function() {

	$('#login').click(function() {

		$("#msg").html("");
		$("#msg").hide();
		executeLogin();
		return false;
		
	});

	$("#n_identification").keydown(function(e) {
        elementValidate($(this));
    });

    $('#pass').keydown(function(e) {
    	elementValidate($(this));
    });

    $(this).keypress(function(e) {
        if(e.which == 13) {
        	executeLogin();
        }
    });

	/***  Functions   ***/
	function executeLogin () {
		if(!formValidate()) {

			$.ajax({
				type: "POST",
				url: "/login",
				contentType: 'application/json',
				data: JSON.stringify(getData()),
				statusCode: {
					400 : function(obj) {
				      printMessageError(obj);
				    },
				    409 : function(obj) {
				    	printMessageError(obj);
				    }
				}
			}).done(function( msg ) {
			    window.location.href =  "/home";
			});
		}
	};

	function printMessageError(obj) {
		$("#msg").html(obj.responseText);
		$("#msg").show();
	}

	function formValidate() {
		var n_identification = elementValidate($('#n_identification'));
		var pass = elementValidate($('#pass'));

		return n_identification && pass;
	}

	function getData() {
		var data = {
			n_identification : $('#n_identification').val(),
			pass : $('#pass').val()
		};

		return data;
	}

	function elementValidate(element) {
		var validate = $(element).val().length == 0;
		if(validate) {
			$(element).closest('.form-group').removeClass('has-success').addClass('has-error');			
		} else {
			$(element).closest('.form-group').removeClass('has-error');
			$("#msg").html("").hide();
		}
		return validate;
	}
});


