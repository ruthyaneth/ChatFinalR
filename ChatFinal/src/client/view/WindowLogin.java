package client.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;

import config.HandlerLanguage;
import config.HandlerProperties;
import constant.ConstanstView;
import constant.ConstantController;
import contoller.Controller;

/**
 * UNIVERSIDAD PEDAGOGICA Y TECNOLOGICA DE COLOMBIA FACULTAD DE INGENIERIA.
 * ESCUELA DE INGENIERIA DE SISTEMAS Y COMPUTACION. PRESENTADO A: Ing Helver
 * Valero. PROGRAMACION III Clase de la ventana de ingreso a la sala de chat
 * 
 * @author Jenny Quesada , Ruth Rojas
 */
public class WindowLogin extends JDialog implements MouseListener {

	// -------Atributtes-------

	private JTextField textField;
	private JPasswordField passwordField;
	private JLabel labelIconPassword;
	private JLabel labelIconGroup;
	private JLabel jLabelRegistrarse;
	private JLabel jLabelRecuper;
	private JButton buttonIconEntry;
	private JButton buttonIconExit;
	private static Controller controller;
	private WindowRegister windowRegister;
	public static WindowLogin windowLogin = null;

	// ------Builder-----

	public WindowLogin(Controller controller, WindowRegister windowRegister) {

		init(controller);
		windowRegister = new WindowRegister(controller);
	}
	public WindowLogin(Controller controller) {

		init(controller);
		windowRegister = new WindowRegister(controller);
	}

	// ------Methods------

	public static WindowLogin getInstance() {
		if (windowLogin == null) {
			windowLogin = new WindowLogin(controller);
		}
		return windowLogin;
	}
	public void init(Controller controller) {
		initThis();
		intiLabel();
		initTx();
		initButton(controller);
		try {
			changeLenguage();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void initThis() {

		this.setSize(440, 270);
		this.setBackground(UIManager.getColor(ConstanstView.DEFAULT_CONSTANTS));
		this.setForeground(new Color(0, 51, 255));
		this.setFont(new Font("Dialog", Font.BOLD, 13));
		this.setForeground(new Color(0, 51, 204));
		this.setLocationRelativeTo(null);
		this.setLayout(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setResizable(false);
	}

	public void intiLabel() {

		this.labelIconPassword = new JLabel();
		this.labelIconPassword.setIcon(new ImageIcon(ConstanstView.DEFAULT_WINDOWL_IM_KEY));
		this.labelIconPassword.setBounds(65, 116, 69, 64);
		this.add(labelIconPassword);

		this.labelIconGroup = new JLabel("");
		this.labelIconGroup.setIcon(new ImageIcon(ConstanstView.DEFAULT_WINDOWL_IM_TEAM));
		this.labelIconGroup.setBounds(55, 37, 64, 58);
		this.add(labelIconGroup);

		this.jLabelRegistrarse = new JLabel();
		this.jLabelRegistrarse.setForeground(new Color(0, 51, 255));
		this.jLabelRegistrarse.setFont(new Font(ConstanstView.DEFAULT_FONT, Font.BOLD | Font.ITALIC, 14));
		this.jLabelRegistrarse.setBounds(195, 166, 149, 17);
		this.jLabelRegistrarse.addMouseListener(this);
		this.add(jLabelRegistrarse);

		this.jLabelRecuper = new JLabel();
		this.jLabelRecuper.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		this.jLabelRecuper.setForeground(new Color(255, 51, 0));
		this.jLabelRecuper.setBounds(133, 191, 232, 14);
		this.add(jLabelRecuper);
	}

	public void initTx() {

		this.textField = new JTextField();
		this.textField.setBounds(169, 50, 149, 28);
		this.add(textField);
		this.passwordField = new JPasswordField();
		this.passwordField.setBounds(169, 116, 149, 28);
		this.add(passwordField);

	}

	public void initButton(Controller controller) {

		this.buttonIconEntry = new JButton("");
		this.buttonIconEntry.setIcon(new ImageIcon(ConstanstView.DEFAULT_WINDOWL_IM_FLECHA));
		this.buttonIconEntry.setBounds(346, 85, 54, 33);
		this.buttonIconEntry.setActionCommand(ConstantController.DEFAULT_WINDOW_CLIENT);
		this.buttonIconEntry.addActionListener(controller);
		this.add(buttonIconEntry);

		this.buttonIconExit = new JButton("");
		this.buttonIconExit.setIcon(new ImageIcon(ConstanstView.DEFAULT_WINDOWL_IM_EXIT));
		this.buttonIconExit.setBounds(10, 194, 45, 39);
		this.add(buttonIconExit);
	}

	public void changeLenguage() throws IOException {

		HandlerProperties handlerProperties = new HandlerProperties(HandlerLanguage.language);
		handlerProperties.load();
		this.setTitle(handlerProperties.getProperty(ConstanstView.DEFAULT_WINDOW_LOGIN));
		this.jLabelRegistrarse.setText(handlerProperties.getProperty(ConstanstView.DEFAULT_WINDOWl_REGISTER));
		this.jLabelRecuper.setText(handlerProperties.getProperty(ConstanstView.DEFAULT_WINDOWL_PASSWORD));
	}

	@Override
	public void mouseClicked(MouseEvent event) {
		// this.controller = new Controller();
		if (event.getSource() == jLabelRegistrarse) {
			windowRegister.setVisible(true);
			this.setVisible(false);
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	// -------SettersAndGetters------

	public JTextField getTextField() {
		return textField;
	}

	public void setTextField(JTextField textField) {
		this.textField = textField;
	}

	public JPasswordField getPasswordField() {
		return passwordField;
	}

	public void setPasswordField(JPasswordField passwordField) {
		this.passwordField = passwordField;
	}

	public WindowRegister getWindowRegister() {
		return windowRegister;
	}

}