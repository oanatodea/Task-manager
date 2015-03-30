
public class Event {

	public enum EventType{ creeaza, modifica, finalizat,nefinalizat};
	private EventType tip;
	private Proiect proiect;
	private Angajat user;
	private int row;
	
	public Event(EventType tip, Proiect proiect,Angajat user, int row){
		this.tip = tip;
		this.proiect = proiect;
		this.user=user;
		this.row=row;
	}

	public EventType getTip() {
		return tip;
	}

	public Proiect getProiect() {
		return proiect;
	}

	public Angajat getUser() {
		return user;
	}

	public int getRow() {
		return row;
	}
	
	
}
