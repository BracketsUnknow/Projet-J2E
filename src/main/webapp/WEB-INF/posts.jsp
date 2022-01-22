<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:import url="header.jsp"></c:import>
<style>
/* Popup container - can be anything you want */
.popup {
	color: #fff;
	text-align: center;
	position: relative;
	display: inline-block;
	cursor: pointer;
	-webkit-user-select: none;
	-moz-user-select: none;
	-ms-user-select: none;
	user-select: none;
	z-index: 200;
}

/* The actual popup */
.popup .popuptext {
	visibility: hidden;
	width: 250px;
	background-color: #555;
	color: #fff;
	text-align: center;
	border-radius: 6px;
	padding: 8px 0;
	position: absolute;
	z-index: 1;
	bottom: 125%;
	left: 50%;
	margin-left: -80px;
}

/* Popup arrow */
.popup .popuptext::after {
	content: "";
	position: absolute;
	top: 100%;
	left: 50%;
	margin-left: -5px;
	border-width: 5px;
	border-style: solid;
	border-color: #555 transparent transparent transparent;
	z-index: 200;
}

/* Toggle this class - hide and show the popup */
.popup .show {
	visibility: visible;
	-webkit-animation: fadeIn 1s;
	animation: fadeIn 1s;
	z-index: 100;
}

/* Add animation (fade in the popup) */
@
-webkit-keyframes fadeIn {
	from {opacity: 0;
}

to {
	opacity: 1;
}

}
@
keyframes fadeIn {
	from {opacity: 0;
}

to {
	opacity: 1;
}

}
#myImg {
	border-radius: 5px;
	cursor: pointer;
	transition: 0.3s;
}

#myImg:hover {
	opacity: 0.7;
}

/* The Modal (background) */
.modal {
	display: none; /* Hidden by default */
	position: fixed; /* Stay in place */
		z-index:200; /* Sit on top */
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

.corps {
	margin-left: 10%;
	position: absolute;
	width: 1049px;
	height: 628px;
	left: 0px;
	top: 0px;
	background: #F2F7F9;
	border-radius: 12px;
}

.composant-menu {
	display: flex;
	flex-direction: column;
	margin-bottom: 124px;
	align-items: center;
	width: 192px;
	height: 628px;
	background: #FFFFFF;
	border-radius: 12px;
}

img {
	width: 175px;
	height: 175px;
	background-color: pink;
	background: url(logobis);
}

.menu {
	margin-top: 100px;
	display: flex;
	flex-direction: column;
	font-family: Roboto;
	font-style: normal;
	font-weight: normal;
	font-size: 24px;
	line-height: 28px;
	color: #000000;
}

.menu-item {
	padding: 12px;
}

input {
	position: absolute;
	width: 181px;
	height: 26px;
	left: 380px;
	top: 19px;
	background: #FFFFFF;
	border-radius: 5px;
}

input[type=submit] {
	margin-left: 50%;
	position: absolute;
	width: 92px;
	height: 26px;
	color: white;
	background: #A263E1;
	border-radius: 12px;
}

.composant-image {
	margin: 89px 156px 264px 398px;
	position: absolute;
	width: 300px;
	height: 500px;
	background: #FFFFFF;
	border-radius: 5px;

}

.pp {
	position: absolute;
	width: 50px;
	height: 50px;
	border-radius: 50%;
}

.nom-user {
	margin-left: 70px;
	position: absolute;
	width: 101px;
	height: 29px;
	font-family: Roboto;
	font-style: normal;
	font-weight: normal;
	font-size: 12px;
	line-height: 14px;
	color: #000000;
}

.image {
	margin: 73px 5px 5px 5px;
	position: absolute;
	width: 290px;
	height: 420px;
}

.icon {
	padding-left: 10px;
	width: 20px;
	height: 20px;
}
</style>
<main>


	<div class="composant-image">
		<img class="pp" src="images/scroll.jpeg">
		<h3 class="nom-user">Fadel</h3>
		<img class="image" id="myImg" src="images/img.png" alt="Snow">
	</div>
	<div id="myModal" class="modal">
		<span class="close">&times;</span> <img class="modal-content"
			id="img01">
		<center>
			<div class="popup" onclick="myFunction()">
				Titre photo <span class="popuptext" id="myPopup">nom:
					maquette<br />date de création: 13janvier<br />album: css
				</span>
			</div>
		</center>
		<div id="caption"></div>
		
		
	</div>



</main>

<script>
	// Get the modal
	var modal = document.getElementById("myModal");

	// Get the image and insert it inside the modal - use its "alt" text as a caption
	var img = document.getElementById("myImg");
	var modalImg = document.getElementById("img01");
	var captionText = document.getElementById("caption");
	img.onclick = function() {
		modal.style.display = "block";
		modalImg.src = this.src;
		captionText.innerHTML = this.alt;
	}

	// Get the <span> element that closes the modal
	var span = document.getElementsByClassName("close")[0];

	// When the user clicks on <span> (x), close the modal
	span.onclick = function() {
		modal.style.display = "none";
	}
</script>



<c:import url="footer.jsp"></c:import>


