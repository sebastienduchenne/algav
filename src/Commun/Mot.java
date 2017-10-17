package Commun;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Mot {
	
	public static void main(String[] args) throws IOException{
		String[] mots = null;
		
		//mots = Fichier.lireFichier(new File("Textes/exemple_de_base.txt"));
		mots = Fichier.lireFichiersRepertoire("Textes/Shakespeare");
		
		int occurrence = 0;
		
		ArrayList<String> a_mots = new ArrayList<String>();
		
		
		for(int i = 0; i < mots.length; i++){
			occurrence = 0;
			//System.out.println(mots[i]);
			for(int j = 0; j < a_mots.size(); j++){
				if(mots[i].equals(a_mots.get(j)) ){
					occurrence++;
				}
			}
			if(occurrence == 0){
				a_mots.add(mots[i]);
			}
		}
		
		System.out.println("nb : " + a_mots.size());
		
	}
}
