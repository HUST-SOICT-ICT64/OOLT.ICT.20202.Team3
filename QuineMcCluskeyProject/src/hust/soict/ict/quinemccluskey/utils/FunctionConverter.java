package hust.soict.ict.quinemccluskey.utils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import hust.soict.ict.quinemccluskey.model.minterm.Implicant;

public class FunctionConverter {	
	private static String swap(String str, String one, String two){
	    return Arrays.stream(str.split(one, -1))
	        .map(s -> s.replaceAll(two, one))
	        .collect(Collectors.joining(two));
	}
	
	public static String DeMorgan(String s) {
		String DM = new String(s);
		StringBuilder newStr = new StringBuilder(s);
		
		DM = swap(s, "D'", "D");
		DM = swap(s, "C'", "C");
		DM = swap(s,"B'", "B");
		DM = swap(s, "A'", "A");
		
		int[] index = new int[DM.length()];
		int j = 0;
		
		for(int i = 0; i < DM.length(); i++) {
			if(newStr.charAt(i) == '.') {
				newStr.setCharAt(i, '+');
				j ++;
				continue;
			}else if(newStr.charAt(i) == '+') {
				index[j] = i;
				newStr.setCharAt(i, '.');
				continue;
			}
		}
		
		newStr.insert(0, '(');
		for(int i = 0; i < newStr.length(); i++) {
			if(newStr.charAt(i) == '.') {
				newStr.insert(i, ')');
				newStr.insert(i + 2, '(');
				i ++;
			}
		}
		
		DM = newStr.append(')').toString();
		return DM;
	}

	public static String fromEPIToFunction(Implicant implicant) {		 
		StringBuilder str = new StringBuilder();
		
		for(int i = 0; i < implicant.getBinaryExpression().length(); i++) {
			if(implicant.getBinaryExpression().charAt(i) == '-') {
				continue;
			}
			
			if(implicant.getBinaryExpression().charAt(i) == '0') {
				str.append((char) (i + 'A') + "'");
			}
			else if(implicant.getBinaryExpression().charAt(i) == '1') {
				str.append((char) (i + 'A'));
			}
			str.append('.');
		}
		
		if(str.length() > 0) {
			str.setLength(str.length() - 1);	// get rid of the last '.'
		}
		
		return str.toString();
	}
	
	public static String fromEPIToFunction(List<Implicant> implicants) {		 
		StringBuilder res = new StringBuilder();
		
		for(Implicant imp : implicants) {
			res.append(fromEPIToFunction(imp));
			res.append('+');
		}
		
		if(res.length() > 0) {
			res.setLength(res.length() - 1);
		}
		
		return res.toString();
	}
}
