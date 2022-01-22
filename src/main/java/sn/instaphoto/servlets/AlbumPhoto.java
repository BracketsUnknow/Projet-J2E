package sn.instaphoto.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sn.instaphoto.beans.Album;
import sn.instaphoto.beans.User;
import sn.instaphoto.dao.AlbumDao;
import sn.instaphoto.metier.AlbumPhotoManagement;
import sn.instaphoto.utilities.Workspace;

/**
 * Servlet implementation class AlbumPhoto
 */
@WebServlet({"/createAlbum","/photos","/posts"})
public class AlbumPhoto extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VUE_ALBUM = "/WEB-INF/albums.jsp";
	private static final String VUE_PHOTOS= "/WEB-INF/photos.jsp";
	private static final String VUE_POSTS= "/WEB-INF/posts.jsp";

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		switch (request.getServletPath()) {
		case "/createAlbum":
			int id = (int)((User) request.getSession().getAttribute("user")).getId();
			ArrayList<Album> albums	= AlbumDao.albumsUtilisateur(id);
			request.setAttribute("albums", albums);
			this.getServletContext().getRequestDispatcher(VUE_ALBUM).forward(request, response);
	 		break;

		case "/deleteAlbum":
			int idAlbum = Integer.parseInt(request.getParameter("idAlbum"));
			request.setAttribute("idAlbum",idAlbum);
			doPost(request, response);
			break;
		case "/posts":
			this.getServletContext().getRequestDispatcher(VUE_POSTS).forward(request, response);
			break;
		case "/photos":
			this.getServletContext().getRequestDispatcher(VUE_PHOTOS).forward(request, response);
			break;
		default:
			break;
		}
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		switch (request.getServletPath()) {
		case "/createAlbum":
			AlbumPhotoManagement manage = new AlbumPhotoManagement(request);
			if(manage.createAlbum(request)) {
				
				
				User user = (User) request.getSession().getAttribute("user");
				Workspace.createAlbum(user.getEmail(),manage.getAlbum().getName());
				
				AlbumDao.addAlbum(user.getId(),manage.getAlbum());
				
				request.setAttribute("succes_create_album",true);
				System.out.println("ok");
//				System.out.println(manage.getAlbum().getName());
//				System.out.println(manage.getAlbum().getVisibility());
				
				
			}
			else {
				manage.getErrors().entrySet().forEach(entry -> {
				    System.out.println(entry.getKey() + " " + entry.getValue());
				});
				
				
				System.out.println("pas ok");
				
			}
			
			int id = (int)((User) request.getSession().getAttribute("user")).getId();
			ArrayList<Album> albums	= AlbumDao.albumsUtilisateur(id);
			request.setAttribute("albums", albums);
			this.getServletContext().getRequestDispatcher(VUE_ALBUM).forward(request, response);
	 		break;

		case "/deleteAlbum":
			try {
				int idAlbum = (int) request.getAttribute("idAlbum");
				AlbumDao.supprimerAlbum(idAlbum);
				User user= ((User) request.getSession().getAttribute("user"));
				int idUser =user.getId();
				
				//Workspace.deleteAlbum(user.getEmail(),);
				ArrayList<Album> albumss	= AlbumDao.albumsUtilisateur(idUser);
				request.setAttribute("albums", albumss);
				this.getServletContext().getRequestDispatcher(VUE_ALBUM).forward(request, response);
			
				
			}
			catch (Exception e) {
				// TODO: handle exception
			}
			
			
			break;
		default:
			break;
		}
		
	}

}
