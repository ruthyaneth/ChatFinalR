package client.view;
import java.awt.Font;

import javax.swing.*;

import constant.ConstanstView;
import constant.ConstantController;
import contoller.Controller;


/**
 * UNIVERSIDAD PEDAGOGICA Y TECNOLOGICA DE COLOMBIA
 * FACULTAD DE INGENIERIA.
 * ESCUELA DE INGENIERIA DE SISTEMAS Y COMPUTACION.
 * PRESENTADO A: Ing Helver Valero.
 * PROGRAMACION III
 * Clase De la ventana de registro de cada usuario del chat
 * @author Jenny Quesada , Ruth Rojas
 */
public class WindowRegister extends JDialog {

	//------Atributtes------
	private JTextField txNickName;
	private JTextField txName;
	private JTextField txUserName;
	private JPasswordField txPassword;
	private JPasswordField txConformPassword;
	private JLabel labelName;
	private JLabel labelUsername;
	private JLabel labelNickname;
	private JLabel labelPassword;
	private JLabel labelConfirmPassword;
	private JLabel labelCaracter;
	private JTextField textField;
	private JLabel lblCorreo;
	private JTextField textField_1;
	private JButton buttonRegister;
	private JButton buttonCancel;
	private JLabel labelIcon;
	private JLabel labelLetter;
	private Controller controller;

	//------Builder-------

	public WindowRegister(Controller controller) {

		init(controller);
	}

	//-------Methods--------
	
	public void init(Controller controller){
		initThis();
		initLabel();
		initTx();
		initButton(controller);
	}

	public void initThis(){

		this.setTitle(ConstanstView.DEFAULT_NAME_WINDOW_LOGIN);
		this.setSize(ConstanstView.DEFAUL_SIZE_W, ConstanstView.DEFAULT_SIZ_H);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setResizable(false);
		this.setLayout(null);
		this.setLocationRelativeTo(null);

	}

	public void initLabel(){
		
		this.labelName = new JLabel(ConstanstView.DEFAULT_LABEL_NAME);
		this.labelName.setHorizontalAlignment(SwingConstants.CENTER);
		this.labelName.setFont(new Font(ConstanstView.DEFAULT_FONT, Font.BOLD, 15));
		this.labelName.setBounds(32, 168, 121, 28);
		this.add(labelName);

		this.labelUsername = new JLabel(ConstanstView.DEFAULT_LABEL_USERNAME);
		this.labelUsername.setHorizontalAlignment(SwingConstants.CENTER);
		this.labelUsername.setFont(new Font(ConstanstView.DEFAULT_FONT, Font.BOLD, 15));
		this.labelUsername.setBounds(57, 211, 77, 21);
		this.add(labelUsername);

		this.labelNickname = new JLabel(ConstanstView.DEFAULT_LABEL_NOCKNAME);
		this.labelNickname.setHorizontalAlignment(SwingConstants.CENTER);
		this.labelNickname.setFont(new Font(ConstanstView.DEFAULT_FONT, Font.BOLD, 15));
		this.labelNickname.setBounds(43, 254, 95, 23);
		this.add(labelNickname);

		this.labelPassword = new JLabel(ConstanstView.DEFAULT_LABEL_PASSWORD);
		this.labelPassword.setHorizontalAlignment(SwingConstants.CENTER);
		this.labelPassword.setFont(new Font(ConstanstView.DEFAULT_FONT, Font.BOLD, 15));
		this.labelPassword.setBounds(10, 288, 168, 28);
		this.add(labelPassword);

		this.labelConfirmPassword = new JLabel(ConstanstView.DEFAULT_LABEL_PASSWORD_CON);
		this.labelConfirmPassword.setFont(new Font(ConstanstView.DEFAULT_FONT, Font.BOLD, 15));
		this.labelConfirmPassword.setBounds(28, 327, 172, 23);
		this.add(labelConfirmPassword);

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

		this.labelLetter = new JLabel("");
		this.labelLetter.setIcon(new ImageIcon(ConstanstView.DEFAULT_WINDOWR_LINE));
		this.labelLetter.setBounds(57, 387, 132, 149);
		this.add(labelLetter);


	}

