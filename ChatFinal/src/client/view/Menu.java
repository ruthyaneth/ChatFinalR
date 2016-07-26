package client.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.print.attribute.standard.JobMessageFromOperator;
import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import client.preferences.PreferencesFrame;
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

	private JMenu menuFile;
	private JMenuItem itemExit;
	private JMenuItem itemsendFile;
	private JMenu menuConfiguration;
	private JMenu menuLenguage;
	private JMenuItem itemSpanish;
	private JMenuItem itemEnglish;
	private JMenuItem itemPeronalization;
	private JMenu menuAbout;
	private JMenuItem itemAbout;
	private JMenu menuHelp;
	private JMenuItem itemHelp;
	private Controller controller;

	//------Builder--------

	public Menu(Controller controller) {
		this.controller = controller;
		init();
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

		this.menuFile = new JMenu(ConstanstView.DEFAULT_JFILE);
		this.itemsendFile = new JMenuItem(ConstanstView.DEFAULT_JFILE_SEND);
		this.itemsendFile.setIcon(new ImageIcon(getClass().getResource(ConstanstView.IMAGE_SEND)));
		this.itemExit = new JMenuItem(ConstanstView.DEFAULT_JFILE_EXIT);
		this.itemExit.setIcon(new ImageIcon(getClass().getResource(ConstanstView.IMAGE_EXIT)));
		this.menuFile.add(itemsendFile);
		this.menuFile.add(itemExit);
		this.add(menuFile);
	}

	public void initJMenuConfiguration(){
		this.menuConfiguration = new JMenu(ConstanstView.DEFAULT_JCONFIGURATION);
		this.itemPeronalization = new JMenuItem(ConstanstView.DEFAULT_JCONFIGURATION_PER);
		this.itemPeronalization.setIcon(new ImageIcon(getClass().getResource(ConstanstView.IMAGE_CONF)));
		this.itemPeronalization.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new PreferencesFrame();
				
			}
		});
		this.add(menuConfiguration);
		this.menuConfiguration.add(itemPeronalization);
		
	}

	public void initJMenuLenguage(){
		this.menuLenguage = new JMenu(ConstanstView.DEFAULT_JCONFIGURATION_LENG);
		this.itemEnglish = new JMenuItem(ConstanstView.DEFAULT_ENGLISH);
		this.itemEnglish.setIcon(new ImageIcon(getClass().getResource(ConstanstView.IMAGE_ING)));
		this.itemSpanish = new JMenuItem(ConstanstView.DEFAULT_SPANISH);
		this.itemSpanish.setIcon(new ImageIcon(getClass().getResource(ConstanstView.IMAGE_ESPA)));
		this.itemEnglish.addActionListener(controller);
		this.itemEnglish.setActionCommand(ConstantController.A_ITEM_ENGLISH);
		this.itemSpanish.addActionListener(controller);
		this.itemSpanish.setActionCommand(ConstantController.A_ITEM_SPANISH);
		this.add(menuLenguage);
		this.menuLenguage.add(itemEnglish);
		this.menuLenguage.add(itemSpanish);
	}
	public void initJMenuAbout(){
		this.menuAbout = new JMenu(ConstanstView.DEFAULT_JABOUT);
		this.itemAbout = new JMenuItem(ConstanstView.DEFAULT_JABOUT_ABO);
		this.itemAbout.setIcon(new ImageIcon(getClass().getResource(ConstanstView.IMAGE_INFO)));
		this.add(menuAbout);
		this.menuAbout.add(itemAbout);
		
	}

	public void initJMenuHelp(){
		this.menuHelp = new JMenu(ConstanstView.DEFAULT_JHELP);
		this.itemHelp = new JMenuItem(ConstanstView.DEFAULT_JHELP_INFOR);
		this.itemHelp.setIcon(new ImageIcon(getClass().getResource(ConstanstView.IMAGE_HELP)));
		this.add(menuHelp);
		this.menuHelp.add(itemHelp);

	}
}
