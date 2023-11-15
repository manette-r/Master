package qengine.program;

import java.util.ArrayList;
import java.util.List;

/**
 * Triple stockant le subject, l'object et la predicate trié dans order qui se trouvera dans l'index 
 * 
 */
public class Triple {
	private Integer object;
	private Integer subject;
	private Integer predicate;
	private List<Integer> order;// liste des 3 integers dans l'ordre choisi
	
	/**
	 * Constructeur de Triple 
	 * @param sop Liste des éléments Subject, Object et Predicat dans cet ordre
	 * @param order_wanted Liste d'Integers correspondant au placement des éléments de la liste de sop 
	 */
	public Triple(List<Integer> sop, List<Integer> order_wanted) {
		subject = sop.get(0);
		object = sop.get(1);
		predicate = sop.get(2);
		
		changeOrder(order_wanted.get(0), order_wanted.get(1), order_wanted.get(2));
		
	}
	
	/**
	 * Permet de ranger les éléments selon un ordre choisi
	 * @param ordre_s entier position de subject
	 * @param ordre_o entier position de object
	 * @param ordre_p entier position de predicate 
	 */
	private void changeOrder(int ordre_s, int ordre_o, int ordre_p){
		
		order = new ArrayList<Integer>();
		
		for(int i=0; i < 3; i++) {
			
			if(ordre_s == i)
				order.add(subject);
			else if(ordre_o == i)
				order.add(object);
			else
				order.add(predicate);	
		}
		
	}
	
	/**
	 * Retourne le triple 
	 * @return liste d'entiers
	 */
	public List<Integer> getTriple(){
		return order;
	}
	
	/**
	 * Retourne l'élément object
	 * @return integer
	 */
	public Integer getObject() {
		return object;
	}
	
	/**
	 * Change l'élément object
	 * @param i Integer
	 */
	public void setObject(Integer i) {
		object = i;
	}
	
	/**
	 * Retourne l'élément subject
	 * @return Integer
	 */
	public Integer getSubject() {
		return subject;
	}
	
	/**
	 * Change l'élément subject
	 * @param i Integer
	 */
	public void setSubject(Integer i) {
		subject = i;
	}
	
	/**
	 * Retourne l'élément predicate 
	 * @return Integer
	 */
	public Integer getPredicate() {
		return predicate;
	}
	
	/**
	 * Change l'élément predicate 
	 * @param i Integer
	 */
	public void setPredicate(Integer i) {
		predicate = i;
	}
}
