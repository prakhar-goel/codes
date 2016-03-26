import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
//import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

class Key {

    final String x;
    final String y;

    public Key(String x, String y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Key)) return false;
        Key key = (Key) o;
        //System.out.println("x :"+ x + " key.x :" + key.x + " comp :" + (x==key.x)+" ,y :" +y+ " this.y :"+key.y+ " ,comp2 :" + (x.equals(key.x)));
        //return x==key.x && y==key.y;
        return x.equals(key.x) && y.equals(key.y);
    }

    @Override
    public int hashCode() {
        //String result = x;
        int x_hash = x.hashCode();
        int y_hash = y.hashCode();
        int result = 31 * x_hash + y_hash;
        return result;
    }

}

public class HMMPOSTagger extends java.util.HashMap<Key, Double>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6740498817514749994L;
	/**
	 * @param args
	 * @throws IOException 
	 */
	static HashMap<String,Integer> word_tag_freq_map = new HashMap<String,Integer>();
	static HashSet<String> unique_words_set = new HashSet<String>();
	static HashMap<String,Integer> tag_bigram_freq_map = new HashMap<String,Integer>();
	static HashMap<String,Integer> tag_freq_map = new HashMap<String,Integer>();
	
	static HashMap<Key ,Double> emission_map = new HashMap<Key ,Double>();
	static HashMap<Key ,Double> transition_map = new HashMap<Key ,Double>();
	static HashMap<Key ,Double> viterbi_prob_map = new HashMap<Key ,Double>();
	static HashMap<Key ,String> path_tag_map = new HashMap<Key ,String>();
	static Stemmer st = null;
	//ArrayList<String> word_list = new ArrayList<String>();
	
	int max_tag_count  = 21;
	/*Double get(Key key)
	{
		Double val = 0;
		
		return val;
				
	}*/
	
	
	
	static void fill_UniqueWord_Tag_info_in_map(String fileName, String language) throws IOException
	{
		BufferedReader br = new BufferedReader(new FileReader(fileName));
		String line = "";
		String prevTag = "";
		
		
		
		//st = new MarathiStemmerLight();
		if(language.equals("marathi"))
		{
			st = new MarathiStemmerLight();
		}
		int lineCount = 0; //test...
		while((line = br.readLine()) != null)
		{
			String wordTok[] = line.split("[ ]+");
			//System.out.println("\ntag_bigrams * " + lineCount + " *:");
			
			lineCount++; //test..
			if(lineCount == 3)
				System.out.println("line :" + line);
			//word_list.add("<s>");
			for(int i = 0; i < wordTok.length; i++)
			{
				//stemming and Tag Extraction
				String word = wordTok[i].split("_")[0];
				word = st.stem(word);
				String tag = wordTok[i].split("_")[1];
				if(lineCount == 3)
					System.out.println("word :" + word + " tag :" + tag);
				//word_list.add(word);
				//tag bigrams and fill in tag_bigram_map
				if(prevTag != "")
				{
					String tag_bigram = prevTag + "_" + tag;
					
					//System.out.print(wordTok[i] + "- >" + tag_bigram + " , ");
					
					//populating tag bigram frequency map..
					if(tag_bigram_freq_map.containsKey(tag_bigram))
					{
						int count = tag_bigram_freq_map.get(tag_bigram);
						tag_bigram_freq_map.put(tag_bigram, count+1);
					}
					else
					{
						tag_bigram_freq_map.put(tag_bigram, 1);
					}
					
				}
				prevTag = tag;
				
				//System.out.print("old word : " + wordTok[i] + " -> ");
				
				unique_words_set.add(word);
				word =  word + "_" + tag;
				
				//populating word_tag frequency map
				if(word_tag_freq_map.containsKey(word))
				{
					int count = word_tag_freq_map.get(word);
					word_tag_freq_map.put(word, count+1);
				}
				else
				{
					word_tag_freq_map.put(word, 1);
				}
				
				//populating tag frequency map..
				if(tag_freq_map.containsKey(tag))
				{
					int count = tag_freq_map.get(tag);
					tag_freq_map.put(tag, count+1);
				}
				else
				{
					tag_freq_map.put(tag, 1);
				}
				
			}
			//word_list.add("</s>");
			prevTag = "";
		}
		br.close();
		//unique_words_count = unique_words_set.size();
		//unique_words_set.clear();
		fill_emission_and_transition_matrix();
	}
	
	static void fill_emission_and_transition_matrix()
	{
		//fill emission matrix..
		
		for(String word : unique_words_set)
		{
			for(String tag : tag_freq_map.keySet())
			{
				String word_tag = word+"_"+tag;
				double val = 0;
				Key key = new Key(word,tag);

				if(word_tag_freq_map.containsKey(word_tag)){
					val = ((double)word_tag_freq_map.get(word_tag))/tag_freq_map.get(tag);
					
					emission_map.put(key, val);
					if(word.equals("लिपीत")/* && tag.equals("VM")*/){
						System.out.println("emission map -> word found with tag :" + tag);
						//System.out.println("filling :emii 1: " + emission_map.get(new Key("लिपीत","VM")) + " val :" + val);
						//System.out.println("filling :emii 2: " + emission_map.get(new Key(word,tag)) + " val :" + val);
					}
					//System.out.println("val chk : " + (emission_map.get(key)==emission_map.get(new Key(tag,word))));
				}
				else{
					val = 0;
					emission_map.put(key, val);
				}
			}
		}
		
		//fill transition matrix..
		for(String tag1 : tag_freq_map.keySet())
		{
			for(String tag2 : tag_freq_map.keySet())
			{
				//System.out.println("word+tag : " + (word+"_"+tag));
				String tag_tag = tag1+"_"+tag2;
				double val = 0;
				Key key = new Key(tag1,tag2);

				if(tag_bigram_freq_map.containsKey(tag_tag)){
					val = ((double)tag_bigram_freq_map.get(tag_tag))/tag_freq_map.get(tag2);
					transition_map.put(key, val);
					//System.out.println(transition_map.get(new Key(tag1,tag2)));
				}
				else{
					val = 0;
					transition_map.put(key, val);
				}
			}
		}
		//emission_map.put(new Key("check","chk"), (Double)0.5);
	}
	
	static void viterbiImpl(String line)
	{
		//checkkk
		for(Key key : emission_map.keySet())
			if(key.x.equals("लिपीत") && key.y.equals("VM")){
				String word = key.x; 
				System.out.println("viterbi lipit :"+ emission_map.get(new Key(word,key.y)));
			}
		
		//////
		
		
		
		String prev_word = "";
		String words[] = line.split("[ ]+");
		//for(int i = 0 ; i < word_list.size(); ++i)
		int words_count = words.length;
		for(int i = 0; i < words_count; ++i)
		{
			String word  = st.stem(words[i]);
			
			String best_tag = "UNK";
			for(String tag : tag_freq_map.keySet())
			{
				//System.out.println("key :" + word+ " " +  tag + emission_map.containsKey(new Key(word,tag)));
				//System.out.println(emission_map);
				/*for(Key key : emission_map.keySet())
				{
					if(key.x.equals(word))
					{
						System.out.println(key.x + " " + key.y + " -> "+  emission_map.get(key));
					}
				}*/
				double emission_val = 0;
				boolean word_present;// = false;
				if((word_present = emission_map.containsKey(new Key(word,tag)) ))
						emission_val = emission_map.get(new Key(word,tag));
			//	if(word.equals("प्राचीन") && tag.equals("JJ"))
			//		System.out.println("emii : " + emission_map.get(new Key(word,tag)));
				
					
				System.out.println("emssion_val_check -> "+"*word* :" + word + " *tag* :" + tag + "  word_present :" + word_present + "->"+emission_val);
				//double value = Math.log10((double)emission_val);
				double value = emission_val;
				if(prev_word != "")		
					//value = Math.log10((double)maxConnect(word,prev_word, tag, best_tag)) + Math.log10((double)emission_val);
					value = maxConnect(word,prev_word, tag, best_tag) * emission_val;
				System.out.println(word + " with :" + tag + " - " + value);
				viterbi_prob_map.put(new Key(tag,word), value);
				System.out.println();
			}
			prev_word = word;
		}
		//find best tag for last word..
		double max_prob_val = -99999;
		String best_tag = "UNK";
		for(String tag : tag_freq_map.keySet())
		{
			double temp_prob_val = -99999;
			//System.out.println("*1*");
			String word = st.stem(words[words_count-1]);
			Key key = new Key(tag,word);
			System.out.println("*1*");
			if (viterbi_prob_map.containsKey(key ))
			{
				System.out.println("*2* check :tag :" + tag + " word :" + word);
				temp_prob_val = viterbi_prob_map.get(key);
			}
			//System.out.println("temp prob :" + temp_prob_val);
			if(temp_prob_val > max_prob_val)
			{
				max_prob_val = temp_prob_val;
				best_tag = tag;
			}
		}
		//System.out.println("");
		//backtracking of best tag for each word
		for(int i = words_count-1; i >= 0; --i)
		{
			System.out.print(words[i] + "_" + best_tag + " ");
			String word = st.stem(words[i]);
			best_tag = path_tag_map.get(new Key(best_tag,word));
		}
		System.out.println();
	}
	static double maxConnect(String cur_word, String prev_word, String tag, String best_tag)
	{
		double max_val = -999999;
		best_tag = "UNK";
		
		for(String temp_tag : tag_freq_map.keySet())
		{
			double val = 1;
			if(prev_word != "")
				//val = Math.log10(transition_map.get(new Key(temp_tag,tag))) + viterbi_prob_map.get(new Key(temp_tag,prev_word));
				val = transition_map.get(new Key(temp_tag,tag)) * viterbi_prob_map.get(new Key(temp_tag,prev_word));
			
			if(val > max_val)
			{
				max_val = val;
				best_tag = temp_tag;
			}
		}
		System.out.println(cur_word + " - best tag -" + best_tag + " -with best val : " + max_val);
		
		path_tag_map.put(new Key(tag,cur_word), best_tag);
		return max_val;
	}
	public static void main(String[] args) throws IOException {
		//HMMPOSTagger posObj = new HMMPOSTagger();
		fill_UniqueWord_Tag_info_in_map("marathi_pos.txt", "marathi");
		
		//if(word.equals("प्राचीन") && tag.equals("JJ"))
		//System.out.println("main check :"+ emission_map.containsKey(new Key("check","chk")));
		//String word = "प्राचीन";
		for(Key key : emission_map.keySet())
			if(key.x.equals("लिपी") && key.y.equals("VM"))
				System.out.println("main prachin :"+ emission_map.get(key));
		//System.out.println(emission_map);
			//System.out.println("main :emii : " + emission_map.get(new Key("प्राचीन","JJ")));
	//	viterbiImpl("प्राचीन भारतीय अर्थव्यवस्था अतिशय सुंदर होती");
		viterbiImpl("असो हा लेख अझ्टेक असून अ वर चंद्र येतो"); 
		
		
		//test ...
		/*
		HashMap<String,Double> test_map = new HashMap<String,Double>();
		String []test = {"प्राचीन", "JJ"};
		String word1 = new String("प्राचीन");
		test_map.put(word1, (Double)0.7);
		test_map.put(test[1], (Double)0.3);
		
		System.out.println("test1 :" + test_map.containsKey(test[0]));
		System.out.println("test2 :" + test_map.containsKey("JJ"));
		
		
		HashMap<Key,Double> test_map2 = new HashMap<Key,Double>();
		test_map2.put(new Key(test[0], "JJ"), (Double)0.6);
		//test_map2.put(new Key(test[0], "JJ"), (Double)0.6);
		System.out.println("test3 :" + test_map2.get(new Key(word1, test[1])));*/
	}

}
