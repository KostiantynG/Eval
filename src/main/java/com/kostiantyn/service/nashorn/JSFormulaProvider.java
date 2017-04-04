package com.kostiantyn.service.nashorn;

import com.kostiantyn.service.FormulaProvider;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import static java.io.File.separator;

/**
 * Should provide any Javascript code that returns Double or Long
 * <p>
 * Example: Math.sqrt(Math.pow(x1,2) + Math.pow(x2,2))
 */
@Component
public class JSFormulaProvider implements FormulaProvider {

    private static final String SCRIPT_PATH = "." + separator + "src" + separator + "main" + separator + "resources" + separator + "script.js";

    private String formula;
    private Map<String, String> formulaMap = new HashMap<>();

    @Override
    public String getFormulaDefault() throws IOException {
        try {
            File file = new File(SCRIPT_PATH);
            byte[] encoded = Files.readAllBytes(Paths.get(file.getPath()));
            formula = new String(encoded, "UTF-8");
            return formula;

        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }
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