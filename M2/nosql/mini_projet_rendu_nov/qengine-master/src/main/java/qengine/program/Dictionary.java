package qengine.program;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.eclipse.rdf4j.model.Statement;
/**
 * Classe pour générer un dictionnaire : un entier associé à chaque mot.
 */
public class Dictionary extends HashMap<Integer, String>{
	
	private static final long serialVersionUID = 1L;
	
	private HashMap<Integer,String> dictionary; 
	private int size; 
	
	public Dictionary() {
		dictionary = new HashMap<Integer, String>();
		size = 1;//Le premier mot sera associé à 1
	}

	/**
	 * Ajoute une chaîne de caractères au dictionnaire 
	 * @param str Chaîne de caractères String 
	 * @return Entier correspondant à la chaîne dans le dictionnaire 
	 */
	public Integer addString(String str) {
		
		//Vérification que la chaîne de caractère ne soit pas déjà dans le dictionnaire 
		if(!dictionary.containsValue(str)) {
			
			//Ajout dans le dictionnaire
			dictionary.put(size, str);
			System.out.println(str+" a été ajouté au dictionnaire avec l'entier :"+Integer.toString(size));
			size+=1;
			return size-1;//comme on vient d'augmenter le size pour le prochain mot on applique -1 pour notre mot 
		}
		else {
			System.out.println(str+" existe déjà dans le dictionnaire");
			return getKey(str);
		}
			
	}
	
	/**
	 * Ajout des attributs Subject, Object et Predicate d'un Statement dans le dictionnaire
	 * @param st Statement à traduire 
	 * @return La liste des entiers correspondant aux éléments "traduits" dans le dictionnaire
	 */
	public List<Integer> addStatement(Statement st) {
		
		//liste des éléments à traduire 
		List<Object> list_st = new ArrayList<Object>();
		list_st.add(st.getSubject());
		list_st.add(st.getPredicate());
		list_st.add(st.getObject());
		
		//liste des entiers obtenus
		List<Integer> integers_value = new ArrayList<Integer>();

		for(int i = 0; i< 3; i++) {
			
			String value = (list_st.get(i)).toString();
			integers_value.add(addString(value));	
		}
		
		return integers_value;
	}
	
	/**
	 * Affichage des valeurs du dictionnaire 
	 */
	public void printDico() {		
		
		for(int i = 1; i < size ; i++) {
			System.out.println(Integer.toString(i)+" : "+dictionary.get(i));
		}

	}
	
	/**
	 * Obtenir la clef d'une valeur dans le dictionnaire (on est sûr que la valeur y soit)
	 * @param value Mot présent dans le dictionnaire
	 * @return Integer 
	 */
	public int getKey(String value) {
		
		for (Integer i : dictionary.keySet()) {
			if(value.compareTo(dictionary.get(i)) == 0)
				return i;	
		}
		
		//ne retourne jamais car la value est forcément dedans
		return -1;
	}
	
	/**
	 * Retourne une HashMap avec les entiers en clef et les mots en value
	 * @return HashMap 
	 */
	public HashMap<Integer,String> getDico() {
		return dictionary;
	}
	
	/**
	 * Avoir la taille du dictionnaire 
	 * @return un entier 
	 */
	public int getSize() {
		return size-1;
	}

	
}
