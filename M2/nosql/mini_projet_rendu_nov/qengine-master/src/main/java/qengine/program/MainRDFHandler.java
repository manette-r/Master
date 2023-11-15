package qengine.program;

import org.eclipse.rdf4j.model.Statement;
import org.eclipse.rdf4j.rio.helpers.AbstractRDFHandler;
/**
 * Le RDFHandler intervient lors du parsing de données et permet d'appliquer un traitement pour chaque élément lu par le parseur.
 * 
 */
public final class MainRDFHandler extends AbstractRDFHandler {

	private Dictionary dictionary;
	private Index index;
	
	public MainRDFHandler() {
		dictionary = new Dictionary();
		index = new Index(1, dictionary);//par defaut 1 : spo 
	}
	
	@Override
	public void handleStatement(Statement st) {
		
		System.out.println("\n" + st.getSubject() + "\t " + st.getPredicate() + "\t " + st.getObject()+"\n");
		
		//Ajout du Statement dans l'index
		index.addstatement(st);
		
	};
	
	/**
	 * Avoir le dictionnaire 
	 * @return dictionnaire classe Dictionary
	 */
	public Dictionary getDico() {
		return dictionary;
	}
	
	/**
	 * Avoir l'index 
	 * @return index classe Index 
	 */
	public Index getIndex() {
		return index;
	}
	
}