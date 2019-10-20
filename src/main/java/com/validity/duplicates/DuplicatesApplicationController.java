package com.validity.duplicates;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DuplicatesApplicationController {

    @RequestMapping("/")
    public String index() {
        return "Hello, world!";
    }
}