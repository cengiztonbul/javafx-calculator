package calculator;

public class CalculatorBusiness
{
    public double evaluateExpression(String expression) throws Exception
    {
        Expression e = new Expression(expression);
        return evaluateExpression(e);
    }

    public double evaluateExpression(Expression elements) throws Exception
    {
        for (int i = 0; i < elements.operators.size(); i++)
        {
            if (elements.operators.get(i) == '*')
            {
                double leftNumber = elements.numbers.get(i);
                double rightNumber = elements.numbers.get(i + 1);
                elements.numbers.remove(i);
                elements.numbers.remove(i);
                elements.numbers.add(i, leftNumber * rightNumber);
                elements.operators.remove(i);
                i--;
            }
            else if (elements.operators.get(i) == '/')
            {
                double leftNumber = elements.numbers.get(i);
                double rightNumber = elements.numbers.get(i + 1);
                if (rightNumber == 0)
                {
                    throw new Exception("DivByZero");
                }
                elements.numbers.remove(i);
                elements.numbers.remove(i);
                elements.numbers.add(i,leftNumber / rightNumber);
                elements.operators.remove(i);
                i--;
            }
        }

        for (int i = 0; i < elements.operators.size(); i++)
        {
            if (elements.operators.get(i) == '+')
            {
                double leftNumber = elements.numbers.get(i);
                double rightNumber = elements.numbers.get(i + 1);
                elements.numbers.remove(i);
                elements.numbers.remove(i);
                elements.numbers.add(i, leftNumber + rightNumber);
                elements.operators.remove(i);
                i--;
            }
            else if (elements.operators.get(i) == '-')
            {
                double leftNumber = elements.numbers.get(i);
                double rightNumber = elements.numbers.get(i + 1);
                elements.numbers.remove(i);
                elements.numbers.remove(i);
                elements.numbers.add(i,(double) leftNumber - rightNumber);
                elements.operators.remove(i);
                i--;
            }
        }

        return elements.numbers.get(0);
    }
}