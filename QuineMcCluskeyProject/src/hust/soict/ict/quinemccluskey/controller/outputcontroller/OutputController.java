package hust.soict.ict.quinemccluskey.controller.outputcontroller;


import hust.soict.ict.quinemccluskey.controller.Controller;
import hust.soict.ict.quinemccluskey.model.output.CanonicalForm;
import hust.soict.ict.quinemccluskey.model.table.IntermediateTable;
import hust.soict.ict.quinemccluskey.model.table.PITable;
import hust.soict.ict.quinemccluskey.utils.FunctionConverter;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

public class OutputController extends Controller {
    @FXML
    private GridPane intermediateTable;

    @FXML
    private GridPane primeImplicantTable;

    @FXML
    private GridPane makeEquationTable;
    
    @FXML
    private Label finalEquation;
    
    @FXML
    private Label typeLabel;

    public void setIntermediateTable(IntermediateTable table) {
        for(int i = 0; i < table.size(); i ++) {
            Label intermediatelbl = new Label();
            for(int j = 0; j < table.getColumnAtPosition(i).size(); j ++) {
                intermediatelbl.setText(intermediatelbl.getText() + "\n" 
		            + "(" + table.getColumnAtPosition(i).getImplicantAt(j).getImplicant() + ") " 
		            + table.getColumnAtPosition(i).getImplicantAt(j).getBinaryExpression());
            }
            intermediatelbl.setFont(Font.font(intermediatelbl.getFont().getName(), 
            						FontWeight.BOLD, 
            						FontPosture.REGULAR, 
            						14));
            intermediateTable.add(intermediatelbl, i, 1);
            GridPane.setHalignment(intermediatelbl, HPos.CENTER);
            GridPane.setValignment(intermediatelbl, VPos.TOP);
        }
    }

    public void setPITable(PITable table) {
    	Label[] ticks = new Label[100];
    	
    	for(int i = 0; i < table.getPrimeImplicants().size(); i++) {
    		
    		Label implicantLabel = new Label(table.getPrimeImplicants().
    								get(i).getImplicant());
    		
    		implicantLabel.setFont(Font.font(implicantLabel.getFont().getName(), 
									FontWeight.BOLD, 
									FontPosture.REGULAR, 
									14));
    		
    		primeImplicantTable.add(implicantLabel, 0, i + 1);
			GridPane.setHalignment(implicantLabel, HPos.CENTER);
	        GridPane.setValignment(implicantLabel, VPos.TOP);


    		String[] minterms = table.getPrimeImplicants().get(i).
    							getImplicant().split("\\W+");
    		for(int j = 0; j < minterms.length; j++) {
        		ticks[j] = new Label("x"); 
    			primeImplicantTable.add(ticks[j], Integer.parseInt(minterms[j]) + 1, i + 1);
    			primeImplicantTable.setHalignment(ticks[j], HPos.CENTER);
    		}
 
    	}
    }

    public void setMakeEquationTable(CanonicalForm out) {
    	for(int i = 0; i < out.getEPI().size(); i ++) {
    		Label EPILabel = new Label
    				(out.getEPI().get(i).getImplicant());
    		Label toBinaryLabel = new Label
    				(out.getEPI().get(i).getBinaryExpression());
    		FunctionConverter.fromEPIToFunction(out.getEPI().get(i));
    		Label characterLabel = new Label
    				(out.getResult());
    		EPILabel.setFont(Font.font(EPILabel.getFont().getName(), 
					FontWeight.BOLD, 
					FontPosture.REGULAR, 
					14));
    		toBinaryLabel.setFont(Font.font(toBinaryLabel.getFont().getName(), 
					FontWeight.BOLD, 
					FontPosture.REGULAR, 
					14));
    		characterLabel.setFont(Font.font(characterLabel.getFont().getName(), 
					FontWeight.BOLD, 
					FontPosture.REGULAR, 
					14));

    		makeEquationTable.add(EPILabel, 0, i + 1);
    		makeEquationTable.add(toBinaryLabel, 1, i + 1);
    		makeEquationTable.add(characterLabel, 2, i + 1);
    		GridPane.setHalignment(EPILabel, HPos.CENTER);
            GridPane.setValignment(EPILabel, VPos.TOP);
            GridPane.setHalignment(toBinaryLabel, HPos.CENTER);
            GridPane.setValignment(toBinaryLabel, VPos.TOP);
            GridPane.setHalignment(characterLabel, HPos.CENTER);
            GridPane.setValignment(characterLabel, VPos.TOP);
    	}
    }
    
    public void setFinalEquation(CanonicalForm out) {
    	finalEquation.setText(out.getResult());
    }
}