package com.jonathanpaul.calculator.operations;

public class CalculateBase {
    private double firstValue;

    private double secondValue;

    private double result;

    // An empty constructor is needed to create a new instance using reflection (inspecting code in the same system).
    // Note that if we are not providing any additional constructors with arguments for our class, we do not need to
    // provide this empty constructor because the JVM gives us one by default.
    public CalculateBase() {}

    public CalculateBase(double firstValue, double secondValue) {
        this.firstValue = firstValue;
        this.secondValue = secondValue;
    }

    public double getFirstValue() {
        return firstValue;
    }

    public void setFirstValue(double firstValue) {
        this.firstValue = firstValue;
    }

    public double getSecondValue() {
        return secondValue;
    }

    public void setSecondValue(double secondValue) {
        this.secondValue = secondValue;
    }

    public double getResult() {
        return result;
    }

    public void setResult(double result) {
        this.result = result;
    }
}
