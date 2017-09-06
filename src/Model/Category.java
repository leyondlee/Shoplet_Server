package Model;

public class Category {
	private String name;
	private byte[] image;
	
	public Category() {

	}

	public Category(String name, byte[] image) {
		this.name = name;
		this.image = image;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}
}
