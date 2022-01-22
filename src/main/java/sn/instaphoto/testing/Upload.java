package sn.instaphoto.testing;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.tomcat.util.http.fileupload.IOUtils;

@WebServlet("/upload")
@MultipartConfig
public class Upload extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VUE_UPLOAD = "/WEB-INF/upload.jsp";
	private static final String compatibleExtensions[] = { "jpeg", "jpg", "png", "gif", "svg", "img" };

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.getServletContext().getRequestDispatcher(VUE_UPLOAD).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String titre = request.getParameter("titre");
		String theme = request.getParameter("theme");

		Part part = request.getPart("fichier");

		String nomFichier = getNomFichier(part);
		System.out.println("nom du fichier :" + nomFichier);

		if (nomFichier == null && nomFichier.isEmpty()) {
			// champ classique
			// String nomChamp = part.getName();
		} else {
			if (checkCompatibleExtension(nomFichier)) {
				String path = System.getProperty("user.dir");
				path = path.replace("eclipse", "");
				System.out.println(path);
				path += "workspace\\AlbumPhoto\\";
				ecrireFichier(part, path, "ibrahima", nomFichier);
			}

		}
	}

	private boolean checkCompatibleExtension(String fileName) {
		String extension = "";

		int i = fileName.lastIndexOf('.');
		if (i > 0) {
			extension = fileName.substring(i + 1);
		}

		for (String ext : compatibleExtensions) {
			if (ext.equalsIgnoreCase(extension))
				return true;
		}

		return false;
	}

	private void ecrireFichier(Part part, String chemin, String userDirectory, String nomFichier) throws IOException {

		File file = new File(chemin +"\\data\\"+ userDirectory	+"\\"+ nomFichier);
		try (OutputStream outputStream = new FileOutputStream(file)) {
			IOUtils.copy(part.getInputStream(), outputStream);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static String getNomFichier(Part part) {
		/* Boucle sur chacun des paramètres de l'en-tête "content-disposition". */
		for (String contentDisposition : part.getHeader("content-disposition").split(";")) {
			/* Recherche de l'éventuelle présence du paramètre "filename". */
			if (contentDisposition.trim().startsWith("filename")) {
				/*
				 * Si "filename" est présent, alors renvoi de sa valeur, c'est-à-dire du nom de
				 * fichier sans guillemets.
				 */
				return contentDisposition.substring(contentDisposition.indexOf('=') + 1).trim().replace("\"", "");
			}
		}
		/* Et pour terminer, si rien n'a été trouvé... */
		return null;
	}
}
