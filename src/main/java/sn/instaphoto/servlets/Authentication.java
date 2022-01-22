package sn.instaphoto.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sn.instaphoto.beans.User;
import sn.instaphoto.metier.LoginUser;
import sn.instaphoto.utilities.PasswordEncode;

@WebServlet({"","/login","/logout"})
public class Authentication extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String VUE_LOGIN = "/WEB-INF/connexion.jsp";
	private static String VUE_POSTS ="/WEB-INF/posts.jsp";
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		switch (request.getServletPath()) {
		case "/login":
			this.getServletContext().getRequestDispatcher(VUE_LOGIN).forward(request, response);
			break;
		case "":
			this.getServletContext().getRequestDispatcher(VUE_POSTS).forward(request, response);
			break;

		case "/logout":
			request.getSession().invalidate();
			this.getServletContext().getRequestDispatcher(VUE_LOGIN).forward(request, response);
			break;
		default:
			break;
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	
	
		LoginUser loginUser = new LoginUser(request);
		loginUser.connexion();
		if(loginUser.connexion() == true) {
			User user =loginUser.getUser();
			user.setPassword(PasswordEncode.decode(user.getPassword()));
			request.getSession().setAttribute("user", user);
			this.getServletContext().getRequestDispatcher(VUE_POSTS).forward(request, response);
		}
		else {
			request.setAttribute("errors_login", loginUser.getErrors());
			this.getServletContext().getRequestDispatcher(VUE_LOGIN).forward(request, response);
		}
	}

}
