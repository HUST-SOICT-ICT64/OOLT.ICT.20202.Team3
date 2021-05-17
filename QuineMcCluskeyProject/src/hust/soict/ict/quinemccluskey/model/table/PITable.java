package hust.soict.ict.quinemccluskey.model.table;

import java.util.ArrayList;
import java.util.List;

import hust.soict.ict.quinemccluskey.model.minterm.Implicant;

public class PITable implements Table {
    private List<Implicant> primeImplicants = new ArrayList<Implicant>();
    private Table intermediateTable;

    public PITable(Table table) {
        primeImplicants = new ArrayList<Implicant>();
        intermediateTable = table;
    }

    @Override
    public void generate() {
        if(intermediateTable instanceof IntermediateTable) {
            primeImplicants = ((IntermediateTable) intermediateTable).getPrimeImplicants();
        }
    }

    @Override
    public void display() {
        for(Implicant i : primeImplicants) {
            System.out.println(i.getImplicant() + " " + i.getBinaryExpression());
        }
    }

}
