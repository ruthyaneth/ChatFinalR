package client.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import constant.ConstanstView;

/**
 * UNIVERSIDAD PEDAGOGICA Y TECNOLOGICA DE COLOMBIA
 * FACULTAD DE INGENIERIA
 * ESCUELA DE INGENIERIA DE SISTEMAS Y COMPUTACION 
 * PRESENTADO A : ING HELVER VALERO.
 * PROGRAMACION III-
 * Clase donde  se inicializa  el componenete grafico
 * @author Jenny Quesada , Ruth Rojas
 * 																																																						
 */
public class PanelSplash extends JPanel{
	
	//------Atributtes-----
	
	//-----Builder------
	public PanelSplash() {
		this.setBackground(Color.white);
	}
	//-----Methods------
	@Override
	public void paintComponent(Graphics graphics){
		Dimension dimension = getSize();
		ImageIcon  imageBackground = new ImageIcon(getClass().getResource(ConstanstView.DEFAULT_IMAGE_LOCATION));
		graphics.drawImage(imageBackground.getImage(),0,0,dimension.width, dimension.height, null);
		setOpaque(false);
		super.paintComponent(graphics);
	}

}
