package hust.soict.ict.quinemccluskey.model.minterm;

public abstract class Implicant {

	protected String minterm; 			// 0 	3 	 5
	protected String binaryExpression; 	// 0000 0011 0101

	// Getters and setters
	public String getMinterm() {
		return minterm;
	}

	public void setMinterm(String minterm) {
		this.minterm = minterm;
	}
	
	public String getBinaryExpression() {
		return binaryExpression; 
	}
	
	// Constructors
	Implicant(String minterm) {
		this.minterm = minterm;
	}
	
	// Methods
	public boolean parityCheck(Minterm minterm) {
		String e2[] = this.binaryExpression.split("\\s");
		String e1[] = minterm.getBinaryExpression().split("\\s");
		int countDiff = 0;
		
		// 1st loop for checking the length of the argument (split by white space)
		// 2nd loop for checking the length of original input (split by white space)
		// 3rd loop for checking the differences between each character of the 2 split element
		
		outerloop:
		for (int i = 0; i < e1.length; i++) {						
			for (int j = 0; j < e2.length; j++) {					
				for (int k = 0; k < e1[i].length(); k++) {
					
					if (e1[i].charAt(k) != e2[k].charAt(k)) {
						countDiff += 1;
					}
					
					if (countDiff == 1) {
						break outerloop;			// break with a label of the outer loop
					} else {
						countDiff = 0;
					}
					
				}
			}	
		}
		
		return false;
		
	}
}
