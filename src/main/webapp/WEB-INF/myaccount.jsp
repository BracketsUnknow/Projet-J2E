<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
<div class="container">
        <div class="main-body">
            <div class="row">
                <div class="col-lg-4">
                    <div class="card">
                        <div class="card-body">
                            <div class="d-flex flex-column align-items-center text-center">
                                <img src="<c:url value="data/test/photo.jpg"/> " alt="Admin" class="rounded-circle p-1 bg-primary" width="110">
                               <div class="mt-3">              
                                    <h4>${sessionScope.user.getName() }</h4>
                                    <p class="text-secondary mb-1">${sessionScope.user.getFunction() }</p>
                                </div>
                            </div>
                            <hr class="my-4">

                        </div>
                    </div>
                </div>
             
                <div class="col-lg-8">
                    <div class="card">
                         <form method="post" action="modifyUser">
                         <input type="hidden" value="${sessionScope.user.getId() }" name="id" />
                         <input type="hidden" value="${sessionScope.user.getFunction() }" name="function" />
                        <div class="card-body">
                            <div class="row mb-3">
                            <p class="erreur">${ requestScope.errors_update.name }</p>
                                <div class="col-sm-3">
                                    <h6 class="mb-0">Name</h6>
                                </div>
                                <div class="col-sm-9 text-secondary">
                                    <input type="text"  name="name" class="form-control" value="${sessionScope.user.getName() }">
                                </div>
                            </div>
                            <div class="row mb-3">
                             <p class="erreur">${ requestScope.errors_update.surname }</p>
                                <div class="col-sm-3">
                                    <h6 class="mb-0">surname</h6>
                                </div>
                                <div class="col-sm-9 text-secondary">
                                    <input name="surname" type="text" class="form-control" value="${sessionScope.user.getSurname() }">
                                </div>
                            </div>
                            <div class="row mb-3">
                            	 <p class="erreur">${ requestScope.errors_update.email }</p>
                                <div class="col-sm-3">
                                    <h6 class="mb-0">Email</h6>
                                </div>
                                <div class="col-sm-9 text-secondary">
                                    <input name="email" type="text" class="form-control" value="${sessionScope.user.getEmail() }">
                                </div>
                            </div>
                            <div class="row mb-3">
                              <p class="erreur">${ requestScope.errors_update.password }</p>
                                <div class="col-sm-3">
                                    <h6 class="mb-0">password</h6>
                                </div>
                                <div class="col-sm-9 text-secondary">
                                    <input name="password" class="form-control" value="${sessionScope.user.getPassword() }">
                                </div>
                            </div>
                            <div class="row mb-3">
                             <p class="erreur">${ requestScope.errors_update.password }</p>
                                <div class="col-sm-3">
                                    <h6 class="mb-0">Confirm password</h6>
                                </div>
                                <div class="col-sm-9 text-secondary">
                                    <input  name="passwordBis" class="form-control" value="${sessionScope.user.getPassword() }">
                                </div>
                                
                            </div>
                            <div class="row">
                                <div class="col-sm-3"></div>
                                <div class="col-sm-9 text-secondary">
                                    <input type="submit" class="btn btn-primary px-4" value="Modifier">
                                </div>
                            </div>
                        </div>
                        </form>
                    </div>
               
                    <div class="row">
                        <div class="col-sm-2">
                            <div class="card">
                                
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

  </main>




<c:import url="footer.jsp"></c:import>





