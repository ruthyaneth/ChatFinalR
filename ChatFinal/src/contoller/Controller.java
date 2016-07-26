package contoller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import client.logic.ManagerUser;
import client.logic.User;
import client.view.WindowClient;
import client.view.WindowLogin;
import config.HandlerLanguage;
import constant.ConstantController;
import persistence.XmlUser;

/**
 * UNIVERSIDAD PEDAGOGICA Y TECNOLOGICA DE COLOMBIA FACULTAD DE INGENIERIA
 * ESCUELA DE INGENIERIA DE SISTEMAS Y COMPUTACION PRESENTADO A : ING HELVER
 * VALERO. PROGRAMACION III- Clase del controlador de eventos de los botones
 * 
 * @author Jenny Quesada , Ruth Rojas
 * 
 */
public class Controller implements ActionListener {

	public static final String RUTA_CLIENTE = "src/data/arrayClientes.xml";
	private HandlerLanguage handlerLanguage;
	// ------Atributtes------
	private WindowClient windowClient;
	private WindowLogin windowLogin;
	// private WindowRegister windowRegister;
	private ManagerUser managerUser;

	// -----Builder------
	public Controller() {
		loadConfiguration();
		// windowRegister = new WindowRegister(this);
		this.windowLogin = new WindowLogin(this);
		managerUser = new ManagerUser();
	}

	// -----Methods-----

	public void showWindowClient() {

		this.windowClient = new WindowClient(this);
		this.windowClient.setVisible(true);
	}

	public void showWindowLogin() {
		this.windowLogin.setVisible(true);

	}

	public void closeWindowRegister() {

	}

	public void loadConfiguration() {
		if (handlerLanguage == null) {
			handlerLanguage = new HandlerLanguage("language/config.ini");
			try {
				handlerLanguage.loadLanguage();
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println(HandlerLanguage.language);
		}
	}

	public void changeToEnglish() {
		HandlerLanguage handlerLanguage = new HandlerLanguage("language/config.ini");
		try {
			handlerLanguage.language = "language/languageUs.properties";
			handlerLanguage.setLanguage();
			windowLogin.getWindowRegister().changeLenguage();
			windowLogin.changeLenguage();
			windowClient.getMenu().changeLenguage();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void changeToSpanish() {
		HandlerLanguage handlerLanguage = new HandlerLanguage("language/config.ini");
		try {
			handlerLanguage.language = "language/languageEs.properties";
			handlerLanguage.setLanguage();
			windowLogin.getWindowRegister().changeLenguage();
			windowLogin.changeLenguage();
			windowClient.getMenu().changeLenguage();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void actionPerformed(ActionEvent event) {

		switch (event.getActionCommand()) {
		case ConstantController.DEFAULT_WINDOW_CLIENT:
			showWindowClient();
			break;
		case ConstantController.DEFAULT_WINDOW_BACK:
			showWindowLogin();
			break;
		case ConstantController.A_BUTTON_REGISTER_OK:
			System.out.println("escucho");
			addUser();
			System.out.println("SALIO EXITOSAMENTE");
			break;
		case ConstantController.A_ITEM_ENGLISH:
			changeToEnglish();
			break;
		case ConstantController.A_ITEM_SPANISH:
			changeToSpanish();
		default:
			break;
		}
		// if(event.getActionCommand().equals(ConstantController.DEFAULT_WINDOW_CLIENT)){
		// showWindowClient();
		// }else
		// if(event.getActionCommand().equals(ConstantController.DEFAULT_WINDOW_BACK)){
		// showWindowLogin();
		//
		// }

	}

	public void addUser() {
		User cliente = windowLogin.getWindowRegister().createUser();
		if (cliente != null) {
			managerUser.addUser(cliente);
			// ventanaPrincipal.agregarCliente(cliente);

			XmlUser.EscribirXML(managerUser.getListUser(), RUTA_CLIENTE);
		}
	}
}
