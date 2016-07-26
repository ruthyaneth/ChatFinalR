package client.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Random;

import javax.swing.*;

import client.logic.ManagerUser;
import client.logic.User;
import config.HandlerLanguage;
import config.HandlerProperties;
import constant.ConstanstView;
import constant.ConstantController;
import contoller.Controller;

/**
 * UNIVERSIDAD PEDAGOGICA Y TECNOLOGICA DE COLOMBIA FACULTAD DE INGENIERIA.
 * ESCUELA DE INGENIERIA DE SISTEMAS Y COMPUTACION. PRESENTADO A: Ing Helver
 * Valero. PROGRAMACION III Clase De la ventana de registro de cada usuario del
 * chat
 * 
 * @author Jenny Quesada , Ruth Rojas
 */
public class WindowRegister extends JDialog {

	private static final long serialVersionUID = 1L;
	
	// ------Atributtes------
	private JTextField jTextFieldNickName;
	private JTextField jTextFieldName;
	private JTextField jTextFielLastName;
	private JPasswordField jPasswordFiel;
	private JPasswordField jPasswordFielConfirmPassword;
	private JLabel jLabelName;
	private JLabel jLabelLastName;
	private JLabel jLabelNickname;
	private JLabel jLabelPassword;
	private JLabel jLabelConfirmPassword;
	private JLabel labelCaracter;
	private JTextField jTextFielReDigitCaptcha;
	private JLabel lblCorreo;
	private JTextField jTextFielEmail;
	private JButton buttonRegister;
	private JButton buttonCancel;
	private JLabel labelIcon;
	private JLabel labelCaptcha;
	private Controller controller;
	private JButton jButtonOkCaptcha;

	// ------Builder-------

	public WindowRegister(final Controller controller) {

		init(controller);
	}

	// -------Methods--------

