package server;

import javax.swing.JFrame;

import constant.ConstanstView;


/**
 * UNIVERSIDAD PEDAGOGICA Y TECNOLOGICA DE COLOMBIA
 * FACULTAD DE INGENIERIA.
 * ESCUELA DE INGENIERIA DE SISTEMAS Y COMPUTACION.
 * PRESENTADO A: Ing Helver Valero.
 * PROGRAMACION III
 * Ventana del interfaz del servidor donde va ahacer la conexion para esperar los usuarios 
 * @author Jenny Quesada , Ruth Rojas
 */
public class WindowServer  extends JFrame {

	//------Atributtes------


	//-----Builder------
	public WindowServer() {
		init();
	}
	
	//------Methods ------

	public void init(){
		this.setTitle(ConstanstView.DEFAULT_NAME_WINDOW);
		this.setSize(ConstanstView.DEFAULT_SIZE_W,ConstanstView.DEFAULT_SIZE_H);
		this.setLocationRelativeTo(null);
		this.add(new ServerPanel());
		setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	public static void main(String[] args) {

		new WindowServer();

	}
	//------Getss&&Setts------

}
