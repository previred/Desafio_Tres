package cl.gao;

import java.util.ArrayList;
import java.util.List;

public class PairGenerator {
	static public class Pair<T>{
		public T firstItem;
		public T secondItem;
		public Pair(T firstItemArg, T secondItemArg) {
			firstItem = firstItemArg;
			secondItem = secondItemArg;
		}
	}
	private PairGenerator() {
		
	}
	static public <T> List<Pair<T>> getPairs(List<T> list) {	
		ArrayList<Pair<T>> result = new ArrayList<Pair<T>>();		
		for (int elementIndex=0;elementIndex<list.size()-1;elementIndex++) {			
			result.add(new Pair<T>(list.get(elementIndex),list.get(elementIndex+1)));
		}
		return result;
	}
}
