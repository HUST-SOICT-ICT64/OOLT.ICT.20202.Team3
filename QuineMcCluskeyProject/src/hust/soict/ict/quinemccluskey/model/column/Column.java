package hust.soict.ict.quinemccluskey.model.column;

import java.util.ArrayList;
import java.util.List;

import hust.soict.ict.quinemccluskey.model.Variable;
import hust.soict.ict.quinemccluskey.model.minterm.CombinedImplicant;
import hust.soict.ict.quinemccluskey.model.minterm.Implicant;
import hust.soict.ict.quinemccluskey.utils.Cache;

public class Column {
	private List<Implicant> implicants;
	
	public Column() {
		implicants = new ArrayList<Implicant>();	
	}

	public Column(List<Implicant> implicants) {
		this.implicants = implicants;
	}

	public void addImplicant(Implicant implicant) {
		implicants.add(implicant);
	}

	public void addImplicant(List<Implicant> implicants) {
		this.implicants.addAll(implicants);
	}

	public Implicant get(int index) {
		return implicants.get(index);
	}

	public int size() {
		return implicants.size();
	}

	public boolean ableToGenerateNextCol() {
		for(int i = 0; i < size(); i++) {
			Implicant first = implicants.get(i);
			for(int j = i + 1; j < size(); j++) {
				Implicant second = implicants.get(j);		
				if(first.equals(second)) {
					return true;
				}
			}
		}
		return false;
	}

	public List<Implicant> generateNextColumn() {
		List<Implicant> implicantsOfNextCol = new ArrayList<Implicant>();
		for(int i = 0; i < implicants.size(); i++) {
			Implicant first = implicants.get(i);
			for(int j = i + 1; j < implicants.size(); j++) {
				Implicant second = implicants.get(j);		
				if(first.parityCheck(second) == true) {
					// merge 2 implicants
					Implicant possibleImplicant = mergeTwoImplicants(first, second);

					// check if the combination exists yet
					if(Cache.exists(possibleImplicant)) {
						continue;
					}
					// if not existed yet then add to cache memory to mark it appearance
					Cache.add(possibleImplicant);

					implicantsOfNextCol.add(possibleImplicant);
				}
			}
		}
		return implicantsOfNextCol;
	}

	public Implicant mergeTwoImplicants(Implicant first, Implicant second) {
		String implicant = first.getImplicant() + " " + second.getImplicant();
		StringBuffer binaryExpression = new StringBuffer();
		for(int i = 0; i < Variable.numberDigits; i++) {
			char bit = first.getBinaryExpression().charAt(i);
			char firstBit = first.getBinaryExpression().charAt(i);
			char secondBit =  second.getBinaryExpression().charAt(i);
			if(firstBit != secondBit) {
				bit = '-';	
			}
			binaryExpression.append(bit);
		}
		return new CombinedImplicant(implicant, binaryExpression.toString()); 
	}
}
