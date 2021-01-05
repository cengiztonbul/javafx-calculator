package calculator;

import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class CalculatorController
{
    @FXML
    private TextField inputText;

    @FXML
    private TextField outputText;

    private boolean startingWithOperator;

    private boolean emptyOutput;
    private double outputValue;

    public CalculatorController()
    {
    }

    @FXML
    private void initialize()
    {
        emptyOutput = true;
        outputValue = 0;
        outputText.setText("0");
    }


    // call for numerical buttons
    // writes the button value to the input field
    @FXML
    void inputButton(ActionEvent event)
    {
        String buttonValue = ((Button)event.getSource()).getUserData().toString();
        String newInputText = inputText.getText() + buttonValue;

        inputText.setText(newInputText);
    }

    @FXML
    private void printOutput(ActionEvent event)
    {
        try
        {
            char firstChar = inputText.getText().charAt(0);
            startingWithOperator = firstChar == '+' || firstChar == '-' || firstChar == '*' || firstChar == '/';
            String input = inputText.getText();

            // if the input starts with an operator and output is not empty
            // insert output at the beginning of the input
            if (startingWithOperator && !emptyOutput)
            {
                input = (int)outputValue + input;
            }

            // run the logic and set output text
            CalculatorBusiness cb = new CalculatorBusiness();
            double res = cb.evaluateExpression(input);
            outputText.setText(Double.toString(res));
            outputValue = res;
            outputText.setText(Double.toString(res));
            emptyOutput = false;
        }
        catch (Exception e)
        {
            // if there is an error,  print the error
            outputText.setText(e.getMessage());
            emptyOutput = true;
        }

        inputText.clear();
    }

    @FXML
    private void clear()
    {
        emptyOutput = true;
        inputText.setText("");
        outputText.setText("0");
    }
}