package client.view;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.swing.border.*;

import config.HandlerLanguage;
import config.HandlerProperties;
import constant.ConstanstView;
import control.ChatSocketControl;

/**
 * UNIVERSIDAD PEDAGOGICA Y TECNOLOGICA DE COLOMBIA
 * FACULTAD DE INGENIERIA.
 * ESCUELA DE INGENIERIA DE SISTEMAS Y COMPUTACION.
 * PRESENTADO A: Ing Helver Valero.
 * PROGRAMACION III
 * Panel donde se conecta los usuarios
 * @author Jenny Quesada , Ruth Rojas
 */
public class LoginPanel extends JPanel {
	
	//-----Atribttes------
	private static LoginPanel singleton = null;
	private JTextField jTextFielPort;
	private JTextField jTextFieldhost;
	private JTextField user;
	private JToggleButton optionConection;

	//------Builder------
	
	private LoginPanel() {
		init();
	}
	
	//------Methods-------
	
	public void init(){
		initThis();
		initComponent();
	}
	
	public void initThis(){
		this.setLayout(new FlowLayout(FlowLayout.LEFT));
	}
	
	public void initComponent(){
		jTextFieldhost = new JTextField(15);
		jTextFielPort = new JTextField(15);
		user = new JTextField(15);
		optionConection = new JToggleButton(ConstanstView.NAME_TO_BUTTON);
		

		optionConection.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (optionConection.getText().equals(ConstanstView.NAME_TO_BUTTON)) {
					

						try {
							ChatSocketControl.getInstance().openSocket(Integer.parseInt(jTextFielPort.getText()),jTextFieldhost.getText(),
									new String(user.getText().getBytes(),
											"UTF-8"));
						} catch (NumberFormatException | UnsupportedEncodingException e1) {
							e1.printStackTrace();
						}

				
				} else {
					ChatSocketControl.getInstance().closeSocket();
				}
			}
		});

		JLabel showlabel;
		showlabel = new JLabel(ConstanstView.NAME_IP);
		this.add(showlabel);
		this.add(jTextFieldhost);
		showlabel = new JLabel(ConstanstView.NAME_PORT);
		this.add(showlabel);
		this.add(jTextFielPort);
		showlabel = new JLabel(ConstanstView.NAME_USER);
		this.add(showlabel);
		this.add(user);
		this.add(optionConection);
	}
	

	public static LoginPanel getInstance() {
		if (singleton == null)
			singleton = new LoginPanel();
		return singleton;
	}

	public void lock() {
		jTextFielPort.setEnabled(false);
		jTextFieldhost.setEnabled(false);
		user.setEnabled(false);
		optionConection.setSelected(true);
		optionConection.setText(ConstanstView.NAME_TO_BUTTOND);
	}

	public void unlock() {
		jTextFielPort.setEnabled(true);
		jTextFieldhost.setEnabled(true);
		user.setEnabled(true);
		optionConection.setSelected(false);
		optionConection.setText(ConstanstView.NAME_TO_BUTTON);
	}
	public void changeLenguage() throws IOException {

		HandlerProperties handlerProperties = new HandlerProperties(HandlerLanguage.language);
		handlerProperties.load();
	}
	
	public String getUserName() {
		return user.getText();
	}
}
