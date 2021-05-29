package hust.soict.ict.quinemccluskey.model.table;

import java.util.ArrayList;
import java.util.List;

import hust.soict.ict.quinemccluskey.model.column.Column;
import hust.soict.ict.quinemccluskey.model.minterm.Implicant;

public class IntermediateTable extends Table {
    private List<Column> columns = new ArrayList<Column>();

    public IntermediateTable(Column c) {
        this.columns.add(c);
    }

    public List<Implicant> getPrimeImplicants() {
        for(Column col : columns) {
            for(int i = 0; i < col.size(); i++) {
                if(col.getImplicantAt(i).isPI()) {
                    this.primeImplicants.add(col.getImplicantAt(i));
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
                System.out.println(c.getImplicantAt(i).getImplicant() + " " + c.getImplicantAt(i).getBinaryExpression());
            }
            j++;
        }
    }

	public int size() {
		return columns.size();
	}

	public Column getColumnAtPosition(int index) {
		return columns.get(index);
	}

}
