package com.jonathanpaul.calculator.operations;

import com.jonathanpaul.calculator.interfaces.MathProcessing;

public class Divider extends CalculateBase implements MathProcessing {

    public Divider() {}

    public Divider(double firstValue, double secondValue) {
        super(firstValue, secondValue);
    }

    @Override
    public String getKeyword() {
        return "divide";
    }

    @Override
    public String getSymbol() {
        return "/";
    }

    @Override
    public double doCalculation(double firstValue, double secondValue) {
        return firstValue / secondValue;
    }
}
