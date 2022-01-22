package sn.instaphoto.beans;

public class Album {

	private int idAlbum;
	private String name;
	private String detail;
	private String visibility;
	private String theme;

	public Album() {
	}

	public Album(int idAlbum, String name, String detail, String visibility) {

		this(name, detail, visibility);

		this.idAlbum = idAlbum;
	}

	public Album(String name, String detail, String visibility) {

		this.name = name;
		this.detail = detail;
		this.visibility = visibility;

	}
	public Album(String name, String theme, String detail, String visibility) {
		this.theme =theme;
		this.name = name;
		this.detail = detail;
		this.visibility = visibility;

	}

	public int getIdAlbum() {
		return idAlbum;
	}

	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getVisibility() {
		return visibility;
	}

	public void setVisibility(String visibility) {
		this.visibility = visibility;
	}

}
