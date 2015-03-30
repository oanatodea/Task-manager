
public class Client {

	@AnnotationColumns(columnName  = "ID")
	private int id;
	@AnnotationColumns(columnName  = "Nume")
	private String nume;
	@AnnotationColumns(columnName  = "Prenume")
	private String prenume;
	@AnnotationColumns(columnName  = "Email")
	private String email;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNume() {
		return nume;
	}
	public void setNume(String nume) {
		this.nume = nume;
	}
	public String getPrenume() {
		return prenume;
	}
	public void setPrenume(String prenume) {
		this.prenume = prenume;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
