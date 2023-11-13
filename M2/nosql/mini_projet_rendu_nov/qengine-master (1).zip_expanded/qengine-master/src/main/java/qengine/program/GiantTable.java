package qengine.program;

import java.util.*;

import org.eclipse.rdf4j.model.Statement;

public class GiantTable {

	public List<Integer> subject;
	public List<Integer> predicate;
	public List<Integer> object;
	private List<List<Integer>> giantTable;
	public int storage;
	public Dictionary d;
	
	/**
	 * Constructeur de Giant Table avec tous les attributs en public sauf le giantTable 
	 * @param d Dictionnaire 
	 */
	public GiantTable(Dictionary d) {
		
		this.d =d;
		subject = new ArrayList<Integer>();
		predicate = new ArrayList<Integer>();
		object = new ArrayList<Integer>();
		storage = 1; //par défaut 
	}
	
	/**
	 * Prend en entrée l'index pour réaliser la Giant Table, et retourne les colonnes dans l'ordre voulu
	 * @param index de 1 à 6 : sop, spo, osp, ops, pos, pso
	 * @return une liste de trois listes (subject, object et predicate dans l'ordre voulu par l'index)
	 */
	public void hexastore(int index){

		if(index>0 && index<7) {
			
			switch(index){
				case 1 : //sop
					giantTable = new ArrayList<List<Integer>>(Arrays.asList(subject,object,predicate));
					storage = 1;
					break;
				case 2 : //spo
					giantTable = new ArrayList<List<Integer>>(Arrays.asList(subject,predicate,object));
					storage = 2;
					break;
				case 3 : //osp
					giantTable = new ArrayList<List<Integer>>(Arrays.asList(object,subject,predicate));
					storage = 3;
					break;
				case 4 : //ops
					giantTable = new ArrayList<List<Integer>>(Arrays.asList(object,predicate,subject));
					storage = 4;
					break;
				case 5 : //pos
					giantTable = new ArrayList<List<Integer>>(Arrays.asList(predicate,object,subject));
					storage = 5;
					break;					
				default : //pso
					giantTable = new ArrayList<List<Integer>>(Arrays.asList(predicate,subject,object));
					storage = 6;
			}
			
		}
	}
	
	/**
	 * Ajouter un Statement dans les listes de Subject, Object et Predicate 
	 * @param st Statement 
	 */
	public void addSPO(Statement st) {
		List<Integer> sop = d.transformStatement(st);
				
		subject.add(sop.get(0));
		object.add(sop.get(1));
		predicate.add(sop.get(2));
		
	}
	
	/**
	 * Afficher la Giant Table dans le terminal 
	 */
	public void printGiantT() {
		
		//actualisation
		hexastore(storage);
		
		//Affichage de l'hexastorage choisi
		List<String> hexastore_string = new ArrayList<String>(Arrays.asList("S\tO\tP", "S\tP\tO", "O\tS\tP", "O\tP\tS", "P\tO\tS", "P\tS\tO"));
		System.out.println("\n\n"+hexastore_string.get(storage-1)+"\n");
		
		switch(storage){
		case 1 : //sop
			for(int i = 0; i < subject.size() ; i++) {System.out.println(subject.get(i)+"\t"+object.get(i)+"\t"+predicate.get(i));}
			break;
		case 2 : //spo
			for(int i = 0; i < subject.size() ; i++) {System.out.println(subject.get(i)+"\t"+predicate.get(i)+"\t"+object.get(i));}
			break;
		case 3 : //osp
			for(int i = 0; i < subject.size() ; i++) {System.out.println(object.get(i)+"\t"+subject.get(i)+"\t"+predicate.get(i));}
			break;
		case 4 : //ops
			for(int i = 0; i < subject.size() ; i++) {System.out.println(object.get(i)+"\t"+predicate.get(i)+"\t"+subject.get(i));}
			break;
		case 5 : //pos
			for(int i = 0; i < subject.size() ; i++) {System.out.println(predicate.get(i)+"\t"+object.get(i)+"\t"+subject.get(i));}
			break;					
		default : //pso
			for(int i = 0; i < subject.size() ; i++) {System.out.println(predicate.get(i)+"\t"+subject.get(i)+"\t"+object.get(i));}

	}
	}
	
	/**
	 * Retourne la giant table après l'avoir actualisée 
	 * @return giant table, liste de listes d'entiers 
	 */
	public List<List<Integer>> get(){
	
		hexastore(storage);
		return giantTable;
	}
	
	
}
