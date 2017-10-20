package trie_hybride;


import java.util.ArrayList;

import patricia_trie.PatriciaTrie;
import Commun.*;


public class TrieHybride extends Trie implements ITrieHybride{

	private static int nbMots = 0;
	
	private char cle;
	private int valeur;

	private TrieHybride fils_gauche;
	private TrieHybride fils_centre;
	private TrieHybride fils_droit;
	
	
	public TrieHybride(){
		this.valeur = -1;
		this.cle = 0;
	}
	

	//primitives de base
	
	
	public boolean isEmpty(){
		boolean b = false;
		if(cle == 0) {
			b = true;
		}
		return b;
	}
	
	
	public int nbNoeud(){
		return 0;
	}
	
	
	@Override
	public TrieHybride addWord(String word){
		//System.out.println("*" + word.charAt(0));
		char l = word.charAt(0);
		
		/*
		SI taille == 1
			SI isEmpty
				cle = l
				n++
				valeur = n
			SI lettre < cle
				SI gauche == null
					gauche = new TH
				gauche.addWord(mot)
			SI lettre == cle && valeur != -1
				n++
				valeur = n
			SI lettre > cle
				SI droit == null
					droit = new TH
				droit.addWord(mot)
		SI taille > 1
			SI isEmpty
				cle = l
				centre = new TH
				centre.addWord(mot-1)
			SI lettre < cle
				SI gauche == null
					gauche = new TH
				gauche.addWord(mot)
			SI lettre == cle
				SI centre == null
					centre = new TH
				centre.addWord(mot-1)
			SI lettre > cle
				SI droit == null
					droit = new TH
				droit.addWord(mot)
		*/
		
		if(word.length() == 1) {
			if(isEmpty()) {
				cle = l;
				//System.out.println("ii."+l);
				nbMots++;
				valeur = nbMots;
			} else if(l < cle) {
				//System.out.println("ie1."+l);
				if(fils_gauche == null) {
					fils_gauche = new TrieHybride();
				}
				fils_gauche.addWord(word);
			} else if (l == cle && valeur == -1) {
				//System.out.println("ie2."+l);
				nbMots++;
				valeur = nbMots;
			} else if(l > cle) {
				//System.out.println("ie3."+l);
				if(fils_droit == null) {
					fils_droit = new TrieHybride();
				}
				fils_droit.addWord(word);
			}
		} else {
			if(isEmpty()) {
				//System.out.println("ei."+l);
				cle = l;
				fils_centre = new TrieHybride();
				fils_centre.addWord(word.substring(1));
			} else if(l < cle) {
				//System.out.println("ee1."+l);
				if(fils_gauche == null) {
					fils_gauche = new TrieHybride();
				}
				fils_gauche.addWord(word);
			} else if (l == cle) {
				//System.out.println("ee2."+l);
				if(fils_centre == null) {
					fils_centre = new TrieHybride();
				}
				fils_centre.addWord(word.substring(1));
			} else if(l > cle) {
				//System.out.println("ee3."+l);
				if(fils_droit == null) {
					fils_droit = new TrieHybride();
				}
				fils_droit.addWord(word);
			}
		}

		return this;
	}
	
	
	public void afficherTrie(){//affiche une suite de lettre
		System.out.println("-"+this.cle);
		
		if(this.fils_gauche != null){
			this.fils_gauche.afficherTrie();
		}
		if(this.fils_centre != null){
			this.fils_centre.afficherTrie();
		}
		if(this.fils_droit != null){
			this.fils_droit.afficherTrie();
		}
	}
	
	//affiche l'arbre verticalement
	public void afficherTrie2(String s){
		
		if(this.fils_droit != null){
			String ss = "";
			for(int i = 0; i < s.length(); i++) {ss += "-";}
			this.fils_droit.afficherTrie2(ss+"-");
		}

		
		if(this.fils_centre != null){
			//System.out.println("c");
			this.fils_centre.afficherTrie2(s + this.cle);
		} else {
			System.out.println(s+this.cle);//-------------
		}
		
		
		if(this.fils_gauche != null){
			//System.out.println("**");
			String ss = "";
			for(int i = 0; i < s.length(); i++) {ss += "-";}
			this.fils_gauche.afficherTrie2(ss+"-");
		
		}
		
	}
	
	
	/*************************/
	// Fonctions avancees
	/*************************/
	
	
	@Override
	public boolean rechercher(String word){
		char c;
		boolean b = false;
		
		if(word.length() > 0){
			c = word.charAt(0);
		} else {
			return false;
		}
		
		if(c < this.cle && this.fils_gauche != null){
			b = this.fils_gauche.rechercher(word);
		} else if(c > this.cle && this.fils_droit != null){
			b = this.fils_droit.rechercher(word);
		} else if(c == this.cle) {
			if(word.length() == 1 && this.valeur != -1){
				b = true;
			} else if(this.fils_centre != null){
				b = this.fils_centre.rechercher(word.substring(1));
			}
		} else {
			b = false;
		}
		
		return b;
	}
	
	
	@Override
	public int compterMots(){//OK
		int n = 0;
		if(this.valeur != -1) {
			n++;
		}
		
		if(this.fils_gauche != null){
			n = n + this.fils_gauche.compterMots();
		}
		
		if(this.fils_centre != null){
			n = n + this.fils_centre.compterMots();
		}
		
		if(this.fils_droit != null){
			n = n + this.fils_droit.compterMots();
		}
		
		return n;
		//return nbMots;
	}
	
	
	
	
	public ArrayList<String> listerMots(ArrayList<String> words, String s){
		if(this.fils_gauche != null){
			this.fils_gauche.listerMots(words, s);
		}
		
		if(this.valeur != -1 && this.fils_centre != null) {
			words.add(s + this.cle);
		}
		
		if(this.fils_centre != null){
			this.fils_centre.listerMots(words, s + this.cle);
		} else {
			words.add(s + this.cle);
		}
		
		if(this.fils_droit != null){
			this.fils_droit.listerMots(words, s);
		}
		
		return words;
	}


