$(function() {

	$('#b_register').click(function() {
		var identification = $('#t_identification').val();
		var n_identification = $('#n_identification').val();
		var p_name = $('#f_name').val();
		var s_name = $('#s_name').val();
		var p_lastname = $('#p_lastname').val();
		var s_lastname = $('#s_lastname').val();
		var d_birth = $('#d_birth').val();
		var p_birth = $('#p_birth').val();
		var m_status = $('#m_status').val();
		var n_phone = $('#n_phone').val();
		var mail = $('#mail').val();
		var pass = $('#pass').val();
		var status = "A";

		var data = { 
				t_identification : identification, 
				n_identification : n_identification,
				p_name : p_name,
				s_name : s_name,
				p_lastname : p_lastname,
				s_lastname : s_lastname,
				d_birth : d_birth,
				p_birth : p_birth,
				m_status : m_status,
				n_phone : n_phone,
				mail : mail,
				pass : pass,
				status : status
			};

		$.ajax({
			type: "POST",
			url: "/register",
			contentType: 'application/json',
			data: JSON.stringify(data)
		}).done(function( msg ) {
		    alert( "Data Saved: " + msg );
		});

	});	
});