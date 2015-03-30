import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Map.Entry;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.MatteBorder;
import javax.swing.event.CellEditorListener;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.text.TableView.TableCell;
import javax.swing.text.TableView.TableRow;

import com.toedter.calendar.JCalendar;


public class ProiectePanel extends JPanel implements Observer{
	
	private Table proiecteTableModel = new Table((new Proiect()).getClass());
	private JTable proiecteTable = new JTable(proiecteTableModel);
	private JTextArea proiecteTextArea= new JTextArea();
	private JTextField numetxt = new JTextField();
	private JTextField descrieretxt = new JTextField();
	private JTextField idtxt = new JTextField();
	private JButton creeazabtn = new JButton("Creeaza");
	private JButton toatebtn = new JButton("Toate proiectele");
	private JButton actualebtn = new JButton("Proiectele actuale");
	private JButton trecutebtn = new JButton("Proiectele trecute");
	private JButton adaugabtn = new JButton("Adauga persoana");
	private JButton finalizatbtn = new JButton("Marcheaza ca finalizat");
	private JButton nefinalizatbtn = new JButton("Marcheaza ca nefinalizat");
	private JButton stergebtn = new JButton("Sterge persoana");
	private JButton memobtn = new JButton("Memo");
	JList<String> disponibilList = new JList<>();
	JList<String> adaugatiList = new JList<>();
	private JCalendar calendar = new JCalendar();
	private JTextArea memotxt = new JTextArea();
	
	public ProiectePanel() {
		
		memotxt.setLineWrap(true);
		
		proiecteTable.setGridColor(Color.BLACK);
		proiecteTable.setBorder(new MatteBorder(1,1,1,1,Color.BLACK));
		proiecteTextArea.setEditable(false);
		
		setEnableFields(false);
		
		disponibilList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		disponibilList.setLayoutOrientation(JList.VERTICAL_WRAP);
		disponibilList.setVisibleRowCount(-1);
		
		adaugatiList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		adaugatiList.setLayoutOrientation(JList.VERTICAL_WRAP);
		adaugatiList.setVisibleRowCount(-1);
		
		numetxt.setToolTipText("Numele proiectului");
		descrieretxt.setToolTipText("Descriere");
		idtxt.setToolTipText("Id client");
		
		GridBagLayout cc = new GridBagLayout();
		cc.columnWidths=new int[]{200,200,200,200};
		
		this.setLayout(cc);
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.insets= new Insets(5, 5, 5, 5);
		c.gridwidth=1;
		
		c.gridx =0;
		c.gridy=7;
		this.add(toatebtn,c);
		
		c.gridx =1;
		c.gridy=7;
		this.add(actualebtn,c);
		
		c.gridx =2;
		c.gridy=7;
		this.add(trecutebtn,c);
		
		c.gridx =3;
		c.gridy=7;
		this.add(memobtn,c);
		
		c.gridx =0;
		c.gridy=3;
		this.add(numetxt,c);
		
		c.gridx =0;
		c.gridy=4;
		c.gridheight=1;
		c.gridwidth=2;
		this.add(descrieretxt,c);
		
		c.gridx =1;
		c.gridy=3;
		c.gridheight=1;
		c.gridwidth=1;
		this.add(idtxt,c);
		
		
		c.gridx =2;
		c.gridy=3;
		c.gridheight=2;
		c.gridwidth=1;
		this.add(calendar,c);

		c.gridx =3;
		c.gridy =3;
		c.gridheight=1;
		c.gridwidth=1;
		this.add(creeazabtn,c);
		
		
		c.gridx =0;
		c.gridy=5;
		c.gridwidth=1;
		c.gridheight=2;
		this.add(disponibilList,c);
		
		c.gridx =1;
		c.gridy=5;
		c.gridwidth=1;
		c.gridheight=1;
		this.add(adaugabtn,c);
		
		c.gridx =1;
		c.gridy=6;
		this.add(stergebtn,c);
		
		c.gridx =2;
		c.gridy=5;
		c.gridwidth=1;
		c.gridheight=2;
		this.add(adaugatiList,c);
		
		c.gridx =3;
		c.gridy=5;
		c.gridwidth=1;
		c.gridheight=1;
		this.add(finalizatbtn,c);
		
		c.gridx =3;
		c.gridy=6;
		c.gridwidth=1;
		c.gridheight=1;
		this.add(nefinalizatbtn,c);
		
		c.gridx =0;
		c.gridy=0;
		c.gridwidth=2;
		c.gridheight=3;
		this.add(new JScrollPane(proiecteTable),c);
		
		c.gridx=2;
		c.gridy=0;
		c.gridwidth=2;
		c.gridheight=3;
		c.ipadx=400;
		this.add(new JScrollPane(proiecteTextArea),c);
		
	}
	
