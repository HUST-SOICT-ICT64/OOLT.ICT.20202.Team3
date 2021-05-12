package hust.soict.ict.quinemccluskey.model.minterm;

public class Minterm extends Implicant {

	// Getters and setters
	public Minterm(String minterm) {
		super(minterm);
	}

	// Methods
	public void toBinaryExpression() {
		String binEx[] = super.implicant.split("\\s");			// split by white space
		
		for (int i = 0; i < binEx.length; i++) {
			super.binaryExpression = Integer.toBinaryString(Integer.parseInt(binEx[i])) + " ";
		}	
	}
	
}
