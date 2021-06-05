package hust.soict.ict.quinemccluskey.test;

import java.util.ArrayList;

import hust.soict.ict.quinemccluskey.model.column.Column;
import hust.soict.ict.quinemccluskey.model.minterm.Implicant;
import hust.soict.ict.quinemccluskey.model.minterm.Minterm;
import hust.soict.ict.quinemccluskey.model.output.CanonicalForm;
import hust.soict.ict.quinemccluskey.model.output.POS;
import hust.soict.ict.quinemccluskey.model.output.SOP;
import hust.soict.ict.quinemccluskey.model.table.IntermediateTable;
import hust.soict.ict.quinemccluskey.model.table.PITable;
import hust.soict.ict.quinemccluskey.model.table.Table;
import hust.soict.ict.quinemccluskey.utils.Variable;

public class OutputFunctionTest{
	public static void main(String[] args) {
		CanonicalForm out = new SOP();
		ArrayList<Implicant> buff = new ArrayList<Implicant>();
		Variable.numberDigits = 4;
		
		Implicant a = new Minterm("0");
		Implicant b = new Minterm("1");
		Implicant c = new Minterm("3");
		Implicant d = new Minterm("6");
		Implicant e = new Minterm("7");
		Implicant f = new Minterm("9");
		Implicant g = new Minterm("11");
		Implicant h = new Minterm("13");
		Implicant i = new Minterm("15");
		
		buff.add(a);
		buff.add(b);
		buff.add(c);
		buff.add(d);
		buff.add(e);
		buff.add(f);
		buff.add(g);
		buff.add(h);
		buff.add(i);

		Column firstCol = new Column(buff);
		
		Table intermediateTable = new IntermediateTable(firstCol);
		intermediateTable.generate();
		
		Table primeImplicantTable = new PITable(intermediateTable);
		primeImplicantTable.generate();

		out.generate((PITable)primeImplicantTable, buff);
		System.out.println(out.getResult());
		
	}
}
