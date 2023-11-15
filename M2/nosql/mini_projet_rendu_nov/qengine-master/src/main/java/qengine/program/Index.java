package qengine.program;

import java.util.*;

import org.eclipse.rdf4j.model.Statement;

/**
 * Index : liste de triples (et triple-binaire, triple-unaire)
 * Triple binaire avec deux éléments Subjects, Objects ou Predicate mais stockés dans un Triple 
 */
public class Index {
	private int index_actual; 
	private String index_string;//signification de l'index_actual 
	public List<String> hexastorage, binarystorage, uniquestorage; 
	private List<Triple> list_index;
	private Dictionary dictionary;
	
	public Index(int index_actual, Dictionary d) {
		
		dictionary = d;
		hexastorage = new ArrayList<String>(Arrays.asList("spo", "sop","pso","ops","pos","osp"));
		binarystorage = new ArrayList<String>(Arrays.asList("sp", "so","ps","op","po","os"));
		uniquestorage = new ArrayList<String>(Arrays.asList("s","o","p"));
		
		list_index = new ArrayList<Triple>();
		
		//on paramètre index_actual et index_string 
		changeIndex(index_actual, true);
		
	}
	
	/**
	 * Retourne les placements du sujet, l'objet et le predicat avec un entier associé à leur position dans le triple
	 * @return Liste d'Integer
	 */
	private List<Integer> getOrder(){
		
		//ordre index de 1 à 12
		List<List<Integer>> hexastorage_order = new ArrayList<List<Integer>>();
		hexastorage_order.add(new ArrayList<Integer>(Arrays.asList(0,2,1)));//spo
		hexastorage_order.add(new ArrayList<Integer>(Arrays.asList(0,1,2)));//sop
		hexastorage_order.add(new ArrayList<Integer>(Arrays.asList(1,2,0)));//pso
		hexastorage_order.add(new ArrayList<Integer>(Arrays.asList(2,0,1)));//ops
		hexastorage_order.add(new ArrayList<Integer>(Arrays.asList(2,1,0)));//pos
		hexastorage_order.add(new ArrayList<Integer>(Arrays.asList(1,0,2)));//osp
		
		if(index_actual<7)
			return hexastorage_order.get(index_actual-1);
		else if(index_actual<13)
			return hexastorage_order.get(index_actual-7);
		else {
			//index de 13 a 15 on prend des ordres d'index avec s o et p en premier
			if(index_actual == 13)
				return hexastorage_order.get(0);
			else if(index_actual == 14)
				return hexastorage_order.get(3);
			else 
				return hexastorage_order.get(2);
		}		

	}
	
	/**
	 * Affichage de l'index
	 */
	public void printIndex() {
		
		//Si c'est triple, binaire ou unaire 
		if(index_actual <7)
			System.out.println("\n"+index_string.substring(0, 1)+"\t"+ index_string.substring(1, 2)+"\t"+ index_string.substring(2, 3));
		else if(index_actual <13)
			System.out.println("\n"+index_string.substring(0, 1)+"\t"+ index_string.substring(1, 2));
		else 
			System.out.println("\n"+index_string);

		for(Triple t : list_index) {
			List<Integer> list_triple = t.getTriple();
			
			String affichage = "(";
			
			//Si c'est triple, binaire ou unaire 
			if(index_actual<7) 
				affichage+=list_triple.get(0)+",\t"+list_triple.get(1)+",\t"+list_triple.get(2);
			else if(index_actual < 13)
				affichage+=list_triple.get(0)+",\t"+list_triple.get(1);
			else 
				affichage+=list_triple.get(0);
			
			affichage+=")";
			
			System.out.println(affichage);
			
		}
	}

	/**
	 * Supprime un triple de l'index avec sa position dans la liste 
	 * @param position_statement entier
	 */
	public void deleteStatement(int position_statement) {
		Triple t_remove = list_index.remove(position_statement);
		System.out.println("Le triple "+t_remove.getTriple()+ " a bien été supprimé de l'index.");
	}
	
