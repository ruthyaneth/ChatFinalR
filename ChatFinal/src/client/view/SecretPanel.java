package client.view;

import javax.swing.*;
import javax.swing.border.TitledBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
/**
 * UNIVERSIDAD PEDAGOGICA Y TECNOLOGICA DE COLOMBIA
 * FACULTAD DE INGENIERIA.
 * ESCUELA DE INGENIERIA DE SISTEMAS Y COMPUTACION.
 * PRESENTADO A: Ing Helver Valero.
 * PROGRAMACION III
 * Panel 
 * @author Jenny Quesada , Ruth Rojas
 */
public class SecretPanel extends JPanel {
	
	//------Atributtes------
	
	private StyleTextArea textArea;
	private String who;
	private UserInputPanel userInput;
	
	//------Builder----
	
	public SecretPanel(String who) {
		this.who = who;
		this.textArea = new StyleTextArea();
		this.setLayout(new BorderLayout());
		this.add(textArea, BorderLayout.CENTER);
		userInput = new UserInputPanel();
		userInput.setWhisper(who);
		this.add(userInput, BorderLayout.SOUTH);
	}

	//------Methods-----
	
	public void appendMessage(String msg) {
		this.textArea.append(msg, 1);
	}
}
