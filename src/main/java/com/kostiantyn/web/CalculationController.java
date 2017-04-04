package com.kostiantyn.web;

import com.kostiantyn.domain.Arguments;
import com.kostiantyn.service.CalculatorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class CalculationController {
    @Autowired
    private CalculatorFactory calculatorFactory;

    /**
     * curl -H "Content-Type: application/json" -X POST -d '{"values":[2,3,4]}' http://localhost:8090/calculate?engine=nashorn
     */
    @PostMapping(value = "/calculate",
            consumes = "application/json")
    public ResponseEntity<String> handler(@RequestBody Arguments arguments,
                                          @RequestParam(value = "engine", required = false, defaultValue = "evalex") String engine) {
        String result;

        try {
            result = calculatorFactory.getCalculator(engine).process(arguments).toString();
        } catch (ArithmeticException | NumberFormatException e) {
            result = "Wrong arguments " + e.getMessage();
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
