package hust.soict.ict.quinemccluskey.model.output;

import java.util.ArrayList;
import java.util.List;

import hust.soict.ict.quinemccluskey.model.minterm.Implicant;
import hust.soict.ict.quinemccluskey.model.table.PITable;

public abstract class CanonicalForm implements OutputFunction{
	protected String result;	// in SOP format
	protected List<Implicant> EPI = new ArrayList<Implicant> ();
	

	public List<Implicant> getEPI() {
		return EPI;
	}

	public String getResult() {
		return this.result;
	}	
	
	protected List<Implicant> takeEPI(PITable table, List<Implicant> minterms) {
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
