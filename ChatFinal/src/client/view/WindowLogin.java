package client.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;

import constant.ConstanstView;
import constant.ConstantController;
import contoller.Controller;



/**
 * UNIVERSIDAD PEDAGOGICA Y TECNOLOGICA DE COLOMBIA
 * FACULTAD DE INGENIERIA.
 * ESCUELA DE INGENIERIA DE SISTEMAS Y COMPUTACION.
 * PRESENTADO A: Ing Helver Valero.
 * PROGRAMACION III
 * Clase de la ventana  de ingreso a la sala de chat 
 * @author Jenny Quesada , Ruth Rojas
 */
public class WindowLogin extends JDialog implements MouseListener {
	
	//-------Atributtes-------
	
	
	private JTextField textField;
	private JPasswordField passwordField;
	private JLabel labelIconPassword ;
	private JLabel labelIconGroup;
	private JLabel lblRegistrarse;
	private JLabel labelRecuper;
	private JButton buttonIconEntry;
	private JButton buttonIconExit;
	private Controller controller;
	
	
	
	//------Builder-----
	
	public WindowLogin(Controller controller) {
		
		init(controller);
	}
	
	//------Methods------
	
	public void init(Controller controller){
		initThis();
		intiLabel();
		initTx();
		initButton(controller);
	}
	
	public void initThis(){
		
		this.setTitle(ConstanstView.DEFAULT_WINDOW_LOGIN);
		this.setSize(440, 270);
		this.setBackground(UIManager.getColor(ConstanstView.DEFAULT_CONSTANTS));
		this.setForeground(new Color(0, 51, 255));
		this.setFont(new Font("Dialog", Font.BOLD, 13));
		this.setForeground(new Color(0, 51, 204));
		this.setLocationRelativeTo(null);
		this.setLayout(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setResizable(false);
		this.setVisible(true);	
		
	}
		
	public void intiLabel(){
		
		this.labelIconPassword = new JLabel();
		this.labelIconPassword.setIcon(new ImageIcon(ConstanstView.DEFAULT_WINDOWL_IM_KEY));
		this.labelIconPassword.setBounds(65, 116, 69, 64);
		this.add(labelIconPassword);
	
		
		this.labelIconGroup = new JLabel("");
		this.labelIconGroup.setIcon(new ImageIcon(ConstanstView.DEFAULT_WINDOWL_IM_TEAM));
		this.labelIconGroup.setBounds(55, 37, 64, 58);
		this.add(labelIconGroup);
	
		this.lblRegistrarse = new JLabel(ConstanstView.DEFAULT_WINDOWl_REGISTER);
		this.lblRegistrarse.setForeground(new Color(0, 51, 255));
		this.lblRegistrarse.setFont(new Font(ConstanstView.DEFAULT_FONT, Font.BOLD | Font.ITALIC, 14));
		this.lblRegistrarse.setBounds(195, 166, 149, 17);
		this.lblRegistrarse.addMouseListener(this);
		this.add(lblRegistrarse);
		
		this.labelRecuper = new JLabel(ConstanstView.DEFAULT_WINDOWL_PASSWORD);
		this.labelRecuper.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		this.labelRecuper.setForeground(new Color(255, 51, 0));
		this.labelRecuper.setBounds(133, 191, 232, 14);
		this.add(labelRecuper);
	
	}
		
		public void initTx(){
			
			this.textField = new JTextField();
			this.textField.setBounds(169, 50, 149, 28);
			this.add(textField);
			
			
			this.passwordField = new JPasswordField();
			this.passwordField.setBounds(169, 116, 149, 28);
			this.add(passwordField);
			
		}
		
		
		public void initButton(Controller controller){
			
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
		
		
		@Override
		public void mouseClicked(MouseEvent event) {
			this.controller = new Controller();
			if(event.getSource() == lblRegistrarse){
				WindowRegister register = new WindowRegister(controller);
				register.setVisible(true);
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

		//-------SettersAndGetters------
		
		
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

	
		
		
}