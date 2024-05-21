package AI;

import java.util.HashMap;
import java.util.HashSet;

public class CryptArProblem {
	
	public static int number(String s, HashMap<Character,Integer> map) {
		String no = "";
		for(int i=0;i<s.length();i++) {
			char ch = s.charAt(i);
			no+=map.get(ch);
		}
		return Integer.parseInt(no);
	}

	public static void helper(int idx,String s1,String s2, String result,String unstring, HashSet<Integer> set,HashMap<Character,Integer> map) {
		
		if(idx==unstring.length()) {
			int no1 = number(s1,map);
			int no2 = number(s2,map);
			int no3 = number(result,map);
			
			if(no1+no2==no3) {
				System.out.println(map);
			}
			return;
		}
		
		char c = unstring.charAt(idx);
		for(int i=0;i<10;i++) {
			if(!set.contains(i)) {
				map.put(c, i);
				set.add(i);
				helper(idx+1, s1,s2,result,unstring,set, map);
				map.put(c, -1);
				set.remove(i);
			}
		}
	}
	
	public static void cap(String s1,String s2,String result){
		HashMap<Character,Integer> map = new HashMap<>();
		HashSet<Integer> set = new HashSet<>();
		for(int i=0;i<s1.length();i++) {
			char c = s1.charAt(i);
			if(!map.containsKey(c)) {
				map.put(c, -1);
			}
		}
		for(int i=0;i<s2.length();i++) {
			char c = s2.charAt(i);
			if(!map.containsKey(c)) {
				map.put(c, -1);
			}
		}
		for(int i=0;i<result.length();i++) {
			char c = result.charAt(i);
			if(!map.containsKey(result.charAt(i))) {
				map.put(c, -1);
			}
		}
		String unstring = "";
		for (Character c : map.keySet())  {
            unstring+=c; 
		}
		helper(0,s1,s2,result,unstring,set,map);
	}
	public static void main(String[] args) {
		String s1 = "SEND";
		String s2 = "MORE";
		String result = "MONEY";
		cap(s1,s2,result);
	}
}
