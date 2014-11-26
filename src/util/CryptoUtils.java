package util;

import java.io.*;
import java.util.ArrayList;

public class CryptoUtils {
	static int NUM_SPACING_MAX = 20;
	static double[] englishFrequencies = 	{0.08167, 0.01492, 0.02782, 0.04253, 0.12702, 0.02228,
											 0.02015, 0.06094, 0.06966, 0.00153, 0.00772, 0.04025,
											 0.02406, 0.06749, 0.07507, 0.01929, 0.00095, 0.05987,
											 0.06327, 0.09056, 0.02758, 0.00978, 0.02360, 0.00150,
											 0.01974, 0.00074};
	public static void main(String[] args) {
		String path = "src/CT.txt";
		String cipher = readFile(path);
//		for(int i = 0; i < cipher.length(); i++) {
////			System.out.print(String.valueOf((char)cipher.get(i))+" ");
//			System.out.print(cipher.charAt(i)+" ");
//		}
		//find angrams
		ArrayList<Sequence> patterns = findPatterns(cipher);
		for(int i = 0; i < patterns.size(); i++) {
			Sequence seq = patterns.get(i);
			System.out.println(seq.start+":"+seq.pair+":"+seq.sequence+":");
		}
		//see which angrams match with which key length
		ArrayList<Sequence> spacing = getSpacing(patterns, cipher);
//		for(int i = 0; i < spacing.size(); i++) {
//			Sequence seq = spacing.get(i);
//			System.out.println(seq.matches);
//		}
		//get the percentages
		double[] percents = getPercents(spacing);
		for(int i = 0; i < percents.length; i++) {
			System.out.println((i+2)+":"+percents[i]+":"+(int)((percents[i]/percents[0])*100));
		}
		// find best keylength
		int keylength = 0;
		double percent = 0;
		for(int i = 3; i < percents.length; i++) {
			double working = (int)((percents[i]/percents[0])*100);
			if(working > percent || working > percent*0.9) {
				keylength = i+2;//length starts at 2
				percent = working;
			}
		}
		
		System.out.println();
		// do frequency analysis and get key
		int[] key = frequencyAnalysis(cipher, keylength);
		for(int i = 0; i < key.length; i++) {
			System.out.print(key[i]+":");
		}
		
		System.out.println();
		for(int i = 0; i < key.length; i++) {
			System.out.print((char)(key[i] + 65));
		}
	}
	
	public static String readFile(String path) {
		//ArrayList result = new ArrayList();
		String result = "";
		FileInputStream in = null;
		try {
			in = new FileInputStream(path);
			int c;
			while ((c = in.read()) != -1) {
				result += String.valueOf((char)c);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) { }
			}
		}
		return result;
	}
	
	public static ArrayList<Sequence> findPatterns(String cipher) {
		int i = 0;
		String checker = "", checker2 = "";
		ArrayList<Sequence> sequences = new ArrayList<Sequence>();
		while(i < cipher.length()) {
			//1. Grab current letter
			checker = cipher.substring(i,i+1);
			int j = i+1;
			int iter = 1;
			while(j < cipher.length()) {
				//2. find matching letter
				checker2 = cipher.substring(j,j+1);
				while(checker.equals(checker2) && j+iter < cipher.length()) {
					//3. found match. Check next letters, and get the max matching angram.
					checker = cipher.substring(i,i+iter);
					checker2 = cipher.substring(j,j+iter);
					if(checker.equals(checker2)) {
						if(iter >= 3) {
							sequences.add(new Sequence(i, j, cipher.substring(i,i+iter-1)));
						}
						iter++;
					}
					else break;
//					else iter -= 1;
				}
				//4. Got max angram. Store it and continue.
				if(iter > 3) {
//					sequences.add(new Sequence(i, j, cipher.substring(i,i+iter-1)));
					i += iter-1;
					break;
				} else {
					j++;
					iter = 1;
					checker = cipher.substring(i,i+iter);
				}
			}
			i++;
		}
		return sequences;
	}
	
	public static ArrayList<Sequence> getSpacing(ArrayList<Sequence> sequences, String cipher) {
		ArrayList<Sequence> result = sequences;
		for(int i = 2; i < NUM_SPACING_MAX; i++) {
			for(int j = 0; j < result.size(); j++) {
				Sequence check = result.get(j);
//				if(check.start%i == check.pair%i) check.matches += "X";
				if((check.pair-check.start)%i == 0) check.matches += "X";
				else check.matches += "_";
			}
		}
		return result;
	}
	
	public static double[] getPercents(ArrayList<Sequence> sequences) {
		double[] result = new double[sequences.get(0).matches.length()];
		for(int i = 0; i < result.length; i++) {
			result[i] = 0.0;
		}
		for(int j = 0; j < sequences.size(); j++) {
			Sequence check = sequences.get(j);
			for(int i = 0; i < result.length; i++) {
				if(check.matches.substring(i,i+1).equals("X")) result[i] += 1;
			}
			
		}
		return result;
	}
	
	public static int[] frequencyAnalysis(String cipher, int keyLength) {
		//do entire key
		int[] key = new int[keyLength];
		for(int i = 0; i < keyLength; i++) {
			double[] letters = new double[26];
			double total = 0;
			double[] deviation = new double[26];
			
			// 1. get letter frequency
			double[] w = new double[26];
			for(int j = i; j < cipher.length(); j+=keyLength) {
				int iter = (cipher.charAt(j)-'A');
				letters[iter]++;
				total++;
			}
			// 2. normalize letter frequency
			for(int j = 0; j < letters.length; j++) {
				double percent = (letters[j] / total);
				w[j] = percent;
			}
			// get estimates
			for(int j = 0; j < deviation.length; j++) {
				for(int k = 0; k < w.length; k++) {
					deviation[j] += w[(j+k)%26] * englishFrequencies[k];
				}
			}
			// get best estimate
			double closest = 0;
			int best = -1;
			for(int j = 0; j < deviation.length; j++) {
				if(deviation[j] > closest) {
					best = j;
					closest = deviation[j];
				}
			}
			// store it
			key[i] = best;
		}
		return key;
	}
	
	public static class Sequence {
		int start, pair;
		String sequence;
		String matches;
		public Sequence(int start, int pair, String sequence) {
			this.start = start;
			this.pair = pair;
			this.sequence = sequence;
			this.matches = "";
		}
	}
}
