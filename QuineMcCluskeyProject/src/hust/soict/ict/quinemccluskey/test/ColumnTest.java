package hust.soict.ict.quinemccluskey.test;

import java.util.Scanner;

import hust.soict.ict.quinemccluskey.model.Variable;
import hust.soict.ict.quinemccluskey.model.column.Column;
import hust.soict.ict.quinemccluskey.model.minterm.Implicant;
import hust.soict.ict.quinemccluskey.model.minterm.Minterm;

public class ColumnTest {
    public static void main(String[] args) {
        Variable.numberDigits = 4;
        String implicant;
        Scanner scanner = new Scanner(System.in);
        Column firstColumn = new Column();
        for(int i = 0; i < 7; i++) {
            implicant = scanner.nextLine();
            firstColumn.addImplicant(new Minterm(implicant));
        }
    
        System.out.println("Implicant and their Binary Expression");
        for(int i = 0; i < firstColumn.size(); i++) {
            Implicant im = firstColumn.getImplicantAt(i);
            System.out.println(im.getImplicant() + " " + im.getBinaryExpression());
        }

        System.out.println("Merging column until the end");
        int stage = 1;
        Column lastCol = firstColumn;
        while(true) {
            Column nextCol = new Column();
            nextCol.addImplicant(lastCol.generateNextColumn());
            if(nextCol.size() != 0) {
                System.out.println("Stage " + stage);
                for(int i = 0; i < nextCol.size(); i++) {
                    Implicant im = nextCol.getImplicantAt(i);
                    System.out.println(im.getImplicant() + " " + im.getBinaryExpression());
                }
                stage++;
                lastCol = nextCol;
            }
            else {
                break;
            }
        }
        scanner.close();
    } 
}
