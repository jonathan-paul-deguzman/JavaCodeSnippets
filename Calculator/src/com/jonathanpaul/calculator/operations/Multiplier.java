package com.jonathanpaul.calculator.operations;

import com.jonathanpaul.calculator.interfaces.MathProcessing;

public class Multiplier extends CalculateBase implements MathProcessing {

    public Multiplier() {}

    public Multiplier(double firstValue, double secondValue) {
        super(firstValue, secondValue);
    }

    @Override
    public String getKeyword() {
        return "multiply";
    }

    @Override
    public String getSymbol() {
        return "*";
    }

    @Override
    public double doCalculation(double firstValue, double secondValue) {
        return firstValue * secondValue;
    }
}