	public void addProiectPanelListener(MouseListener l){
		proiecteTable.addMouseListener(l);
		creeazabtn.addMouseListener(l);
		adaugabtn.addMouseListener(l);
		stergebtn.addMouseListener(l);
		finalizatbtn.addMouseListener(l);
		nefinalizatbtn.addMouseListener(l);
		toatebtn.addMouseListener(l);
		trecutebtn.addMouseListener(l);
		actualebtn.addMouseListener(l);
		disponibilList.addMouseListener(l);
		adaugatiList.addMouseListener(l);
		memobtn.addMouseListener(l);
	}

	public JTextArea getMemotxt() {
		return memotxt;
	}

	public void setMemotxt(JTextArea memotxt) {
		this.memotxt = memotxt;
	}

	public JButton getMemobtn() {
		return memobtn;
	}

	public JButton getNefinalizatbtn() {
		return nefinalizatbtn;
	}

	public Table getProiecteTableModel() {
		return proiecteTableModel;
	}

	public JTable getProiecteTable() {
		return proiecteTable;
	}

	public JTextArea getProiecteTextArea() {
		return proiecteTextArea;
	}

	public JTextField getNumetxt() {
		return numetxt;
	}
	
	public JButton getCreeazabtn() {
		return creeazabtn;
	}

	public JButton getToatebtn() {
		return toatebtn;
	}

	public JButton getActualebtn() {
		return actualebtn;
	}

	public JButton getTrecutebtn() {
		return trecutebtn;
	}

	public JButton getAdaugabtn() {
		return adaugabtn;
	}

	public JButton getFinalizatbtn() {
		return finalizatbtn;
	}

	public JButton getStergebtn() {
		return stergebtn;
	}

	public JList<String> getDisponibilList() {
		return disponibilList;
	}

	public JList<String> getAdaugatiList() {
		return adaugatiList;
	}

	
	public JTextField getDescrieretxt() {
		return descrieretxt;
	}

	public JTextField getIdtxt() {
		return idtxt;
	}

	public JCalendar getCalendar() {
		return calendar;
	}

	public void initializare(ProiecteTree proiecte, Angajat user){
		int row=0; 
		
		for(Entry<String,Proiect> e : proiecte.getEntrySet()){
			if(e.getValue().getAngajati().contains(user)){
				proiecteTableModel.addRow();
				proiecteTableModel.setValueAt(e.getValue().getNume(), row, 0);
				proiecteTableModel.setValueAt(e.getValue().getClient().getId(), row, 1);
				if(e.getValue().isFinalizat())
					proiecteTableModel.setValueAt("Finalizat", row, 2);
				else
					proiecteTableModel.setValueAt("Nefinalizat", row, 2);
				if(e.getValue().isDisponibil())
					proiecteTableModel.setValueAt("Disponibil", row, 3);
				else
					proiecteTableModel.setValueAt("Indisponibil", row, 3);
				row++;
			}
		}
		proiecteTableModel.fireTableDataChanged();
	}
	
