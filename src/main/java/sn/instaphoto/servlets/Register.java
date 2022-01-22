package sn.instaphoto.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sn.instaphoto.beans.User;
import sn.instaphoto.dao.UserDao;
import sn.instaphoto.metier.AccountManagement;
import sn.instaphoto.utilities.PasswordEncode;
import sn.instaphoto.utilities.Workspace;

@WebServlet({ "/register", "/validate" })
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VUE_REGISTER = "/WEB-INF/connexion.jsp";
	private static final String VUE_VALIDATION = "/WEB-INF/validation.jsp";

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		switch (request.getServletPath()) {
		case "/register":
			getServletContext().getRequestDispatcher(VUE_REGISTER).forward(request, response);
			break;

		case "/validate":
			getServletContext().getRequestDispatcher(VUE_VALIDATION).forward(request, response);
			break;
		default:
			break;
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		AccountManagement register = new AccountManagement(request);
		
		if (request.getServletPath().equals("/register")) {
			if (register.register()) {
				request.getSession().setAttribute("generatedCode", register.getCodeDeConfirmation());
				request.getSession().setAttribute("user", register.getUser());
				getServletContext().getRequestDispatcher(VUE_VALIDATION).forward(request, response);
			} else {
				register.getErrors().entrySet().forEach(entry -> {
				    System.out.println(entry.getKey() + " " + entry.getValue());
				});
				request.setAttribute("errors_register", register.getErrors());
				getServletContext().getRequestDispatcher(VUE_REGISTER).forward(request, response);
			}

		}
		if (request.getServletPath().equals("/validate")) {
			if (register.checkValidate(request)) {
				User user = (User) request.getSession().getAttribute("user");
				user.setPassword(PasswordEncode.encode(user.getPassword()));
				UserDao.addUser(user);
				Workspace.createWorkspace(user.getEmail());
				System.out.println("utilisateur ajouté avec succes !");
				this.getServletContext().getRequestDispatcher("/WEB-INF/posts.jsp");
			} else {

				System.out.println("mauvais code");
				request.setAttribute("errors", register.getErrors());
				response.sendRedirect(VUE_VALIDATION);

			}

		}

	}

}