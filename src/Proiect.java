import java.util.ArrayList;
import java.util.Date;


public class Proiect {
	
	@AnnotationColumns(columnName  = "Nume")
	private String nume;
	private ArrayList<Angajat> angajati = new ArrayList<>();
	@AnnotationColumns(columnName  = "ID client")
	private Client client;
	@AnnotationColumns(columnName  = "Stare")
	private boolean finalizat;
	@AnnotationColumns(columnName  = "Disponibilitate")
	private boolean disponibil;
	private Date dataInceput = new Date();
	private Date dataSfarsit = new Date();
	private String descriere;
	private String memo;
	
	public Proiect(){
		this.memo="";
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}
	public String getMemo() {
		return memo;
	}
	public String getNume() {
		return nume;
	}
	public void setNume(String nume) {
		this.nume = nume;
	}
	public ArrayList<Angajat> getAngajati() {
		return angajati;
	}
	public void addAngajat(Angajat angajat) {
		angajati.add(angajat);
	}
	public void removeAngajat(Angajat angajat){
		angajati.remove(angajat);
	}
	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}
	public boolean isFinalizat() {
		return finalizat;
	}
	public void setFinalizat(boolean finalizat) {
		this.finalizat = finalizat;
	}
	public boolean isDisponibil() {
		return disponibil;
	}
	public void setDisponibil(boolean disponibil) {
		this.disponibil = disponibil;
	}
	public Date getDataInceput() {
		return dataInceput;
	}
	public void setDataInceput(Date dataInceput) {
		this.dataInceput = dataInceput;
	}
	public Date getDataSfarsit() {
		return dataSfarsit;
	}
	public void setDataSfarsit(Date dataSfarsit) {
		this.dataSfarsit = dataSfarsit;
	}
	public String getDescriere() {
		return descriere;
	}
	public void setDescriere(String descriere) {
		this.descriere = descriere;
	}
	
	
}
