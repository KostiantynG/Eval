package com.kostiantyn.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class CalculatorFactory {

    @Autowired
    @Qualifier(value = "evalExCalculator")
    private CalculationService evalExCalculator;

    @Autowired
    @Qualifier(value = "nashornCalculator")
    private CalculationService nashornCalculator;

    public CalculationService getCalculator(String engine) {
        if (engine == null) {
            return null;
        }
        if (engine.equalsIgnoreCase("nashorn")) {

            return nashornCalculator;

        } else if (engine.equalsIgnoreCase("evalex")) {

            return evalExCalculator;

        }

        return null;
    }
}
