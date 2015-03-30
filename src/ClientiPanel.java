import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.TextArea;
import java.awt.event.MouseListener;
import java.lang.reflect.Field;
import java.util.Map.Entry;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;


public class ClientiPanel extends JPanel implements Observer{

	private Table clientiTableModel = new Table((new Client()).getClass());
	private JTable clientiTable = new JTable(clientiTableModel);
	private JTextArea clientiTextArea = new JTextArea();
	private JLabel numelbl = new JLabel("Nume client");
	private JLabel prenumelbl = new JLabel("Prenume client");
	private JLabel emaillbl = new JLabel("Email client");
	private JTextField numetxt = new JTextField();
	private JTextField prenumetxt = new JTextField();
	private JTextField emailtxt = new JTextField();
	private JButton adaugabtn = new JButton("Adauga");
	
	public ClientiPanel() {
		
		clientiTable.setGridColor(Color.BLACK);
		clientiTable.setBorder(new MatteBorder(1,1,1,1,Color.BLACK));
		clientiTextArea.setEditable(false);
		
		GridBagLayout cc = new GridBagLayout();
		
		this.setLayout(cc);
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.insets= new Insets(5, 5, 5, 5);
		c.gridwidth=1;
		
		c.gridx =0;
		c.gridy=2;
		this.add(numelbl,c);
		
		c.gridx =1;
		c.gridy=2;
		this.add(numetxt,c);
		
		c.gridx =0;
		c.gridy=3;
		this.add(prenumelbl,c);
		
		c.gridx =1;
		c.gridy=3;
		this.add(prenumetxt,c);
		
		c.gridx =2;
		c.gridy=2;
		this.add(emaillbl,c);
		
		c.gridx =3;
		c.gridy=2;
		this.add(emailtxt,c);
		
		c.gridx =3;
		c.gridy =3;
		this.add(adaugabtn,c);
		
		
		c.gridx =0;
		c.gridy=0;
		c.gridwidth=2;
		c.gridheight=2;
		this.add(new JScrollPane(clientiTable),c);
		
		c.gridx=2;
		c.gridy=0;
		c.gridwidth=2;
		c.gridheight=2;
		c.ipadx=400;
		this.add(new JScrollPane(clientiTextArea),c);
	}
	
	public void addClientiPanelListener(MouseListener l){
		clientiTable.addMouseListener(l);
		adaugabtn.addMouseListener(l);
	}

	public Table getClientiTableModel() {
		return clientiTableModel;
	}

	public JTable getClientiTable() {
		return clientiTable;
	}

	public JTextArea getClientiTextArea() {
		return clientiTextArea;
	}

	public JTextField getNumetxt() {
		return numetxt;
	}

	public JTextField getPrenumetxt() {
		return prenumetxt;
	}

	public JTextField getEmailtxt() {
		return emailtxt;
	}

	public JButton getAdaugabtn() {
		return adaugabtn;
	}
	
	public void initializare(ClientiTree clienti){
		int row=0; 
		
		for(Entry<Integer, Client> e : clienti.getEntrySet()){
			clientiTableModel.addRow();
			clientiTableModel.setValueAt(e.getValue().getId(), row, 0);
			clientiTableModel.setValueAt(e.getValue().getNume(), row, 1);
			clientiTableModel.setValueAt(e.getValue().getPrenume(), row, 2);
			clientiTableModel.setValueAt(e.getValue().getEmail(), row, 3);	
			row++;
		}
		clientiTableModel.fireTableDataChanged();
	}
	
	public void setTextArea(Client client, JTextArea textArea){
		textArea.setText('\n'+"- Id - "+'\n'+'\t'+client.getId());
		textArea.append('\n'+""+'\n'+'\n'+"- Nume - "+'\n'+'\t'+client.getNume());
		textArea.append('\n'+""+'\n'+'\n'+"- Prenume - "+'\n'+'\t'+client.getPrenume());
		textArea.append('\n'+""+'\n'+'\n'+"- Email - "+'\n'+'\t'+client.getEmail());
	}

	public void update(Observable o, Object arg) {
		System.out.println(clientiTableModel.getRowCount());
		for(int i=clientiTableModel.getRowCount()-1;i>=0;i--){
			clientiTableModel.removeRow(i);
		}System.out.println(clientiTableModel.getRowCount());
		initializare((ClientiTree)o);
		clientiTextArea.setText("");
	}
	
}
