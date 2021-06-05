package hust.soict.ict.quinemccluskey.test;


import java.util.Scanner;

import hust.soict.ict.quinemccluskey.model.minterm.Minterm;
import hust.soict.ict.quinemccluskey.utils.Variable;

public class ImplicantTest {

	public static void main(String[] args) {
        Variable.numberDigits = 4;
        String str;
        
        Scanner keyboard = new Scanner(System.in);
        
        System.out.println("2 random implicants from 0 to 15: ");
        str = keyboard.nextLine();
        Minterm minterm1 = new Minterm(str);
        System.out.println(minterm1.getImplicant() + ": " + minterm1.getBinaryExpression());
        
        str = keyboard.nextLine();
        Minterm minterm2 = new Minterm(str);
        System.out.println(minterm2.getImplicant() + ": " + minterm2.getBinaryExpression());
        
        // equals() test
    	System.out.print("equals() test: ");
        if (minterm1.equals(minterm2)) {
        	System.out.println("Equal!");
        } else {
        	System.out.println("Not equal!");
        }
        
        // parityCheck() test
    	System.out.print("parityCheck test: ");
        if (minterm1.parityCheck(minterm2)) {
        	System.out.println("Can be merged!");
        } else {
        	System.out.println("Can not merged!");
        }
        
        keyboard.close();
    }
	
}
