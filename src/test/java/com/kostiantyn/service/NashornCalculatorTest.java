package com.kostiantyn.service;

import com.kostiantyn.domain.Arguments;
import com.kostiantyn.service.nashorn.NashornCalculator;
import org.junit.Test;

import static java.util.Arrays.asList;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class NashornCalculatorTest {

    public static final double DELTA = 0.0000001d;

    @Test
    public void testProcess() throws Exception {
        FormulaProvider formulaProviderMock = mock(FormulaProvider.class);
        when(formulaProviderMock.getFormulaDefault()).thenReturn("Math.sqrt(Math.pow(x1,2) + Math.pow(x2,2))");

        NashornCalculator nashornCalculator = new NashornCalculator();
        nashornCalculator.setFormulaProvider(formulaProviderMock);

        Arguments arguments = new Arguments();
        arguments.setValues(asList(2d, 3d));
        Number result = nashornCalculator.process(arguments);
        System.out.println(result);

    }

    @Test
    public void testCalc() throws Exception {
        FormulaProvider formulaProviderMock = mock(FormulaProvider.class);
        when(formulaProviderMock.getFormulaDefault()).thenReturn("Math.sqrt(Math.pow(x1,2) + Math.pow(x2,2))");

        NashornCalculator calculationService = new NashornCalculator();
        calculationService.setFormulaProvider(formulaProviderMock);

        Arguments arguments = new Arguments();
        arguments.setValues(asList(2d, 3d));

        Number result = calculationService.process(arguments);

        assertEquals(3.6055512d, result.doubleValue(), DELTA);
    }

}