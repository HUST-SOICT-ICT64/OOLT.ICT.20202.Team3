package hust.soict.ict.quinemccluskey.test;

import hust.soict.ict.quinemccluskey.model.Variable;
import hust.soict.ict.quinemccluskey.model.column.Column;
import hust.soict.ict.quinemccluskey.model.minterm.Minterm;
import hust.soict.ict.quinemccluskey.model.table.IntermediateTable;
import hust.soict.ict.quinemccluskey.model.table.Table;


public class IntermediateTableTest {
    public static void main(String[] args) {
        Variable.numberDigits = 4;

        Column firstColumn = new Column();
        firstColumn.addImplicant(new Minterm("0"));
        firstColumn.addImplicant(new Minterm("2"));
        firstColumn.addImplicant(new Minterm("4"));
        firstColumn.addImplicant(new Minterm("5"));
        firstColumn.addImplicant(new Minterm("6"));
        firstColumn.addImplicant(new Minterm("9"));
        firstColumn.addImplicant(new Minterm("10"));
        
        Table table = new IntermediateTable(firstColumn);
        table.generate();
        table.display(); 
    
    
    
    }   
}
