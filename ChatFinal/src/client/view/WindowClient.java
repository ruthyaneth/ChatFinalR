package client.view;

import javax.imageio.ImageIO;
import java.io.*;

import javax.swing.*;

import org.pushingpixels.substance.api.SubstanceLookAndFeel;
import org.pushingpixels.substance.api.SubstanceConstants.ImageWatermarkKind;
import org.pushingpixels.substance.api.skin.RavenSkin;
import org.pushingpixels.substance.api.watermark.SubstanceImageWatermark;

import constant.ConstanstView;
import contoller.Controller;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

import control.*;
/**
 * UNIVERSIDAD PEDAGOGICA Y TECNOLOGICA DE COLOMBIA
 * FACULTAD DE INGENIERIA.
 * ESCUELA DE INGENIERIA DE SISTEMAS Y COMPUTACION.
 * PRESENTADO A: Ing Helver Valero.
 * PROGRAMACION III
 * Panel donde se conecta los usuarios
 * @author Jenny Quesada , Ruth Rojas
 */
public class WindowClient extends JFrame {
	private Controller controller;

	//------Atributtes------
	
	private Menu menu;
	
	//------Builder------
	public WindowClient(Controller controller) {
		this.controller = controller;
		init();
	}
	
	//-----Methods-----
	
	public void init(){
		initThis();
	}
	
	public void initThis(){
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle(ConstanstView.NAME_WINDOW_CLIENT);
		this.setSize(ConstanstView.WINDOW_CLIENT_W, ConstanstView.WINDOW_CLIENT_H);
		this.menu = new Menu(controller);
		this.setJMenuBar(menu);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.add(PrincipalPanel.getInstance());	
		this.setVisible(true);
		PreferencesControl.loadProperties();
	}
	
	
}
