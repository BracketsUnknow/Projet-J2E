<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
	<title>connexion</title>
	<link rel="stylesheet" href="css/connexion.css">
	<script src="js/conn.js"></script>
	<link rel="stylesheet" href="css/inscription.css">
	<style>
	.erreur{
	color:red; align:center; font-size:14px;
	maring:0;
	}
	</style>
</head>
<body>
<div class="container">
	<!-- code here -->
	<div class="card">
		<div class="card-image">	
		</div>
		<form class="card-form" method="post" action="login">
			<c:if test="${!empty requestScope.errors_login }">
				<label style="color:red; align:center; font-size:16px;" class="error-label">Identifiants incorrect !</label>
			</c:if>
			<div class="input">
				<label class="input-label">Email</label>
				<input type="text" name="email" class="input-field" value="${param.email}"required/>
			</div>
			<div class="input">
				<label class="input-label">Password</label>
				<input type="password" name="password" class="input-field" required/>
			</div>
			<div class="action">
				<button class="action-button">Se connecter</button>
				<button class="action-button" onclick="openForm()">S'inscrire</button>
				
							
			</div>
		</form>

		
	</div>
</div>
<html>
<div class="form-popup" id="myForm">
  <form action="register" method="post" class="form-container">
    <h1>Sign in</h1>
	
	<p class="erreur">${requestScope.errors_register.email }</p>
    <label for="email"><b>Email</b></label>
    <input type="text" value="${param.email}" name="email" required>
    <p class="erreur">${requestScope.errors_register.surname }</p>
    <label for="prenom"><b>Prenom</b></label>
    <input type="text" name="surname" value="${param.surname}" required>
    <p class="erreur">${requestScope.errors_register.name }</p>
    <label for="nom"><b>Nom</b></label>
    <input type="text"  name="name"  value="${param.name}" required>
	<p class="erreur">${requestScope.errors_register.password }</p>
    <label for="psw"><b>Password</b></label>
    <input type="password" placeholder="Enter Password" name="password" required>
    <label for="psw"><b>Confirmer Password</b></label>
    <input type="password" placeholder="Confirmer Password" name="passwordBis" required>
    

    <button type="submit" class="btn">Souscrire</button>
    <button type="button" class="btn cancel" onclick="closeForm()">Close</button>
  </form>
</div>



</body>
</html>




