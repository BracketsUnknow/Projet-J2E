package sn.instaphoto.beans;

import java.util.Date;

public class Image {
	
	private int idImage;
	private String title;
	private String description;
	private Date creationDate;
	private Date updateDate;
	private int height;
	private int width;
	private String accessibility;
	private String imgePath;
	
	
	public Image() {
		
	}

	public Image(int idImage, String title, String description, Date creationDate, Date updateDate, int height,
			int width, String accessibility, String imgePath) {
		this(title, description, creationDate, updateDate, height,
				width, accessibility, imgePath);
		this.idImage = idImage;
	}
	
	public Image(String title, String description, Date creationDate, Date updateDate, int height, int width,
			String accessibility, String imgePath) {
		super();
		this.title = title;
		this.description = description;
		this.creationDate = creationDate;
		this.updateDate = updateDate;
		this.height = height;
		this.width = width;
		this.accessibility = accessibility;
		this.imgePath = imgePath;
	}
	public int getIdImage() {
		return idImage;
	}
	public void setIdImage(int idImage) {
		this.idImage = idImage;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public String getAccessibility() {
		return accessibility;
	}
	public void setAccessibility(String accessibility) {
		this.accessibility = accessibility;
	}
	public String getImgePath() {
		return imgePath;
	}
	public void setImgePath(String imgePath) {
		this.imgePath = imgePath;
	}
	
	
}
