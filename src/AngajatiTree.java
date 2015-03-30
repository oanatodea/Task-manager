import java.util.Map;
import java.util.Map.Entry;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;
import java.util.TreeMap;


public class AngajatiTree{

	TreeMap<Integer, Angajat> tree = new TreeMap<>();
	private static AngajatiTree instance;

	private AngajatiTree(){
		
	}
	
	public void addAngajat(Angajat angajat){
		tree.put(angajat.getId(), angajat);
	}
	
	public Angajat getAngajat(int id){
		return tree.get(id);
	}
	
	public Set<Map.Entry<Integer,Angajat>> getEntrySet(){
		return tree.entrySet();
	}
	

	public static AngajatiTree getInstance(){
		if(instance == null){
			instance =  new AngajatiTree();
		}
		return instance;
	}
	
	public Angajat getAngajat(String nume){
		for(Entry<Integer, Angajat> e : tree.entrySet()){
			if(e.getValue().getNume().equalsIgnoreCase(nume))
				return e.getValue();
		}
		return null;
	}
	
}
