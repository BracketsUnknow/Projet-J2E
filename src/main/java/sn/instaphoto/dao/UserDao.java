package sn.instaphoto.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import sn.instaphoto.beans.User;

public class UserDao {

	private static ArrayList<User> users = null;
	private static final String LIST_USER = ("SELECT * FROM utilisateur");
	private static final String REQUET_LOGIN = "SELECT * FROM utilisateur where email = ? and password = ?";
	private static final String SELECT_REQUEST = ("SELECT * FROM utilisateur WHERE email = ");
	private static final String DELELTE_USER = ("DELETE FROM utilisateur WHERE ID_UTILISATEUR=?");
	private static final String ADD_REQUEST_PREP = "INSERT INTO utilisateur (NOM,PRENOM,EMAIL,PROFIL,PASSWORD,FONCTION) values (?,?,?,?,?,?)";
	private static final String UPDATA_USER = ("update utilisateur  SET nom = ?, prenom = ? ,email =? , profil =? ,password =?,fonction=? WHERE id_utilisateur = ?");

	public static boolean addUser(User user) {

		Connection connection = Connexion.getConnexion();

		try {

			PreparedStatement statement = UtilityDAO.createStatement(connection, ADD_REQUEST_PREP, user.getName(),
					user.getSurname(), user.getEmail(), user.getProfil(), user.getPassword(), user.getFunction());
			int resultat = statement.executeUpdate();

			UtilityDAO.closeConnection(connection, statement);

			if (resultat != 0) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;

	}

	public static boolean checkEmail(String email) {

		Connection connection = Connexion.getConnexion();
		try {

			Statement statement = connection.createStatement();
			ResultSet resultat = statement.executeQuery(SELECT_REQUEST +" '"+email+" ' ");
			while (resultat.next()) {
				return true;

			}
			UtilityDAO.closeConnection(connection, statement, resultat);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;

	}

	// recuperer le login et le mot passe comparer avec ceux dans la base de donnee
	// pour verifier si l utilisateur a bien un compte

	public static User checkConnexion(String email, String mot_de_passe)// methode pour la connection

	{

		Connection connection = Connexion.getConnexion();
		PreparedStatement statement = null;
		ResultSet resultat = null;
		User userTest = null;
		try {
			statement = UtilityDAO.createStatement(connection, REQUET_LOGIN, email, mot_de_passe);
			resultat = statement.executeQuery();
			if (resultat.next()) {
				userTest = new User(resultat.getInt(1),resultat.getString(2), resultat.getString(3), resultat.getString(4),
						resultat.getString(5), resultat.getString(6), resultat.getString(7));
				return userTest;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			UtilityDAO.closeConnection(connection, statement, resultat);
		}

		return userTest;

	}

	public static ArrayList<User> lister() {
		PreparedStatement statement = null;
		ResultSet resultat = null;
		Connection connection = Connexion.getConnexion();
		users = new ArrayList();
		try {
			statement = UtilityDAO.createStatement(connection, LIST_USER);
			resultat = statement.executeQuery();
			while (resultat.next()) {

				User user = new User(resultat.getInt(1), resultat.getString(2), resultat.getString(3),
						resultat.getString(4), resultat.getString(5), resultat.getString(6), resultat.getString(7));
				users.add(user);
			}
			// System.out.println(users);
			return users;

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			UtilityDAO.closeConnection(connection, statement, resultat);
		}

		return users;

	}

	public static boolean supprimer(int IdUser) {
		Connection connection = Connexion.getConnexion();
		try {
			PreparedStatement statement = UtilityDAO.createStatement(connection, DELELTE_USER, IdUser);
			int resultat = statement.executeUpdate();

			UtilityDAO.closeConnection(connection, statement);

			if (resultat != 0) {
				return true;
			}

		} catch (SQLException e) {

			e.printStackTrace();

		}

		return false;

	}

	public static boolean modifier(User user) 
	{
		Connection connection = Connexion.getConnexion();
        try {
    	   PreparedStatement statement = UtilityDAO.createStatement(connection,UPDATA_USER,user.getName(),
					user.getSurname(), user.getEmail(), user.getProfil(), user.getPassword(), user.getFunction(),user.getId());
			int resultat = statement.executeUpdate();

			UtilityDAO.closeConnection(connection, statement);

			if (resultat != 0) 
			{
				return true;
			}
			
		} 
        catch (com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException e) 
        {System.out.println("l email deja utilisé");//message persso
        System.out.println(e.getMessage());
        	//e.printStackTrace();
        return false;
 			}
       
       catch (SQLException e) 
       {e.printStackTrace();
       return false;
			}
        

		return false;

	}

}
