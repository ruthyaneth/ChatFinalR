package client.view;

import java.awt.*;
import javax.swing.*;

import constant.ConstanstView;
import control.ChatSocketControl;
import java.util.*;
/**
 * UNIVERSIDAD PEDAGOGICA Y TECNOLOGICA DE COLOMBIA
 * FACULTAD DE INGENIERIA.
 * ESCUELA DE INGENIERIA DE SISTEMAS Y COMPUTACION.
 * PRESENTADO A: Ing Helver Valero.
 * PROGRAMACION III
 * Panel principal donde se vera la conversacion que se tiene con  los usuarios
 * @author Jenny Quesada , Ruth Rojas
 */
public class PrincipalPanel extends JPanel {
	
	//------Atributtes------
	
	private static PrincipalPanel singleton = null;
	private UserInputPanel userInput;
	private JTabbedPane tabbedPane;
	private JPanel chatPanel;
	
	//------Builder------
	

	public PrincipalPanel() {
		init();
	}
	
	//------Methods-------
	
	public void init(){
		initComponent();
	}
	
	public void initComponent(){
		this.userInput = new UserInputPanel();
		this.tabbedPane = new JTabbedPane();
		this.setLayout(new BorderLayout());
		this.chatPanel = new JPanel();
		this.chatPanel.setLayout(new BorderLayout());
		this.chatPanel.add(LoginPanel.getInstance(), BorderLayout.NORTH);
		this.chatPanel.add(RoomPanel.getInstance(), BorderLayout.CENTER);
		this.chatPanel.add(userInput, BorderLayout.SOUTH);
		this.tabbedPane.addTab(ConstanstView.NAME_TABPANE, chatPanel);
		this.add(tabbedPane, BorderLayout.CENTER);	
	}
	
	public void openSocketAction() {
		LoginPanel.getInstance().lock();
	}

	public void closeSocketAction() {
		LoginPanel.getInstance().unlock();
		RoomPanel.getInstance().getUserListModel().clear();
	}

	public void clientQueryGame(String player) {
		ChatSocketControl.getInstance().clientQueryGame(player);
		tabbedPane.setSelectedIndex(1);
	}
	
	public static PrincipalPanel getInstance() {
		if (singleton == null)
			singleton = new PrincipalPanel();
		return singleton;
	}

	public void chatMessageAction(String type, String msg) {
		if (type.equals("E") || type.equals("S")) {
			RoomPanel.getInstance().getTextArea().append(msg, 2);
		} else if (type.equals("M")) {
			RoomPanel.getInstance().getTextArea().append(msg, 0);
		} else if (type.equals("MH")) {
			RoomPanel.getInstance().getTextArea().appendHTML(msg);
		} else {
			StringTokenizer st = new StringTokenizer(type, "@");
			st.nextToken();
			String label = st.nextToken();
			RoomPanel.getInstance().getTextArea().append(label + msg, 1);
			for (int i = 2; i < this.tabbedPane.getTabCount(); i++) {
				if (this.tabbedPane.getTitleAt(i).equals(label)) {
					Component c = this.tabbedPane.getComponentAt(i);
					if (c instanceof SecretPanel) {
						((SecretPanel) c).appendMessage(label + msg);
					}
					return;
				}
			}
			SwingUtilities.invokeLater(new Runnable() {
				private String label, msg;

				public Runnable init(String label, String msg) {
					this.label = label;
					this.msg = msg;
					return this;
				}

				public void run() {
					SecretPanel sp = new SecretPanel(label);
					sp.appendMessage(msg);
					tabbedPane.addTab(label, sp);
					tabbedPane.setTabComponentAt(tabbedPane.getTabCount() - 1,null);

				}
			}.init(label, label + msg));

		}
	}

}
