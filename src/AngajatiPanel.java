import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.TextArea;
import java.awt.event.MouseListener;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;
import java.util.Map.Entry;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.border.MatteBorder;


public class AngajatiPanel extends JPanel{

	private Table angajatiTableModel = new Table((new Angajat()).getClass());
	private JTable angajatiTable = new JTable(angajatiTableModel);
	private JTextArea angajatiTextArea = new JTextArea();
	
	public AngajatiPanel() {
		
		angajatiTable.setGridColor(Color.BLACK);
		angajatiTable.setBorder(new MatteBorder(1,1,1,1,Color.BLACK));
		angajatiTextArea.setEditable(false);
		
		GridBagLayout cc = new GridBagLayout();
		cc.columnWidths=new int[]{220,220,400};
		
		this.setLayout(cc);
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.insets= new Insets(5, 5, 5, 5);
		
		c.gridx =0;
		c.gridy=0;
		c.gridwidth=2;
		this.add(new JScrollPane(angajatiTable),c);
		
		c.gridx=2;
		c.gridy=0;
		c.gridwidth=1;
		c.ipadx=400;
		this.add(new JScrollPane(angajatiTextArea),c);
	
	}

	
	public void addAngajatiPanelListener(MouseListener l){
		angajatiTable.addMouseListener(l);
	}

	public Table getAngajatiTableModel() {
		return angajatiTableModel;
	}

	public JTable getAngajatiTable() {
		return angajatiTable;
	}

	public JTextArea getAngajatiTextArea() {
		return angajatiTextArea;
	}
	
	public void initializare(AngajatiTree angajati){
		int row=0; 
		
		for(Entry<Integer, Angajat> e : angajati.getEntrySet()){
				angajatiTableModel.addRow();
				angajatiTableModel.setValueAt(e.getValue().getId(), row, 0);
				angajatiTableModel.setValueAt(e.getValue().getNume(), row, 1);
				angajatiTableModel.setValueAt(e.getValue().getPrenume(), row, 2);
				angajatiTableModel.setValueAt(e.getValue().getVarsta(), row, 3);
				angajatiTableModel.setValueAt(e.getValue().getEmail(), row, 4);
				angajatiTableModel.setValueAt(e.getValue().getDepartament(), row, 5);
				row++;
		}
		
		angajatiTableModel.fireTableDataChanged();
	}
	
	public void setTextArea(Angajat angajat,ProiecteTree proiecte,JTextArea textArea){
		
		textArea.setText('\n'+"- Id - "+'\n'+'\t'+angajat.getId());
		textArea.append('\n'+""+'\n'+'\n'+"- Nume - "+'\n'+'\t'+angajat.getNume());
		textArea.append('\n'+""+'\n'+'\n'+"- Prenume - "+'\n'+'\t'+angajat.getPrenume());
		textArea.append('\n'+""+'\n'+'\n'+"- Varsta - "+'\n'+'\t'+angajat.getVarsta());
		textArea.append('\n'+""+'\n'+'\n'+"- Email - "+'\n'+'\t'+angajat.getEmail());
		textArea.append('\n'+""+'\n'+'\n'+"- Departament - "+'\n'+'\t'+angajat.getDepartament());
		
		for(Entry<String, Proiect> e : proiecte.getEntrySet()){
			if(e.getValue().getAngajati().contains(angajat)){
				textArea.append('\n'+""+'\n'+'\n'+" Proiect"+'\n'+'\t'+e.getValue().getNume());
				textArea.append('\n'+""+'\n'+" Perioada"+'\n'+'\t'+e.getValue().getDataInceput()+" - "+e.getValue().getDataSfarsit());
				textArea.append('\n'+""+'\n'+" Colegi");
				for(Angajat a : e.getValue().getAngajati()){
					if(!a.equals(angajat))
						textArea.append('\n'+""+'\t'+a.getNume()+" "+a.getPrenume());
				}			
			}
		}
	}	
}
