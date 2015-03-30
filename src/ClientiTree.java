import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;
import java.util.TreeMap;


public class ClientiTree extends Observable{
	
	private List<Observer> obs = new ArrayList<>();
	TreeMap<Integer, Client> tree = new TreeMap<>();
	private static ClientiTree instance;
	
	private ClientiTree(){
	}
	
	public void addClient(Client client){
		tree.put(client.getId(), client);
	}
	public int getSize(){
		return tree.size();
	}
	
	public Client getClient(int id){
		return tree.get(id);
	}
	
	public static ClientiTree getInstance(){
		if(instance == null){
			instance =  new ClientiTree();
		}
		return instance;
	}
	
	public Set<Map.Entry<Integer,Client>> getEntrySet(){
		return tree.entrySet();
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
