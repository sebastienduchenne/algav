package patricia_trie;

import java.util.ArrayList;

import trie_hybride.TrieHybride;
import Commun.*;


public class PatriciaTrie extends Trie implements IPatriciaTrie{

	public static final char CHAR_FIN_CHAINE = '\3';
	
	
	public PatriciaTrie() {
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Trie addWord(String word) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean rechercher(String word) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int compterMots() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ArrayList<String> listerMots(ArrayList<String> words, String word) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int compterNil() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int hauteur() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Double profondeurMoyenne(ArrayList<Double> hauteurs, Double h) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int prefixe(String prefixe) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean suppression(String word) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public PatriciaTrie fusion2PatriciaTrie(PatriciaTrie pt) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TrieHybride patriciaTrieToTrieHybride() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}

    