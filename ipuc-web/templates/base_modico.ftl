<#macro layout_base_modico stylesheets="" javascripts="" title="" section="" sectionmin="">
<!doctype html>
<html class="fixed">
	<head>

		<!-- Basic -->
		<meta charset="UTF-8">

		<title>${title}</title>
		<meta name="keywords" content="módico ipuc" />
		<meta name="description" content="Módico Admin - IPUC">
		<meta name="author" content="wilson rivera">

		<!-- Mobile Metas -->
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />

		<!-- Web Fonts  -->
		<link href="http://fonts.googleapis.com/css?family=Open+Sans:300,400,600,700,800|Shadows+Into+Light" rel="stylesheet" type="text/css">

		<!-- Vendor CSS -->
		<link rel="stylesheet" href="../static/assets/vendor/bootstrap/css/bootstrap.css" />
		<link rel="stylesheet" href="../static/assets/vendor/font-awesome/css/font-awesome.css" />
		<link rel="stylesheet" href="../static/assets/vendor/magnific-popup/magnific-popup.css" />
		<link rel="stylesheet" href="../static/assets/vendor/bootstrap-datepicker/css/datepicker3.css" />

		<!-- Specific Page Vendor CSS -->
		<link rel="stylesheet" href="../static/assets/vendor/jquery-ui/css/ui-lightness/jquery-ui-1.10.4.custom.css" />
		<link rel="stylesheet" href="../static/assets/vendor/select2/select2.css" />
		<link rel="stylesheet" href="../static/assets/vendor/bootstrap-multiselect/bootstrap-multiselect.css" />
		<link rel="stylesheet" href="../static/assets/vendor/bootstrap-tagsinput/bootstrap-tagsinput.css" />
		<link rel="stylesheet" href="../static/assets/vendor/bootstrap-colorpicker/css/bootstrap-colorpicker.css" />
		<link rel="stylesheet" href="../static/assets/vendor/bootstrap-timepicker/css/bootstrap-timepicker.css" />
		<link rel="stylesheet" href="../static/assets/vendor/dropzone/css/basic.css" />
		<link rel="stylesheet" href="../static/assets/vendor/dropzone/css/dropzone.css" />
		<link rel="stylesheet" href="../static/assets/vendor/bootstrap-markdown/css/bootstrap-markdown.min.css" />
		<link rel="stylesheet" href="../static/assets/vendor/summernote/summernote.css" />
		<link rel="stylesheet" href="../static/assets/vendor/summernote/summernote-bs3.css" />
		<link rel="stylesheet" href="../static/assets/vendor/codemirror/lib/codemirror.css" />
		<link rel="stylesheet" href="../static/assets/vendor/codemirror/theme/monokai.css" />

		<#if stylesheets?? >
	        <#if stylesheets?is_directive>
	            <@stylesheets/>
	        <#else>
	              ${stylesheets}
	        </#if>
	    </#if>

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
		<section class="body">

			<!-- start: header -->
			<header class="header">
				<div class="logo-container">
					<a href="/" class="logo">
						<img src="../static/assets/images/logo.png" height="35" alt="Porto Admin" />
					</a>
					<div class="visible-xs toggle-sidebar-left" data-toggle-class="sidebar-left-opened" data-target="html" data-fire-event="sidebar-left-opened">
						<i class="fa fa-bars" aria-label="Toggle sidebar"></i>
					</div>
				</div>
			
				<!-- start: search & user box -->
				<div class="header-right">			
					<div id="userbox" class="userbox">
						<a href="#" data-toggle="dropdown">
							<figure class="profile-picture">
								<img src="../static/assets/images/!logged-user.jpg" alt="Joseph Doe" class="img-circle" data-lock-picture="../static/assets/images/!logged-user.jpg" />
							</figure>
							<div class="profile-info">
								<span class="name">${pastor.nombreApellido()}</span>
								<span class="role">${pastor.rolMasImportante()}</span>
							</div>			
							<i class="fa custom-caret"></i>
						</a>
			
						<div class="dropdown-menu">
							<ul class="list-unstyled">
								<li class="divider"></li>
								<li>
									<a role="menuitem" tabindex="-1" href="pages-user-profile.html"><i class="fa fa-user"></i> Perfil</a>
								</li>
								<li>
									<a role="menuitem" tabindex="-1" href="/logout"><i class="fa fa-power-off"></i> Cerrar Sesión</a>
								</li>
							</ul>
						</div>
					</div>
				</div>
				<!-- end: search & user box -->
			</header>
			<!-- end: header -->

			<div class="inner-wrapper">
				<!-- start: sidebar -->
				<aside id="sidebar-left" class="sidebar-left">
				
					<div class="sidebar-header">
						<div class="sidebar-title">
							Menú
						</div>
						<div class="sidebar-toggle hidden-xs" data-toggle-class="sidebar-left-collapsed" data-target="html" data-fire-event="sidebar-left-toggle">
							<i class="fa fa-bars" aria-label="Toggle sidebar"></i>
						</div>
					</div>
				
					<div class="nano">
						<div class="nano-content">
							<hr class="separator" />
							<#if pastor.esConsistorio()>

								<div class="sidebar-widget widget-stats">
									<div class="widget-header">
										<h6>Panel Administrador</h6>
										<div class="widget-toggle">+</div>
									</div>
									<div class="widget-content">
										<nav id="menu" class="nav-main" role="navigation">
											<ul class="nav nav-main">
												<li>
													<a href="#">
														<i class="fa fa-home" aria-hidden="true"></i>
														<span>Registrar directivo</span>
													</a>
												</li>
											</ul>
										</nav>
									</div>
								</div>

							<#else>

								<#if pastor.esDirectivo() >

									<div class="sidebar-widget widget-stats">
										<div class="widget-header">
											<h6>Panel Directivo</h6>
											<div class="widget-toggle">+</div>
										</div>
										<div class="widget-content">
											<nav id="menu" class="nav-main" role="navigation">
												<ul class="nav nav-main">
													<li <#if sectionmin = "home"> class="nav-active"</#if> >
														<a href="/home">
															<i class="fa fa-home" aria-hidden="true"></i>
															<span>Inicio</span>
														</a>
													</li>									
													<li <#if sectionmin = "registerpastor"> class="nav-active"</#if> >
														<a href="/registerPastor">
															<i class="fa fa-users" aria-hidden="true"></i>
															<span>Registrar pastor</span>
														</a>
													</li>
													<li <#if sectionmin = "registercongregacion"> class="nav-active"</#if> >
														<a href="/registerCongregacion">
															<i class="fa fa-users" aria-hidden="true"></i>
															<span>Registrar congregación</span>
														</a>
													</li>
												</ul>
											</nav>
										</div>
									</div>
									<hr class="separator" />

								</#if>

								<!-- Panel de la funcionalidad del pastor -->
								<div class="sidebar-widget widget-stats">
									<div class="widget-header">
										<h6>Panel Pastor</h6>
										<div class="widget-toggle">+</div>
									</div>
									<div class="widget-content">
										<nav id="menu" class="nav-main" role="navigation">
											<ul class="nav nav-main">
												<li>
													<a href="#">
														<i class="fa fa-home" aria-hidden="true"></i>
														<span>Membresias</span>
													</a>
												</li>
											</ul>
										</nav>
									</div>
								</div>
								<!-- fin panel del pastor -->

							</#if>
							<hr class="separator" />
						</div>
				
					</div>
				
				</aside>
				<!-- end: sidebar -->

				<section role="main" class="content-body">
					<header class="page-header">
						<h2>${section}</h2>					
					</header>

					<!-- start: page -->
					<#nested/>					
					
					<!-- end: page -->
				</section>
			</div>
		</section>

		<!-- Vendor -->
		<script src="../static/assets/vendor/jquery/jquery.js"></script>
		<script src="../static/assets/vendor/jquery-browser-mobile/jquery.browser.mobile.js"></script>
		<script src="../static/assets/vendor/bootstrap/js/bootstrap.js"></script>
		<script src="../static/assets/vendor/nanoscroller/nanoscroller.js"></script>
		<script src="../static/assets/vendor/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>
		<script src="../static/assets/vendor/magnific-popup/magnific-popup.js"></script>
		<script src="../static/assets/vendor/jquery-placeholder/jquery.placeholder.js"></script>
		
		<!-- Specific Page Vendor -->
		<script src="../static/assets/vendor/jquery-ui/js/jquery-ui-1.10.4.custom.js"></script>
		<script src="../static/assets/vendor/jquery-ui-touch-punch/jquery.ui.touch-punch.js"></script>
		<script src="../static/assets/vendor/select2/select2.js"></script>
		<script src="../static/assets/vendor/bootstrap-multiselect/bootstrap-multiselect.js"></script>
		<script src="../static/assets/vendor/jquery-maskedinput/jquery.maskedinput.js"></script>
		<script src="../static/assets/vendor/bootstrap-tagsinput/bootstrap-tagsinput.js"></script>
		<script src="../static/assets/vendor/bootstrap-colorpicker/js/bootstrap-colorpicker.js"></script>
		<script src="../static/assets/vendor/bootstrap-timepicker/js/bootstrap-timepicker.js"></script>
		<script src="../static/assets/vendor/fuelux/js/spinner.js"></script>
		<script src="../static/assets/vendor/dropzone/dropzone.js"></script>
		<script src="../static/assets/vendor/bootstrap-markdown/js/markdown.js"></script>
		<script src="../static/assets/vendor/bootstrap-markdown/js/to-markdown.js"></script>
		<script src="../static/assets/vendor/bootstrap-markdown/js/bootstrap-markdown.js"></script>
		<script src="../static/assets/vendor/codemirror/lib/codemirror.js"></script>
		<script src="../static/assets/vendor/codemirror/addon/selection/active-line.js"></script>
		<script src="../static/assets/vendor/codemirror/addon/edit/matchbrackets.js"></script>
		<script src="../static/assets/vendor/codemirror/mode/javascript/javascript.js"></script>
		<script src="../static/assets/vendor/codemirror/mode/xml/xml.js"></script>
		<script src="../static/assets/vendor/codemirror/mode/htmlmixed/htmlmixed.js"></script>
		<script src="../static/assets/vendor/codemirror/mode/css/css.js"></script>
		<script src="../static/assets/vendor/summernote/summernote.js"></script>
		<script src="../static/assets/vendor/bootstrap-maxlength/bootstrap-maxlength.js"></script>
		<script src="../static/assets/vendor/ios7-switch/ios7-switch.js"></script>

		<script src="../static/assets/vendor/jquery-validation/jquery.validate.js"></script>
		
		<!-- Theme Base, Components and Settings -->
		<script src="../static/assets/javascripts/theme.js"></script>
		
		<!-- Theme Custom -->
		<script src="../static/assets/javascripts/theme.custom.js"></script>
		
		<!-- Theme Initialization Files -->
		<script src="../static/assets/javascripts/theme.init.js"></script>

		<!-- Examples -->
		<script src="../static/assets/javascripts/forms/examples.advanced.form.js" /></script>


		<script type="text/javascript">
		$("#registroPastor").validate({
			highlight: function(element) {
				$(element).closest('.form-group').removeClass('has-success').addClass('has-error');
			},
			success: function(element) {
				$(element).closest('.form-group').removeClass('has-error');
			}
		});
	</script>
		<!--Page Scripts-->
        <#if javascripts?? >
            <#if javascripts?is_directive>
                <@javascripts/>
            <#else>
                ${javascripts}
            </#if>
        </#if>

	</body>
</html>
</#macro>