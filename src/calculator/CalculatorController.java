package calculator;

import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;



public class CalculatorController
{
    @FXML
    private TextField inputText;

    @FXML
    private TextField outputText;


    private boolean isLastOperatorOrEmpty = true;

    public CalculatorController()
    {
    }

    @FXML
    private void initialize()
    {
    }

    @FXML void numericalInputButton(ActionEvent event)
    {
    }

    @FXML void operatorInputButton(ActionEvent event)
    {
    }

    @FXML
    private void printOutput(ActionEvent event)
    {
    }
}