	public void init(Controller controller) {
		initThis();
		initLabel();
		initTx();
		initButton(controller);
		addJButtonOkCaptcha();
		try {
			changeLenguage();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void initThis() {

		this.setSize(ConstanstView.DEFAUL_SIZE_W, ConstanstView.DEFAULT_SIZ_H);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setResizable(false);
		this.setLayout(null);
		this.setLocationRelativeTo(null);

	}

	public void addJButtonOkCaptcha() {
		this.jButtonOkCaptcha = new JButton("Ok");
		this.jButtonOkCaptcha.setBounds(239, 410, 107, 23);
		this.jButtonOkCaptcha.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String getTxt = jTextFielReDigitCaptcha.getText();
				if (getTxt.equals(labelCaptcha.getText())) {
					JOptionPane.showMessageDialog(null, "OK!! No Eres un Robot!!!!");
				} else {
					JOptionPane.showMessageDialog(null, "Error Captcha! Porfavor re-escriba el texto!.");
					labelCaptcha.setText(generateCaptcha());
				}
			}
		});
		this.add(jButtonOkCaptcha);
	}

	public void initLabel() {

		this.jLabelName = new JLabel();
		this.jLabelName.setHorizontalAlignment(SwingConstants.CENTER);
		this.jLabelName.setFont(new Font(ConstanstView.DEFAULT_FONT, Font.BOLD, 15));
		this.jLabelName.setBounds(32, 168, 121, 28);
		this.add(jLabelName);

		this.jLabelLastName = new JLabel();
		this.jLabelLastName.setHorizontalAlignment(SwingConstants.CENTER);
		this.jLabelLastName.setFont(new Font(ConstanstView.DEFAULT_FONT, Font.BOLD, 15));
		this.jLabelLastName.setBounds(57, 211, 77, 21);
		this.add(jLabelLastName);

		this.jLabelNickname = new JLabel();
		this.jLabelNickname.setHorizontalAlignment(SwingConstants.CENTER);
		this.jLabelNickname.setFont(new Font(ConstanstView.DEFAULT_FONT, Font.BOLD, 15));
		this.jLabelNickname.setBounds(43, 254, 95, 23);
		this.add(jLabelNickname);

		this.jLabelPassword = new JLabel();
		this.jLabelPassword.setHorizontalAlignment(SwingConstants.CENTER);
		this.jLabelPassword.setFont(new Font(ConstanstView.DEFAULT_FONT, Font.BOLD, 15));
		this.jLabelPassword.setBounds(10, 288, 168, 28);
		this.add(jLabelPassword);

		this.jLabelConfirmPassword = new JLabel();
		this.jLabelConfirmPassword.setFont(new Font(ConstanstView.DEFAULT_FONT, Font.BOLD, 15));
		this.jLabelConfirmPassword.setBounds(28, 327, 172, 23);
		this.add(jLabelConfirmPassword);

		this.labelCaracter = new JLabel(ConstanstView.DEFAULT_LABEL_CARACTER);
		this.labelCaracter.setHorizontalAlignment(SwingConstants.CENTER);
		this.labelCaracter.setFont(new Font(ConstanstView.DEFAULT_FONT, Font.BOLD, 15));
		this.labelCaracter.setBounds(46, 530, 132, 28);
		this.add(labelCaracter);

		this.lblCorreo = new JLabel(ConstanstView.DEFAULT_LABEL_EMAIL);
		this.lblCorreo.setFont(new Font(ConstanstView.DEFAULT_FONT, Font.BOLD, 15));
		this.lblCorreo.setBounds(32, 367, 168, 21);
		this.add(lblCorreo);

		this.labelIcon = new JLabel("");
		this.labelIcon.setIcon(new ImageIcon(ConstanstView.DEFAULT_WINDOWR_ICON));
		this.labelIcon.setBounds(184, 11, 150, 156);
		this.add(labelIcon);

		this.labelCaptcha = new JLabel(generateCaptcha());
		this.labelCaptcha.setBorder(BorderFactory.createLineBorder(Color.BLUE, 7));
		this.labelCaptcha.setBorder(BorderFactory.createTitledBorder("Captcha"));
		this.labelCaptcha.setBounds(57, 387, 132, 149);
		this.add(labelCaptcha);
	}

	public void initTx() {

		this.jTextFieldNickName = new JTextField();
		this.jTextFieldNickName.setBounds(239, 248, 180, 28);
		this.add(jTextFieldNickName);

		this.jTextFieldName = new JTextField();
		this.jTextFieldName.setBounds(239, 170, 180, 28);
		this.add(jTextFieldName);

		this.jTextFielLastName = new JTextField();
		this.jTextFielLastName.setBounds(239, 209, 180, 28);
		this.add(jTextFielLastName);

		this.jPasswordFiel = new JPasswordField();
		this.jPasswordFiel.setBounds(239, 287, 180, 28);
		this.add(jPasswordFiel);

		this.jPasswordFielConfirmPassword = new JPasswordField();
		this.jPasswordFielConfirmPassword.setBounds(239, 326, 180, 28);
		this.add(jPasswordFielConfirmPassword);

		this.jTextFielReDigitCaptcha = new JTextField();
		this.jTextFielReDigitCaptcha.setBounds(239, 530, 180, 28);
		this.add(jTextFielReDigitCaptcha);

		this.jTextFielEmail = new JTextField();
		this.jTextFielEmail.setBounds(239, 365, 180, 28);
		this.add(jTextFielEmail);

	}

	public void initButton(Controller controller) {

		this.buttonRegister = new JButton(ConstanstView.DEFAULT_BUTTON_REGISTER);
		this.buttonRegister.setFont(new Font(ConstanstView.DEFAULT_FONT, Font.BOLD, 12));
		this.buttonRegister.setBounds(123, 595, 110, 23);
		this.buttonRegister.setActionCommand(ConstantController.A_BUTTON_REGISTER_OK);
		this.buttonRegister.addActionListener(controller);
		this.add(buttonRegister);

		this.buttonCancel = new JButton(ConstanstView.DEFAULT_BUTTON_CANCEL);
		this.buttonCancel.setFont(new Font(ConstanstView.DEFAULT_FONT, Font.BOLD, 12));
		this.buttonCancel.setBounds(275, 595, 107, 23);
		this.buttonCancel.setActionCommand(ConstantController.DEFAULT_WINDOW_BACK);
		this.buttonCancel.addActionListener(controller);
		this.add(buttonCancel);
	}

	public String generateCaptcha() {
		
		Random random = new Random();
		int length = 5;
		StringBuffer captchaStringBuffer = new StringBuffer();
		for (int i = 0; i < length; i++) {
			int captchaNumber = Math.abs(random.nextInt()) % 60;
			int charNumber = 0;
			if (captchaNumber < 26) {
				charNumber = 65 + captchaNumber;
			} else if (captchaNumber < 52) {
				charNumber = 97 + (captchaNumber - 26);
			} else {
				charNumber = 48 + (captchaNumber - 52);
			}
			captchaStringBuffer.append((char) charNumber);
		}

		return captchaStringBuffer.toString();
		
	}
	public User createUser(){
		char[] arrayC = jPasswordFiel.getPassword(); 
		String pass = new String(arrayC); 
		User cliente = ManagerUser.createUser(jTextFieldName.getText(), jTextFielLastName.getText(), jTextFieldNickName.getText(), pass, jTextFielEmail.getText());
		System.out.println(jTextFieldName.getText()+ "--->");
		dispose();
//		eliminarDatosTableCliente();
		return cliente;
	}

	// ------Gets&&Sets-------

	private void eliminarDatosTableCliente() {
		jTextFieldName.setText("");
		jTextFielLastName.setText("");
		jTextFieldNickName.setText("");
		jTextFielReDigitCaptcha.setText("");
		jTextFielEmail.setText("");
		jPasswordFiel.setText("");
		jPasswordFielConfirmPassword.setText("");
	}
	public void changeLenguage() throws IOException {

		HandlerProperties handlerProperties = new HandlerProperties(HandlerLanguage.language);
		handlerProperties.load();
		this.setTitle(handlerProperties.getProperty(ConstanstView.DEFAULT_NAME_WINDOW_LOGIN));
		this.jLabelName.setText(handlerProperties.getProperty(ConstanstView.DEFAULT_LABEL_NAME));
		this.jLabelLastName.setText(handlerProperties.getProperty(ConstanstView.DEFAULT_LABEL_USERNAME));
		this.jLabelNickname.setText(handlerProperties.getProperty(ConstanstView.DEFAULT_LABEL_NOCKNAME));
		this.jLabelPassword.setText(handlerProperties.getProperty(ConstanstView.DEFAULT_LABEL_PASSWORD));
		this.jLabelConfirmPassword.setText(handlerProperties.getProperty(ConstanstView.DEFAULT_LABEL_PASSWORD_CON));
		// comprobando
	}

	public JTextField getTxNickName() {
		return jTextFieldNickName;
	}

	public void setTxNickName(JTextField txNickName) {
		this.jTextFieldNickName = txNickName;
	}

	public JTextField getTxName() {
		return jTextFieldName;
	}

	public void setTxName(JTextField txName) {
		this.jTextFieldName = txName;
	}

	public JTextField getTxUserName() {
		return jTextFielLastName;
	}

	public void setTxUserName(JTextField txUserName) {
		this.jTextFielLastName = txUserName;
	}

	public JPasswordField getTxPassword() {
		return jPasswordFiel;
	}

	public void setTxPassword(JPasswordField txPassword) {
		this.jPasswordFiel = txPassword;
	}

	public JPasswordField getTxConformPassword() {
		return jPasswordFielConfirmPassword;
	}

	public void setTxConformPassword(JPasswordField txConformPassword) {
		this.jPasswordFielConfirmPassword = txConformPassword;
	}

	public JLabel getLabelName() {
		return jLabelName;
	}

	public void setLabelName(JLabel labelName) {
		this.jLabelName = labelName;
	}

	public JLabel getLabelUsername() {
		return jLabelLastName;
	}

	public void setLabelUsername(JLabel labelUsername) {
		this.jLabelLastName = labelUsername;
	}

	public JLabel getLabelNickname() {
		return jLabelNickname;
	}

	public void setLabelNickname(JLabel labelNickname) {
		this.jLabelNickname = labelNickname;
	}

	public JLabel getLabelPassword() {
		return jLabelPassword;
	}

	public void setLabelPassword(JLabel labelPassword) {
		this.jLabelPassword = labelPassword;
	}

	public JLabel getLabelConfirmPassword() {
		return jLabelConfirmPassword;
	}

	public void setLabelConfirmPassword(JLabel labelConfirmPassword) {
		this.jLabelConfirmPassword = labelConfirmPassword;
	}

	public JLabel getLabelCaracter() {
		return labelCaracter;
	}

	public void setLabelCaracter(JLabel labelCaracter) {
		this.labelCaracter = labelCaracter;
	}

	public JTextField getTextField() {
		return jTextFielReDigitCaptcha;
	}

	public void setTextField(JTextField textField) {
		this.jTextFielReDigitCaptcha = textField;
	}

	public JLabel getLblCorreo() {
		return lblCorreo;
	}

	public void setLblCorreo(JLabel lblCorreo) {
		this.lblCorreo = lblCorreo;
	}

	public JTextField getTextField_1() {
		return jTextFielEmail;
	}

	public void setTextField_1(JTextField textField_1) {
		this.jTextFielEmail = textField_1;
	}

	public JButton getButtonRegister() {
		return buttonRegister;
	}

	public void setButtonRegister(JButton buttonRegister) {
		this.buttonRegister = buttonRegister;
	}

	public JButton getButtonCancel() {
		return buttonCancel;
	}

	public void setButtonCancel(JButton buttonCancel) {
		this.buttonCancel = buttonCancel;
	}

	public JLabel getLabelIcon() {
		return labelIcon;
	}

	public void setLabelIcon(JLabel labelIcon) {
		this.labelIcon = labelIcon;
	}

	public JLabel getLabelLetter() {
		return labelCaptcha;
	}

	public void setLabelLetter(JLabel labelLetter) {
		this.labelCaptcha = labelLetter;
	}
}
