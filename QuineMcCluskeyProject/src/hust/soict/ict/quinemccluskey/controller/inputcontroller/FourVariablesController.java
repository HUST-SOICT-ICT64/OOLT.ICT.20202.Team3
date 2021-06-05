package hust.soict.ict.quinemccluskey.controller.inputcontroller;

import hust.soict.ict.quinemccluskey.utils.Variable;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

public class FourVariablesController extends InputController {
			
    @FXML
    private GridPane truthTable;
    
    @FXML
    void initialize() {
        this.truthTable.setGridLinesVisible(true);
        Variable.numberDigits = 4;
        Variable.maxNumberVariables = 16;

        this.radioButton = new RadioButton[16];
        this.valueLabel = new Label[16];

        for(int i = 0; i < 16; i++) {
            radioButton[i] = new RadioButton();
            valueLabel[i] = new Label("0");

            valueLabel[i].setFont(Font.font(valueLabel[i].getFont().getName(), 
                                            FontWeight.BOLD, 
                                            FontPosture.ITALIC, 
                                            18));

            GridPane.setHalignment(valueLabel[i], HPos.CENTER);
            GridPane.setHalignment(radioButton[i], HPos.CENTER);

            truthTable.add(radioButton[i], 6, i + 1);
            truthTable.add(valueLabel[i], 5, i + 1);

            final int j = i;
            radioButton[i].setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event) {
                    if(radioButton[j].isSelected()) {
                        valueLabel[j].setText("1");
                    }
                    else {
                        valueLabel[j].setText("0");
                    }
                }

            });
        }
    }

    
}
