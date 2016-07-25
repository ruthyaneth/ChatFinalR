package client.view;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.border.*;

import client.preferences.*;
import constant.ConstanstView;
import control.ChatSocketControl;
import control.WindowControl;

import java.util.*;
public class UserInputPanel extends JPanel {
	private JComboBox inputOption;
	private JTextField inputMsg;
	private JButton sendBtn;
	private JButton emotionBtn;
	private JPanel emotionPanel;
	private JDialog emotionDialog;
	public UserInputPanel() {
		Font displayFont = new Font("Serif", Font.BOLD, 18);
		String option[] = { "Publico", "Privado" };

		inputOption = new JComboBox(option);
		inputMsg = new JTextField();
		sendBtn = new JButton("Enviar");
		inputOption.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					if (e.getItem().toString().equals("Privado")) {
						SwingUtilities.invokeLater(new Runnable() {
							public void run() {
								if (inputOption.getItemCount() >= 5)
									inputOption.removeItemAt(4);
								DefaultListModel userListModel = RoomPanel
										.getInstance().getUserListModel();
								String choices[] = new String[userListModel
										.getSize()];
								if (choices.length == 0) {
									inputOption.setSelectedIndex(0);
									return;
								}
								for (int i = 0; i < userListModel.size(); i++)
									choices[i] = userListModel.get(i)
											.toString();
								String input = (String) JOptionPane
										.showInputDialog(null, "Chat Privado",
												"Usuarios",
												JOptionPane.QUESTION_MESSAGE,
												null, choices, choices[0]);
								if (input == null) {
									inputOption.setSelectedIndex(0);
									return;
								}
								inputOption.insertItemAt(input,
										inputOption.getItemCount());
								inputOption.setSelectedIndex(inputOption
										.getItemCount() - 1);
							}
						});
					}
				}
			}
		});
		inputMsg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String who;
				if (inputOption.getSelectedIndex() == 0)
					who = "";
				else
					who = inputOption.getSelectedItem().toString();
				ChatSocketControl.getInstance().clientTalk(inputMsg.getText(),
						who);
				inputMsg.setText("");
			}
		});
		sendBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String who;
				if (inputOption.getSelectedIndex() == 0)
					who = "";
				else
					who = inputOption.getSelectedItem().toString();
				ChatSocketControl.getInstance().clientTalk(inputMsg.getText(),
						who);
				inputMsg.setText("");

			}
		});

		JButton htmlBtn = new JButton(createImageIcon(ConstanstView.IMAGE_HTML));
		htmlBtn.setContentAreaFilled(false);
		htmlBtn.setBorderPainted(false);
		htmlBtn.setToolTipText("Enviar Link");
		htmlBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String urlstr = JOptionPane.showInputDialog("Ingrese  URL");
				if (urlstr == null || urlstr.trim().length() == 0)
					return;
				urlstr = StyleTextArea.transferHyperlink(urlstr);
				if (urlstr.length() > 0) {
					String attachInfo = "<div class=\"normal\"><font>"
							+ LoginPanel.getInstance().getUserName()
							+ "&nbsp;&gt;&nbsp Upload Hyperlink</font></div>";
					ChatSocketControl.getInstance().clientCmdTalk(
							"TH@" + attachInfo + urlstr);
				}

			}
		});

		JButton imgBtn = new JButton(createImageIcon(ConstanstView.IMAGE));
		imgBtn.setContentAreaFilled(false);
		imgBtn.setBorderPainted(false);
		imgBtn.setToolTipText("Enviar Imagen");
		imgBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String urlstr = JOptionPane
						.showInputDialog("Ingrese URL, (*.png, *.gif, *.jpeg)");
				if (urlstr == null || urlstr.trim().length() == 0)
					return;
				urlstr = StyleTextArea.transferImageHyperlink(urlstr);
				String attachInfo = "<div class=\"normal\"><font>"
						+ LoginPanel.getInstance().getUserName()
						+ "&nbsp;&gt;&nbsp Upload Image</font></div>";
				ChatSocketControl.getInstance().clientCmdTalk(
						"TH@" + attachInfo + urlstr);

			}
		});


		JPanel upPanel = new JPanel();
		JPanel downPanel = new JPanel();
		FlowLayout layout = new FlowLayout();
		layout.setAlignment(FlowLayout.LEFT);
		upPanel.setLayout(layout);
		upPanel.add(htmlBtn);
		upPanel.add(imgBtn);

		downPanel.setLayout(new BorderLayout());
		downPanel.add(inputOption, BorderLayout.WEST);
		downPanel.add(inputMsg, BorderLayout.CENTER);
		downPanel.add(sendBtn, BorderLayout.EAST);
		this.setLayout(new BorderLayout());
		this.add(upPanel, BorderLayout.NORTH);
		this.add(downPanel, BorderLayout.SOUTH);
	}

	private  ImageIcon createImageIcon(String path) {
		java.net.URL imgURL = getClass().getResource(path);
		if (imgURL != null) {
			return new ImageIcon(imgURL);
		} else {
			return null;
		}
	}
	
	public void setWhisper(String name) {
		inputOption.setEnabled(false);
		inputOption.getModel().setSelectedItem(name);
	}

}
