package unidade3;
import java.util.*;

public class Hash {
	public static void main(String[] args) {
		Hashtable<Integer,String> table = new Hashtable<>(10);
		table.put(100, "Bob esponja");
		table.put(123, "Patrick");
		table.put(321, "Sandy");
		table.put(555, "Lula Molusco");
		table.put(777, "Gary");
		
		
		for ( Integer key : table.keySet()) {
			System.out.println( key.hashCode() % 10+ "\t" + key + "\t" + table.get(key));
		}
		
		// se o chave for string
		for ( String key : table.keySet()) {
			System.out.println(  key.hashCode() % 10+ "\t" + key + "\t" + table.get(key));
		}
		
		
		
		
		
	}

}
