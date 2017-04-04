package com.kostiantyn.service.evalex;

import com.kostiantyn.domain.Arguments;
import com.kostiantyn.service.CalculationService;
import com.kostiantyn.service.FormulaProvider;
import com.udojava.evalex.Expression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class EvalExCalculator implements CalculationService {

    private FormulaProvider formulaProvider;

    @Override
    public Number process(Arguments arguments) throws IOException {
        List<Double> values = arguments.getValues();

        Expression expression = new Expression(formulaProvider.getFormulaDefault());

        for (int i = 0; i < values.size(); i++) {
            expression.with("x" + (i + 1), values.get(i).toString());
        }

        return expression.eval();
    }

    @Override
    @Autowired
    @Qualifier(value = "propertyFormulaProvider")
    public void setFormulaProvider(FormulaProvider formulaProvider) {
        this.formulaProvider = formulaProvider;
    }
}
