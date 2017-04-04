package com.kostiantyn.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
public class MainPageController {

    @RequestMapping(value = {"/", "/index"})
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<String> startPage() {
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
