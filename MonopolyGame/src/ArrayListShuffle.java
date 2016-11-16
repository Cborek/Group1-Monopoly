package borek.colin.arraylistshuffle;

import java.util.ArrayList;
import java.util.Random;

public class ArrayListShuffle {
	public static ArrayList<Integer> original = new ArrayList<Integer>();
	public static void main(String[] args) {
		for(int i = 1; i <= 10; i++){
			original.add(i);
		}
		System.out.println("Original ArrayList.");
		System.out.println(original);
		System.out.println("Shuffled ArrayList");
		System.out.println(shuffleList(original));
		
	}
	
	public static ArrayList<Integer> shuffleList(ArrayList<Integer> a){
		Random rand = new Random();
		for(int i = 0; i < 5; i++){
			int pullVal = rand.nextInt(a.size());
			int copy = a.get(pullVal);
			a.remove(pullVal);
			a.add(rand.nextInt(a.size()), copy);
		}
		return a;
	}

}
