package sn.instaphoto.metier;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import sn.instaphoto.beans.User;
import sn.instaphoto.dao.UserDao;
import sn.instaphoto.utilities.Enums;
import sn.instaphoto.utilities.UtilityForm;



public class AccountManagement {

	private static final String CHAMP_NOM = "name";
	private static final String CHAMP_PRENOM = "surname";
	private static final String CHAMP_EMAIL = "email";
	private static final String CHAMP_PASSWORD = "password";
	private static final String CHAMP_PASSWORD_BIS = "passwordBis";
	private static final String CHAMP_FUNCTION = "function";
	private static final String CHAMP_CODE ="code";
	private static final String CHAMP_PROFIL ="";
	private static final String CHAMP_ID ="id";
	String name;
	String surname;
	String email;
	String password;
	String passwordBis;

	private HttpServletRequest request;
	private Map<String, String> erreurs;
	private User user;
	private String codeDeConfirmation;

	public String getCodeDeConfirmation() {
		return codeDeConfirmation;
	}

	public AccountManagement(HttpServletRequest request) {
		this.request = request;
		erreurs = new HashMap<String, String>();

	}

	public boolean register() {

		getCommonFields();
		String function ="utilisateur_simple";
		
		String profil ="";
		user = new User(name, surname, email, profil, password,function);		
		UtilityForm.validerChamps(request,erreurs,CHAMP_NOM, CHAMP_PRENOM, CHAMP_EMAIL, CHAMP_PASSWORD, CHAMP_PASSWORD_BIS);
		UtilityForm.emailIsValid(request, erreurs, CHAMP_EMAIL);
		UtilityForm.checkDuplicateEmail(request, erreurs, CHAMP_EMAIL);
		UtilityForm.securePassword(request, erreurs, CHAMP_PASSWORD);
		UtilityForm.validerPassword(request,erreurs,CHAMP_PASSWORD,CHAMP_PASSWORD_BIS);
		if(request.getServletPath().equals("/addUser") || request.getServletPath().equals("/modifyByAdmin")) {
			function =  UtilityForm.getParameter(request,CHAMP_FUNCTION);
			user.setFunction(function);
			
		}
		
		if(erreurs.isEmpty()) {
			if(request.getServletPath().equals("/addUser") || request.getServletPath().equals("/modifyByAdmin"))
				return true;
			//envoi de mail
			codeDeConfirmation = EmailConfirmation.genererCode();
			if(EmailConfirmation.envoiEmail(user.getEmail(), codeDeConfirmation)) {
				//le code a bien ete envoyé ou dumoins sil met un mail valide
				
				return true;
			}
			
		}
		
			//redirection page d'inscription avec les erreurs
		return false;

	}
	
	public boolean modifyAccount() {
		getCommonFields();
		String function = UtilityForm.getParameter(request,CHAMP_FUNCTION);
		int id =Integer.parseInt(UtilityForm.getParameter(request, CHAMP_ID));
		UtilityForm.validerChamps(request,erreurs,CHAMP_NOM, CHAMP_PRENOM, CHAMP_EMAIL, CHAMP_PASSWORD, CHAMP_PASSWORD_BIS);
		UtilityForm.emailIsValid(request, erreurs, CHAMP_EMAIL);
		//verifier si le mail saisi n'existe pas en base de données oubien on lui laisse pas changer ca
		
		UtilityForm.securePassword(request, erreurs, CHAMP_PASSWORD);
		UtilityForm.validerPassword(request,erreurs,CHAMP_PASSWORD,CHAMP_PASSWORD_BIS);
		//UtilityForm.checkRole(request, erreurs, CHAMP_FUNCTION);

		user = new User(id,name,surname,email,password,function);
		if(erreurs.isEmpty())
			return true;
		
		return false;
	}
	
	
	public static void deleteUser(int idUser) {
		
		//appel delete user dao
		
		UserDao.supprimer(idUser);
			
			//suppresion du repertoire
			
	}
	
	
	
	public void getCommonFields() {
		name = UtilityForm.getParameter(request,CHAMP_NOM);
		surname =  UtilityForm.getParameter(request,CHAMP_PRENOM);
		email =  UtilityForm.getParameter(request,CHAMP_EMAIL);
		password =  UtilityForm.getParameter(request,CHAMP_PASSWORD);
		passwordBis =  UtilityForm.getParameter(request,CHAMP_PASSWORD_BIS);
	}

	public  boolean checkValidate(HttpServletRequest request) {
		boolean check = false;
		String generatedCode = (String) request.getSession().getAttribute("generatedCode");
		System.out.println("code genere :"+generatedCode);
		String fieldCode = UtilityForm.getParameter(request, CHAMP_CODE);
		System.out.println("code saisi :"+fieldCode);

		UtilityForm.validerChamps(request, erreurs, CHAMP_CODE);
		if(!erreurs.isEmpty())
			return false;
		if(generatedCode.equalsIgnoreCase(fieldCode))
			check =true;
		
		return check;
	}
	
	public User getUser() {
		return user;
	}
	

	public Map<String, String> getErrors() {
		return erreurs;
	}

}