	public void initTx(){

		this.txNickName = new JTextField();
		this.txNickName.setBounds(239, 248, 180, 28);
		this.add(txNickName);


		this.txName = new JTextField();
		this.txName.setBounds(239, 170, 180, 28);
		this.add(txName);


		this.txUserName = new JTextField();
		this.txUserName.setBounds(239, 209, 180, 28);
		this.add(txUserName);

		this.txPassword = new JPasswordField();
		this.txPassword.setBounds(239, 287, 180, 28);
		this.add(txPassword);

		this.txConformPassword = new JPasswordField();
		this.txConformPassword.setBounds(239, 326, 180, 28);
		this.add(txConformPassword);

		this.textField = new JTextField();
		this.textField.setBounds(239, 530, 180, 28);
		this.add(textField);

		this.textField_1 = new JTextField();
		this.textField_1.setBounds(239, 365, 180, 28);
		this.add(textField_1);

	}

	public void initButton(Controller controller){

		this.buttonRegister = new JButton(ConstanstView.DEFAULT_BUTTON_REGISTER);
		this.buttonRegister.setFont(new Font(ConstanstView.DEFAULT_FONT, Font.BOLD, 12));
		this.buttonRegister.setBounds(123, 595, 110, 23);
//		this.buttonRegister.setActionCommand(ConstanstView.A_BUTTON_REGISTER_OK);
//		this.buttonRegister.addActionListener(controller);
		this.add(buttonRegister);

		this.buttonCancel = new JButton(ConstanstView.DEFAULT_BUTTON_CANCEL);
		this.buttonCancel.setFont(new Font(ConstanstView.DEFAULT_FONT, Font.BOLD, 12));
		this.buttonCancel.setBounds(275, 595, 107, 23);
		this.buttonCancel.setActionCommand(ConstantController.DEFAULT_WINDOW_BACK);
		this.buttonCancel.addActionListener(controller);
		this.add(buttonCancel);
	}
	
	
	//------Gets&&Sets-------

	public JTextField getTxNickName() {
		return txNickName;
	}

	public void setTxNickName(JTextField txNickName) {
		this.txNickName = txNickName;
	}

	public JTextField getTxName() {
		return txName;
	}

	public void setTxName(JTextField txName) {
		this.txName = txName;
	}

	public JTextField getTxUserName() {
		return txUserName;
	}

	public void setTxUserName(JTextField txUserName) {
		this.txUserName = txUserName;
	}

	public JPasswordField getTxPassword() {
		return txPassword;
	}

	public void setTxPassword(JPasswordField txPassword) {
		this.txPassword = txPassword;
	}

	public JPasswordField getTxConformPassword() {
		return txConformPassword;
	}

	public void setTxConformPassword(JPasswordField txConformPassword) {
		this.txConformPassword = txConformPassword;
	}

	public JLabel getLabelName() {
		return labelName;
	}

	public void setLabelName(JLabel labelName) {
		this.labelName = labelName;
	}

	public JLabel getLabelUsername() {
		return labelUsername;
	}

	public void setLabelUsername(JLabel labelUsername) {
		this.labelUsername = labelUsername;
	}

	public JLabel getLabelNickname() {
		return labelNickname;
	}

	public void setLabelNickname(JLabel labelNickname) {
		this.labelNickname = labelNickname;
	}

	public JLabel getLabelPassword() {
		return labelPassword;
	}

	public void setLabelPassword(JLabel labelPassword) {
		this.labelPassword = labelPassword;
	}

	public JLabel getLabelConfirmPassword() {
		return labelConfirmPassword;
	}

	public void setLabelConfirmPassword(JLabel labelConfirmPassword) {
		this.labelConfirmPassword = labelConfirmPassword;
	}

	public JLabel getLabelCaracter() {
		return labelCaracter;
	}

	public void setLabelCaracter(JLabel labelCaracter) {
		this.labelCaracter = labelCaracter;
	}

	public JTextField getTextField() {
		return textField;
	}

	public void setTextField(JTextField textField) {
		this.textField = textField;
	}

	public JLabel getLblCorreo() {
		return lblCorreo;
	}

	public void setLblCorreo(JLabel lblCorreo) {
		this.lblCorreo = lblCorreo;
	}

	public JTextField getTextField_1() {
		return textField_1;
	}

	public void setTextField_1(JTextField textField_1) {
		this.textField_1 = textField_1;
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
		return labelLetter;
	}

	public void setLabelLetter(JLabel labelLetter) {
		this.labelLetter = labelLetter;
	}
	
	
	
	
}

