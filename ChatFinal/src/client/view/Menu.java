package client.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.print.attribute.standard.JobMessageFromOperator;
import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import client.preferences.PreferencesFrame;
import config.HandlerLanguage;
import config.HandlerProperties;
import constant.ConstanstView;
import constant.ConstantController;
import contoller.Controller;

/**
 * UNIVERSIDAD PEDAGOGICA Y TECNOLOGICA DE COLOMBIA
 * FACULTAD DE INGENIERIA.
 * ESCUELA DE INGENIERIA DE SISTEMAS Y COMPUTACION.
 * PRESENTADO A: Ing Helver Valero.
 * PROGRAMACION III
 * Clase de menu  donde los usuarios pueden tomar multiples opciones 
 * @author Jenny Quesada , Ruth Rojas
 */
public class Menu  extends JMenuBar{

	//-----Atributtes-------

	private JMenu jMenuFile;
	private JMenuItem jMenuItemExit;
	private JMenuItem jMenuItemSendFile;
	private JMenu jMenuConfiguration;
	private JMenu jMenuLenguage;
	private JMenuItem jMenuItemSpanish;
	private JMenuItem jMenuItemEnglish;
	private JMenuItem jMenuItemPeronalization;
	private JMenu jMenuAbout;
	private JMenuItem jMenuItemAbout;
	private JMenu jMenuHelp;
	private JMenuItem jMenuItemHelp;
	private Controller controller;

	//------Builder--------

	public Menu(Controller controller) {
		this.controller = controller;
		init();
		try {
			changeLenguage();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	//------Methods--------

	public void init(){
		intiJMenuFile();
		initJMenuLenguage();
		initJMenuConfiguration();
		initJMenuHelp();
		initJMenuAbout();
	}

	public void intiJMenuFile(){

		this.jMenuFile = new JMenu();
		this.jMenuItemSendFile = new JMenuItem();
		this.jMenuItemSendFile.setIcon(new ImageIcon(getClass().getResource(ConstanstView.IMAGE_SEND)));
		this.jMenuItemExit = new JMenuItem();
		this.jMenuItemExit.setIcon(new ImageIcon(getClass().getResource(ConstanstView.IMAGE_EXIT)));
		this.jMenuFile.add(jMenuItemSendFile);
		this.jMenuFile.add(jMenuItemExit);
		this.add(jMenuFile);
	}

	public void initJMenuConfiguration(){
		this.jMenuConfiguration = new JMenu();
		this.jMenuItemPeronalization = new JMenuItem();
		this.jMenuItemPeronalization.setIcon(new ImageIcon(getClass().getResource(ConstanstView.IMAGE_CONF)));
		this.jMenuItemPeronalization.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new PreferencesFrame();
				
			}
		});
		this.add(jMenuConfiguration);
		this.jMenuConfiguration.add(jMenuItemPeronalization);
		
	}

	public void initJMenuLenguage(){
		this.jMenuLenguage = new JMenu();
		this.jMenuItemEnglish = new JMenuItem();
		this.jMenuItemEnglish.setIcon(new ImageIcon(getClass().getResource(ConstanstView.IMAGE_ING)));
		this.jMenuItemSpanish = new JMenuItem();
		this.jMenuItemSpanish.setIcon(new ImageIcon(getClass().getResource(ConstanstView.IMAGE_ESPA)));
		this.jMenuItemEnglish.addActionListener(controller);
		this.jMenuItemEnglish.setActionCommand(ConstantController.A_ITEM_ENGLISH);
		this.jMenuItemSpanish.addActionListener(controller);
		this.jMenuItemSpanish.setActionCommand(ConstantController.A_ITEM_SPANISH);
		this.add(jMenuLenguage);
		this.jMenuLenguage.add(jMenuItemEnglish);
		this.jMenuLenguage.add(jMenuItemSpanish);
	}
	public void initJMenuAbout(){
		this.jMenuAbout = new JMenu();
		this.jMenuItemAbout = new JMenuItem();
		this.jMenuItemAbout.setIcon(new ImageIcon(getClass().getResource(ConstanstView.IMAGE_INFO)));
		this.add(jMenuAbout);
		this.jMenuAbout.add(jMenuItemAbout);
		
	}

	public void initJMenuHelp(){
		this.jMenuHelp = new JMenu();
		this.jMenuItemHelp = new JMenuItem();
		this.jMenuItemHelp.setIcon(new ImageIcon(getClass().getResource(ConstanstView.IMAGE_HELP)));
		this.add(jMenuHelp);
		this.jMenuHelp.add(jMenuItemHelp);

	}
	public void changeLenguage() throws IOException {

		HandlerProperties handlerProperties = new HandlerProperties(HandlerLanguage.language);
		handlerProperties.load();
		this.jMenuFile.setText(handlerProperties.getProperty(ConstanstView.DEFAULT_JFILE));
		this.jMenuItemSendFile.setText(handlerProperties.getProperty(ConstanstView.DEFAULT_JFILE_SEND));
		this.jMenuItemExit.setText(handlerProperties.getProperty(ConstanstView.DEFAULT_JFILE_EXIT));
		this.jMenuConfiguration.setText(handlerProperties.getProperty(ConstanstView.DEFAULT_JCONFIGURATION));
		this.jMenuItemPeronalization.setText(handlerProperties.getProperty(ConstanstView.DEFAULT_JCONFIGURATION_PER));
		this.jMenuLenguage.setText(handlerProperties.getProperty(ConstanstView.DEFAULT_JCONFIGURATION_LENG));
		this.jMenuItemEnglish.setText(handlerProperties.getProperty(ConstanstView.DEFAULT_ENGLISH));
		this.jMenuItemSpanish.setText(handlerProperties.getProperty(ConstanstView.DEFAULT_SPANISH));
		this.jMenuAbout.setText(handlerProperties.getProperty(ConstanstView.DEFAULT_JABOUT));
		this.jMenuItemAbout.setText(handlerProperties.getProperty(ConstanstView.DEFAULT_JABOUT_ABO));
		this.jMenuHelp.setText(handlerProperties.getProperty(ConstanstView.DEFAULT_JHELP));
		this.jMenuItemHelp.setText(handlerProperties.getProperty(ConstanstView.DEFAULT_JHELP_INFOR));
	}
}