	/**
	 * Ajoute un statement en le traduisant via le dictionnaire pour avoir les entiers correspondant
	 * @param st Statement 
	 */
	public void addstatement(Statement st) {
		
		//Liste d'Integer dans l'ordre Subject Object Predicate
		List<Integer> values = dictionary.addStatement(st);
		list_index.add(new Triple(values, getOrder()));
	}
	
	/**
	 * Changer les placements des éléments dans les triples 
	 */
	private void changeTriples() {
		List<Triple> list_index_buffer = new ArrayList<Triple>();
		List<Integer> triple_buffer;
		for (Triple t : list_index) {
			
			//liste des éléments dans l'ordre sop 
			triple_buffer = new ArrayList<Integer>(Arrays.asList(t.getSubject(), t.getObject(), t.getPredicate()));

			list_index_buffer.add(new Triple(triple_buffer, getOrder()));
		}
		list_index = list_index_buffer;
	}
	
	/**
	 * Changer ou initialiser les paramètres index_actual, index_string et les tuples
	 * @param index_wanted
	 * @param initialisation si la méthode a été appelée depuis le constructeur 
	 */
	public void changeIndex(int index_wanted, boolean initialisation) {
		
		//On vérifie que ce ne soit pas le même index 
		if(index_wanted != index_actual || initialisation) {
			
			if(initialisation)
				index_actual = index_wanted;
			
			//on considère que l'index donné est entre 1 et 15 
			//on vérifie que l'index soit compatible avec l'ancien 
			if(index_wanted<7 && index_actual <7) {
				index_actual = index_wanted;
				index_string = hexastorage.get(index_actual-1);
				changeTriples();
			}
			else if(6<index_wanted && index_wanted<13 && index_actual<13) {
				//index wanted doit être entre 7 et 12 car on ne pas changer un index binaire en triple  
				//ex : S P -> S P O pas possible car O null 
				String index_w_string = binarystorage.get(index_wanted-7);
				boolean allowed = true;
				if(6<index_actual) {
					//alors les deux index sont binaires 
					//on vérifie que le changement est possible 
					//ex : PO -> SP pas possible car il nous manque S 
					
					for(char c : index_w_string.toCharArray()) {
						//on vérifie que toutes les elements de index_w sont contenus dans index_actual à partir des équivalents en String 
						if(!index_string.contains(String.valueOf(c))) {
							allowed = false;
						}
							
					}
				}
				
				if(allowed) {
					index_actual = index_wanted;
					index_string = index_w_string;
					changeTriples();
				}
				else 
					System.out.println("\nChangement non autorisé.\n");

			}
			else if(12<index_wanted && index_wanted<16 && index_actual<16) {
				//index wanted doit être entre 13 et 15 car on ne pas changer un index unique en triple en binaire 
				//ex : S -> S O pas possible car O null 
				
				String index_w_string = uniquestorage.get(index_wanted-13);
				boolean allowed = true;
				
				if(!(12<index_actual && index_w_string.equals(index_string))) 
					//alors les deux index sont unaires 
					//on vérifie que le changement est possible 
					//ex : P -> S pas possible car il nous manque S 
					allowed = false;
					
				if(allowed) {
					index_actual = index_wanted;
					index_string = index_w_string;
					changeTriples();
				}
				else 
					System.out.println("\nChangement non autorisé.\n");

			}
			else 
				System.out.println("\nChangement non autorisé.\n");
		}

	}
	
	/**
	 * Retourne le dictionnaire 
	 * @return Dictionary
	 */
	public Dictionary getDico() {
		return dictionary;
	}
	
	/**
	 * Retourne l'index
	 * @return liste de triples 
	 */
	public List<Triple> getList(){
		return list_index;
	}
	
	/**
	 * Retourne l'index actuel 
	 * @return entier 
	 */
	public int getIndex() {
		return index_actual;
	}
	
	/**
	 * Retourne le type d'index correspondant dans une indexation hexastorage
	 * @return entier 
	 */
	public String getIndexHexa() {
		return index_string;
	}
}
