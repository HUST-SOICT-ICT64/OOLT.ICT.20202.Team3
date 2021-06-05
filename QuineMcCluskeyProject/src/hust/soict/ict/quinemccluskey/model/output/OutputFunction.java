package hust.soict.ict.quinemccluskey.model.output;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import hust.soict.ict.quinemccluskey.model.minterm.Implicant;
import hust.soict.ict.quinemccluskey.model.table.PITable;

public interface OutputFunction {
	public void generate(PITable table, List<Implicant> implicants);
}
