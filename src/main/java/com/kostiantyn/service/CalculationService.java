package com.kostiantyn.service;

import com.kostiantyn.domain.Arguments;

public interface CalculationService {
    Number process(Arguments arguments) throws Exception;
    void setFormulaProvider(FormulaProvider formulaProvider);
}
