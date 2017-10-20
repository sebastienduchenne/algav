package trie_hybride;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.sound.midi.Synthesizer;

import Commun.*;

public class MainTH {
	
	public static void main(String[] args) {
		
		TrieHybride t = new TrieHybride();
		String mots[] = null;
		
		
		mots = shakespeare();
		//mots = get_words_texte("exemple_de_base.txt");
		//mots = get_words_texte("comedy_errors_1000w.txt");
		//mots = new String[] {"toto"};
		
		
		//ajout
		for(int i = 0; i < mots.length; i++){ t.addWord(mots[i].trim()); }
		
		//afficherTrie
		System.out.println("\n********** affichage trie *******");
		//t.afficherTrie2("");
		//for(int i = 0; i < array.size(); i++){System.out.println((array.get(i).trim()));}
		System.out.println("\n********** fin affichage trie *******");
		
		
		//affichage des mots
		System.out.println("\n********** liste des mots *******");
		ArrayList<String> words = new ArrayList<String>();
		t.listerMots(words, new String());
		for(int i = 0; i < words.size(); i++){ System.out.println(words.get(i)); }
		System.out.println("\n********** fin liste des mots *******");
		
		//nombre de nil
		//System.out.println("\nnb nil : "+t.compterNil());
		
		//nombre de mots
		//System.out.println("\nnb mots : " + t.compterMots());
		
		//hauteur
		//System.out.println("\nhauteur : " + t.hauteur());
		
		//recherche d'un mot
		String rec_mot = "affd";
		//System.out.println("\n'"+ rec_mot +  "' existe ? " + t.rechercher(rec_mot));
		
		//profondeur moyenne
		//System.out.println(t.profondeurMoyenne(null, null));
		
		//prefixe
		String p = "y";
		System.out.println("\nnb mots avec préfixe " + p + " : " + t.prefixe(p));
		
		//supprimer mot
		String s = "dactylo";
		//System.out.println("\nsuppression de '"+s+"'");
		//System.out.println("\n********** affichage trie *******");
		//t.afficherTrie2("");
		//for(int i = 0; i < array.size(); i++){System.out.println((array.get(i).trim()));}
		//System.out.println("\n********** fin affichage trie *******");
		
		//TH2PT
		
		
		//addThenBalance
		
		
		/*
		 prem(cle)
		 reste(cle)
		 car(cle, i)
		 lgueur(cle)
		 val(A)
		 
		 addWord : ok
		 isEmpty : ok
		 rechercher : ca à l'air bon
		 compterMot : ok
		 listerMot : ca à l'air bon
		 compterNil : ok
		 hauteur : ok
		 profondeurMoyenne : 
		 prefixe : : ca à l'air bon
		 supprimer : 
		 
		 TH2PT : 
		 
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
