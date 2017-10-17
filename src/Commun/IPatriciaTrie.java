package Commun;

import patricia_trie.PatriciaTrie;
import trie_hybride.TrieHybride;

public interface IPatriciaTrie {

	public PatriciaTrie fusion2PatriciaTrie(PatriciaTrie pt);
	
	public TrieHybride patriciaTrieToTrieHybride();
	
}
