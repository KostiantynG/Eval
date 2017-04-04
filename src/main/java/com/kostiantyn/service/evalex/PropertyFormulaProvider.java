package com.kostiantyn.service.evalex;

import com.kostiantyn.service.FormulaProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@PropertySource("classpath:config.properties")
@Component
public class PropertyFormulaProvider implements FormulaProvider {

    @Value("${formula}")
    private String formula;
    private Map<String, String> formulaMap = new HashMap<>();

    @Override
    public String getFormulaDefault() {
        return formula;
    }

    @Override
    public String getFormula(String name) {
        return formulaMap.get(name);
    }

    @Override
    public void registerDefault(String formula) {
        this.formula = formula;
    }

    @Override
    public void registerFormula(String name, String formula) {
        formulaMap.put(name, formula);
    }
}
