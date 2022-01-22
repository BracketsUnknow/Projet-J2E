<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:import url="header.jsp"></c:import>
<style>
.modal {
	display: none; /* Hidden by default */
	position: fixed; /* Stay in place */
	z-index: 1244; /* Sit on top */
	padding-top: 100px; /* Location of the box */
	left: 0;
	top: 0;
	width: 100%; /* Full width */
	height: 700px; /* Full height */
	overflow: auto; /* Enable scroll if needed */
	background-color: rgb(0, 0, 0); /* Fallback color */
	background-color: rgba(0, 0, 0, 0.3); /* Black w/ opacity */
}

/* Modal Content (image) */
.modal-content {
	margin: auto;
	display: block;
	width: 50%;
	height: auto;
	max-width: 700px;
}

/* Caption of Modal Image */
#caption {
	margin: auto;
	display: block;
	width: 80%;
	max-width: 700px;
	text-align: center;
	color: #ccc;
	padding: 10px 0;
	height: 700px;
}

/* Add Animation */
.modal-content, #caption {
	-webkit-animation-name: zoom;
	-webkit-animation-duration: 0.6s;
	animation-name: zoom;
	animation-duration: 0.6s;
}

@
-webkit-keyframes zoom {
	from {-webkit-transform: scale(0)
}

to {
	-webkit-transform: scale(1)
}

}
@
keyframes zoom {
	from {transform: scale(0)
}

to {
	transform: scale(1)
}

}
/* The Close Button */
.close {
	position: absolute;
	top: 15px;
	right: 35px;
	color: #f1f1f1;
	font-size: 40px;
	font-weight: bold;
	transition: 0.3s;
}

.close:hover, .close:focus {
	color: #bbb;
	text-decoration: none;
	cursor: pointer;
}

/* 100% Image Width on Smaller Screens */
@media only screen and (max-width: 700px) {
	.modal-content {
		width: 100%;
	}
}

.formulaire {
	width: 30%;
	border-radius: 5px;
	background-color: #f2f2f2;
	padding: 20px;
	margin-left: auto;
	margin-right: auto;
}

form {
	margin-left: auto;
	margin-right: auto;
}

.btn {
	margin-left: 85%;
}

input[type=text] {
	padding: 12px 20px;
	border: 1px solid #ccc;
	border-radius: 4px;
	box-sizing: border-box;
	width: 50%;
}
</style>

<main>
	<div class="album">
		<img src="images/addImage.png" width="85" height="75"
			onclick="afficher()">
		<p>creer album</p>
		<img src="images/star.png" width="20" height="20">
	</div>

	<c:forEach items="${requestScope.albums }" var="album">
		<div class="album">
		<p><img class="option" src="images/pluss.png"  onclick="myFunction()"></p>
			<img src="images/empty.png" width="85" height="75">
			<p class="title">${album.name }</p>
			<c:if test="${ album.getVisibility() eq 'prive' }">
				<img src="images/lock.png" width="20" height="20">
			</c:if>
			<c:if test="${ album.getVisibility() eq 'public' }">
				<img src="images/unlock.png" width="20" height="20">
			</c:if>
		</div>
		<div class="dropdown">
			<div id="myDropdown" class="dropdown-content">
				<a href="">Ajouter une photo</a> <a href="">infos</a> <a href="">supprimer</a> <a href="">modifier</a>
			</div>
		</div>
	</c:forEach>


	<style>
	.image{
  width: 100%;
}


.option{
  margin-left: 10%;
}
.dropbtn {
  background-color: #3498DB;
  color: white;
  padding: 16px;
  font-size: 16px;
  border: none;
  cursor: pointer;
}

.dropbtn:hover, .dropbtn:focus {
  background-color: #2980B9;
}

.dropdown {
  position: relative;
  display: inline-block;
}

.dropdown-content {
  display: none;
  position: absolute;
  background-color: #f1f1f1;
  min-width: 160px;
  overflow: auto;
  box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
  z-index: 1;
}

.dropdown-content a {
  color: black;
  padding: 12px 16px;
  text-decoration: none;
  display: block;
}

.dropdown a:hover {background-color: #ddd;}

.show {display: block;}
.image {
	width: 100%;
}

.option {
	margin-left: 85%;
}

.dropbtn {
	background-color: #3498DB;
	color: white;
	padding: 16px;
	font-size: 16px;
	border: none;
	cursor: pointer;
}

.dropbtn:hover, .dropbtn:focus {
	background-color: #2980B9;
}

.dropdown {
	position: relative;
	display: inline-block;
}

.dropdown-content {
	display: none;
	position: absolute;
	background-color: #f1f1f1;
	min-width: 160px;
	overflow: auto;
	box-shadow: 0px 8px 16px 0px rgba(0, 0, 0, 0.2);
	z-index: 1;
}

.dropdown-content a {
	color: black;
	padding: 12px 16px;
	text-decoration: none;
	display: block;
}

.dropdown a:hover {
	background-color: #ddd;
}

.show {
	display: block;
}
</style>


	<script>
		/* When the user clicks on the button, 
		toggle between hiding and showing the dropdown content */
		function myFunction() {
			document.getElementById("myDropdown").classList.toggle("show");
		}
		var img = document.getElementsByClassName("option");

		// Close the dropdown if the user clicks outside of it
		img.onclick = function(event) {
			if (!event.target.matches('.dropbtn')) {
				var dropdowns = document
						.getElementsByClassName("dropdown-content");
				var i;
				for (i = 0; i < dropdowns.length; i++) {
					var openDropdown = dropdowns[i];
					if (openDropdown.classList.contains('show')) {
						openDropdown.classList.remove('show');
					}
				}
			}
		}
	</script>



























	<div id="myModal" class="modal">
		<span class="close">&times;</span>
		<div class="formulaire">
			<form action="createAlbum" method="post" class="form-container">
				<input type="text" name="nameAlbum" placeholder="Nom album"><br />
				<input type="text" name="theme" placeholder="détails"><br />
				<select name="visibility" id="img">
					<option value="public">public</option>
					<option value="prive">prive</option>
				</select> <input type="submit" class="btn" value="Suivant">
			</form>

		</div>

		<script>
			// Get the modal
			var modal = document.getElementById("myModal");

			function afficher() {
				modal.style.display = "block";

			}
			var span = document.getElementsByClassName("close")[0];
			span.onclick = function() {
				modal.style.display = "none";
			}
		</script>


	</div>

</main>


<c:if test="${!empty succes_create_album }">

	<script>
		alert("Album crée avec succes !");
	</script>

</c:if>
<script>
    /* When the user clicks on the button, 
    toggle between hiding and showing the dropdown content */
    function myFunction() {
      document.getElementById("myDropdown").classList.toggle("show");
    }
    var img= document.getElementsByClassName("option");
    
    // Close the dropdown if the user clicks outside of it
    img.onclick = function(event) {
      if (!event.target.matches('.dropbtn')) {
        var dropdowns = document.getElementsByClassName("dropdown-content");
        var i;
        for (i = 0; i < dropdowns.length; i++) {
          var openDropdown = dropdowns[i];
          if (openDropdown.classList.contains('show')) {
            openDropdown.classList.remove('show');
          }
        }
      }
    }
    </script>
    
<c:import url="footer.jsp"></c:import>


