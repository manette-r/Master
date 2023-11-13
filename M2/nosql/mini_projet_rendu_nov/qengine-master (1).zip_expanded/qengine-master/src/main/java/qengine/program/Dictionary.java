package qengine.program;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.eclipse.rdf4j.model.Statement;

public class Dictionary extends HashMap<Integer, String>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	HashMap<Integer,String> dictionary; 
	
	public Dictionary() {
		dictionary = new HashMap<Integer, String>();
	}
	
	/**
	 * Transforme le Statement donné en une liste d'entiers correspondant aux subject,object et predicate du dictionnaire
	 * @param st Statement à traduire en entiers
	 * @return Liste d'entiers : Subject, Object et Predicate
	 */
	public List<Integer> transformStatement(Statement st) {
		List<Integer> sop = new ArrayList<Integer>();
		
		//on ajoute le Statement au doctionnaire 
		addStatement(st);
		
		//on va chercher leur entier attribué
		sop.add(getKey(st.getSubject().toString()));
		sop.add(getKey(st.getObject().toString()));
		sop.add(getKey(st.getPredicate().stringValue()));//toString n'est plus disponible pour Predicate
		
		return sop;
	}
	
	/**
	 * Ajout des attributs Subject, Object et Predicate d'un Statement dans le dictionnaire
	 * @param st Statement 
	 */
	public void addStatement(Statement st) {
		
		List<Object> list = new ArrayList<Object>();
		list.add(st.getSubject());
		list.add(st.getPredicate());
		list.add(st.getObject());
		
		int size = dictionary.size();
		

		for(int i = 0; i< 3; i++) {
			
			String value = (list.get(i)).toString();
			
			//Vérification que la chaîne de caractère ne soit pas déjà dans le dictionnaire 
			if(!dictionary.containsValue(value)) {
				
				//Ajout dans le dictionnaire
				dictionary.put(size, value);
				System.out.println(value+" a été ajouté au dictionnaire avec l'entier :"+Integer.toString(size));
				size+=1;
			}
			else 
				System.out.println(value+" existe déjà dans le dictionnaire");
		}
	}
	
	/**
	 * Affichage des valeurs du dictionnaire 
	 */
	public void printDico() {
		
		for(int i = 0; i < dictionary.size(); i++) {
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

	
}
