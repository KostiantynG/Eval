package com.kostiantyn.service.nashorn;

import com.kostiantyn.domain.Arguments;
import com.kostiantyn.service.CalculationService;
import com.kostiantyn.service.FormulaProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.IOException;
import java.util.List;

@Service
public class NashornCalculator implements CalculationService {

    private FormulaProvider formulaProvider;

    @Override
    public Number process(Arguments arguments) throws IOException, ScriptException, NoSuchMethodException {
        // Nashorn
        ScriptEngineManager mgr = new ScriptEngineManager();
        ScriptEngine engine = mgr.getEngineByName("JavaScript");

        String formula = formulaProvider.getFormulaDefault();

        List<Double> values = arguments.getValues();
        for (int i = 0; i < values.size(); i++) {
            formula = formula.replaceAll("x" + (i + 1) + "(?!\\d)", values.get(i).toString());
        }
        Object result = engine.eval(formula);

        if (result instanceof Double) {
            return (Double) result;
        } else if (result instanceof Long) {
            return (Long) result;
        } else {
            throw new RuntimeException("Script has wrong return type " + result.getClass().getTypeName());
        }
    }

    @Override
    @Autowired
    @Qualifier(value = "JSFormulaProvider")
    public void setFormulaProvider(FormulaProvider formulaProvider) {
        this.formulaProvider = formulaProvider;
    }
}
