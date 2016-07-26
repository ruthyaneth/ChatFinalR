package client.logic;

import java.util.ArrayList;

public class ManagerUser {
	
	private ArrayList<User> listUser;
	
	public ManagerUser() {
		listUser = new ArrayList<User>();
	}
	public static User createUser(String nombre, String apellido, String nickName, String passWord, String email){
		return new User(nombre, apellido, nickName,passWord, email);
	}
	
	public void addUser(User user){
		listUser.add(user);
	}
	

	public ArrayList<User> getListUser() {
		return listUser;
	}

	public void setListUser(ArrayList<User> listUser) {
		this.listUser = listUser;
	}
	
}
