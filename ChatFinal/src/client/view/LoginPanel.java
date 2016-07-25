package client.view;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.UnsupportedEncodingException;

import javax.swing.border.*;

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
	private JTextField port;
	private JTextField host;
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
		host = new JTextField(15);
		port = new JTextField(15);
		user = new JTextField(15);
		optionConection = new JToggleButton(ConstanstView.NAME_TO_BUTTON);
		

		optionConection.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (optionConection.getText().equals(ConstanstView.NAME_TO_BUTTON)) {
					

						try {
							ChatSocketControl.getInstance().openSocket(Integer.parseInt(port.getText()),host.getText(),
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
		this.add(host);
		showlabel = new JLabel(ConstanstView.NAME_PORT);
		this.add(showlabel);
		this.add(port);
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
		port.setEnabled(false);
		host.setEnabled(false);
		user.setEnabled(false);
		optionConection.setSelected(true);
		optionConection.setText(ConstanstView.NAME_TO_BUTTOND);
	}

	public void unlock() {
		port.setEnabled(true);
		host.setEnabled(true);
		user.setEnabled(true);
		optionConection.setSelected(false);
		optionConection.setText(ConstanstView.NAME_TO_BUTTON);
	}
	
	public String getUserName() {
		return user.getText();
	}
}
