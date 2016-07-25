package control;

import java.io.BufferedReader;
import java.net.Socket;
import java.util.StringTokenizer;

import client.view.PrincipalPanel;
import client.view.RoomPanel;

//public class ClientThread  extends Thread{
//
//	//------Atributtes------
//	private boolean clientRunning = false;
//	private ChatSocketControl socketControl;
//	
//	//------Builder------
//	
//		public void run() {
//			String msg;
//			while (clientRunning) {
//				try {
//					msg = socketControl.getClientReader().readLine();
//					if (msg == null || msg.length() == 0)
//						continue;
//					
//					StringTokenizer st = new StringTokenizer(msg, "@");
//					msg = st.nextToken();
//					if (msg.equals("Cerrar")) {
//						HomePage.getInstance().chatMessageAction("S", "----- Servidor Desconecto  -----");
//						HomePage.getInstance().closeSocketAction();
//						socketControl.closeSocket();
//					} else if (msg.equals("S")) {
//						msg = "Usuario> " + st.nextToken() + "\r\n";
//						HomePage.getInstance().chatMessageAction("S", msg);
//					} else if (msg.equals("M")) {
//						msg = st.nextToken();
//						while (st.hasMoreTokens())
//							msg += "@" + st.nextToken();
//						HomePage.getInstance().chatMessageAction("M", msg + "\r\n");
//					} else if (msg.equals("MS")) {
//						String who = st.nextToken();
//						msg = st.nextToken();
//						while (st.hasMoreTokens())
//							msg += "@" + st.nextToken();
//						HomePage.getInstance().chatMessageAction("MS@" + who, msg
//								+ "\r\n");
//					} else if (msg.equals("MH")) {
//						msg = st.nextToken();
//						while (st.hasMoreTokens())
//							msg += "@" + st.nextToken();
//						HomePage.getInstance().chatMessageAction("MH", msg);
//					} else if (msg.equals("A")) {
//						RoomPanel.getInstance().getUserListModel().addElement(st.nextToken());
//					} else if (msg.equals("D")) {
//						RoomPanel.getInstance().getUserListModel().removeElement(st.nextToken());
//					} else if (msg.equals("E")) {
//						msg = " Usuario > " + st.nextToken() + "\r\n";
//						HomePage.getInstance().chatMessageAction("E", msg);
//						HomePage.getInstance().closeSocketAction();
//						socketControl.closeSocket();
//					} else if (msg.equals("GLS")) {
//						int port = Integer.parseInt(st.nextToken());
//						String ip = st.nextToken();
//					}
//				} catch (Exception e) {
//					e.getStackTrace();
//					socketControl.showServerErr("Desconecto Servidor" + e.getMessage());
//				}
//			}
//		}
//	}
//	

