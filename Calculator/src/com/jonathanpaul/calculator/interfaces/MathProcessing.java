package com.jonathanpaul.calculator.interfaces;

public interface MathProcessing {

    String SEPARATER = " ";

    String getKeyword();

    String getSymbol();

    double doCalculation(double firstValue, double secondValue);
}
