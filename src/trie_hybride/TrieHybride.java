package trie_hybride;


import java.util.ArrayList;

import patricia_trie.PatriciaTrie;
import Commun.*;


public class TrieHybride extends Trie implements ITrieHybride{
	
	private static int nbMots = 0;
	private char cle;
	private boolean isFinMot;

	private TrieHybride pere;
	
	private TrieHybride fils_gauche;
	private TrieHybride fils_centre;
	private TrieHybride fils_droit;
	
	
	public TrieHybride(TrieHybride pere){
		this.pere = pere;
		this.isFinMot = false;
		this.cle = 0;
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
			this.fils_centre.afficherTrie2(s + this.cle);
		} else {
			System.out.println(s+this.cle);//-------------
		}
		
		if(this.fils_gauche != null){
			String ss = "";
			for(int i = 0; i < s.length(); i++) {ss += "-";}
			this.fils_gauche.afficherTrie2(ss+"-");
		
		}
	}
	

	/**************************/
	/*** Primitives de base ***/
	/**************************/
	
	
	public boolean isEmpty(){
		boolean b = false;
		if(cle == 0) {
			b = true;
		}
		return b;
	}
	
	public char val() {
		return cle;
	}
	

	@Override
	public TrieHybride addWord(String word){
		char l = word.charAt(0);
		
		if(word.length() == 1) {
			if(isEmpty()) {
				cle = l;
				nbMots++;
				isFinMot = true;
			} else if(l < cle) {
				if(fils_gauche == null) {
					fils_gauche = new TrieHybride(this);
				}
				fils_gauche.addWord(word);
			} else if (l == cle && !isFinMot) {
				nbMots++;
				isFinMot = true;
			} else if(l > cle) {
				if(fils_droit == null) {
					fils_droit = new TrieHybride(this);
				}
				fils_droit.addWord(word);
			}
		} else {
			if(isEmpty()) {
				cle = l;
				fils_centre = new TrieHybride(this);
				fils_centre.addWord(word.substring(1));
			} else if(l < cle) {
				if(fils_gauche == null) {
					fils_gauche = new TrieHybride(this);
				}
				fils_gauche.addWord(word);
			} else if (l == cle) {
				if(fils_centre == null) {
					fils_centre = new TrieHybride(this);
				}
				fils_centre.addWord(word.substring(1));
			} else if(l > cle) {
				if(fils_droit == null) {
					fils_droit = new TrieHybride(this);
				}
				fils_droit.addWord(word);
			}
		}

		return this;
	}
	
	
	
	/**************************/
	/*** Fonctions avancees ***/
	/**************************/
	
	
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
			if(word.length() == 1 && isFinMot){
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
	public int compterMots(){
		if(pere != null) {
			int n = 0;
			if(isFinMot) {
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
		} else {
			return nbMots;
		}
	}
	
	
	@Override
	public ArrayList<String> listerMots(ArrayList<String> words, String s){
		if(this.fils_gauche != null){
			this.fils_gauche.listerMots(words, s);
		}
		
		if(isFinMot && this.fils_centre != null) {
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
	public int compterNil() {
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
	public int hauteur() {
		int h = 0;//hauteur fils centre
		int hd = 0;//hauteur fils droit
		int hg = 0;//hauteur fils gauche
		
		if(this.fils_gauche != null){
			hg = 1 + this.fils_gauche.hauteur();
		}
		
		if(this.fils_centre != null){
			h = 1 + this.fils_centre.hauteur();
		}
		
		if(this.fils_droit != null){
			hd = 1 + this.fils_droit.hauteur();
		}
		
		
		if(hg > h && hg > hd){
			return hg;
		} else if(hd > h && hd > hg){
			return hd;
		} else {
			return h;
		}
	}


	@Override
	public Double profondeurMoyenne(ArrayList<Double> hauteurs, Double h) {
		Double hauteur = new Double(h);
		
		if(this.getPere() == null) { // si this est racine
			hauteurs = new ArrayList<Double>();
			hauteur = 0.0;
		}
		
		if(this.fils_gauche != null){
			hauteur++;
			this.fils_gauche.profondeurMoyenne(hauteurs, hauteur);
		}
		
		if(this.fils_centre != null){
			hauteur++;
			this.fils_centre.profondeurMoyenne(hauteurs, hauteur);
		} else {
			hauteurs.add(hauteur);
		}
		
		if(this.fils_droit != null){
			hauteur++;
			this.fils_droit.profondeurMoyenne(hauteurs, hauteur);
		}
		
		if(this.getPere() == null) { // si this est racine
			Double somme = 0.0;
			for(int i = 0; i < hauteurs.size(); i++){ 
				somme = somme + hauteurs.get(i);
			}
			Double moyenne = somme/hauteurs.size();
			return moyenne;
		}
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
				if(isFinMot){
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
	public boolean suppression(String word) {
		if(word == null || word.equals("")) {
			return false;
		}
		
		char c;
		boolean b = false;
		
		if(word.length() > 0){
			c = word.charAt(0);
		} else {
			return false;
		}
		
		if(c < cle && fils_gauche != null){
			b = fils_gauche.suppression(word);
		} else if(c > cle && fils_droit != null){
			b = fils_droit.suppression(word);
		} else if(c == cle) {
			if(word.length() == 1 && isFinMot){
				System.out.println("FOUND");
				b = deleteWord();
				if(b) {
					nbMots--;
					return true;
				} else {
					return false;
				}
			} else if(fils_centre != null){
				b = fils_centre.suppression(word.substring(1));
			}
		} else {
			b = false;
		}
		
		return b;
	}
	
	
	private void copieTH(TrieHybride th1, TrieHybride th2) {
		th1.cle = th2.cle;
		th1.isFinMot = th2.isFinMot;
		th1.pere = th2.pere;
		th1.fils_gauche = th2.fils_gauche;
		th1.fils_centre = th2.fils_centre;
		th1.fils_droit = th2.fils_droit;
	}
	
	private boolean deleteWord() {
		//si a un/des fils
		if(fils_gauche != null || fils_centre != null || fils_droit != null) {
			if(pere == null) {
				if(fils_gauche != null && fils_centre == null && fils_droit == null) {
					copieTH(this, fils_gauche);
					return true;
				} else if(fils_gauche == null && fils_centre == null && fils_droit != null) {
					copieTH(this, fils_droit);
					return true;
				} else if(fils_gauche != null && fils_centre == null && fils_droit != null) {
					copieTH(this, fils_gauche);
					
					TrieHybride c = fils_gauche;
					while(c.fils_droit != null) {
						c = c.fils_droit;
					}
					
					c.fils_droit = fils_droit;
					return true;
				} else {
					isFinMot = false;
					return true;
				}
			} else {
				if(fils_centre != null) {
					isFinMot = false;
					return true;
				}
				
				if(pere.fils_gauche == this) {
					if(fils_gauche != null && fils_centre == null && fils_droit == null) {
						pere.fils_gauche = fils_gauche;
						fils_gauche.pere = pere;
						return true;
					} else if(fils_gauche == null && fils_centre == null && fils_droit != null) {
						pere.fils_gauche = fils_droit;
						fils_droit.pere = pere;
						return true;
					} else if(fils_gauche != null && fils_centre == null && fils_droit != null) {
						pere.fils_gauche = fils_gauche;
						fils_gauche.pere = pere;
						fils_gauche.fils_droit = fils_droit;
						fils_droit.pere = fils_gauche;
						return true;
					} 
				} else if(pere.fils_centre == this) {
					if(fils_gauche != null && fils_centre == null && fils_droit == null) {
						pere.setFils_centre(fils_gauche);
						fils_gauche.setPere(pere);
						return true;
					} else if(fils_gauche == null && fils_centre == null && fils_droit != null) {
						pere.setFils_centre(fils_droit);
						fils_droit.setPere(pere);
						return true;
					} else if(fils_gauche != null && fils_centre == null && fils_droit != null) {
						fils_gauche.setFils_droit(fils_droit);
						pere.setFils_centre(fils_gauche);
						fils_gauche.setPere(pere);
						return true;
					}
				} else if(pere.fils_droit == this) {
					if(fils_gauche != null && fils_centre == null && fils_droit == null) {
						pere.fils_droit = fils_gauche;
						fils_gauche.pere = pere;
						return true;
					} else if(fils_gauche == null && fils_centre == null && fils_droit != null) {
						pere.fils_droit = fils_droit;
						fils_droit.pere = pere;
						return true;
					} else if(fils_gauche != null && fils_centre == null && fils_droit != null) {
						pere.fils_droit = fils_gauche;
						fils_gauche.pere = pere;
						fils_gauche.fils_droit = fils_droit;
						fils_droit.pere = fils_gauche;
						return true;
					} 
				}
			}
			
		//si pas de fils
		} else {
			TrieHybride cr = pere;
			if(pere == null) {
				cr = this;
				cr = new TrieHybride(null);
				return true;
			} else {
				boolean isDeleted = false;
				
				while(!isDeleted) {
					
					if(cr.pere == null) {
						if(!cr.isFinMot) {
							if(cr.fils_gauche == null && cr.fils_droit == null) {
								cr = null;
								return true;
							} else if(cr.fils_gauche != null && cr.fils_droit == null) {
								cr = cr.fils_gauche;
								cr.pere = null;
								return true;
							} else if(cr.fils_gauche != null && cr.fils_droit != null) {
								TrieHybride fd = cr.fils_droit;
								cr = cr.fils_gauche;
								cr.pere = null;
								
								while(cr.fils_droit != null) {
									cr = cr.fils_droit;
								}
								cr.fils_droit = fd;
								fd.pere = cr;
								return true;
							} else if(cr.fils_gauche == null && cr.fils_droit != null) {
								cr = cr.fils_droit;
								cr.pere = null;
								return true;
							}
						} else {
							cr.fils_centre = null;
							return true;
						}
					} else {//si pere != null
						if(cr == cr.pere.fils_gauche) {
							if(!cr.isFinMot) {
								if(cr.fils_gauche == null && cr.fils_droit == null) {
									cr.pere.fils_gauche = null;
									return true;
								} else if(cr.fils_gauche != null && cr.fils_droit == null) {
									cr.pere.fils_gauche = cr.fils_gauche;
									cr.fils_gauche.pere = cr.pere;
									return true;
								} else if(cr.fils_gauche != null && cr.fils_droit != null) {
									cr.pere.fils_gauche = cr.fils_gauche;
									cr.fils_gauche.pere = cr.pere;
									cr.fils_gauche.fils_droit = cr.fils_droit;
									cr.fils_droit.pere = cr.fils_gauche;
									return true;
								} else if(cr.fils_gauche == null && cr.fils_droit != null) {
									cr.pere.fils_gauche = cr.fils_droit;
									cr.fils_droit.pere = cr.pere;
									return true;
								}
							} else {
								cr.fils_centre = null;
								return true;
							}
						} else if(cr == cr.pere.fils_centre) {
							if(!cr.isFinMot) {
								if(cr.fils_gauche == null && cr.fils_droit == null) {//
									cr = cr.pere;
									continue;
								} else if(cr.fils_gauche != null && cr.fils_droit == null) {
									cr.pere.fils_centre = cr.fils_gauche;
									cr.fils_gauche.pere = cr.pere;
									return true;
								} else if(cr.fils_gauche != null && cr.fils_droit != null) {
									cr.pere.fils_centre = cr.fils_gauche;
									cr.fils_gauche.pere = cr.pere;
									cr.fils_gauche.fils_droit = cr.fils_droit;
									cr.fils_droit.pere = cr.fils_gauche;
									return true;
								} else if(cr.fils_gauche == null && cr.fils_droit != null) {
									cr.pere.fils_centre = cr.fils_droit;
									cr.fils_droit.pere = cr.pere;
									return true;
								}
							} else {
								cr.fils_centre = null;
								return true;
							}
						} else if(cr == cr.pere.fils_droit) {
							if(!cr.isFinMot) {
								if(cr.fils_gauche == null && cr.fils_droit == null) {
									cr.pere.fils_droit = null;
									return true;
								} else if(cr.fils_gauche != null && cr.fils_droit == null) {
									cr.pere.fils_droit = cr.fils_gauche;
									cr.fils_gauche.pere = cr.pere;
									return true;
								} else if(cr.fils_gauche != null && cr.fils_droit != null) {
									cr.pere.fils_droit = cr.fils_gauche;
									cr.fils_gauche.pere = cr.pere;
									cr.fils_gauche.fils_droit = cr.fils_droit;
									cr.fils_droit.pere = cr.fils_gauche;
									return true;
								} else if(cr.fils_gauche == null && cr.fils_droit != null) {
									cr.pere.fils_droit = cr.fils_droit;
									cr.fils_droit.pere = cr.pere;
									return true;
								}
							} else {
								cr.fils_centre = null;
								return true;
							}
						}
						return false;
					}
				}
			}
		}
		return true;
	}
	
	
	/***************************/
	/*** fonctions complexes ***/
	/***************************/
	
	
	//parcourir le PATRICIA trie et fabriquer un trie hybride pour chaque lettre
	@Override
	public PatriciaTrie trieHybrideToPatriciaTrie() {
		PatriciaTrie pt = new PatriciaTrie();
		
		return pt;
	}
	
	
	@Override
	public TrieHybride addWordAndBalance(String word){
		if(word != null && !word.equals("")) {
			this.addWord(word);
		}
		return this;
	}
	
	
	@Override
	public boolean isBalanced(ArrayList<Double> hauteurs, Double h){
		return false;
	}
	
	
	@Override
	public TrieHybride balance(){//voir rotation gauche/droite

		return null;
	}


	/***************************/
	/*** guetters et setters ***/
	/***************************/
	

	public char getCle() {
		return cle;
	}


	public void setCle(char cle) {
		this.cle = cle;
	}
	
	
	public TrieHybride getPere() {
		return this.pere;
	}
	
	
	public void setPere(TrieHybride pere) {
		this.pere = pere;
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
