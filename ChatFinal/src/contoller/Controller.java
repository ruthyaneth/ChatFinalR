package contoller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import client.view.WindowClient;
import client.view.WindowLogin;
import client.view.WindowRegister;
import constant.ConstantController;

/**
 * UNIVERSIDAD PEDAGOGICA Y TECNOLOGICA DE COLOMBIA
 * FACULTAD DE INGENIERIA
 * ESCUELA DE INGENIERIA DE SISTEMAS Y COMPUTACION 
 * PRESENTADO A : ING HELVER VALERO.
 * PROGRAMACION III-
 * Clase del controlador de eeventos de los botones
 * @author Jenny Quesada , Ruth Rojas
 * 																																																						
 */
public class Controller implements ActionListener{

	//------Atributtes------
	private WindowClient windowClient;
	private WindowLogin windowLogin;
	private WindowRegister windowRegister;
	
	//-----Builder------
	public Controller() {
	}
	
	//-----Methods-----
	
	public void showWindowClient(){
		
		this.windowClient = new WindowClient();
		this.windowClient.setVisible(true);
	}
	
	public void showWindowLogin(){
		this.windowLogin = new WindowLogin(this);
		this.windowLogin.setVisible(true);

		
	}
	
	public void closeWindowRegister(){
		
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
		
		if(event.getActionCommand().equals(ConstantController.DEFAULT_WINDOW_CLIENT)){
			showWindowClient();
		}else if(event.getActionCommand().equals(ConstantController.DEFAULT_WINDOW_BACK)){
			showWindowLogin();
		
		}
		
	}
	

}
