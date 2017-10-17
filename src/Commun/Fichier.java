package Commun;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Fichier {

	/* liste les mots presents dans le fichier specifie */
	public static String[] lireFichier(File fileName) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(fileName));
		try {
			StringBuilder sb = new StringBuilder();
			String line = br.readLine();
			while (line != null) {
				sb.append(line);
				sb.append(System.lineSeparator());
				line = br.readLine();
			}
			String everything = sb.toString();
			String[] mots = everything.split(System.lineSeparator());
			
			//retirer les caracteres [,],&
			/*for(int i = 0; i < mots.length; i++){
				StringBuilder strb= new StringBuilder(mots[i]);
				for(int j = 0; j < strb.length(); j++){
					char c = strb.charAt(j);
					if( (c >= 0 && c < 65) || (c >= 91 && c < 97) || (c > 122)){
						//System.out.println(c);
						strb.deleteCharAt(j);
					}
				}
				mots[i] = strb.toString();
			}*/
			
			return mots;
		} finally {
			br.close();
		}
	}
	
	/* liste des mots dans tous les fichiers present dans le dossier specifie */
	public static String[] lireFichiersRepertoire(String dirName) throws IOException {
		File dir = new File(dirName);
		String[] buffer = new String[0];
		if (!dir.isDirectory()) {
			return buffer;
		} else {
			String[] files = dir.list();
			for (int i = 0; i < files.length; i++) { 
				File temp = new File(dirName + "/" + files[i]);
				buffer = concatener(buffer, lireFichier(temp));
			}
			return buffer;
		}
	}
	
	private static String[] concatener(String[] liste1, String[] liste2) {
		List<String> concat = new ArrayList<String>(liste1.length + liste2.length);
		Collections.addAll(concat, liste1);
		Collections.addAll(concat, liste2);
		return concat.toArray(new String[concat.size()]);
	}
	
	public static void main(String[] args) {
		String[] mots = null;
		/*Scanner sc2 = new Scanner(System.in); 
		String fileName = sc2.nextLine();*/
		try {
			mots = Fichier.lireFichier(new File("Textes/test.txt"));
			for(int i = 0; i < mots.length; i++){
				System.out.println(mots[i]);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		//sc2.close();
	}
}
