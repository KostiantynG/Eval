package com.kostiantyn.service;

import java.io.IOException;

public interface FormulaProvider {
    String getFormulaDefault() throws IOException;

    String getFormula(String name);

    void registerDefault(String formula);

    void registerFormula(String name, String formula);
}
