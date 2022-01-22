package sn.instaphoto.metier;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import sn.instaphoto.utilities.UtilityForm;

public class UploadManagement {

	public Map<String, String> getErrors() {
		return errors;
	}

	private HttpServletRequest request;
	private Map<String, String> errors;
	private static final String FIELD_NAME_IMAGE = "name";
	private static final String FIELD_THEME = "theme";
	private static final String FIELD_FILE = "image";

	public UploadManagement(HttpServletRequest request) {
		this.request = request;
		errors = new HashMap<String, String>();
	}

	public boolean upload() {

		String name = UtilityForm.getParameter(request, FIELD_NAME_IMAGE);
		String theme = UtilityForm.getParameter(request, FIELD_THEME);

		UtilityForm.validerChamps(request, errors, FIELD_NAME_IMAGE);
		UtilityForm.validerChamps(request, errors, FIELD_THEME);

		Part part = null;
		try {
			part = request.getPart("image");
			String file = UtilityForm.getNomFichier(part);

			if (file == null && file.isEmpty()) 
				errors.put(FIELD_FILE,"Extension de fichier non pris en chargee !"); 
			else {
				if (UtilityForm.checkCompatibleExtension(file)) {
					String path = System.getProperty("user.dir");
					path = path.replace("eclipse", "");
					System.out.println(path);
					path += "workspace\\AlbumPhoto\\";
					UtilityForm.ecrireFichier(part, path, "ibrahima", file);
					}
				}
			
		}
		catch (IOException e) {
			errors.put(FIELD_FILE,"Extension de fichier non pris en charge !");
			e.printStackTrace();
			}
		catch (Exception e) {
			errors.put(FIELD_FILE,"Erreur lors du chargement du fichier ");
			e.printStackTrace();
		}

		
		if(errors.isEmpty())
			return true;

		return false;
	}

}