	public void initializareActuale(ProiecteTree proiecte, Angajat user){
		int row=0; 
		for(Entry<String,Proiect> e : proiecte.getEntrySet()){
			if(e.getValue().getAngajati().contains(user) && e.getValue().isDisponibil()){
				proiecteTableModel.addRow();
				proiecteTableModel.setValueAt(e.getValue().getNume(), row, 0);
				proiecteTableModel.setValueAt(e.getValue().getClient().getId(), row, 1);
				if(e.getValue().isFinalizat())
					proiecteTableModel.setValueAt("Finalizat", row, 2);
				else
					proiecteTableModel.setValueAt("Nefinalizat", row, 2);
				if(e.getValue().isDisponibil())
					proiecteTableModel.setValueAt("Disponibil", row, 3);
				else
					proiecteTableModel.setValueAt("Indisponibil", row, 3);
				row++;
			}
		}
		proiecteTableModel.fireTableDataChanged();
	}
	
	public void initializareTrecute(ProiecteTree proiecte, Angajat user){
		int row=0; 
		for(Entry<String,Proiect> e : proiecte.getEntrySet()){
			if(e.getValue().getAngajati().contains(user) && !e.getValue().isDisponibil()){
				proiecteTableModel.addRow();
				proiecteTableModel.setValueAt(e.getValue().getNume(), row, 0);
				proiecteTableModel.setValueAt(e.getValue().getClient().getId(), row, 1);
				if(e.getValue().isFinalizat())
					proiecteTableModel.setValueAt("Finalizat", row, 2);
				else
					proiecteTableModel.setValueAt("Nefinalizat", row, 2);
				if(e.getValue().isDisponibil())
					proiecteTableModel.setValueAt("Disponibil", row, 3);
				else
					proiecteTableModel.setValueAt("Indisponibil", row, 3);
				row++;
			}
		}
		proiecteTableModel.fireTableDataChanged();
	}
	
	public void setTextArea(Proiect proiect, JTextArea textArea,Angajat user){
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		textArea.setText('\n'+"- Nume proiect - "+'\n'+'\t'+proiect.getNume());
		textArea.append('\n'+""+'\n'+'\n'+"- Nume client - "+'\n'+'\t'+proiect.getClient().getNume()+" "+proiect.getClient().getPrenume());
		textArea.append('\n'+""+'\n'+'\n'+"- Perioada - "+'\n'+'\t'+format.format(proiect.getDataInceput())+" - "+format.format(proiect.getDataSfarsit()));
		textArea.append('\n'+""+'\n'+'\n'+"- Descriere - "+'\n'+'\t'+proiect.getDescriere());
		textArea.append('\n'+""+'\n'+"- Coechipieri - ");
		for(Angajat a : proiect.getAngajati()){
			if(!a.equals(user))
				textArea.append('\n'+""+'\t'+a.getNume()+" "+a.getPrenume());
		}	
	}

	public void update(Observable o, Object obj) {
		Event event = (Event)obj;
		if(event.getTip().equals(Event.EventType.creeaza)){
			for(int i=proiecteTableModel.getRowCount()-1;i>=0;i--)
				proiecteTableModel.removeRow(i);
			initializare((ProiecteTree)o, event.getUser());
			proiecteTextArea.setText("");
		}
		else
			if(event.getTip().equals(Event.EventType.finalizat)){
				proiecteTableModel.setValueAt("Finalizat", event.getRow(), proiecteTableModel.getColumnCount()-2);
				proiecteTableModel.fireTableDataChanged();
			}
			else
				if(event.getTip().equals(Event.EventType.nefinalizat)){
					proiecteTableModel.setValueAt("Nefinalizat", event.getRow(), proiecteTableModel.getColumnCount()-2);
					proiecteTableModel.fireTableDataChanged();
				}
			else{
				setTextArea(event.getProiect(), proiecteTextArea, event.getUser());
				
			}
	}
	
	public void setEnableFields(boolean b){
		getDisponibilList().setEnabled(b);
		getAdaugatiList().setEnabled(b);
		getAdaugabtn().setEnabled(b);
		getStergebtn().setEnabled(b);
		getFinalizatbtn().setEnabled(b);
		getNefinalizatbtn().setEnabled(b);
		memobtn.setEnabled(b);
	}
	
}
