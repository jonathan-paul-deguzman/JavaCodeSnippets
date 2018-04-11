package com.jonathanpaul.calculator.operations;

import com.jonathanpaul.calculator.interfaces.MathProcessing;

public class Subtracter extends CalculateBase implements MathProcessing {

    public Subtracter() {}

    public Subtracter(double firstValue, double secondValue) {
        super(firstValue, secondValue);
    }

    @Override
    public String getKeyword() {
        return "subtract";
    }

    @Override
    public String getSymbol() {
        return "-";
    }

    @Override
    public double doCalculation(double firstValue, double secondValue) {
        return firstValue - secondValue;
    }
}
