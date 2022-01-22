package sn.instaphoto.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import sn.instaphoto.beans.Album;
import sn.instaphoto.beans.User;



public class AlbumDao {
	private static ArrayList<Album> albums=null;
	//liste des requete sur album 	
	private static final String DELELTE_ALBUM = ("DELETE FROM album WHERE ID_ALBUM=?");
private static final String UPDATE_ALBUM = ("update album  SET nom = ? , detail = ? ,visibilite = ? WHERE id_album = ?");
private static final String SELECT_REQUEST_ALBUM ="SELECT * FROM album Where id_utilisateur=";//list des albums d un utilisateur 
private static final String SELECT_ALBUM_PUBLIC ="select *from album where visibilite='public'"; //liste des albums public 
private static final String SELECT_ALBUM_PUBLIC_USER ="select *from album where visibilite='public' and id_utilisateur =";// liste des album public par utilisateur
private static final String SELECT_ALBUM_PRIVE_USER ="select *from album where visibilite='prive' and id_utilisateur  =";// liste des album prive par utilisateur
private static final String ADD_REQUEST_PREP_album = "INSERT INTO album (id_utilisateur, NOM,DETAIL,VISIBILITE) values (?,?,?,?)";// ajout d un album 
	
	
	
	// ajout d un album 
public static boolean addAlbum(int IdUtilisateur,Album album) {
	
	Connection connection = Connexion.getConnexion();
	
	try {
		
		
		PreparedStatement statement = UtilityDAO.createStatement(connection, ADD_REQUEST_PREP_album, IdUtilisateur ,album.getName(),album.getDetail(),album.getVisibility());
		int resultat = statement.executeUpdate();
		
		UtilityDAO.closeConnection(connection, statement);
		
		if(resultat != 0) {
			return true;
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	return false;
	
}
public static ArrayList<Album> albumsUtilisateur(int IdUtilisateur)
{
	PreparedStatement statement=null;
	ResultSet resultat=null;
	albums = new ArrayList<Album>();
		
		Connection connection = Connexion.getConnexion();
		try {
			
			 statement  = UtilityDAO.createStatement(connection,SELECT_REQUEST_ALBUM+IdUtilisateur);
			
			 resultat = statement.executeQuery();
			 while(resultat.next())
			{ 
				
			 Album  album = new Album(resultat.getString(3),resultat.getString(4), resultat.getString(5));
						albums.add(album);	
				}
				System.out.println(albums);
					return albums;
					
				
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			
			UtilityDAO.closeConnection(connection, statement, resultat);
		}
				
		return albums;	

		
	}
public static  ArrayList<Album> AlbumPublic(){
	
		PreparedStatement statement=null;
		ResultSet resultat=null;
			
			Connection connection = Connexion.getConnexion();
			try {
				
				 statement  = UtilityDAO.createStatement(connection,SELECT_ALBUM_PUBLIC);
				
				 resultat = statement.executeQuery();
				 while(resultat.next())
				{ 
					
					 Album  album = new Album(resultat.getString(3),resultat.getString(4), resultat.getString(5));
							albums.add(album);	
					}
					System.out.println(albums);
						return albums;
						
					
			} 
			catch (SQLException e)
			{
				e.printStackTrace();
			}
			finally
			{
				
				UtilityDAO.closeConnection(connection, statement, resultat);
			}
					
			return albums;

	}
public static ArrayList<Album> AlbumPublicUtilisateur(int  IdUtilisateur){
	
	PreparedStatement statement=null;
	   ResultSet resultat=null;

	Connection connection = Connexion.getConnexion();
	try {
		
	 statement  = UtilityDAO.createStatement(connection,SELECT_ALBUM_PUBLIC_USER+IdUtilisateur);
		
		 resultat = statement.executeQuery();
		 while(resultat.next())
		{ 
			
			 Album  album = new Album(resultat.getString(3),resultat.getString(4), resultat.getString(5));
					albums.add(album);	
			}
			System.out.println(albums);
				return albums;
				
			
	} 
	catch (SQLException e)
	{
		e.printStackTrace();
	}
	finally
	{
		
		UtilityDAO.closeConnection(connection, statement, resultat);
	}
			
	return albums;	

}
public static ArrayList<Album> AlbumPriveUtilisateur(int  IdUtilisateur)
{
	PreparedStatement statement=null;
   ResultSet resultat=null;

Connection connection = Connexion.getConnexion();
try {
	
 statement  = UtilityDAO.createStatement(connection,SELECT_ALBUM_PRIVE_USER+IdUtilisateur);
	
	 resultat = statement.executeQuery();
	 while(resultat.next())
	{ 
		
		 Album  album = new Album(resultat.getString(3),resultat.getString(4), resultat.getString(5));
				albums.add(album);	
		}
		System.out.println(albums);
			return albums;
			
		
} 
catch (SQLException e)
{
	e.printStackTrace();
}
finally
{
	
	UtilityDAO.closeConnection(connection, statement, resultat);
}
		
return albums;	
}
public static boolean modifierAlbum(Album album) 
{
	Connection connection = Connexion.getConnexion();
    try {
	   PreparedStatement statement = UtilityDAO.createStatement(connection,UPDATE_ALBUM,
				album.getName(), album.getDetail(), album.getVisibility(),album.getIdAlbum());
		int resultat = statement.executeUpdate();

		UtilityDAO.closeConnection(connection, statement);

		if (resultat != 0) 
		{
			return true;
		}
		
	} 
    
   
   catch (SQLException e) 
   {e.printStackTrace();
		}
    

	return false;

}



public static boolean supprimerAlbum(int IdAlbum) 
{
	Connection connection = Connexion.getConnexion();
   User user = null;
	
   try {
	   PreparedStatement statement = UtilityDAO.createStatement(connection, DELELTE_ALBUM,IdAlbum);
		int resultat = statement.executeUpdate();

		UtilityDAO.closeConnection(connection, statement);

		if (resultat != 0) 
		{
			return true;
		}
		
	} 
   catch (SQLException e) 
   {
		// TODO Auto-generated catch block
		
		e.printStackTrace();
		
	}

	return false;

}
	
	
}



