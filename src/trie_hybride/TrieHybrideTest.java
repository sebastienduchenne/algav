package trie_hybride;


import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import Commun.Fichier;

public class TrieHybrideTest {
	
	private TrieHybride t;
	private String mots[];
	
	@Before
	public void preparation(){
		t = new TrieHybride(null);
		//mots = shakespeare();
		mots = get_words_texte("exemple_de_base.txt");
		//mots = new String[] {"loup", "de"};
	}
	
	@Test
	public void TestAdd() {
		for(int i = 0; i < mots.length; i++){ t.addWord(mots[i].trim()); }
		int nbMots = t.compterMots();
		assertTrue(nbMots == 35);
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
