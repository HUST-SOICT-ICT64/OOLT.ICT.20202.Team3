package hust.soict.ict.quinemccluskey.model.output;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import hust.soict.ict.quinemccluskey.model.minterm.Implicant;
import hust.soict.ict.quinemccluskey.model.table.PITable;

public abstract class OutputFunction {
	private String result;	// in SOP format
	List<Implicant> EPI = new ArrayList<Implicant> ();
	
	public String getResult() {
		return this.result;
	}	
	public OutputFunction() {
		super();
	}
	public OutputFunction(String result) {
		super();
		this.result = result;
	}
	private String swap(String str, String one, String two){
	    return Arrays.stream(str.split(one, -1))
	        .map(s -> s.replaceAll(two, one))
	        .collect(Collectors.joining(two));
	}
	public String DeMorgan() {
		result = swap(result, "D'", "D");
		result = swap(result, "C'", "C");
		result = swap(result,"B'", "B");
		result = swap(result, "A'", "A");
		StringBuilder newStr = new StringBuilder(result);
		int []index = new int[result.length()];
		int j = 0;
		for(int i = 0; i < result.length(); i++) {
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
		newStr.append(')');
		return newStr.toString();
	}

	public void toCharacterEquation(List<Implicant> a) {		 
		StringBuilder res = new StringBuilder();						
		String []buff = new String[a.size()];
		char apostrophe = "\u0027".charAt(0);	// apostrophe character: " ' "	
		int[] count = new int[a.size()];	// count number of consecutive character '-' from the end of the string
		for(int i = 0; i < a.size(); i ++) {
			count[i] = 1;
		}
		for(int i = 0; i < a.size(); i ++) {
			buff[i] = a.get(i).getBinaryExpression().toString();
			for(int j = 0; j < buff[i].length(); j ++) {
				if((buff[i].length() - (j + 2) > 0)
					&& buff[i].charAt(buff[i].length() - (j + 1)) == '-' 
					&& buff[i].charAt(buff[i].length() - (j + 2)) == '-' ) {	
					// count consecutive character '-' from the end of the string
					count[i] ++;
					
				}
			}
		}
		for(int i = 0; i < a.size(); i ++) {
			buff[i] = a.get(i).getBinaryExpression().toString();
			StringBuilder str = new StringBuilder();
			for(int j = 0; j < buff[i].length(); j ++) {
				if(buff[i].charAt(buff[i].length() - 1) == '-') {	// case when '-' is the ending char
					if(buff[i].charAt(j) != '-') {
						str.append((char)(j + 65));
						if(buff[i].charAt(j) == '0')
							str.append(apostrophe);		
						if((buff[i].length() != count[i] + 1)  && j != (buff[i].length() - (count[i] + 1)) && j >= 0 ) {
							str.append('.');
						}
					}
				}
				else {
					if(buff[i].charAt(j) != '-') {
						str.append((char)(j + 65));
						if(buff[i].charAt(j) == '0')
							str.append(apostrophe);		
						if(j != (buff[i].length() - 1)) {
							str.append('.');
						}
					}
				}
			}
			res.append(str);
			if(i != (a.size() - 1))
				res.append('+');
		}
		result = res.toString();
	}


	public List<Implicant> takeEPI(PITable table, List<Implicant> minterms) {
		List<Implicant> primeImplicants = table.getPrimeImplicants();
		EPI.addAll(primeImplicants);

		StringBuilder firstString = new StringBuilder();
		for(int i = 0; i < primeImplicants.size(); i++) {
			firstString.append("0");
		}

		backtrack(0, firstString, primeImplicants, minterms);
		return EPI;
	}

	private void backtrack(int index, StringBuilder s, List<Implicant> PI, List<Implicant> minterms) {
		if(index == s.length()) {
			possibleEPI(s.toString(), PI, minterms);
			return;
		}
		
		s.setCharAt(index, '0');
		backtrack(index + 1, s, PI, minterms);

		s.setCharAt(index, '1');
		backtrack(index + 1, s, PI, minterms);
	}
	
	private void possibleEPI(String s, List<Implicant> PI, List<Implicant> minterms) {
		StringBuilder testString = new StringBuilder();
		List<Implicant> maybeEPI = new ArrayList<Implicant>();

		for(int i = 0; i < s.length(); i++) {
			if(s.charAt(i) == '1') {
				testString.append(PI.get(i).getImplicant() + " ");
				maybeEPI.add(PI.get(i));
			}
		}

		String[] number = testString.toString().split("\\s");
		for(Implicant i : minterms) {
			int j = 0;
			for(j = 0; j < number.length; j++) {
				if(i.getImplicant().equals(number[j])) {
					break;
				}
			}
			if(j >= number.length) {
				return;
			}
		}	

		if(maybeEPI.size() < EPI.size()) {
			EPI.clear();
			EPI.addAll(maybeEPI);
		}
	}
}
