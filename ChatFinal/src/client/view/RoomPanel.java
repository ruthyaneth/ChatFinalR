package client.view;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.*;

import constant.ConstanstView;
/**
 * UNIVERSIDAD PEDAGOGICA Y TECNOLOGICA DE COLOMBIA
 * FACULTAD DE INGENIERIA.
 * ESCUELA DE INGENIERIA DE SISTEMAS Y COMPUTACION.
 * PRESENTADO A: Ing Helver Valero.
 * PROGRAMACION III
 * Panel donde se encuentran los  usuarios conectados
 * @author Jenny Quesada , Ruth Rojas
 */
public class RoomPanel extends JPanel {
	
	//------Atributtes-------
	
	private static RoomPanel singleton = null;
	private StyleTextArea textArea;
	private JList userList;
	private DefaultListModel userListModel;
	private JScrollPane leftPane;
	private JScrollPane rightPane;
	private JSplitPane splitPane1;

	//------Builder------
	
	private RoomPanel() {
		init();
		
	}
	
	//------Methods------
	
	public void init (){
		initThis();
	}
	
	public void initThis(){
		
		this.setLayout(new FlowLayout(FlowLayout.LEFT));
		this.userListModel = new DefaultListModel();
		this.userList = new JList(userListModel);
		this.textArea = new StyleTextArea();

		this.userList.setOpaque(false);
		this.userList.setForeground(Color.GREEN);

		this.leftPane = new JScrollPane(userList);
		this.rightPane = new JScrollPane(textArea);
		this.splitPane1 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,leftPane, rightPane);
		this.splitPane1.setDividerLocation(100);
		this.splitPane1.setOpaque(false);
		this.leftPane.setBorder(new TitledBorder(ConstanstView.DEFAULT_ONLINE));
		this.leftPane.setOpaque(false);
		this.rightPane.setOpaque(false);
		this.rightPane.getVerticalScrollBar().addAdjustmentListener(
											new AdjustmentListener() {
											boolean locked = true;
											private JScrollBar sb;

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
		this.setLayout(new BorderLayout());
		this.setOpaque(false);
		this.add(splitPane1, BorderLayout.CENTER);
	}

	public DefaultListModel getUserListModel() {
		return this.userListModel;
	}

	public StyleTextArea getTextArea() {
		return this.textArea;
	}
	
	public static RoomPanel getInstance() {
		if (singleton == null)
			singleton = new RoomPanel();
		return singleton;
	}

}
