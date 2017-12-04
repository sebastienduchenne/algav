package Commun;

import java.util.ArrayList;


public interface IFonctions {
	
	//fonctions de base
	
	public boolean isEmpty();
	
	public Trie addWord(String word);
	
	//fonctions avancees
	
	public boolean rechercher(String word);
	
	public int compterMots();
	
	public ArrayList<String> listerMots(ArrayList<String> words, String word);
	
	public int compterNil();
	
	public int hauteur();
	
	public Double profondeurMoyenne(ArrayList<Double> hauteurs, Double h);
	
	public int prefixe(String prefixe);
	
	public boolean suppression(String word);
	
}
