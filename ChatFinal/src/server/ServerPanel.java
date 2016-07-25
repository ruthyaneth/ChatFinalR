package server;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.border.TitledBorder;

/**
 * UNIVERSIDAD PEDAGOGICA Y TECNOLOGICA DE COLOMBIA
 * FACULTAD DE INGENIERIA.
 * ESCUELA DE INGENIERIA DE SISTEMAS Y COMPUTACION.
 * PRESENTADO A: Ing Helver Valero.
 * PROGRAMACION III
 * Panel donde iran los componentes del la ventana
 * @author Jenny Quesada , Ruth Rojas
 */
public class ServerPanel extends JPanel {
	
	//------Atributtes------
	
	private JTextField inputPort;
	private JToggleButton inputSwitch;
	private JPanel northPanel;

	private JTextArea contentArea;
	private JList userList;
	private DefaultListModel userListModel;
	private JPanel centerPanel;

	private JTextField inputMsg;
	private JButton sendBtn;
	private JPanel southPanel;

	private boolean serverRunning;
	private ServerSocket serverSocket;
	private ServerThread serverThread;
	private ArrayList<ClientThread> clientList;
	
	public ServerPanel() {
		this.serverRunning = false;
		createNorthPanel();
		createCenterPanel();
		createSouthPanel();
		this.setLayout(new BorderLayout());
		this.add(northPanel, BorderLayout.NORTH);
		this.add(centerPanel, BorderLayout.CENTER);
		this.add(southPanel, BorderLayout.SOUTH);
	}
	
