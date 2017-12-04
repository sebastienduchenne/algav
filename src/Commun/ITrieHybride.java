package Commun;

import java.util.ArrayList;

import patricia_trie.PatriciaTrie;
import trie_hybride.TrieHybride;

public interface ITrieHybride {

	public PatriciaTrie TH2PT(PatriciaTrie pt, String word);
	
	public TrieHybride addWordAndBalance(String word);
	
	public boolean isBalanced(ArrayList<Double> hauteurs, Double h);
	
	public TrieHybride balance();
	
}
