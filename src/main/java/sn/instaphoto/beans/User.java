package sn.instaphoto.beans;

public class User {
	
	int id;
	String name;
	String surname;
	String email;
	String profil;
	String password;
	String function;
	
	public User() {
		
	}
	
	public User(int id, String nom, String prenom, String email, String profil, String password,
			String function) {
		this(nom, prenom, email, profil, password, function);
		this.id = id;
	}
	public User(String nom, String prenom, String email, String password, String function) {
		
		this.name = nom;
		this.surname = prenom;
		this.email = email;
		this.function =function;
		this.password = password;
		
	}
public User(int id ,String nom, String prenom, String email, String password, String function) {
		
	this.id=id;
		this.name = nom;
		this.surname = prenom;
		this.email = email;
		this.function =function;
		this.password = password;
		
	}
	public User(String nom, String prenom, String email, String profil, String password, String function) {
	
		this.name = nom;
		this.surname = prenom;
		this.email = email;
		this.function =function;
		this.profil =profil;
		this.password = password;
		
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getProfil() {
		return profil;
	}
	public void setProfil(String profil) {
		this.profil = profil;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFunction() {
		return function;
	}
	public void setFunction(String function) {
		this.function = function;
	}
	
	
}