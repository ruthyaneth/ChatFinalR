package control;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.StringTokenizer;
import java.util.Timer;
import java.util.TimerTask;
import socket.*;

/**
 * UNIVERSIDAD PEDAGOGICA Y TECNOLOGICA DE COLOMBIA
 * FACULTAD DE INGENIERIA.
 * ESCUELA DE INGENIERIA DE SISTEMAS Y COMPUTACION.
 * PRESENTADO A: Ing Helver Valero.
 * PROGRAMACION III
 * Clase donde se controla el servidor
 * @author Jenny Quesada , Ruth Rojas
 */

public class ServerControl {

	//-------Atributtes------

	private static ServerControl singleton = null;
	private boolean serverRunning = false;
	private ServerSocket serverSocket;
	private ServerThread serverThread;
	private ClientThread clientThread;
	private int serverPort;

	//------Builder------
	
	public ServerControl() {
		while (!openServer());
	}

	//------Methods-----

	public static ServerControl getInstance() {
		if (singleton == null)
			singleton = new ServerControl();
		return singleton;
	}

	
	private boolean openServer() {
		if (serverRunning)
			return false;
		try {
			int port;
			port = (int) (Math.random() * 60000) + 1023;
			serverSocket = new ServerSocket(port);
			serverThread = new ServerThread(serverSocket);
			serverThread.start();
			serverRunning = true;
			serverPort = port;
			return true;
		} catch (java.net.BindException be) {
			System.out.println(be.getMessage());
		} catch (Exception ee) {
			System.out.println(ee.getMessage());
		}
		return false;
	}

	public boolean closeServer() {
		try {
			gameServerCmdTalk("CLOSE");
			serverThread.stop();
			serverSocket.close();
			serverRunning = false;
			return true;
		} catch (Exception ee) {
			System.out.print(ee.getMessage());
		}
		return false;
	}

	public void gameServerCmdTalk(String str) {
		if (clientThread != null) {
			clientThread.getWriter().println(str);
			clientThread.getWriter().flush();
		}
	}

	public int getGameServerPort() {
		return this.serverPort;
	}


	class ServerThread extends Thread {
		private ServerSocket serverSocket;

		public ServerThread(ServerSocket serverSocket) {
			this.serverSocket = serverSocket;
		}

		public void run() {
			Socket socket;
			BufferedReader reader;
			PrintWriter writer;
			User user;
			while (true) {
				try {
					socket = serverSocket.accept();
					reader = new BufferedReader(new InputStreamReader(
							socket.getInputStream()));
					writer = new PrintWriter(socket.getOutputStream());
					String userName = reader.readLine();
					user = new User(userName, socket.getInetAddress()
							.getHostAddress().toString());
					clientThread = new ClientThread(socket, user);
					clientThread.start();
				} catch (Exception ee) {

				}
			}
		}
	}

	class ClientThread extends Thread {
		private Socket gameSocket;
		private User user;
		private BufferedReader reader;
		private PrintWriter writer;

		public ClientThread(Socket socket, User user) {
			try {
				this.gameSocket = socket;
				this.user = user;
				this.reader = new BufferedReader(new InputStreamReader(
						gameSocket.getInputStream(), "UTF-8"));
				this.writer = new PrintWriter(new OutputStreamWriter(
						gameSocket.getOutputStream(), "UTF-8"));
				writer.println("GOK");
				writer.flush();
				writer.flush();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		public void closeGameSocket() {
			try {

				gameSocket.close();
				this.stop();
			} catch (Exception e) {
			}
		}

		public void run() {
			String msg;
			while (true) {
				try {
					msg = reader.readLine();
				} catch (Exception e) {

				}
			}
		}
		public BufferedReader getReader() {
			return reader;
		}

		public PrintWriter getWriter() {
			return writer;
		}
	}
}

