import java.lang.reflect.Field;
import java.util.Date;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.swing.DefaultListModel;


public class UserModel {

	private DBQuery dbq = new DBQuery();
	private AngajatiTree angajati = AngajatiTree.getInstance();
	private ClientiTree clienti = ClientiTree.getInstance();
	private ProiecteTree proiecte = ProiecteTree.getInstance();
	
	public void initializeazaAngajati(){
		dbq.statement("select * from angajati");
		while(dbq.isResultSet())
        {
			Angajat angajat = new Angajat();
            angajat.setCnp(dbq.getNextString("cnp"));
            angajat.setDepartament(dbq.getNextString("departament"));
            angajat.setEmail(dbq.getNextString("email"));
            angajat.setId(dbq.getNextInt("id"));
            angajat.setNume(dbq.getNextString("nume"));
            angajat.setPrenume(dbq.getNextString("prenume"));
            angajat.setVarsta(dbq.getNextInt("varsta"));
            angajati.addAngajat(angajat);
        }
		dbq.inchideStatement();
	}
	
	public void initializeazaClienti(){
		dbq.statement("select * from clienti");
		while(dbq.isResultSet())
        {
			Client client = new Client();
			client.setEmail(dbq.getNextString("email"));
			client.setId(dbq.getNextInt("id"));
			client.setNume(dbq.getNextString("nume"));
			client.setPrenume(dbq.getNextString("prenume"));
			clienti.addClient(client);
        }
		dbq.inchideStatement();
	}
	
	public void initializeazaProiecte(){
		dbq.statement("select * from proiecte");
		while(dbq.isResultSet())
        {
			Proiect proiect;
			String nume; 
			nume = dbq.getNextString("nume");
			if(proiecte.getProiect(nume) == null){
				proiect = new Proiect();
				proiect.setNume(nume);
				proiect.setClient(clienti.getClient(dbq.getNextInt("idClient")));
				proiect.addAngajat(angajati.getAngajat(dbq.getNextInt("idAngajat")));
				proiect.setFinalizat(dbq.getNextBoolean("finalizat"));
				proiect.setDisponibil(dbq.getNextBoolean("disponibil"));
				proiect.setDataSfarsit(dbq.getNextDate("dataFinalizare"));
				proiect.setDataInceput(dbq.getNextDate("dataInceput"));
				proiect.setMemo(dbq.getNextString("memo"));
				if(proiect.getDataSfarsit().before(new Date())){
					proiect.setDisponibil(false);
				}
				else{
					proiect.setDisponibil(true);
				}
				proiect.setDescriere(dbq.getNextString("descriere"));
				proiecte.addProiect(proiect);
			}
			else{
				proiect = proiecte.getProiect(nume);
				proiect.addAngajat(angajati.getAngajat(dbq.getNextInt("IdAngajat")));
			}

        }
		dbq.inchideStatement();
		for(Entry<String,Proiect> e : proiecte.getEntrySet()){
			dbq.statement("update proiecte set proiecte.disponibil="+e.getValue().isDisponibil()+" where proiecte.nume=\""+e.getValue().getNume()+"\";");
			dbq.inchideStatement();
		}
	}

	public AngajatiTree getAngajati() {
		return angajati;
	}

	public ClientiTree getClienti() {
		return clienti;
	}

	public ProiecteTree getProiecte() {
		return proiecte;
	}
	
	public boolean sendStatemtnDbq(String s){
		boolean b;
		b=dbq.statement(s);
		dbq.inchideStatement();
		return b;
	}
	
	public DefaultListModel<String> getDispPers(Proiect proiect){
		DefaultListModel<String> list = new DefaultListModel<>();
		for(Entry<Integer, Angajat> e : angajati.getEntrySet()){
			if(!proiect.getAngajati().contains(e.getValue()))
				list.addElement(e.getValue().getNume());
		}
		return list;
	}
	
	public DefaultListModel<String> getAdaugPers(Proiect proiect,Angajat user){
		DefaultListModel<String> list = new DefaultListModel<>();
		for(Entry<Integer, Angajat> e : angajati.getEntrySet()){
			if(proiect.getAngajati().contains(e.getValue()) && !e.getValue().equals(user))
				list.addElement(e.getValue().getNume());
		}
		return list;
	}
}
