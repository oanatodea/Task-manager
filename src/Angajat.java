
public class Angajat {
	
	private String cnp;
	@AnnotationColumns(columnName  = "ID")
	private int id;
	@AnnotationColumns(columnName  = "Nume")
	private String nume;
	@AnnotationColumns(columnName  = "Prenume")
	private String prenume;
	@AnnotationColumns(columnName  = "Varsta")
	private int varsta;
	@AnnotationColumns(columnName  = "Email")
	private String email;
	@AnnotationColumns(columnName  = "Departament")
	private String departament;
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
	public int getVarsta() {
		return varsta;
	}
	public void setVarsta(int varsta) {
		this.varsta = varsta;
	}
	public String getCnp() {
		return cnp;
	}
	public void setCnp(String cnp) {
		this.cnp = cnp;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDepartament() {
		return departament;
	}
	public void setDepartament(String departament) {
		this.departament = departament;
	}
	
}