	@Override
	public int compterNil() {//OK
		int n = 0;
		
		if(this.fils_gauche == null){
			n++;
		} else {
			n = n + this.fils_gauche.compterNil();
		}
		
		if(this.fils_centre == null){
			n++;
		} else {
			n = n + this.fils_centre.compterNil();
		}
		
		if(this.fils_droit == null){
			n++;
		} else {
			n = n + this.fils_droit.compterNil();
		}
		
		return n;
	}


	@Override
	public int hauteur() {//OK
		int h = 0;
		int hd = 0;
		int hg = 0;
		
		if(this.fils_gauche != null){
			hg = 1 + this.fils_gauche.hauteur();
		}
		
		if(this.fils_centre != null){
			h = 1 + this.fils_centre.hauteur();
		}
		
		if(this.fils_droit != null){
			hd = 1 + this.fils_droit.hauteur();
		}
		
		//
		if(hg > h && hg > hd){
			//System.out.println("hg");
			return hg;
		} else if(hd > h && hd > hg){
			//System.out.println("hd");
			return hd;
		} else {
			//System.out.println("h");
			return h;
		}
	}


	@Override
	public Double profondeurMoyenne(ArrayList<Double> hauteurs, Double h) {
		return 0.0;
	}


	@Override
	public int prefixe(String word) {
		char c;
		int nbMot = 0;
		
		if(word.length() > 0)
			c = word.charAt(0);
		else 
			return 0;


		if(c < this.cle && this.fils_gauche != null){
			nbMot += this.fils_gauche.prefixe(word);
		} else if(c > this.cle && this.fils_droit != null){
			nbMot += this.fils_droit.prefixe(word);
		} else if(c == this.cle){
			if(word.length() == 1){
				if(this.valeur != -1){
					nbMot++;
				}
				if(this.fils_centre != null) {
					nbMot += this.fils_centre.compterMots();
				}
			} else {
				nbMot += this.fils_centre.prefixe(word.substring(1));
			}
		} else {
			nbMot = 0;
		}
		
		return nbMot;
	}


	@Override
	public Trie suppression(String word) {
		return this;
	}
	
	
	public TrieHybride getFinMot(String word){
		char c;
		TrieHybride t = null;
		
		if(word.length() > 0){
			c = word.charAt(0);
		} else {
			return null;
		}
		
		
		//System.out.println("1 - c : " + c + " - cle : " + this.cle);
		if(c < this.cle && this.fils_gauche != null){
			t = this.fils_gauche.getFinMot(word);
			//System.out.println("b:"+b);
		} else if(c > this.cle && this.fils_droit != null){
			//System.out.println("2 - c : " + c + " - cle : " + this.cle);
			t = this.fils_droit.getFinMot(word);
			//System.out.println("b:"+b);
		} else if(c == this.cle){
			if(word.length() == 1){
				//System.out.println("3 - c : " + c + " - cle : " + this.cle);
				if(this.valeur != -1 || this.valeur != -1){
					return this;
					//System.out.println("-");
				}
			} else {
				t = this.fils_centre.getFinMot(word.substring(1));
			}
		} else {
			t = null;
		}
		
		return t;
	}
	
	
	
	/**************************/
	//fonctions complexes
	/**************************/
	
	
	//parcourir le PATRICIA trie et fabriquer un trie hybride pour chaque lettre
	@Override
	public PatriciaTrie trieHybrideToPatriciaTrie() {
		PatriciaTrie pt = new PatriciaTrie();
		
		return pt;
	}
	
	
	@Override
	public TrieHybride addWordAndBalance(String word){
		return null;
	}
	
	
	@Override
	public boolean isBalanced(ArrayList<Double> hauteurs, Double h){
		return false;
	}
	
	
	@Override
	public TrieHybride balance(){

		return null;
	}


	/*
	 * guetters, setters et autres
	 * */
	

	public char getCle() {
		return cle;
	}


	public void setCle(char cle) {
		this.cle = cle;
	}
	
	
	public int getValeur() {
		return valeur;
	}

	
	public void setValeur(int valeur) {
		this.valeur = valeur;
	}
	
	
	public int getNbMots() {
		return nbMots;
	}


	public TrieHybride getFils_gauche() {
		return fils_gauche;
	}


	public void setFils_gauche(TrieHybride fils_gauche) {
		this.fils_gauche = fils_gauche;
	}


	public TrieHybride getFils_centre() {
		return fils_centre;
	}


	public void setFils_centre(TrieHybride fils_centre) {
		this.fils_centre = fils_centre;
	}


	public TrieHybride getFils_droit() {
		return fils_droit;
	}


	public void setFils_droit(TrieHybride fils_droite) {
		this.fils_droit = fils_droite;
	}

}