	private void createNorthPanel() {
		Font displayFont = new Font("Tahoma", Font.BOLD, 18);
		northPanel = new JPanel();
		northPanel.setBorder(new TitledBorder("Conectar"));
		northPanel.setLayout(new GridLayout(1, 3));


		inputPort = new JTextField();
		inputSwitch = new JToggleButton("Conectar");

		
		inputPort.setFont(displayFont);
		inputSwitch.setFont(displayFont);
		inputSwitch.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					if (openServer()) {
						inputSwitch.setText("Desconectar");
						inputPort.setEnabled(false);
						
					} else if (!serverRunning) {
						inputSwitch.setSelected(false);
					}
				} else if (e.getStateChange() == ItemEvent.DESELECTED) {
					if (closeServer()) {
						inputSwitch.setText("Conectar");
						inputPort.setEnabled(true);
						
					} else if (serverRunning) {
						inputSwitch.setSelected(true);
					}
				}
			}
		});

		JLabel showlabel;
		showlabel = new JLabel("Numero Puerto");
		showlabel.setFont(displayFont);
		northPanel.add(showlabel);
		northPanel.add(inputPort);
		northPanel.add(inputSwitch);
	}

	private void createCenterPanel() {
		Font displayFont = new Font("Tahoma", Font.BOLD, 18);
		Font displayFontII = new Font("Tahoma", Font.BOLD, 24);
		centerPanel = new JPanel();
		userListModel = new DefaultListModel();

		userList = new JList(userListModel);
		contentArea = new JTextArea();
		
		userList.setForeground(Color.decode("#9400D3"));
		userList.setFont(displayFont);
		contentArea.setForeground(Color.decode("#00BFFF"));
		contentArea.setFont(displayFontII);

		JScrollPane leftPane = new JScrollPane(userList);
		JScrollPane rightPane = new JScrollPane(contentArea);
		JSplitPane splitPane1 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
				leftPane,null);
		splitPane1.setDividerLocation(100);
		JSplitPane splitPane2 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
				splitPane1, rightPane);
		splitPane2.setDividerLocation(250);

		leftPane.setBorder(new TitledBorder("Lista De Usuarios"));
		rightPane.setBorder(new TitledBorder("Sala De Chat"));

		rightPane.getVerticalScrollBar().addAdjustmentListener(
				new AdjustmentListener() {
					boolean locked = true;
					JScrollBar sb;

					private AdjustmentListener init(JScrollBar sb) {
						this.sb = sb;
						return this;
					}

					public void adjustmentValueChanged(AdjustmentEvent e) {
						if (sb.getMaximum() - sb.getValue() - sb.getHeight() > 200)
							locked = false;
						else
							locked = true;
						if (locked == true) {
							sb.setValue(sb.getMaximum());
						}
					}
				}.init(rightPane.getVerticalScrollBar()));
		centerPanel.setLayout(new BorderLayout());
		centerPanel.add(splitPane2, BorderLayout.CENTER);
	}

	private void createSouthPanel() {
		Font displayFont = new Font("Tahoma", Font.BOLD, 18);
		Font displayFontII = new Font("Tahoma", Font.BOLD, 24);
		southPanel = new JPanel();

		inputMsg = new JTextField();
		sendBtn = new JButton("Enviar");

		inputMsg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				serverBroadcasting();
			}
		});
		sendBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				serverBroadcasting();
			}
		});

		southPanel.setLayout(new BorderLayout());
		southPanel.add(inputMsg, BorderLayout.CENTER);
		southPanel.add(sendBtn, BorderLayout.EAST);
	}

	private boolean openServer() {
		if (serverRunning)
			return false;
		try {
			int port, limitcap;
			port = Integer.parseInt(this.inputPort.getText());
			serverSocket = new ServerSocket(port);
			serverThread = new ServerThread(serverSocket);
			serverThread.start();
			clientList = new ArrayList<ClientThread>();
			serverRunning = true;
			return true;
		} catch (java.net.BindException be) {
			showServerErr(be.getMessage());
		} catch (Exception ee) {
			showServerErr(ee.getMessage());
		}
		return false;
	}

	private boolean closeServer() {
		try {
		
			serverCmdBroadcasting("Desconecto" + "\r\n");
			serverThread.stop();
			serverSocket.close();
			serverRunning = false;
			userListModel.clear();

			for (int i = 0; i < clientList.size(); i++)
				clientList.get(i).stop();
			clientList.clear();
			return true;
		} catch (Exception ee) {
			showServerErr(ee.getMessage());
		}
		return false;
	}

	private void showServerErr(String err) {
		contentArea.append("Error Servidor [ " + err + " ]\r\n");
	}

	public  void serverCmdBroadcasting(String str) {
		for (int i = clientList.size() - 1; i >= 0; i--) {
			clientList.get(i).getWriter().println(str);
			clientList.get(i).getWriter().flush();
		}
		contentArea.append("Accion Servidor > " + str);
		inputMsg.setText("");
	}

	public void serverCmdSpecialLine(String name, String str) {
		for (int i = clientList.size() - 1; i >= 0; i--) {
			if (clientList.get(i).user.getNameUser().equals(name)) {
				clientList.get(i).getWriter().println(str);
				clientList.get(i).getWriter().flush();
				return;
			}
		}
	}

	public void serverBroadcasting() {
		if (clientList != null) {
			for (int i = clientList.size() - 1; i >= 0; i--) {
				clientList.get(i).getWriter()
						.println("S@" + inputMsg.getText() + "\r\n");
				clientList.get(i).getWriter().flush();
			}
		}
		contentArea.append("Usuario > " + inputMsg.getText() + "\r\n");
		inputMsg.setText("");
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
			ClientThread clientThread;
			UserServer user;
			while (true) {
				try {
					socket = serverSocket.accept();
					reader = new BufferedReader(new InputStreamReader(
							socket.getInputStream()));
					writer = new PrintWriter(socket.getOutputStream());
					String msg = reader.readLine();
					StringTokenizer st = new StringTokenizer(msg, "@");
					String userName = st.nextToken();
					int gameServerPort = Integer.parseInt(st.nextToken());
					boolean sameNameFlag = false;
					for (int i = 0; i < userListModel.size(); i++) {
						if (userListModel.get(i).toString().equals(userName)) {
							sameNameFlag = true;
						}
					}
					if (sameNameFlag) {
						writer.println("E@Same Name User");
						writer.flush();
						socket.close();
						continue;
					}
					userListModel.addElement(userName);
					user = new UserServer(userName, socket.getInetAddress()
							.getHostAddress().toString());
					clientThread = new ClientThread(socket, user,gameServerPort);
					clientThread.start();
					clientList.add(clientThread);
					inputMsg.setText(userName + " Ha entrado a la sala.");
					serverBroadcasting();
					serverCmdBroadcasting("A@" + user.getNameUser() + "\r\n");
				} catch (Exception ee) {
					System.out.println("ServerThread Err " + ee.getMessage());
				}
			}
		}
	}

	public class ClientThread extends Thread {
		private Socket socket;
		UserServer user;
		private BufferedReader reader;
		private PrintWriter writer;
		private int gameServerPort;

		public ClientThread(Socket socket, UserServer user, int gameServerPort) {
			try {
				this.socket = socket;
				this.user = user;
				this.gameServerPort = gameServerPort;
				this.reader = new BufferedReader(new InputStreamReader(
						socket.getInputStream(), "UTF-8"));
				this.writer = new PrintWriter(new OutputStreamWriter(
						socket.getOutputStream(), "UTF-8"));
				for (int i = clientList.size() - 1; i >= 0; i--)
					writer.println("A@" + clientList.get(i).user.getNameUser());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		public void closeSocket() {
			try {
				String msg = user.getNameUser() + " Ha salido de la sala  .\r\n";
				inputMsg.setText(msg);
				serverBroadcasting();
				serverCmdBroadcasting("D@" + user.getNameUser());

				userListModel.removeElement(user.getNameUser());
				for (int i = 0; i < clientList.size(); i++) {
					if (clientList.get(i).user.getNameUser().equals(
							user.getNameUser())) {
						clientList.remove(i);
						break;
					}

				}
				socket.close();
				this.stop();
			} catch (Exception e) {
			}
		}

		public void run() {
			String msg;
			while (true) {
				try {
					msg = reader.readLine();
					dispatcherMessage(msg);
				} catch (Exception e) {

				}
			}
		}

		public void dispatcherMessage(String msg) {
			if (msg == null || msg.length() == 0)
				return;
			StringTokenizer st = new StringTokenizer(msg, "@");
			msg = st.nextToken();
			if (msg.equals("T")) {
				String sentence = st.nextToken();
				while (st.hasMoreTokens()) {
					sentence += "@" + st.nextToken();
				}
				sentence = this.user.getNameUser() + "> " + sentence + "\r\n";
				contentArea.append(sentence);
				serverCmdBroadcasting("M@" + sentence);
			} else if (msg.equals("TS")) {
				String to = st.nextToken();
				String sentence = st.nextToken();
				while (st.hasMoreTokens()) {
					sentence += "@" + st.nextToken();
				}
				
				serverCmdSpecialLine(to, "MS@" + this.user.getNameUser() + "@ >> " + sentence + "\r\n");
				serverCmdSpecialLine(this.user.getNameUser(), "MS@" + to + "@ << " + sentence + "\r\n");
			} else if (msg.equals("TH")) {
				String sentence = st.nextToken();
				while (st.hasMoreTokens())
					sentence += "@" + st.nextToken();
			
				serverCmdBroadcasting("MH@" + sentence);
			}else if (msg.equals("GR")) {
			} else if (msg.equals("GE")) {
			} else if (msg.equals("CLOSE")) {
				closeSocket();
			}
		}

		public BufferedReader getReader() {
			return reader;
		}

		public PrintWriter getWriter() {
			return writer;
		}
	}

	public JTextField getInputPort() {
		return inputPort;
	}

	public void setInputPort(JTextField inputPort) {
		this.inputPort = inputPort;
	}

	public JToggleButton getInputSwitch() {
		return inputSwitch;
	}

	public void setInputSwitch(JToggleButton inputSwitch) {
		this.inputSwitch = inputSwitch;
	}

	public JPanel getNorthPanel() {
		return northPanel;
	}

	public void setNorthPanel(JPanel northPanel) {
		this.northPanel = northPanel;
	}

	public JTextArea getContentArea() {
		return contentArea;
	}

	public void setContentArea(JTextArea contentArea) {
		this.contentArea = contentArea;
	}

	public JList getUserList() {
		return userList;
	}

	public void setUserList(JList userList) {
		this.userList = userList;
	}

	public DefaultListModel getUserListModel() {
		return userListModel;
	}

	public void setUserListModel(DefaultListModel userListModel) {
		this.userListModel = userListModel;
	}

	public JPanel getCenterPanel() {
		return centerPanel;
	}

	public void setCenterPanel(JPanel centerPanel) {
		this.centerPanel = centerPanel;
	}

	public JTextField getInputMsg() {
		return inputMsg;
	}

	public void setInputMsg(JTextField inputMsg) {
		this.inputMsg = inputMsg;
	}

	public JButton getSendBtn() {
		return sendBtn;
	}

	public void setSendBtn(JButton sendBtn) {
		this.sendBtn = sendBtn;
	}

	public JPanel getSouthPanel() {
		return southPanel;
	}

	public void setSouthPanel(JPanel southPanel) {
		this.southPanel = southPanel;
	}

	public boolean isServerRunning() {
		return serverRunning;
	}

	public void setServerRunning(boolean serverRunning) {
		this.serverRunning = serverRunning;
	}

	public ServerSocket getServerSocket() {
		return serverSocket;
	}

	public void setServerSocket(ServerSocket serverSocket) {
		this.serverSocket = serverSocket;
	}

	public ServerThread getServerThread() {
		return serverThread;
	}

	public void setServerThread(ServerThread serverThread) {
		this.serverThread = serverThread;
	}

	public ArrayList<ClientThread> getClientList() {
		return clientList;
	}

	public void setClientList(ArrayList<ClientThread> clientList) {
		this.clientList = clientList;
	}
	
	
	
}
