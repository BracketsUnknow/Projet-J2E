package sn.instaphoto.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import sn.instaphoto.beans.Image;
import sn.instaphoto.beans.User;

public class ImageDao {
	private static final ArrayList<Image> images = new ArrayList();
	private static final String DELELTE_IMAGE = ("DELETE FROM album WHERE id_image=?");
	private static final String UPDATE_IMAGE = ("update image  SET titre = ? , description = ? ,hauteur = ?,date_de_creation= ? , date_de_mise_a_jour = ?,largeur = ? ,accessibilite = ?, lien_fichier_image=? WHERE id_image = ?");
	private static final String SELECT_REQUEST_IMAGE_ALBUM = "SELECT *FROM image Where idAlbum=";// les image par albums
	private static final String ADD_REQUEST_PREP_IMAGE = "INSERT INTO image (TITRE, DESCRIPTION , HAUTEUR,DATE_DE_CREATION,DATE_DE_MISE_A_JOUR,LARGEUR,ACCESSIBILITE,LIEN_FICHIER_IMAGE) values (?,?,?,?,?,?,?,?)";
	private static final String SELECT_IMAGE_PUBLIC_USER = "select *from IMAGE where 	ACCESSIBILITE=public and id_album  =";// liste
																																// des
																																// image
																																// public
																																// par
																																// album
	private static final String SELECT_IMAGE_PRIVE_USER = "select *from album where 	ACCESSIBILITE=prive and id_album  =";// liste
																																// des
																																// images
																																// privees
																																// par
																																// album

	public static boolean addImage(Image image) {

		Connection connection = Connexion.getConnexion();

		try {

			PreparedStatement statement = UtilityDAO.createStatement(connection, ADD_REQUEST_PREP_IMAGE,
					image.getTitle(), image.getDescription(), image.getHeight(), image.getCreationDate(),
					image.getUpdateDate(), image.getWidth(), image.getAccessibility(), image.getImgePath());
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

//	public static ArrayList<Image> imageAlbum(int IdAlbum) {
//		PreparedStatement statement = null;
//		ResultSet resultat = null;
//		Connection connection = Connexion.getConnexion();
//		try {
//
//			statement = UtilityDAO.createStatement(connection, SELECT_REQUEST_IMAGE_ALBUM + IdAlbum);
//
//			resultat = statement.executeQuery();
//			while (resultat.next()) {
//				Image image = new Image(resultat.getString(3), resultat.getString(4), resultat.getInt(5),
//						resultat.getDate(6), resultat.getDate(7), resultat.getInt(8), resultat.getString(9),
//						resultat.getString(10));
//				images.add(image);
//
//			}
//			System.out.println(images);
//			return images;
//
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//
//			UtilityDAO.closeConnection(connection, statement, resultat);
//		}
//
//		return images;
//
//	}

//	public static ArrayList<Image> imagePublicUtilisateur(int IdUtilisateur) {
//		PreparedStatement statement = null;
//		ResultSet resultat = null;
//		Connection connection = Connexion.getConnexion();
//		try {
//
//			statement = UtilityDAO.createStatement(connection, SELECT_IMAGE_PUBLIC_USER + IdUtilisateur);
//
//			resultat = statement.executeQuery();
//			while (resultat.next()) {
//				Image image = new Image(resultat.getString(3), resultat.getString(4), resultat.getInt(5),
//						resultat.getDate(6), resultat.getDate(7), resultat.getInt(8), resultat.getString(9),
//						resultat.getString(10));
//				images.add(image);
//
//			}
//			System.out.println(images);
//			return images;
//
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//
//			UtilityDAO.closeConnection(connection, statement, resultat);
//		}
//
//		return images;
//
//	}

//	public static ArrayList<Image> imagePriveUtilisateur(int IdUtilisateur) {
//		PreparedStatement statement = null;
//		ResultSet resultat = null;
//		Connection connection = Connexion.getConnexion();
//		try {
//
//			statement = UtilityDAO.createStatement(connection, SELECT_IMAGE_PRIVE_USER + IdUtilisateur);
//
//			resultat = statement.executeQuery();
//			while (resultat.next()) {
//				Image image = new Image(resultat.getString(3), resultat.getString(4), resultat.getInt(5),
//						resultat.getDate(6), resultat.getDate(7), resultat.getInt(8), resultat.getString(9),
//						resultat.getString(10));
//				images.add(image);
//
//			}
//			System.out.println(images);
//			return images;
//
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//
//			UtilityDAO.closeConnection(connection, statement, resultat);
//		}
//
//		return images;
//
//	}

	public static boolean modifierAlbum(Image image) {
		Connection connection = Connexion.getConnexion();
		try {
			PreparedStatement statement = UtilityDAO.createStatement(connection, UPDATE_IMAGE, image.getTitle(),
					image.getDescription(), image.getHeight(), image.getCreationDate(), image.getUpdateDate(),
					image.getWidth(), image.getAccessibility(), image.getImgePath(), image.getIdImage());
			int resultat = statement.executeUpdate();

			UtilityDAO.closeConnection(connection, statement);

			if (resultat != 0) {
				return true;
			}

		}

		catch (SQLException e) {
			e.printStackTrace();
		}

		return false;

	}

	public static boolean supprimerAlbum(int IdImage) {
		Connection connection = Connexion.getConnexion();
		User user = null;

		try {
			PreparedStatement statement = UtilityDAO.createStatement(connection, DELELTE_IMAGE, IdImage);
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

}
