package ouc.cs.course.java.musicserver.model;

public class User {

	private int id;
	private String name;
	private String passMd5value;

	public User() {
		name="";
		passMd5value="";
	}

	public User(String name, String passMd5value) {
		this.setPassMd5value(passMd5value);
		this.name = name;
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

	public String getPassMd5value() {
		return passMd5value;
	}

	public void setPassMd5value(String passMd5value) {
		this.passMd5value = passMd5value;
	}
}