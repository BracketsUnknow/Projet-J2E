package sn.instaphoto.utilities;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import org.apache.tomcat.util.http.fileupload.IOUtils;

import sn.instaphoto.dao.UserDao;

public class UtilityForm {

	private static final String compatibleExtensions[] = { "jpeg", "jpg", "png", "gif", "svg", "img" };
	public static String getParameter(HttpServletRequest request, String parameter) {
		String value = request.getParameter(parameter);
		if (value == null || value.trim().isEmpty()) {
			return null;
		}
		return value.trim();
	}

	public static void validerChamps(HttpServletRequest request, Map<String, String> erreurs, String... fields) {
		for (String field : fields) {
			if (getParameter(request, field) == null)
				erreurs.put(field, "Vous devez remplir ce champ");
		}

	}

	public static void validerPassword(HttpServletRequest request, Map<String, String> erreurs, String CHAMP_PASSWORD,
			String CHAMP_PASSWORD_BIS) {
		String password = getParameter(request, CHAMP_PASSWORD);
		String passwordBis = getParameter(request, CHAMP_PASSWORD_BIS);
		if (password != null && !password.equals(passwordBis)) {
			erreurs.put(CHAMP_PASSWORD, "Les deux mots de passe ne sont pas identiques !");
			erreurs.put(CHAMP_PASSWORD_BIS, "Les deux mots de passe ne sont pas identiques !");
		}
	}

	public static void securePassword(HttpServletRequest request, Map<String, String> erreurs,String CHAMP_PASSWORD) {
		String password = getParameter(request,CHAMP_PASSWORD);
		String securePassword = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,50}$";
		String error = " Le mot de passe doit contenir entre 8 et 50 carteres \n il doit contenir au moins une lettre ,un chiffre et un caractere spécial";
		Pattern pattern = Pattern.compile(securePassword);
		if (password == null || pattern.matcher(password).matches() == false)
			erreurs.put(CHAMP_PASSWORD, error);

	}
	
	public static void emailIsValid(HttpServletRequest request, Map<String, String> erreurs,String CHAMP_EMAIL) {
		String emailPattern = "^[a-zA-Z0-9_+&*-]+(?:\\." + "[a-zA-Z0-9_+&*-]+)*@" + "(?:[a-zA-Z0-9-]+\\.)+[a-z"
				+ "A-Z]{2,7}$";
		Pattern patttern = Pattern.compile(emailPattern);
		String email = getParameter(request,CHAMP_EMAIL);
		if (email == null || patttern.matcher(email).matches() == false)
			erreurs.put(CHAMP_EMAIL, "cet email est invalide !");
			
	}
	
	public static void checkDuplicateEmail(HttpServletRequest request,Map<String, String> errors,String CHAMP_EMAIL) {
		String email =getParameter(request, CHAMP_EMAIL); 
		if(UserDao.checkEmail(email))
				 errors.put(CHAMP_EMAIL, "Cet email existe déja");
			 
	}
	
	public static boolean checkCompatibleExtension(String fileName) {
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

	public static void ecrireFichier(Part part, String chemin, String userDirectory, String nomFichier) throws IOException {

		File file = new File(chemin +"\\data\\"+ userDirectory	+"\\"+ nomFichier);
		try (OutputStream outputStream = new FileOutputStream(file)) {
			IOUtils.copy(part.getInputStream(), outputStream);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	public static String getNomFichier(Part part) {
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
	
	
//	public static void checkRole(HttpServletRequest request,Map<String, String> errors,String CHAMP_FUNCTION) {
//		String function = getParameter(request, CHAMP_FUNCTION);
//		if(!("administrateur".equals(function) || "utilisateur_simple".equals(function)))
//			errors.put(CHAMP_FUNCTION, "Erreur vous avez modifié le fichier HTML !");
//			
//	}

	
}
