package com.jonathanpaul.calculator.operations;

import com.jonathanpaul.calculator.interfaces.MathProcessing;

public class Adder extends CalculateBase implements MathProcessing {

    public Adder() {}

    public Adder(double firstValue, double secondValue) {
        super(firstValue, secondValue);
    }

    @Override
    public String getKeyword() {
        return "add";
    }

    @Override
    public String getSymbol() {
        return "+";
    }

    @Override
    public double doCalculation(double firstValue, double secondValue) {
        return firstValue + secondValue;
    }
}
