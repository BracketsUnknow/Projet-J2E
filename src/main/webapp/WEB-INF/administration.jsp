<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script>
function confirmation(button) {
	

	  var href = button.getAttribute("href");
	  console.log(href);
	  var result = confirm("Etes vous sur de supprimer cet utilisateur  ?");
	  if (result) {
	    window.location.href = href;
	  } else {

	    return false;
	  }
	}

</script>
<style>

	.erreur{
	color:red; align:center; font-size:14px;
	maring:0;
	}
</style>

<c:import url="header.jsp"></c:import>
	<c:if test="${!empty requestScope.success_modify }">
		
		<script>
			alert("Modification réussie");
		</script>
		
		
		</c:if>
<main>
	

	<c:if test="${!empty requestScope.success_add }">
		<script>
			alert("Ajout Effectué avec succes !"); 
 	</script>
	
	</c:if>

	<link rel="stylesheet" type="text/css"  href="css/modify.css" />
<!-- 	<div style="align:center;"> -->
<!-- 	<button style="margin:auto; display:block;"> -->
	
<!-- 	Ajouter un utilisateur</button> -->
<!-- 	</div> -->
	<div class="imagediv">
		<img class="image" id="myImg" src="images/icon.png" alt="Snow" >
	</div>
	
	

	
	<div class="left">
		<div class="container mt-3 mb-4">
			<div class="col-lg-9 mt-1 mt-lg-0">
				<div class="row">
					<div class="col-md-12">
						<div
							class="user-dashboard-info-box table-responsive mb-0 bg-white p-4 shadow-sm">
							<table class="table manage-candidates-top mb-0">
								<thead>
									<tr>
										<th>utilisateur</th>
										<th class="action text-right">Action</th>
									</tr>
								</thead>
								<tbody>
									<!-- bouble -->

									<c:forEach items="${requestScope.users }" var="user">
										<tr class="candidates-list" style="background-color: white;">
											<td class="title">
												<div class="thumb">
													<img class="img-fluid"
														src="https://bootdey.com/img/Content/avatar/avatar7.png"
														alt="">
												</div>
												<div style="background-color: white;"
													class="candidate-list-details">
													<div class="candidate-list-info">
														<div class="candidate-list-title">
															<h5 class="mb-0">
																<a href="#"><c:out value="${user.name}" /> <c:out
																		value="${user.surname}" /></a>
															</h5>
														</div>
														<div class="candidate-list-option"
															style="background-color: white;">
															<ul class="list-unstyled"
																style="background-color: white;">
																<p>
																	<c:out value="${user.function}" />
																</p>
															</ul>
														</div>
													</div>
												</div>
											</td>
											<c:if test="${user.function eq 'utilisateur_simple'}">
												<td>
													<ul style="background-color: white;" list-unstyled mb-0d-flexjustify-content-end">
														<li><a href="<c:url value="/seeUser?id=${ user.id }"/>" class="text-primary"
															data-toggle="tooltip" title="" data-original-title="view"><i
																class="far fa-eye"></i></a></li>
														<li><a href="<c:url value="/modifyByAdmin?id=${ user.id }"/>" class="text-info" data-toggle="tooltip" title="" data-original-title="Edit"><i class="fas fa-pencil-alt"></i></a></li>
														<li><a href="<c:url value="/deleteUser?id=${ user.id }"/>" class="text-danger"
															data-toggle="tooltip" title=""
															data-original-title="Delete"  onclick="return confirmation(this)"><i
																class="far fa-trash-alt"></i></a></li>
													</ul>
												</td>
											</c:if>
										</tr>

									</c:forEach>
									<!--end 
							 bouble -->
								</tbody>
							</table>
							<div class="text-center mt-3 mt-sm-3"></div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	
		
	<c:if test="${!empty requestScope.success_delete }">
	
		<script>
		
			alert("suppression réussie");
		
		</script>
	
	</c:if>
	<div id="myModal" class="modal"> 
		<span class="close">&times;</span> 
		<div class="formulaire">
		  <form action="addUser" class="form-container" method="post">
		    <h1>Ajouter un utilisateur</h1>

			<p class="erreur">${ requestScope.error_add.email }</p>
		    <label for="email"><b>Email</b></label>
		    <input type="text" placeholder="Enter Email" name="email"	value="${requestScope.user.email }" required>
		    <p class="erreur">${ requestScope.error_add.surname }</p>
		    <label for="prenom"><b>Prenom</b></label>
		    <input type="text" name="surname" required value="${requestScope.user.surname }">
		    <p class="erreur">${ requestScope.error_add.name }</p>
		    <label for="nom"><b>Nom</b></label>
		    <input type="text"  name="name" value="${requestScope.user.name }" required>
			<p class="erreur">${ requestScope.error_add.password }</p>
		    <label for="psw"><b>Mot de Passe</b></label>
		    <input type="password" placeholder="Enter Password" name="password"  required>
		    <label for="psw"><b>Confirmer Mot de Passe</b></label>
		    <input type="password" placeholder="Confirmer Password" name="passwordBis"  required>
		    <select name="function" id="function" value="${requestScope.user.function }">
			  	<option name="function" value="utilisateur_Simple">Utilisateur simple</option>
			  	<option name="function" value="administrateur">Administrateur</option>
			</select>

		    <input type="submit" class="btn" value="Souscrire">
		    
		  </form>

		</div>

<script>
// Get the modal
var modal = document.getElementById("myModal");

// Get the image and insert it inside the modal - use its "alt" text as a caption
var img = document.getElementById("myImg");

img.onclick = function(){
  modal.style.display = "block";
  
}

// Get the <span> element that closes the modal
var span = document.getElementsByClassName("close")[0];

// When the user clicks on <span> (x), close the modal
span.onclick = function() { 
  modal.style.display = "none";
}
</script>



	<c:if test="${!empty requestScope.error_add }">
	
		<script>
		
			//alert("erreur lors de l'ajout");

			// Get the modal
			var modal = document.getElementById("myModal");
			(function(){
			  modal.style.display = "block";
			  
			})();

			
		
		</script>
	
	</c:if>


<c:if test="${!empty requestScope.myUser }">



	
		<script>
		
			alert("ok voir");
			

		

			
		
		</script>
	
	</c:if>









		


<c:if test="${!empty requestScope.modifyUser }">
	
		<script>
		
		
		
			
			//alert("erreur lors de l'ajout");

			// Get the modal
			var modal = document.getElementById("myModal");
			(function(){
			  modal.style.display = "block";
			  
			})();

			
		

			
		
		</script>
	
	</c:if>

	<!--  -->

</main>




<c:import url="footer.jsp"></c:import>


