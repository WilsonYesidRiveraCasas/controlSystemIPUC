<!doctype html>
<html class="fixed">
	<head>

		<!-- Basic -->
		<meta charset="UTF-8">

		<title>Módico - Inicio de Sesión</title>

		<meta name="keywords" content="" />
		<meta name="description" content="Módico Login">
		<meta name="author" content="wilson-rivera">

		<!-- Mobile Metas -->
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />

		<!-- Web Fonts  -->
		<link href="http://fonts.googleapis.com/css?family=Open+Sans:300,400,600,700,800|Shadows+Into+Light" rel="stylesheet" type="text/css">

		<!-- Vendor CSS -->
		<link rel="stylesheet" href="../static/assets/vendor/bootstrap/css/bootstrap.css" />
		<link rel="stylesheet" href="../static/assets/vendor/font-awesome/css/font-awesome.css" />
		<link rel="stylesheet" href="../static/assets/vendor/magnific-popup/magnific-popup.css" />
		<link rel="stylesheet" href="../static/assets/vendor/bootstrap-datepicker/css/datepicker3.css" />

		<!-- Theme CSS -->
		<link rel="stylesheet" href="../static/assets/stylesheets/theme.css" />

		<!-- Skin CSS -->
		<link rel="stylesheet" href="../static/assets/stylesheets/skins/default.css" />

		<!-- Theme Custom CSS -->
		<link rel="stylesheet" href="../static/assets/stylesheets/theme-custom.css">

		<!-- Head Libs -->
		<script src="../static/assets/vendor/modernizr/modernizr.js"></script>

	</head>
	<body>
		<!-- start: page -->
		<section class="body-sign">
			<div class="center-sign">
				<a href="/" class="logo pull-left">
					<img src="../static/assets/images/logo.png" height="54" alt="Porto Admin" />
				</a>

				<div class="panel panel-sign">
					<div class="panel-title-sign mt-xl text-right">
						<h2 class="title text-uppercase text-bold m-none"><i class="fa fa-user mr-xs"></i> Inicio de Sesión</h2>
					</div>
					<div class="panel-body">
						<div class="form-group mb-lg">
							<label>Número de identificación</label>
							<div class="input-group input-group-icon">
								<input name="n_identification" id="n_identification" type="text" class="form-control input-lg" required/>
								<span class="input-group-addon">
									<span class="icon icon-lg">
										<i class="fa fa-user"></i>
									</span>
								</span>
							</div>
						</div>

						<div class="form-group mb-lg">
							<div class="clearfix">
								<label class="pull-left">Contraseña</label>
								<!--a href="pages-recover-password.html" class="pull-right">Lost Password?</a-->
							</div>
							<div class="input-group input-group-icon">
								<input name="pass" id="pass" type="password" class="form-control input-lg" required />
								<span class="input-group-addon">
									<span class="icon icon-lg">
										<i class="fa fa-lock"></i>
									</span>
								</span>
							</div>
						</div>
						<label id="msg" class="error"></label>
						<div class="form-group mb-lg">
							<div class="text-right">
								<button id="login" class="btn btn-primary">Ingresar</button>						
							</div>
						</div>
					</div>
				</div>

				<p class="text-center text-muted mt-md mb-md">&copy; Copyright ${fecha}. Todos los derechos reservados</p>
			</div>
		</section>
		<!-- end: page -->

		<!-- Vendor -->
		<script src="../static/assets/vendor/jquery/jquery.js"></script>
		<script src="../static/assets/vendor/jquery-browser-mobile/jquery.browser.mobile.js"></script>
		<script src="../static/assets/vendor/bootstrap/js/bootstrap.js"></script>
		<script src="../static/assets/vendor/nanoscroller/nanoscroller.js"></script>
		<script src="../static/assets/vendor/jquery-placeholder/jquery.placeholder.js"></script>
		
		<!-- Theme Base, Components and Settings -->
		<script src="../static/assets/javascripts/theme.js"></script>
		
		<!-- Theme Custom -->
		<script src="../static/assets/javascripts/theme.custom.js"></script>
		
		<!-- Theme Initialization Files -->
		<script src="../static/assets/javascripts/theme.init.js"></script>
		<script type="text/javascript" src="../static/js/login.js"></script>
	</body>
</html>