package client.logic;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.swing.ImageIcon;

import persistence.Texto;

//import persistencia.Texto;
//import modelo.entidades.Autor;
//import modelo.entidades.Cliente;
//import modelo.entidades.Libro;

public class Util {

	public static boolean validarValor(String numero){
		try {
			Double.parseDouble(numero);	
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	public static boolean validarCopias(String numero){
		try {
			Double.parseDouble(numero);	
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	

	public static String[] clienteAVector(User cliente){
		return new String[]{Integer.toString(cliente.getId()),cliente.getName(), cliente.getLastName(),
				cliente.getNickName(), cliente.getPassword(), cliente.getEmail()};
	}
	
	
	
	public static ImageIcon createImageIcon(String path) {
		java.net.URL imgURL = Util.class.getResource(path);
		if (imgURL != null) {
			return new ImageIcon(imgURL);
		} else {
			return null;
		}
	}
	
	public static void guardarImagen(String name , String direccionEntrada){
		Path RutaImagenEntrada = Paths.get(direccionEntrada);
		Path RutaImagenNueva = Paths.get("src/img/imgLibros/"+name+".jpg");
		try {
			Files.copy(RutaImagenEntrada, RutaImagenNueva);
		} catch (IOException e) {
			System.out.println("error al guardar la imagen");
		}
	}
	
	public static int asignarId(String dir){
		Texto.CrearArchivoTexto(dir);
		Texto.abrir('r');
		int numero = 0;
		numero = cantidadDatos() + 1;
		Texto.cerrar();
		Texto.abrir('t');
		Texto.grabar(Integer.toString(numero));
		Texto.cerrar();
		return numero;
	}

	public static int cantidadDatos(){
		int cont = 0;
		while (Texto.leer() != null) {
			cont++;
		}
		return cont;
	}
}
