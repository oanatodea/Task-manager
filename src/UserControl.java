import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map.Entry;

import javax.swing.JOptionPane;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;


public class UserControl implements MouseListener {

	private LogInView logView = new LogInView();
	private UserView view;
	private UserModel model = new UserModel();
	private Angajat user;
	
	public UserControl(){
		
		model.initializeazaAngajati();
		model.initializeazaClienti();
		model.initializeazaProiecte();
		
		logView.addLogInListener(this);


	}
	
	public void creeareView(){
		
		view = new UserView();
		view.getAngajatiPanel().addAngajatiPanelListener(this);
		view.getClientiPanel().addClientiPanelListener(this);
		view.getProiectePanel().addProiectPanelListener(this);
		
		view.getAngajatiPanel().initializare(model.getAngajati());
		view.getClientiPanel().initializare(model.getClienti());
		view.getProiectePanel().initializare(model.getProiecte(),user);
		
		model.getClienti().addObserver(view.getClientiPanel());
		model.getProiecte().addObserver(view.getProiectePanel());
		
	}
	
	public void setUser(Angajat user){
		this.user = user;
	}

	public void mouseClicked(MouseEvent e) {
		if(e.getComponent() == logView.getLogin()){
			if(verificaCont(model.getAngajati()) == null)
				JOptionPane.showMessageDialog(logView, "Email sau cnp incorect !");
			else{
				setUser(verificaCont(model.getAngajati()));
				CrearePdf pdf = new CrearePdf(model.getProiecte(),user);
		//		SendEmail email = new SendEmail(user.getEmail(), "Memo", "Astea sunt proiectele disponibile pe azi");	
				logView.dispose();
				creeareView();
			}	
		}
		else{
			if(e.getComponent() == view.getClientiPanel().getClientiTable()){
				int row = view.getClientiPanel().getClientiTable().getSelectedRow();
				Client client = model.getClienti().getClient(Integer.parseInt(view.getClientiPanel().getClientiTable().getValueAt(row, 0).toString()));
				view.getClientiPanel().setTextArea(client,view.getClientiPanel().getClientiTextArea());
			}
			if(e.getComponent() == view.getAngajatiPanel().getAngajatiTable()){
				int row = view.getAngajatiPanel().getAngajatiTable().getSelectedRow();
				Angajat angajat = model.getAngajati().getAngajat(Integer.parseInt(view.getAngajatiPanel().getAngajatiTable().getValueAt(row, 0).toString()));
				view.getAngajatiPanel().setTextArea(angajat,model.getProiecte(),view.getAngajatiPanel().getAngajatiTextArea());
			}
			if(e.getComponent() == view.getProiectePanel().getProiecteTable()){
				int row = view.getProiectePanel().getProiecteTable().getSelectedRow();
				Proiect proiect = model.getProiecte().getProiect(view.getProiectePanel().getProiecteTable().getValueAt(row, 0).toString());
				view.getProiectePanel().setTextArea(proiect, view.getProiectePanel().getProiecteTextArea(),user);
				view.getProiectePanel().setEnableFields(true);
				view.getProiectePanel().getDisponibilList().setModel(model.getDispPers(proiect));
				view.getProiectePanel().getAdaugatiList().setModel(model.getAdaugPers(proiect,user));	
			}	
			if(e.getComponent() == view.getClientiPanel().getAdaugabtn()){
				System.out.println("da");
			}
			if(e.getComponent() == view.getProiectePanel().getFinalizatbtn()){
				int row= view.getProiectePanel().getProiecteTable().getSelectedRow();
				model.sendStatemtnDbq("update proiecte set proiecte.finalizat=true where proiecte.nume=\"" + view.getProiectePanel().getProiecteTable().getValueAt(row, 0).toString()+"\";");
				Proiect proiect = model.getProiecte().getProiect(view.getProiectePanel().getProiecteTable().getValueAt(row, 0).toString());
				proiect.setFinalizat(true);
				model.getProiecte().notifyObservers(new Event(Event.EventType.finalizat,proiect,user,row));
				view.getProiectePanel().getProiecteTable().setRowSelectionInterval(row, row);
			}
			if(e.getComponent() == view.getProiectePanel().getNefinalizatbtn()){
				int row= view.getProiectePanel().getProiecteTable().getSelectedRow();
				model.sendStatemtnDbq("update proiecte set proiecte.finalizat=false where proiecte.nume=\"" + view.getProiectePanel().getProiecteTable().getValueAt(row, 0).toString()+"\";");
				Proiect proiect = model.getProiecte().getProiect(view.getProiectePanel().getProiecteTable().getValueAt(row, 0).toString());
				proiect.setFinalizat(false);
				model.getProiecte().notifyObservers(new Event(Event.EventType.nefinalizat,proiect,user,row));
				view.getProiectePanel().getProiecteTable().setRowSelectionInterval(row, row);
			}
			if(e.getComponent() == view.getProiectePanel().getAdaugabtn()){
				int row = view.getProiectePanel().getProiecteTable().getSelectedRow();
				Proiect proiect = model.getProiecte().getProiect(view.getProiectePanel().getProiecteTable().getValueAt(row, 0).toString());
				String nume = view.getProiectePanel().getDisponibilList().getSelectedValue();
				if(nume != null){
					Angajat angajat = model.getAngajati().getAngajat(nume);
					proiect.addAngajat(angajat);
					DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
					model.getProiecte().notifyObservers(new Event(Event.EventType.modifica,proiect,user,row));
					model.sendStatemtnDbq("insert into proiecte value (\""+proiect.getNume()+"\","+angajat.getId()+","+proiect.getClient().getId()+","+proiect.isFinalizat()+","
							+proiect.isDisponibil()+",'"+format.format(proiect.getDataSfarsit())+"',\""+proiect.getDescriere()+"\",'"+format.format(proiect.getDataInceput())+"',\""+proiect.getMemo()+"\");");
					view.getProiectePanel().getDisponibilList().setModel(model.getDispPers(proiect));
					view.getProiectePanel().getAdaugatiList().setModel(model.getAdaugPers(proiect,user));
				}
			}
			if(e.getComponent() == view.getProiectePanel().getStergebtn()){
				int row = view.getProiectePanel().getProiecteTable().getSelectedRow();
				Proiect proiect = model.getProiecte().getProiect(view.getProiectePanel().getProiecteTable().getValueAt(row, 0).toString());
				String nume = view.getProiectePanel().getAdaugatiList().getSelectedValue();
				if(nume != null){
					Angajat angajat = model.getAngajati().getAngajat(nume);
					proiect.removeAngajat(angajat);
					model.getProiecte().notifyObservers(new Event(Event.EventType.modifica,proiect,user,row));
					model.sendStatemtnDbq("delete from proiecte where proiecte.nume=\""+proiect.getNume()+"\" and proiecte.idAngajat="+angajat.getId()+";");
					view.getProiectePanel().getDisponibilList().setModel(model.getDispPers(proiect));
					view.getProiectePanel().getAdaugatiList().setModel(model.getAdaugPers(proiect,user));
				}
			}
			if(e.getComponent() == view.getProiectePanel().getCreeazabtn()){
				if(verificaProiect()==false){
					JOptionPane.showMessageDialog(view, "Nu s-au introdus toate datele");
				}
				else{
					Proiect proiect = new Proiect();
					proiect.setClient(model.getClienti().getClient(Integer.parseInt(view.getProiectePanel().getIdtxt().getText())));
					DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
					Date date = new Date();
					Date now = new Date();
					proiect.setDataInceput(date);
					proiect.setDataSfarsit(view.getProiectePanel().getCalendar().getDate());
					proiect.setDescriere(view.getProiectePanel().getDescrieretxt().getText());
					if(proiect.getDataSfarsit().after(now))
						proiect.setDisponibil(true);
					else
						proiect.setDisponibil(false);
					proiect.setFinalizat(false);
					proiect.setNume(view.getProiectePanel().getNumetxt().getText());
					proiect.addAngajat(user);
					
					if(model.sendStatemtnDbq("insert into proiecte value (\""+proiect.getNume()+"\","+user.getId()+","+proiect.getClient().getId()+","+proiect.isFinalizat()+","
							+proiect.isDisponibil()+",'"+format.format(proiect.getDataSfarsit())+"',\""+proiect.getDescriere()+"\",'"+format.format(proiect.getDataInceput())+"',\""+proiect.getMemo()+"\");")){
						model.getProiecte().addProiect(proiect);
						model.getProiecte().notifyObservers(new Event(Event.EventType.creeaza, proiect, user, 0));
					}	
				}	
			}
			if(e.getComponent() == view.getClientiPanel().getAdaugabtn()){
				if(verificaClient()==false){
					JOptionPane.showMessageDialog(view, "Nu s-au introdus toate datele");
				}
				else{
					Client client = new Client();
					client.setEmail(view.getClientiPanel().getEmailtxt().getText());
					client.setNume(view.getClientiPanel().getNumetxt().getText());
					client.setPrenume(view.getClientiPanel().getPrenumetxt().getText());
					client.setId(model.getClienti().getSize()+1);
					if(model.sendStatemtnDbq("insert into clienti value (\""+client.getNume()+"\",\""+client.getPrenume()+"\",\""+client.getEmail()+"\","+client.getId()+");")){
						model.getClienti().addClient(client);
						model.getClienti().notifyObservers(new Event(Event.EventType.creeaza, null, user, 0));
					}		
				}	
			}
			if(e.getComponent() == view.getProiectePanel().getActualebtn()){
				for(int i=view.getProiectePanel().getProiecteTableModel().getRowCount()-1;i>=0;i--){
					view.getProiectePanel().getProiecteTableModel().removeRow(i);
				}
				view.getProiectePanel().initializareActuale(model.getProiecte(), user);
			}
			if(e.getComponent() == view.getProiectePanel().getToatebtn()){
				for(int i=view.getProiectePanel().getProiecteTableModel().getRowCount()-1;i>=0;i--){
					view.getProiectePanel().getProiecteTableModel().removeRow(i);
				}
				view.getProiectePanel().initializare(model.getProiecte(), user);
			}
			if(e.getComponent() == view.getProiectePanel().getTrecutebtn()){
				for(int i=view.getProiectePanel().getProiecteTableModel().getRowCount()-1;i>=0;i--){
					view.getProiectePanel().getProiecteTableModel().removeRow(i);
				}
				view.getProiectePanel().initializareTrecute(model.getProiecte(), user);
			}
			if(e.getComponent() == view.getProiectePanel().getMemobtn()){
				int row = view.getProiectePanel().getProiecteTable().getSelectedRow();
				Proiect proiect = model.getProiecte().getProiect(view.getProiectePanel().getProiecteTable().getValueAt(row, 0).toString());
				view.getProiectePanel().getMemotxt().setText(proiect.getMemo());
				JScrollPane scroll = new JScrollPane(view.getProiectePanel().getMemotxt());
				scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
				scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
				scroll.setPreferredSize(new Dimension(100,150));
				int result = JOptionPane.showOptionDialog(null,scroll, "Memo",  JOptionPane.OK_CANCEL_OPTION,JOptionPane.PLAIN_MESSAGE, null, null, null);
				if(result == JOptionPane.OK_OPTION){
					if(!view.getProiectePanel().getMemotxt().getText().equals(null)){
						proiect.setMemo(view.getProiectePanel().getMemotxt().getText());
						model.sendStatemtnDbq("update proiecte set proiecte.memo=\""+proiect.getMemo()+"\" where proiecte.nume=\"" + view.getProiectePanel().getProiecteTable().getValueAt(row, 0).toString()+"\";");
					}
				}
			}
			else{
				view.pack();
			}
		}
	}

	public void mouseEntered(MouseEvent arg0) {
		
	}

	public void mouseExited(MouseEvent arg0) {
		
	}

	public void mousePressed(MouseEvent arg0) {
		
	}

	public void mouseReleased(MouseEvent arg0) {
		
	}
	
	
	public Angajat verificaCont(AngajatiTree angajati){
		for(Entry<Integer, Angajat> e : angajati.getEntrySet()){
			if(e.getValue().getEmail().equalsIgnoreCase(logView.getEmail())){
				if(e.getValue().getCnp().equalsIgnoreCase(logView.getCnp()))
					return e.getValue();
			}
		}
		return null;
	}
	public boolean verificaProiect(){
		if(view.getProiectePanel().getNumetxt().getText().equals(""))
			return false;
		if(view.getProiectePanel().getIdtxt().getText().equals(""))
			return false;		
		return true;
	}
	public boolean verificaClient(){
		if(view.getClientiPanel().getNumetxt().getText().equals(""))
			return false;
		if(view.getClientiPanel().getPrenumetxt().getText().equals(""))
			return false;	
		if(view.getClientiPanel().getEmailtxt().getText().equals(""))
			return false;
		return true;
	}
}
