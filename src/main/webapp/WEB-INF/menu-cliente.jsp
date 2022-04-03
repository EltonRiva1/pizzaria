<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<style>
#btn-sair {
	margin-top: 7px;
}
</style>
<nav class="navbar navbar-default">
	<div class="container-fluid">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="#"><spring:message
					code="views.menu.pizzaria" /></a>
		</div>
		<div class="collapse navbar-collapse"
			id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav">
				<li><a href="pizzarias">Pizzarias</a></li>
				<li><a href="?lang=pt_BR"><img alt="Português"
						src="${path}/static/img/br.png" height="25px"></a></li>
				<li><a href="?lang=en_US"><img alt="English"
						src="${path}/static/img/us.png" height="25px"></a></li>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li><a href="#" class="btn btn-default">Me cadastrar</a></li>
				<li><a href="#" class="btn btn-primary">Entrar</a></li>
			</ul>
		</div>
	</div>
</nav>