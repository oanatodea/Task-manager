import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;
import java.util.TreeMap;


public class ProiecteTree extends Observable{

	private List<Observer> obs = new ArrayList<>();
	TreeMap<String, Proiect> tree = new TreeMap<>();
	private static ProiecteTree instance;

	private ProiecteTree(){
		
	}
	
	public void addProiect(Proiect proiect){
		tree.put(proiect.getNume(), proiect);
	}
	
	public Proiect getProiect(String nume){
		return tree.get(nume);
	}
	
	public Set<Map.Entry<String,Proiect>> getEntrySet(){
		return tree.entrySet();
	}
	
	public static ProiecteTree getInstance(){
		if(instance == null){
			instance =  new ProiecteTree();
		}
		return instance;
	}
	
	public void addObserver(Observer o){
		obs.add(o);
	}
	
	public void notifyObservers(Event event){
		for(Observer i:obs){
			i.update(this,event);
		}	
	}
}
