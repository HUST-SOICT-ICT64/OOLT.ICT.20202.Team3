package hust.soict.ict.quinemccluskey.model.table;

import java.util.ArrayList;
import java.util.List;

import hust.soict.ict.quinemccluskey.model.column.Column;
import hust.soict.ict.quinemccluskey.model.minterm.Implicant;

public class IntermediateTable implements Table {
    private List<Column> columns = new ArrayList<Column>();
    private List<Implicant> primeImplicants = new ArrayList<Implicant>();

    public IntermediateTable(Column c) {
        this.columns.add(c);
    }

    public List<Implicant> getPrimeImplicants() {
        for(Column col : columns) {
            for(int i = 0; i < col.size(); i++) {
                if(col.get(i).isPI()) {
                    this.primeImplicants.add(col.get(i));
                }
            }
        }
        return this.primeImplicants;
    }

    @Override
    public void generate() {
        Column lastCol = columns.get(0); 
        while(true) {
            Column nextCol = new Column();
            nextCol.addImplicant(lastCol.generateNextColumn());
            if(nextCol.size() != 0) {
                columns.add(nextCol);
                lastCol = nextCol;
            }
            else {
                break;
            }
        }
    }

    @Override
    public void display() {
        int j = 0;
        for(Column c : columns) {
            System.out.println("Column " + j);
            for(int i = 0; i < c.size(); i++) {
                System.out.println(c.get(i).getImplicant() + " " + c.get(i).getBinaryExpression());
            }
            j++;
        }
    }

}
