package server;
/**
 * UNIVERSIDAD PEDAGOGICA Y TECNOLOGICA DE COLOMBIA
 * FACULTAD DE INGENIERIA.
 * ESCUELA DE INGENIERIA DE SISTEMAS Y COMPUTACION.
 * PRESENTADO A: Ing Helver Valero.
 * PROGRAMACION III
 * Atributtos que necesita el servidor del usuario
 * @author Jenny Quesada , Ruth Rojas
 */
public class UserServer {
	
	//------Atributtes------
	
	private String nameUser;
	private String ip;
	
	//------Builder-------
	
	public UserServer(String nameUser , String ip) {
	
		this.nameUser = nameUser;
		this.ip = ip;
	}

	
	//------Methods------
	
	//-----Gets&&Sets------
	
	public String getNameUser() {
		return nameUser;
	}

	public void setNameUser(String nameUser) {
		this.nameUser = nameUser;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}
	
	
	
	

}
