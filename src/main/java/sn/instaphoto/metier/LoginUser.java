package sn.instaphoto.metier;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import sn.instaphoto.beans.User;
import sn.instaphoto.dao.UserDao;
import sn.instaphoto.utilities.PasswordEncode;
import sn.instaphoto.utilities.UtilityForm;

public class LoginUser {

	private static final String CHAMP_EMAIL = "email";
	private static final String CHAMP_PASSWORD = "password";
	HttpServletRequest request;
	private Map<String, String> errors;

	private User user;

	public Map<String, String> getErrors() {
		return errors;
	}

	public User getUser() {
		return this.user;
	}

	public LoginUser(HttpServletRequest request) {
		this.request = request;
		errors = new HashMap<String, String>();
	}

	public boolean connexion() {

		String email = UtilityForm.getParameter(request, CHAMP_EMAIL);
		String password = UtilityForm.getParameter(request, CHAMP_PASSWORD);
		

		UtilityForm.emailIsValid(request, errors, CHAMP_EMAIL);
		UtilityForm.validerChamps(request, errors,CHAMP_PASSWORD);
		user =null;
		if (errors.isEmpty()) {
			user = UserDao.checkConnexion(email, PasswordEncode.encode(password));
			if (user != null) {
				return true;
			}
		}
		errors.put(email, "aucun compte n'est associé a cet utilisatuer");
		return false;
	}

}
