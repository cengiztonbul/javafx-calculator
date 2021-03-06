package calculator;

import java.util.LinkedList;

// this class represent an expression and supports integer numbers and basic arithmetic operators
public class Expression
{
    // 3 + 5 * 2 will be stored as:
    //      numbers: [3, 5, 2]
    //      operators: [+, *]
    public LinkedList<Character> operators;
    public LinkedList<Double> numbers;

    public Expression(String expression) throws Exception
    {
        if (!validateExpression(expression))
        {
            throw new Exception("Syntax Error!");
        }

        operators = new LinkedList<>();
        numbers = new LinkedList<>();

        splitElements(expression);
    }

    // splits the operators and numbers
    private void splitElements(String expression)
    {
        int lastStartPoint = -1;
        for (int i = 0; i < expression.length(); i++)
        {
            char character = expression.charAt(i);

            if (character == '+' || character == '-' || character == '*' || character == '/')
            {
                operators.add(character);

                char[] number = new char[i - lastStartPoint];
                expression.getChars(lastStartPoint + 1, i, number, 0);
                numbers.add(Double.parseDouble(new String(number)));

                lastStartPoint = i;
            }
        }

        char[] number = new char[expression.length() - lastStartPoint - 1];
        expression.getChars(lastStartPoint + 1, expression.length(), number, 0);
        numbers.add(Double.parseDouble(new String(number)));
    }

    // returns false if the expression is not valid
    private boolean validateExpression(String expression)
    {
        if (expression.equals(""))
        {
            return false;
        }

        char firstCharacter = expression.charAt(0);
        char lastCharacter = expression.charAt(expression.length() - 1);

        if (isOperator(firstCharacter) || isOperator(lastCharacter))
        {
            return false;
        }

        boolean isPrevOperator = false;

        for(int i = 0; i < expression.length(); i++)
        {
            if (isOperator(expression.charAt(i)))
            {
                if (isPrevOperator)
                {
                    return false;
                }
                isPrevOperator = true;
            }
            else if (isNumerical(expression.charAt(i)))
            {
                isPrevOperator = false;
            }
            else
            {
                return false;
            }
        }

        return true;
    }

    private boolean isNumerical(char number)
    {
        return number >= '0' && number <= '9';
    }

    private boolean isOperator(char operator)
    {
        return operator == '+' || operator == '-' || operator == '*' || operator == '/';
    }
}
