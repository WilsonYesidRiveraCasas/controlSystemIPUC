<#import "base_modico.ftl" as layout>

<@layout.layout_base_modico stylesheets=stylesheets javascripts=javascripts title="Módico - Directivos" section="Control principal" sectionmin="home">
<div class="row">
						
	<div class="col-md-6 col-lg-12 col-xl-6">
		<div class="row">
		<#if pastor.esDirectivo() >
			<div class="col-md-12 col-lg-6 col-xl-6">
				<section class="panel panel-featured-left panel-featured-primary">
					<div class="panel-body">
						<div class="widget-summary">
							<div class="widget-summary-col widget-summary-col-icon">
								<div class="summary-icon bg-primary">
									<i class="fa fa-users"></i>
								</div>
							</div>
							<div class="widget-summary-col">
								<div class="summary">
									<h4 class="title">Cant. pastores</h4>
									<div class="info">
										<strong class="amount">${numPastores}</strong>
									</div>
								</div>
								<div class="summary-footer">
									<a class="text-muted text-uppercase">(view all)</a>
								</div>
							</div>
						</div>
					</div>
				</section>
			</div>
			<div class="col-md-12 col-lg-6 col-xl-6">
				<section class="panel panel-featured-left panel-featured-secondary">
					<div class="panel-body">
						<div class="widget-summary">
							<div class="widget-summary-col widget-summary-col-icon">
								<div class="summary-icon bg-secondary">
									<i class="fa fa-university"></i>
								</div>
							</div>
							<div class="widget-summary-col">
								<div class="summary">
									<h4 class="title">Cant. Congregaciones</h4>
									<div class="info">
										<strong class="amount">${numCongregaciones}</strong>
									</div>
								</div>
								<div class="summary-footer">
									<a class="text-muted text-uppercase">(view all)</a>
								</div>
							</div>
						</div>
					</div>
				</section>
			</div>
			</#if>
			<!--div class="col-md-12 col-lg-6 col-xl-6">
				<section class="panel panel-featured-left panel-featured-tertiary">
					<div class="panel-body">
						<div class="widget-summary">
							<div class="widget-summary-col widget-summary-col-icon">
								<div class="summary-icon bg-tertiary">
									<i class="fa fa-shopping-cart"></i>
								</div>
							</div>
							<div class="widget-summary-col">
								<div class="summary">
									<h4 class="title">Today's Orders</h4>
									<div class="info">
										<strong class="amount">38</strong>
									</div>
								</div>
								<div class="summary-footer">
									<a class="text-muted text-uppercase">(statement)</a>
								</div>
							</div>
						</div>
					</div>
				</section>
			</div-->
			<div class="col-md-12 col-lg-6 col-xl-6">
				<section class="panel panel-featured-left panel-featured-quartenary">
					<div class="panel-body">
						<div class="widget-summary">
							<div class="widget-summary-col widget-summary-col-icon">
								<div class="summary-icon bg-quartenary">
									<i class="fa fa-user"></i>
								</div>
							</div>
							<div class="widget-summary-col">
								<div class="summary">
									<h4 class="title">Creyentes</h4>
									<div class="info">
										<strong class="amount">${numCreyentes}</strong>
									</div>
								</div>
								<div class="summary-footer">
									<a class="text-muted text-uppercase">(report)</a>
								</div>
							</div>
						</div>
					</div>
				</section>
			</div>
		</div>
	</div>
</div>

</@layout.layout_base_modico>