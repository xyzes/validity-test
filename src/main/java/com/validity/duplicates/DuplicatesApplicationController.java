package com.validity.duplicates;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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