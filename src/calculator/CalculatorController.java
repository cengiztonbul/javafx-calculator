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

    private boolean isLastOperatorOrEmpty = true;

    private boolean emptyOutput;
    private double outputValue;
    private boolean startingWithOperator;

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

    @FXML
    void numericalInputButton(ActionEvent event)
    {
        isLastOperatorOrEmpty = false;
        String buttonValue = ((Button)event.getSource()).getUserData().toString();
        String newInputText = inputText.getText() + buttonValue;

        inputText.setText(newInputText);
    }

    @FXML
    void operatorInputButton(ActionEvent event)
    {
        if (isLastOperatorOrEmpty && emptyOutput)
        {
            return;
        }
        if (!emptyOutput)
        {
            startingWithOperator = true;
        }

        isLastOperatorOrEmpty = true;

        String buttonValue = ((Button)event.getSource()).getUserData().toString();
        String newInputText = inputText.getText() + buttonValue;

        inputText.setText(newInputText);
    }

    @FXML
    private void printOutput(ActionEvent event)
    {
        try
        {
            char startingChar = inputText.getText().charAt(0);

            startingWithOperator = startingChar == '+' || startingChar == '-' || startingChar == '*' || startingChar == '/';

            String input = inputText.getText();
            if (startingWithOperator)
            {
                input = (int)outputValue + input;
            }

            CalculatorBusiness cb = new CalculatorBusiness();
            double res = cb.evaluateExpression(input);
            outputText.setText(Double.toString(res));
            outputValue = res;
            outputText.setText(Double.toString(res));
            emptyOutput = false;
        }
        catch (Exception e)
        {
            outputText.setText(e.getMessage());
            emptyOutput = true;
        }

        inputText.clear();
        isLastOperatorOrEmpty = true;
    }

    @FXML
    private void clear()
    {
        inputText.setText("");
        outputText.setText("0");
        emptyOutput = true;
    }
}