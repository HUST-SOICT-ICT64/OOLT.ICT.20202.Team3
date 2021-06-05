package hust.soict.ict.quinemccluskey.model.output;

import java.util.List;

import hust.soict.ict.quinemccluskey.model.Variable;
import hust.soict.ict.quinemccluskey.model.minterm.Implicant;
import hust.soict.ict.quinemccluskey.model.table.PITable;
import hust.soict.ict.quinemccluskey.utils.FunctionConverter;

public class POS extends OutputFunction{

	@Override
	public void generate(PITable table, List<Implicant> minterms) {
		takeEPI(table, minterms);
		result = FunctionConverter.fromEPIToFunction(EPI);
		result = FunctionConverter.DeMorgan(result);
		
		if(minterms.size() == 0) {
			result = "1";
		}
		else if(minterms.size() == Variable.maxNumberVariables) {
			result = "0";
		}
	}
}
