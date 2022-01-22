<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="css/page.css" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<title>InstaPhoto</title>
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round" />
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" />
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.1/css/all.min.css"
	integrity="sha256-2XFplPlrFClt0bIdPgpz8H7ojnk10H69xRqd9+uTShA="
	crossorigin="anonymous" />
<link rel="stylesheet"
	href="https://fonts.googleapis.com/icon?family=Material+Icons" />
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" />
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>

</head>
<body>
	<header>
		<nav class="navbar navbar-expand-xl navbar-dark bg-pink">
			<a href="#" class="navbar-brand"><i class="fa fa-cube"></i>SCROLL</b></a>
			<button type="button" class="navbar-toggler" data-toggle="collapse"
				data-target="#navbarCollapse">
				<span class="navbar-toggler-icon"></span>
			</button>
			<!-- Collection of nav links, forms, and other content for toggling -->
			<div id="navbarCollapse"
				class="collapse navbar-collapse justify-content-start">
				<div class="navbar-nav ml-auto">
				<c:if test="${empty sessionScope.user}">
					<div>
						<a href="/AlbumPhoto/login" class="login">Connexion</a>
					</div>
				</c:if>
				<c:if test="${!empty sessionScope.user}">
					<div class="nav-item dropdown">
						<a href="#" data-toggle="dropdown"
							class="nav-item nav-link dropdown-toggle user-action"><img
							src="${sessionScope.user.getProfil()}"  
							class="avatar" alt="Avatar" /> ${sessionScope.user.getName() } <b
							class="caret"></b></a>
						<div class="dropdown-menu">
							<div class="divider dropdown-divider"></div>
							<a href="logout" class="dropdown-item"><i
								class="material-icons">&#xE8AC;</i> Logout</a>
						</div>
					</div>
				</c:if>
				</div>
			</div>
		</nav>
		<link href="https://use.fontawesome.com/releases/v5.8.2/css/all.css"
			rel="stylesheet" />
		<nav>
			<c:if test="${!empty sessionScope.user }">
				<ul>
					<li><a href="<c:url value="/posts"/>">Posts</a></li>
					<li><a href="<c:url value="/createAlbum"/>">Mes Albums</a></li>
					<li><a href="<c:url value="/photos"/>">Mes Photos</a></li>
					<li><a href="#sharewithme">Partagés avec moi</a></li>
					<li><a href="#theme">Themes</a></li>
					<c:if test="${sessionScope.user.getFunction() eq 'administrateur' }">
						<li><a href="<c:url value="/listUser"/>"><c:out
									value="Administrateur" /></a></li>
					</c:if>
					<li><a href="<c:url value="/modifyUser"/>">Mon compte</a></li>
				</ul>
			</c:if>
		</nav>
	</header>