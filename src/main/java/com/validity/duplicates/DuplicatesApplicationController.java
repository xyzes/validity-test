package com.validity.duplicates;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The DuplicatesApplicationController is in charge of issuing commands to
 * obtain or analyze data and returning the output to the web application.
 */
@RestController
public class DuplicatesApplicationController {

    @RequestMapping("/")
    public String index() {
        // Stores variables containing duplicates & non-duplicates
        Utils data = new Utils();
        data.findDuplicates(); // <--- Calls Utils.findDuplicates
        return "<p>Potential duplicates</p><ul>" + data.dupes +
            "</ul><p>Non-duplicates</p><ul>" + data.clear + "</ul>";
    }
}