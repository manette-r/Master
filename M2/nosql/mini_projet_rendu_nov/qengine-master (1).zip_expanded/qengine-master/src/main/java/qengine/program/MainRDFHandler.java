package qengine.program;

import org.eclipse.rdf4j.model.Statement;
import org.eclipse.rdf4j.rio.helpers.AbstractRDFHandler;
/**
 * Le RDFHandler intervient lors du parsing de données et permet d'appliquer un traitement pour chaque élément lu par le parseur.
 * 
 * <p>
 * Ce qui servira surtout dans le programme est la méthode {@link #handleStatement(Statement)} qui va permettre de traiter chaque triple lu.
 * </p>
 * <p>
 * À adapter/réécrire selon vos traitements.
 * </p>
 */
public final class MainRDFHandler extends AbstractRDFHandler {

	Dictionary dictionary;
	GiantTable gT;
	
	public MainRDFHandler() {
		dictionary = new Dictionary();
		gT = new GiantTable(dictionary);
	}
	
	@Override
	public void handleStatement(Statement st) {
		
		System.out.println("\n" + st.getSubject() + "\t " + st.getPredicate() + "\t " + st.getObject()+"\n");
		
		//Ajout du Statement (Object, Subject et Predicate dans le dictionnaire et la Giant Table)
		gT.addSPO(st);
		
	};
	
}