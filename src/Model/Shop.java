package Model;

public class Shop {
	private String name;
	private String contact;
	private String postalcode;
	private String unitno;
	private String category;
	private String description;
	private byte[] image;
	
	public Shop() {

	}

	public Shop(String name, String contact, String postalcode, String unitno, String category, String description, byte[] image) {
		this.name = name;
		this.contact = contact;
		this.postalcode = postalcode;
		this.unitno = unitno;
		this.category = category;
		this.description = description;
		this.image = image;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getPostalcode() {
		return postalcode;
	}

	public void setPostalcode(String postalcode) {
		this.postalcode = postalcode;
	}

	public String getUnitno() {
		return unitno;
	}

	public void setUnitno(String unitno) {
		this.unitno = unitno;
	}
	
	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}
}
