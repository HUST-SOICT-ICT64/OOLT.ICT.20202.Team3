package hust.soict.ict.quinemccluskey.model.minterm;

public abstract class Implicant {

	protected String implicant;
	protected String binaryExpression;

	// Getters and setters
	public String getImplicant() {
		return implicant;
	}

	public void setImplicant(String implicant) {
		this.implicant = implicant;
		if(this instanceof Minterm) {
			((Minterm) this).toBinaryExpression();
		}
	}
	
	public String getBinaryExpression() {
		return binaryExpression; 
	}
	
	// Constructors
	public Implicant(String implicant) {
		this.implicant = implicant;
		if(this instanceof Minterm) {
			((Minterm) this).toBinaryExpression();
		}
	}

	public Implicant(String implicant, String binaryExpression) {
		this.implicant = implicant;
		this.binaryExpression = binaryExpression;
	}
	
	// Methods
	public boolean parityCheck(Implicant implicant) {
		String e1 = this.binaryExpression;
		String e2 = implicant.binaryExpression;
		int countDiff = 0;
		
		for(int i = 0; i < e1.length(); i++) {
			if(e1.charAt(i) != e2.charAt(i)) {
				countDiff++;
			}
		}

		return countDiff == 1;
		
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Implicant) {
			Implicant tempImplicant = (Implicant)obj;
			
			if (binaryExpression.equals(tempImplicant.binaryExpression)) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
}
