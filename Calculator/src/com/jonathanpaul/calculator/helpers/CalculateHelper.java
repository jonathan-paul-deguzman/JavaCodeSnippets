package com.jonathanpaul.calculator.helpers;

import com.jonathanpaul.calculator.exceptions.InvalidStatementException;
import com.jonathanpaul.calculator.interfaces.MathProcessing;

public class CalculateHelper {
    private MathProcessing[] handlers;

    public CalculateHelper(MathProcessing[] handlers) {
        this.handlers = handlers;
    }

    public String process(String statement) throws InvalidStatementException {
        String[] parts = statement.split(MathProcessing.SEPARATER);

        if (parts.length != 3) {
            throw new InvalidStatementException("Incorrect number of fields", statement);
        }

        String keyword = parts[0];
        MathProcessing theHandler = null;
        for (MathProcessing handler : handlers) {
            if (keyword.equalsIgnoreCase(handler.getKeyword())) {
                theHandler = handler;
                break;
            }
        }


        try {
            double firstValue = Double.parseDouble(parts[1]);
            double secondValue = Double.parseDouble(parts[2]);
            double result = theHandler.doCalculation(firstValue, secondValue);

            // StringBuilder provides an efficient way to manipulate String values by providing a mutable string buffer
            StringBuilder sb = new StringBuilder();
            sb.append(firstValue);
            sb.append(" ");
            sb.append(theHandler.getSymbol());
            sb.append(" ");
            sb.append(secondValue);
            sb.append(" = ");
            sb.append(result);

            // Remember to convert the StringBuilder instance back into a String representation
            return sb.toString();
        } catch (NumberFormatException e) {
            throw new InvalidStatementException("Non-numeric data", statement, e);
        }
    }
}
