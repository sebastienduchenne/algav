package trie_hybride;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import Commun.*;
import patricia_trie.PatriciaTrie;

public class MainTH {
	
	public static void main(String[] args) {
		
		TrieHybride t = new TrieHybride(null);
		String mots[] = null;
		long start_ms = 0, end_ms = 0;
		
		//mots = shakespeare();
		mots = get_words_texte("exemple_de_base.txt");
		//mots = new String[] {"loup", "de"};
		
		
		/**ajout**/
		start_ms = System.currentTimeMillis();
		for(int i = 0; i < mots.length; i++){ t.addWord(mots[i].trim()); }
		end_ms = System.currentTimeMillis();
		//System.out.println("\ntps : "+(end_ms - start_ms));
		
		/**afficherTrie**/
		//System.out.println("\n********** affichage trie *******");
		t.afficherTrie();
		t.afficherTrie2("");
		//for(int i = 0; i < array.size(); i++){System.out.println((array.get(i).trim()));}
		//System.out.println("\n********** fin affichage trie *******");
		
		/**affichage des mots**/
		//System.out.println("\n********** liste des mots *******");
		//ArrayList<String> words = new ArrayList<String>();
		//t.listerMots(words, new String());
		//for(int i = 0; i < words.size(); i++){ System.out.println(words.get(i)); }
		//System.out.println("\n********** fin liste des mots *******");
		
		/**nombre de nil**/
		//System.out.println("\nnb nil : "+t.compterNil());
		
		/**nombre de mots**/
		//System.out.println("\nnb mots : " + t.compterMots());
		
		/**hauteur**/
		//System.out.println("\nhauteur : " + t.hauteur());
		
		/**recherche d'un mot**/
		String rec_mot = "affd";
		//System.out.println("\n'"+ rec_mot +  "' existe ? " + t.rechercher(rec_mot));
		
		/**profondeur moyenne**/
		//System.out.println("\nprofondeur moyenne :" + t.profondeurMoyenne(null, 0.0));
		
		/**prefixe**/
		String p = "dac";
		//System.out.println("\nnb mots avec préfixe " + p + " : " + t.prefixe(p));
		
		/**supprimer mot**/
//		System.out.println("\nnb mots AVANT : " + t.compterMots());
//		String s = "de";
//		System.out.println("\nsuppression de '"+s+"'");
//		System.out.println("\nres : " + t.suppression(s));
//		System.out.println("\n********** affichage trie *******");
//		//t.afficherTrie2("");
//		ArrayList<String> words = new ArrayList<String>();
//		t.listerMots(words, new String());
//		for(int i = 0; i < words.size(); i++){ System.out.println(words.get(i)); }
//		System.out.println("\n********** fin affichage trie *******");
//		System.out.println("\nnb mots APRES : " + t.compterMots());
		
		/**TH2PT**/
		PatriciaTrie pt = t.TH2PT(null, null);
		System.out.println("\n********** affichage trie *******");
		ArrayList<String> wordsPT = new ArrayList<String>();
		pt.listerMots(wordsPT, new String());
		System.out.println("\n********** fin affichage trie *******");
		
		/**addThenBalance**/
		//String w = "toto";
		//t.addWordAndBalance(w);
		
		/*
		 --primitives
		 
		 isEmpty : ok
		 val() : ok
		 addWord : ok
		 
		 --Fonctions avancées
		 
		 rechercher : ca à l'air bon
		 compterMot : ok
		 listerMot : ca à l'air bon
		 compterNil : ok
		 hauteur : ok
		 profondeurMoyenne : ca à l'air bon
		 prefixe : ca à l'air bon
		 supprimer : ca à l'air bon
		 
		 --Fonctions complexes
		 
		 TH2PT : A TESTER <---------------------------------
		 addThenBalance : 
		 
		 */
		
	}
	
	
	public static String[] get_words_texte(String texte) {
		String mots[] = null;
		try {mots = Fichier.lireFichier(new File("Textes/" + texte));}
		catch (IOException e) {e.printStackTrace();}
		return mots;
	}
	
	public static String[] shakespeare() {
		String mots[] = null;
		try {mots = Fichier.lireFichiersRepertoire("Textes/Shakespeare");}
		catch (IOException e) {e.printStackTrace();}
		return mots;
	}
	
}

