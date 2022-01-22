package sn.instaphoto.utilities;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Workspace {

	public static String base = System.getProperty("user.dir").replace("eclipse", "");
	public static String WORKSPACE_BASE = "C:\\JavaEE\\workspace\\InstaPhoto\\src\\main\\webapp\\data\\";

	public static void createWorkspace(String email) {
		try {
			Files.createDirectories(Paths.get(WORKSPACE_BASE+email));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void deleteWorkspace() {
		
	}
	
	public static void createAlbum(String email ,String album) {
		try {
			Files.createDirectories(Paths.get(WORKSPACE_BASE+email+"\\"+album));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	
	public static void deleteAlbum(String email , String album) {
		try {
			Files.delete(Paths.get(WORKSPACE_BASE+email+"\\"+album));
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	
}
