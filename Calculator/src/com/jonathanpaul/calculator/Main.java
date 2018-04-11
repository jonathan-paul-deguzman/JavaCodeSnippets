package com.jonathanpaul.calculator;

import com.jonathanpaul.calculator.exceptions.InvalidStatementException;
import com.jonathanpaul.calculator.helpers.CalculateHelper;
import com.jonathanpaul.calculator.interfaces.MathProcessing;
import com.jonathanpaul.calculator.operations.Adder;
import com.jonathanpaul.calculator.operations.Divider;
import com.jonathanpaul.calculator.operations.Multiplier;
import com.jonathanpaul.calculator.operations.PowerOf;
import com.jonathanpaul.calculator.operations.Subtracter;

public class Main {

    public static void main(String[] args) throws InvalidStatementException {
        String[] statements = {
                "add 25.0 92.0",
                "subtract 225.0 17.0",
                "multiply 11.0 3.0",
                "divide 100.0 50.0",
                "power 5.0 2.0"
        };

        CalculateHelper helper = new CalculateHelper(new MathProcessing[] {
                new Adder(),
                new Subtracter(),
                new Multiplier(),
                new Divider(),
                new PowerOf()
        });

        for (String statement : statements) {
            String output = helper.process(statement);
            System.out.println(output);
        }
    }
}
