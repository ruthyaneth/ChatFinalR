package control;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.StringTokenizer;

import client.view.PrincipalPanel;
import client.view.RoomPanel;
/**
 * UNIVERSIDAD PEDAGOGICA Y TECNOLOGICA DE COLOMBIA
 * FACULTAD DE INGENIERIA.
 * ESCUELA DE INGENIERIA DE SISTEMAS Y COMPUTACION.
 * PRESENTADO A: Ing Helver Valero.
 * PROGRAMACION III
 * Clase donde se controla  el socket del  chat
 * @author Jenny Quesada , Ruth Rojas
 */

public class ChatSocketControl  extends Thread{
	
	//------Atributtes------
	
	private static ChatSocketControl singleton;
	private boolean clientRunning ;
	private Socket clientSocket;
	private PrintWriter clientWriter;
	private BufferedReader clientReader;
	private ClientThread clientThread;
	
	//------Builder------
	
	public ChatSocketControl() {
		
		this.singleton = null;
		this.clientRunning = false;
		this.clientThread = new ClientThread();
	}


	public boolean openSocket(int port, String host, String name) {
		if (clientRunning)
			return false;
		try {
			if (name.length() == 0)
				return false;
			clientSocket = new Socket(host, port);
			clientWriter = new PrintWriter(new OutputStreamWriter(
					clientSocket.getOutputStream(), "UTF-8"));
			clientReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream(), "UTF-8"));
			clientCmdTalk(name + "@" + ServerControl.getInstance().getGameServerPort());
			clientThread = new ClientThread();
			clientThread.start();
			clientRunning = true;
			PrincipalPanel.getInstance().openSocketAction();
			return true;
		} catch (Exception ee) {
			showServerErr(ee.getMessage());
		}
		try {
			PrincipalPanel.getInstance().closeSocketAction();
		} catch (Exception ee) {
			showServerErr(ee.getMessage());
		}
		return false;
	}

	public static ChatSocketControl getInstance() {
		if(singleton == null)
			singleton = new ChatSocketControl();
		return singleton;
	}
	public boolean closeSocket() {
		if (!clientRunning)
			return true;
		try {
			clientCmdTalk("Cerrar");
			clientRunning = false;
			clientSocket.close();
			clientThread.stop();
			RoomPanel.getInstance().getUserListModel().clear();
			PrincipalPanel.getInstance().chatMessageAction("S", "Ha ingresada A la sala\r\n");
			PrincipalPanel.getInstance().closeSocketAction();
			return true;
		} catch (Exception ee) {
			showServerErr(ee.getMessage());
		}
		try {
			PrincipalPanel.getInstance().openSocketAction();
		} catch (Exception ee) {
			showServerErr(ee.getMessage());
		}
		return false;
	}

	public void showServerErr(String err) {
		PrincipalPanel.getInstance().chatMessageAction("S", "Error[" + err + "]\r\n");
	}

	public void clientQueryGame(String player) {
		if (player.length() == 0)
			clientCmdTalk("GR");
	}

	public void clientExitGame() {
		clientCmdTalk("GE");
	}

	public void clientCmdTalk(String str) {
		if (clientWriter != null) {
			clientWriter.println(str);
			clientWriter.flush();
		}
	}

	public void clientTalk(String str, String towho) {
		if (clientWriter != null) {
			if (towho == null || towho.length() == 0)
				clientWriter.println("T@" + str);
			else
				clientWriter.println("TS@" + towho + "@" + str);
			clientWriter.flush();
		}
	}

	public BufferedReader getClientReader() {
		return clientReader;
	}

	public void setClientReader(BufferedReader clientReader) {
		this.clientReader = clientReader;
	}
	
	class ClientThread  extends Thread{

		
			public void run() {
				String msg;
				while (clientRunning) {
					try {
						msg = clientReader.readLine();
						if (msg == null || msg.length() == 0)
							continue;
						
						StringTokenizer st = new StringTokenizer(msg, "@");
						msg = st.nextToken();
						if (msg.equals("Cerrar")) {
							PrincipalPanel.getInstance().chatMessageAction("S", "----- Servidor Desconecto  -----");
							PrincipalPanel.getInstance().closeSocketAction();
							closeSocket();
						} else if (msg.equals("S")) {
							msg = "Usuario> " + st.nextToken() + "\r\n";
							PrincipalPanel.getInstance().chatMessageAction("S", msg);
						} else if (msg.equals("M")) {
							msg = st.nextToken();
							while (st.hasMoreTokens())
								msg += "@" + st.nextToken();
							PrincipalPanel.getInstance().chatMessageAction("M", msg + "\r\n");
						} else if (msg.equals("MS")) {
							String who = st.nextToken();
							msg = st.nextToken();
							while (st.hasMoreTokens())
								msg += "@" + st.nextToken();
							PrincipalPanel.getInstance().chatMessageAction("MS@" + who, msg
									+ "\r\n");
						} else if (msg.equals("MH")) {
							msg = st.nextToken();
							while (st.hasMoreTokens())
								msg += "@" + st.nextToken();
							PrincipalPanel.getInstance().chatMessageAction("MH", msg);
						} else if (msg.equals("A")) {
							RoomPanel.getInstance().getUserListModel().addElement(st.nextToken());
						} else if (msg.equals("D")) {
							RoomPanel.getInstance().getUserListModel().removeElement(st.nextToken());
						} else if (msg.equals("E")) {
							msg = " Usuario > " + st.nextToken() + "\r\n";
							PrincipalPanel.getInstance().chatMessageAction("E", msg);
							PrincipalPanel.getInstance().closeSocketAction();
							closeSocket();
						} else if (msg.equals("GLS")) {
							int port = Integer.parseInt(st.nextToken());
							String ip = st.nextToken();
						}
					} catch (Exception e) {
						e.getStackTrace();
						showServerErr("Desconecto Servidor" + e.getMessage());
					}
				}
			}
		}
		
}
