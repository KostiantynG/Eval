package com.kostiantyn.service;

import com.kostiantyn.domain.Arguments;
import com.kostiantyn.service.evalex.EvalExCalculator;
import org.junit.Test;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;


public class EvalExCalculatorTest {

    public static final double DELTA = 0.0000001d;

    @Test
    public void testProcess() throws Exception {
        FormulaProvider formulaProviderMock = mock(FormulaProvider.class);
        when(formulaProviderMock.getFormulaDefault()).thenReturn("SQRT(x1^2 + x2^2)");

        EvalExCalculator calculationService = new EvalExCalculator();
        calculationService.setFormulaProvider(formulaProviderMock);

        Arguments arguments = new Arguments();
        arguments.setValues(asList(2d, 3d));

        Number result = calculationService.process(arguments);

        assertEquals(3.6055512d, result.doubleValue(), DELTA);
    }
}