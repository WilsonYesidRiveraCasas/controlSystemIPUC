$(function() {

	$('#login').click(function() {
		var n_identification = $('#n_identification').val();
		var pass = $('#pass').val();

		var data = { 
				n_identification : n_identification,
				pass : pass,
			};

		$.ajax({
			type: "POST",
			url: "/login",
			contentType: 'application/json',
			data: JSON.stringify(data)
		}).done(function( msg ) {
		    window.location.href =  "/register";
		});

	});	
});