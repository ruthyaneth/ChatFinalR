package client.logic;


public class User {

	private int id;
	private String name;
	private String lastName;
	private String nickName;
	private String password;
	private String email;
	
	
	public User(String name, String lastName, String nickName, String password, String email) {
		
		this.name = name;
		this.id = Util.asignarId("src/data/Archivo Id Clientes.txt");
		this.lastName = lastName;
		this.nickName = nickName;
		this.password = password;
		this.email = email;
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
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
