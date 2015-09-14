
package eu.ailao.glove;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;


/** Contains Glove word embedding dictionary. */
public class GloveDictionary {
	private static GloveDictionary gd = new GloveDictionary();
	private final Map<String,double[]> dictionary;
	private final String path="/glove/glove.6B.50d.txt";

	private GloveDictionary(){
		Map<String,double[]> dictionary=new HashMap<>();
		try {
                        InputStream in = getClass().getResourceAsStream(path);
			BufferedReader br=new BufferedReader(new InputStreamReader(in));
			String line;
			while((line=br.readLine())!=null){
				String[] s=line.split(" ");
				String word=s[0];
				double[] gword=new double[s.length-1];
				for(int i=1;i<s.length;i++){
					gword[i-1]=Double.parseDouble(s[i]);
				}
				dictionary.put(word, gword);
			}
		} catch (FileNotFoundException ex) {
			Logger.getLogger(GloveDictionary.class.getName()).log(Level.SEVERE, null, ex);
                    System.out.println("FileNotFoundException");
		} catch (IOException ex) {
			Logger.getLogger(GloveDictionary.class.getName()).log(Level.SEVERE, null, ex);
                    System.out.println("IOException");
                }
		System.out.println("GloVe dictionary created");
		this.dictionary=dictionary;
	}

	public double[] get(String key){
			return dictionary.get(key);
		}
	public static GloveDictionary getInstance() {
		return gd;
	}
}
