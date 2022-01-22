package sn.instaphoto.servlets;

import java.io.IOException;
import java.util.ArrayList;

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

/**
 * Servlet implementation class Administration
 */
@WebServlet({"/administration","/modifyUser","/listUser","/deleteUser","/addUser","/seeUser","/modifyByAdmin"})
public class Administration extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final static String VUE_ADD_USER ="";
	private final static String VUE_DELETE_USER ="";
	private final static String VUE_LIST_USER ="/WEB-INF/administration.jsp";
	private final static String VUE_MODIFY_USER ="/WEB-INF/myaccount.jsp";
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		switch (request.getServletPath()) {
		case "/addUser":
			this.getServletContext().getRequestDispatcher(VUE_ADD_USER).forward(request, response);
			break;
		case "/deleteUser":
			doPost(request, response);
			//this.getServletContext().getRequestDispatcher(VUE_DELETE_USER).forward(request, response);
			break;
		case "/modifyUser":
			this.getServletContext().getRequestDispatcher(VUE_MODIFY_USER).forward(request, response);
			break;
		case "/listUser":
			request.setAttribute("users", UserDao.lister());
			this.getServletContext().getRequestDispatcher(VUE_LIST_USER).forward(request, response);
			break;
		case "/modifyByAdmin":
			try {
				int id =Integer.parseInt(request.getParameter("id"));
				User myUser =null;
				ArrayList<User> al = UserDao.lister();
				for(User user : al) {
					if(user.getId() == id)
						myUser = user;
				}
				myUser.setPassword(PasswordEncode.decode(myUser.getPassword()));
				request.setAttribute("users", UserDao.lister());
				request.setAttribute("admin",true);
				request.setAttribute("myUser", myUser);
				this.getServletContext().getRequestDispatcher("/WEB-INF/modifyUserAndSeeUser.jsp").forward(request, response);
			}
			catch(Exception e) {

				e.printStackTrace();
			}
			break;
		case "/seeUser":
			try {
				int id =Integer.parseInt(request.getParameter("id"));
				User myUser =null;
				ArrayList<User> al = UserDao.lister();
				for(User user : al) {
					if(user.getId() == id)
						myUser = user;
				}
				request.setAttribute("users", UserDao.lister());
				request.setAttribute("myUser", myUser);
				this.getServletContext().getRequestDispatcher("/WEB-INF/modifyUserAndSeeUser.jsp").forward(request, response);
			}
			catch(Exception e) {
				//erreur 404
			}
			
				
			
			break;
		default:
			break;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		

		switch (request.getServletPath()) {
		case "/addUser":
			AccountManagement management = new AccountManagement(request);
			if(management.register()) {
				UserDao.addUser(management.getUser());
				Workspace.createWorkspace(management.getUser().getEmail());
				request.setAttribute("success_add",true);
				
			}
			else {
				request.setAttribute("error_add",management.getErrors());
				
			}
			request.setAttribute("user",management.getUser());
			request.setAttribute("users", UserDao.lister());
			this.getServletContext().getRequestDispatcher(VUE_LIST_USER).forward(request, response);
			break;
		case "/deleteUser":
			AccountManagement.deleteUser(Integer.parseInt(request.getParameter("id")));
			request.setAttribute("success_delete", true);			
			request.setAttribute("users", UserDao.lister());
			this.getServletContext().getRequestDispatcher(VUE_LIST_USER).forward(request, response);
			break;
		
		case "/modifyUser":
			AccountManagement manage = new AccountManagement(request);
			if(manage.modifyAccount()) {
				User user = manage.getUser();
				user.setPassword(PasswordEncode.encode(manage.getUser().getPassword()));
				if(UserDao.modifier(manage.getUser())) {
					
					user.setPassword(PasswordEncode.decode(manage.getUser().getPassword()));
					request.setAttribute("myUser", user);
					request.setAttribute("success_modify", true);
					
				}
				else {
				
					user.setPassword(PasswordEncode.decode(manage.getUser().getPassword()));
					request.setAttribute("myUser", user);
					request.setAttribute("errors_update", manage.getErrors());
				
				}
				this.getServletContext().getRequestDispatcher(VUE_MODIFY_USER).forward(request, response);
			}
			else {
				manage.getErrors().entrySet().forEach(entry -> {
				    System.out.println(entry.getKey() + " " + entry.getValue());
				});
				System.out.println("pas modifié ehhh");
				request.setAttribute("errors_update", manage.getErrors());
				this.getServletContext().getRequestDispatcher(VUE_MODIFY_USER).forward(request, response);
			}
			
			break;
		case "/listUser":
			break;
			
		case "/modifyByAdmin":
			AccountManagement manager = new AccountManagement(request);
			if(manager.modifyAccount()) {
				User user = manager.getUser();
				user.setPassword(PasswordEncode.encode(manager.getUser().getPassword()));
				if(UserDao.modifier(manager.getUser())) {
					user.setPassword(PasswordEncode.decode(manager.getUser().getPassword()));
					request.getSession().setAttribute("myUser", user);					
					request.setAttribute("success_modify", true);
					request.setAttribute("users",UserDao.lister());
					System.out.println(user.getFunction());
					this.getServletContext().getRequestDispatcher(VUE_LIST_USER).forward(request, response);
					
				}
				else {
					user.setPassword(PasswordEncode.decode(manager.getUser().getPassword()));
					request.setAttribute("errors_update", manager.getErrors());
					this.getServletContext().getRequestDispatcher("/WEB-INF/modifyUserAndSeeUser.jsp").forward(request, response);
				
				
				}
				
			}
			else {
				manager.getErrors().entrySet().forEach(entry -> {
				    System.out.println(entry.getKey() + " " + entry.getValue());
				});
				System.out.println("pas modifié ehhh");
				request.setAttribute("errors_update", manager.getErrors());
				this.getServletContext().getRequestDispatcher("/WEB-INF/modifyUserAndSeeUser.jsp").forward(request, response);
			}
			
			
			break;
//		case "/adduserByAdmin":
//			AccountManagement am = new AccountManagement(request);
//			am.register();
		default:
			break;
		}
		
		
	}

}
