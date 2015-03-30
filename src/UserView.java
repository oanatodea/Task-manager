import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.lang.reflect.Field;
import java.util.Enumeration;
import java.util.Map;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.border.MatteBorder;


public class UserView extends JFrame{
	
	private ClientiPanel clientiPanel = new ClientiPanel();
	private AngajatiPanel angajatiPanel = new AngajatiPanel();
	private ProiectePanel proiectePanel = new ProiectePanel();
	private JTabbedPane tabbedPane = new JTabbedPane();
	
	public UserView(){
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new GridLayout());
		setLocation(150, 100);
		clientiPanel.setVisible(true);
		angajatiPanel.setVisible(true);
		proiectePanel.setVisible(true);
		
		tabbedPane.addTab("Angajati", angajatiPanel);
		tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);
		tabbedPane.addTab("Clienti", clientiPanel);
		tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);
		tabbedPane.addTab("Proiecte", proiectePanel);
		tabbedPane.setMnemonicAt(1, KeyEvent.VK_3);
		tabbedPane.setVisible(true);
		add(tabbedPane);
		tabbedPane.setTabPlacement(tabbedPane.TOP);
		this.setVisible(true);
		this.setTitle("Admin");
		this.pack();
	}

	public ClientiPanel getClientiPanel() {
		return clientiPanel;
	}

	public AngajatiPanel getAngajatiPanel() {
		return angajatiPanel;
	}

	public ProiectePanel getProiectePanel() {
		return proiectePanel;
	}
	
}
