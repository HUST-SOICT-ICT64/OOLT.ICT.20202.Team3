package hust.soict.ict.quinemccluskey.controller.inputcontroller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import hust.soict.ict.quinemccluskey.controller.Controller;
import hust.soict.ict.quinemccluskey.controller.outputcontroller.OutputController;
import hust.soict.ict.quinemccluskey.model.column.Column;
import hust.soict.ict.quinemccluskey.model.minterm.Implicant;
import hust.soict.ict.quinemccluskey.model.minterm.Minterm;
import hust.soict.ict.quinemccluskey.model.output.CanonicalForm;
import hust.soict.ict.quinemccluskey.model.output.OutputFunction;
import hust.soict.ict.quinemccluskey.model.output.POS;
import hust.soict.ict.quinemccluskey.model.output.SOP;
import hust.soict.ict.quinemccluskey.model.table.IntermediateTable;
import hust.soict.ict.quinemccluskey.model.table.PITable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

public class InputController extends Controller {
	protected String homeURL = "/hust/soict/ict/quinemccluskey/view/HomeScreen.fxml";
	protected RadioButton radioButton[];
	protected Label valueLabel[];
	
	@FXML
	protected Button backButton;
	
    @FXML
    protected Button resetButton;
    
    @FXML
    protected Button submitButton;
    
    @FXML
    private ToggleGroup outputExpressionToggle;
    
    @FXML
    protected RadioButton SOPButton;
    
    @FXML
    protected RadioButton POSButton;
    
    @FXML
    void backButtonPressed(ActionEvent event) throws IOException {
    	switchScene(homeURL, event);
    }
    
    /*
     * 
     */
    @FXML
    void submitButtonPressed(ActionEvent event) throws IOException {
    	List<Implicant> minterms = new ArrayList<Implicant>();
    	
    	CanonicalForm out;

    	if (SOPButton.isSelected()) {
    		for (int i = 0; i < radioButton.length; i++) {
    			if (radioButton[i].isSelected()) {
    				minterms.add(new Minterm(String.valueOf(i)));
    			}
    		}
    		out = new SOP();
    	} else {
    		for (int i = 0; i < radioButton.length; i++) {
    			if (radioButton[i].isSelected() == false) {
    				minterms.add(new Minterm(String.valueOf(i)));
    			}
    		}
    		out = new POS();
    	}
    	Column column = new Column(minterms);
    	
    	IntermediateTable table = new IntermediateTable(column);
    	table.generate();
    	
    	PITable primeImplicantTable = new PITable(table);
    	primeImplicantTable.generate();
    	
    	out.generate(primeImplicantTable, minterms);
    	
    	FXMLLoader loader = new FXMLLoader(getClass().
    			getResource("/hust/soict/ict/quinemccluskey/view/Output.fxml"));
        
    	Parent root = loader.load();

        OutputController outputcontroller = loader.getController();
        outputcontroller.setIntermediateTable(table);
        outputcontroller.setPITable(primeImplicantTable);
        outputcontroller.setFinalEquation(out);
        outputcontroller.setMakeEquationTable(out);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    @FXML
    void resetPressed(ActionEvent event) {
    	for (RadioButton b : radioButton) {
    		b.setSelected(false);
    	}
    	
    	for (Label lb : valueLabel) {
    		lb.setText("0");
    	}
    }
}