package persistence;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;


import org.jespxml.JespXML;
import org.jespxml.excepciones.TagHijoNotFoundException;
import org.jespxml.modelo.Atributo;
import org.jespxml.modelo.Encoding;
import org.jespxml.modelo.Tag;
import org.pushingpixels.lafwidget.tabbed.TabOverviewDialog.TabGridOverviewGlassPane;
import org.xml.sax.SAXException;

import client.logic.User;


public class XmlCliente{

	private static Tag raiz;
	private static Tag tagCliente;
	private static Tag id;
	private static Tag nombre;
	private static Tag lastName;
	private static Tag nickName;
	private static Tag password;
	private static Tag email;
	
	public static void EscribirXML(ArrayList<User>  listaClientes, String ruta){
		raiz = new Tag("Chat");
		raiz.addAtributo(new Atributo("Cantidad-Clientes", Integer.toString(listaClientes.size())));
		for (User cliente : listaClientes) {
			tagCliente = new Tag("Cliente");
			id = new Tag("id");
			nombre = new Tag("nombre");
			lastName = new Tag("Apellido");
			nickName = new Tag("NickName");
			password = new Tag("contrasena");
			email = new Tag("email");
			id.addContenido(Integer.toString(cliente.getId()));
			nombre.addContenido(cliente.getName());
			lastName.addContenido(cliente.getLastName());
			nickName.addContenido(cliente.getNickName());
			password.addContenido(cliente.getPassword());
			raiz.addTagHijo(tagCliente);
			tagCliente.addTagHijo(id);
			tagCliente.addTagHijo(nombre);
			tagCliente.addTagHijo(lastName);
			tagCliente.addTagHijo(nickName);
			tagCliente.addTagHijo(password);
			tagCliente.addTagHijo(email);
			
			email.addContenido(cliente.getEmail());
			JespXML xml = new JespXML(ruta, Encoding.UTF_8);
				try {
					xml.escribirXML(raiz);
				} catch (FileNotFoundException | ParserConfigurationException | TransformerException e) {
					e.printStackTrace();
				}
		}
	}
	@SuppressWarnings("deprecation")
	public static ArrayList<User> leerXML(String ruta){
		JespXML xml = new JespXML(ruta);
		ArrayList<User> lista = new ArrayList<User>();
		
			Tag raiz = null;
			try {
				raiz = xml.leerXML();
			} catch (ParserConfigurationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SAXException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
			for(Tag cliente : raiz.getTagsHijos()){
				Tag nombre;
				try {
					nombre = cliente.getTagHijoByName("nombre");
					Tag descripcion = cliente.getTagHijoByName("lastName");
					Tag valor = cliente.getTagHijoByName("nickName");
					Tag imagen = cliente.getTagHijoByName("password");
					Tag email = cliente.getTagHijoByName("email");
					User c = new User(nombre.getContenido(),lastName.getContenido(),nickName.getContenido(),password.getContenido(),email.getContenido());
					lista.add(c);
				} catch (TagHijoNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}

		return lista;
	}
}

