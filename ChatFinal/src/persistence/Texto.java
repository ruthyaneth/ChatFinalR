package persistence;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Texto {
	static File f;
	static FileWriter fw;
	static FileReader fr;
	static BufferedWriter bw = null;
	static BufferedReader br = null;

	public static void CrearArchivoTexto(String direccion) {
		f = new File(direccion);
	}

	public static void abrir(char modo)	{
		try {
			if (modo=='w'){
				fw = new FileWriter(f);
				bw = new BufferedWriter(fw);
			}else if (modo=='t'){
				fw = new FileWriter(f , true);
				bw = new BufferedWriter(fw);
			}if (modo == 'r') {
				fr = new FileReader(f);
				br = new BufferedReader(fr);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}

	public static void grabar(String cad){
		if (bw!=null){
			try {
				bw.write(cad);
				bw.newLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static String leer(){		  
		String cad="";	
		try {
			cad= br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return cad;		   
	}
	
	public static void cerrar(){
		try {
			if (br!=null)
				br.close();
			if (bw!=null)
				bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

