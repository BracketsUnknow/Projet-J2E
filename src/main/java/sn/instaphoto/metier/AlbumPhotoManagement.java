package sn.instaphoto.metier;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import sn.instaphoto.beans.Album;
import sn.instaphoto.beans.Image;
import sn.instaphoto.beans.User;
import sn.instaphoto.utilities.UtilityForm;
import sn.instaphoto.utilities.Workspace;

public class AlbumPhotoManagement {
	
	private static String FIELD_NAME_ALBUM="nameAlbum";
	private static String FIELD_NAME_ALBUM_THEME="theme";
	private static String FIELD_ALBUM_VISIBILITY="visibility";
	private HttpServletRequest request;
	private Map<String, String> errors;
	private Album album ;
	public AlbumPhotoManagement(HttpServletRequest request) {
		this.request = request;
		errors = new HashMap<String, String>();
	}
	
	
	public Map<String, String> getErrors() {
		return errors;
	}
	
	public boolean returnValue() {
		if(errors.isEmpty())
			return true;

		return false;
		
	}
	public Album getAlbum() {
		return this.album;
		
	}
	public boolean createAlbum(HttpServletRequest request) {
		
		String name = request.getParameter(FIELD_NAME_ALBUM);
		String theme = request.getParameter(FIELD_NAME_ALBUM_THEME);
		String visibility = request.getParameter(FIELD_ALBUM_VISIBILITY);
		UtilityForm.validerChamps(request, errors, FIELD_NAME_ALBUM);
		UtilityForm.validerChamps(request, errors, FIELD_NAME_ALBUM_THEME);
		UtilityForm.validerChamps(request, errors, FIELD_ALBUM_VISIBILITY);
		album  = new Album(name, theme, "", visibility);
		if(errors.isEmpty())
			return true;
		return false;

		
	}
	public boolean deleteAlbum(int idAlbum) {
		
		//suprimer de la base de données
		
		//supprimer du repertoire
		
		return returnValue();
	}
	public void modifyAlbum(Album album) {
		
		//renomer de la base de données 
		
		//renommer du folder
		
	}
	public void listAlbum() {
		
	}
	public void uploadImageInAlbum(Image image, Album album) {
		
	}
	public void modifyImage(Image image) {
		
	}
	public boolean deleteImage(int idImage) {
		//suppression de la base
		
		//suppression du folder
		
		return returnValue();
	}
	public void allPublicImage() {
		
	}
	public void privateImageShareWithMe(int idUser) {
		
	}
	public void myPrivateImage(int idUser) {
		
	}
	
	
	
}
