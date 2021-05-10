package hust.soict.ict.quinemccluskey.model.column;

import java.util.ArrayList;
import java.util.List;

import hust.soict.ict.quinemccluskey.model.Variable;
import hust.soict.ict.quinemccluskey.model.minterm.Minterm;

public class Column {
	private List<Minterm> implicants;
	
	public Column() {
		implicants = new ArrayList<Minterm>();	
	}

	public void addMinterm(Minterm minterm) {
		implicants.add(minterm);
	}

	public void addMinterm(List<Minterm> minterms) {
		implicants.addAll(minterms);
	}

	public Minterm get(int index) {
		return implicants.get(index);
	}

	public int size() {
		return implicants.size();
	}

	public boolean ableToGenerateNextCol() {
		for(int i = 0; i < size(); i++) {
			Minterm first = implicants.get(i);
			for(int j = i + 1; j < size(); j++) {
				Minterm second = implicants.get(j);		
				if(first.equals(second)) {
					return true;
				}
			}
		}
		return false;
	}

	public List<Minterm> generateNextColumn() {
		List<Minterm> mintermsOfNextCol = new ArrayList<Minterm>();
		for(int i = 0; i < implicants.size(); i++) {
			Minterm first = implicants.get(i);
			for(int j = i + 1; j < implicants.size(); j++) {
				Minterm second = implicants.get(j);		
				if(first.parityCheck(second) == true) {
					// merge 2 minterms
					Minterm possibleMinterm = mergeTwoMinterms(first, second);
					// check if the combination exists yet
					if(AllMinterms.exist(possibleMinterm)) {
						continue;
					}
					// if not existed yet
					mintermsOfNextCol.add(possibleMinterm);
					allMinterms.add(possibleMinterm);		// this is a static function of 
															// class AllMinterms
				}
			}
		}
		return null;
	}

	public Minterm mergeTwoMinterms(Minterm first, Minterm second) {
		String minterm = first.getMinterm() + second.getMinterm();
		StringBuffer binaryExpression = new StringBuffer();
		for(int i = 0; i < Variable.numberDigits; i++) {
			char bit = first.getBinaryExpression().charAt(i);
			char firstBit = first.getBinaryExpression().charAt(i);
			char secondBit =  second.getBinaryExpression().charAt(i);
			if(firstBit == secondBit) {
				bit = '-';	
			}
			binaryExpression.append(bit);
		}
		return new Minterm(minterm, binaryExpression.toString()); 
	}
}
