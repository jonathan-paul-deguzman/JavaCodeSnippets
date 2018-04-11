package com.jonathanpaul.calculator.operations;

import com.jonathanpaul.calculator.interfaces.MathProcessing;

public class PowerOf extends CalculateBase implements MathProcessing {

    public PowerOf() {}

    public PowerOf(double firstValue, double secondValue) {
        super(firstValue, secondValue);
    }

    @Override
    public String getKeyword() {
        return "power";
    }

    @Override
    public String getSymbol() {
        return "^";
    }

    @Override
    public double doCalculation(double firstValue, double secondValue) {
        return Math.pow(firstValue, secondValue);
    }
}
