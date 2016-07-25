package control;

import java.awt.*;
import javax.swing.*;

import client.view.RoomPanel;

import java.io.*;
import javax.imageio.ImageIO;

import java.util.*;
/**
 * UNIVERSIDAD PEDAGOGICA Y TECNOLOGICA DE COLOMBIA
 * FACULTAD DE INGENIERIA.
 * ESCUELA DE INGENIERIA DE SISTEMAS Y COMPUTACION.
 * PRESENTADO A: Ing Helver Valero.
 * PROGRAMACION III
 * Control de la ventan principal
 * @author Jenny Quesada , Ruth Rojas
 */
public class WindowControl {
	
	//-----Atributtes----
	
	private static WindowControl singleton = null;
	private Hashtable<String, ImageIcon> imageTable;

	//-----Methods------
	
	private WindowControl() {
		init();
	}
	
	//-----Methods-----
	
	public void init(){
		
		imageTable = new Hashtable<String, ImageIcon>();
		String fileIdx[] = { "HTML", "IMG"};
		String fileName[] = { "HTML.png", "IMG.png" };
		Image img;
		try {
			for (int i = 0; i < fileName.length; i++) {
				img = ImageIO.read(WindowControl.class.getResource("images/"
						+ fileName[i]));
				imageTable.put(fileIdx[i], new ImageIcon(img));
			}
		} catch (Exception e) {
			System.out.println("Error !!!!!! " + e.getMessage());
		}
	}
	
	public static WindowControl getInstance() {
		if (singleton == null)
			singleton = new WindowControl();
		return singleton;
	}


	public ImageIcon getImageIcon(String key) {
		return imageTable.get(key);
	}


	public static void setChatFontFamily(String fontName) {
		RoomPanel.getInstance().getTextArea().setTextFontFamily(fontName);
	}

	public static void setChatFontSize(int fontSize) {
		RoomPanel.getInstance().getTextArea().setTextFontSize(fontSize);
	}
}
