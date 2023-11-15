package qengine.program;

import java.util.ArrayList;
import java.util.List;

public class Indexe {
	
	//OPS
	List<Triplet<Integer, Integer, Integer>> giantTable;
	
	
	public Indexe() {
		
		giantTable = new ArrayList<>();
		
	}
	
	
	
	
	//OPS
	public List<Triplet<Integer, Integer, Integer>> remplissageGiantTable(Integer a,Integer b,Integer c ) {
		
		Triplet<Integer,Integer,Integer> triplet = Triplet.of(a, b, c);
		giantTable.add(triplet);
		
		return giantTable;
		
		}
	
	
	
	public void afficheGiantT() {
		System.out.println("Affichage Giant Table \n :" + giantTable);
	}
	
	
	 // OPS dans la Giant Table 
	//  hexastorage 
	
	public List<Triplet<Integer, Integer, Integer>> getIndex(String type) {
		
		List<Triplet<Integer, Integer, Integer>> index_SPO = new ArrayList<>();
		
		
		
		for (int i = 0; i<giantTable.size();i++) {
			
			Triplet<Integer, Integer, Integer> tuple;
			
			switch(type) {
			
			case "SPO":
				 tuple = Triplet.of(giantTable.get(i).third, giantTable.get(i).second, giantTable.get(i).first);
				break;
				
			case "PSO":
				tuple = Triplet.of(giantTable.get(i).second, giantTable.get(i).third, giantTable.get(i).first); 
				break;
			
			case "POS":
				tuple = Triplet.of(giantTable.get(i).second, giantTable.get(i).first, giantTable.get(i).third); 
				break;
				
			case "OSP":
				tuple = Triplet.of(giantTable.get(i).first, giantTable.get(i).third, giantTable.get(i).second); 
				break;
				
			case "OPS":
				 tuple = Triplet.of(giantTable.get(i).first, giantTable.get(i).second, giantTable.get(i).third);
				break;
				
			case "SOP":
				tuple = Triplet.of(giantTable.get(i).third, giantTable.get(i).first, giantTable.get(i).second); 
				break;
				
			 default:
				 tuple = Triplet.of(giantTable.get(i).first, giantTable.get(i).second, giantTable.get(i).third);
				 
			}
			
			 
			index_SPO.add(tuple);
		}
		
		return index_SPO;
		
		
		
	}


	// binary index with counters
	
	public List<List<Triplet<Integer, Integer, Integer>>> get_index_SP(String type) {
		
		List<List<Triplet<Integer, Integer, Integer>>> liste = new ArrayList<>();
		List<Triplet<Integer, Integer, Integer>> index_SPO = new ArrayList<>();
		
		switch(type) {
		
		case "SP":
			index_SPO = getIndex("SPO");
			break;
			
		case "PS":
			index_SPO = getIndex("SPO");
			break;
		
		case "PO":
			index_SPO = getIndex("POS"); 
			break;
			
		case "OS":
			index_SPO = getIndex("OSP");
			break;
			
		case "OP":
			index_SPO = getIndex("OPS");
			break;
			
		case "SO":
			index_SPO = getIndex("SOP");
			break;
			
		 default:
			 index_SPO = getIndex("SOP");
			 
		}
		
		
		for(int i = 0; i< index_SPO.size(); i++  ) {
			
			int a = index_SPO.get(i).first;
			int b = index_SPO.get(i).second;
			
			List<Triplet<Integer, Integer, Integer>> index = new ArrayList<>();
			
			for(int j = 0; j< index_SPO.size(); j ++  ) {
			
				if(a == index_SPO.get(j).first ) {
					
					if(b == index_SPO.get(j).second){
						
						index.add(index_SPO.get(j));
					}
				}
			}
			
			liste.add(index);	}
		return liste;	 }
	
	
	
	
	

	
	
	
	

}
