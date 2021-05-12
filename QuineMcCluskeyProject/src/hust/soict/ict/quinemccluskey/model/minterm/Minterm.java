package hust.soict.ict.quinemccluskey.model.minterm;

public class Minterm extends Implicant {

	// Getters and setters
	Minterm(String minterm) {
		super(minterm);
	}

	// Methods
	public void toBinaryExpression() {
		String binEx[] = super.minterm.split("\\s");			// split by white space
		
		for (int i = 0; i < binEx.length; i++) {
			super.binaryExpression = Integer.toBinaryString(Integer.parseInt(binEx[i])) + " ";
		}	
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Minterm) {
			Minterm tempMinterm = (Minterm)obj;
			
			if (super.binaryExpression == tempMinterm.binaryExpression) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	
}
