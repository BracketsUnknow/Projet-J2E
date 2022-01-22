<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<style>
.erreur {
	color: red;
	align: center;
	font-size: 14px;
	maring: 0;
}
</style>
<c:import url="header.jsp"></c:import>


<main>
	<div class="container">
		<div class="col-lg-8">
			<div class="card">
				<form method="post" action="modifyByAdmin">
					<input type="hidden" value="${requestScope.myUser.getId() }"
						name="id" /> <input type="hidden"
						value="${requestScope.myUser.getFunction() }" name="function" />
					<div class="card-body">
						<div class="row mb-3">
							<p class="erreur">${ requestScope.errors_update.name }</p>
							<div class="col-sm-3">
								<h6 class="mb-0">Name</h6>
							</div>
							<div class="col-sm-9 text-secondary">
								<input type="text" name="name" class="form-control"
									value="${requestScope.myUser.getName() }">
							</div>
						</div>
						<div class="row mb-3">
							<p class="erreur">${ requestScope.errors_update.surname }</p>
							<div class="col-sm-3">
								<h6 class="mb-0">surname</h6>
							</div>
							<div class="col-sm-9 text-secondary">
								<input name="surname" type="text" class="form-control"
									value="${requestScope.myUser.getSurname() }">
							</div>
						</div>
						<div class="row mb-3">
							<p class="erreur">${ requestScope.errors_update.email }</p>
							<div class="col-sm-3">
								<h6 class="mb-0">Email</h6>
							</div>
							<div class="col-sm-9 text-secondary">
								<input name="email" type="text" class="form-control"
									value="${requestScope.myUser.getEmail() }">
							</div>
						</div>
						<div class="row mb-3">
							<p class="erreur">${ requestScope.errors_update.password }</p>
							<div class="col-sm-3">
								<h6 class="mb-0">password</h6>
							</div>
							<div class="col-sm-9 text-secondary">
								<input name="password" class="form-control"
									value="${requestScope.myUser.getPassword() }">
							</div>
						</div>
						<div class="row mb-3">
							<p class="erreur">${ requestScope.errors_update.password }</p>
							<div class="col-sm-3">
								<h6 class="mb-0">Confirm password</h6>
							</div>
							<div class="col-sm-9 text-secondary">
								<input name="passwordBis" class="form-control"
									value="${requestScope.myUser.getPassword() }">
							</div>

						</div>
						<div class="row mb-3">
							<p class="erreur">${ requestScope.errors_update.function }</p>
							<div class="col-sm-3">
								<h6 class="mb-0">Function</h6>
							</div>
							<div class="col-sm-9 text-secondary">
								<select name="function" id="function"
									value="${requestScope.myUser.function }">
									<option name="function" value="utilisateur_Simple">Utilisateur
										simple</option>
									<option name="function" value="administrateur">Administrateur</option>
								</select>
							</div>

						</div>


						<c:if test="${!empty requestScope.admin }">
							<div class="row">
								<div class="col-sm-3"></div>
								<div class="col-sm-9 text-secondary">
									<input type="submit" class="btn btn-primary px-4"
										value="Modifier">
								</div>
							</div>
						</c:if>
					</div>
				</form>
			</div>

			<div class="row">
				<div class="col-sm-2">
					<div class="card"></div>
				</div>
			</div>
		</div>
	</div>
	</div>
	</div>

</main>




<c:import url="footer.jsp"></c:import>





