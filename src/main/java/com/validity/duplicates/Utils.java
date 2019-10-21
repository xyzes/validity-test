package com.validity.duplicates;

import org.apache.commons.csv.CSVParser;
import org.apache.commons.codec.language.DoubleMetaphone;
import org.apache.commons.text.similarity.LevenshteinDistance;

/**
 * Utils class contains function to find duplicates in a CSV file and variables
 * to store duplicates and non-duplicates as HTML list items. These variables are
 * later accessed by DuplicatesApplicationController.
 */
public class Utils {

  // String dupes stores entries that are potential duplicates
  public String dupes = "<li>Test</li><li>Test</li>";
  // String clear stores entries that are not potential duplicates
  public String clear = "<li>Test</li><li>Test</li>";

  public void findDuplicates() {
    // TODO : Parse through CSV file and use String similarity helpers to find dupes.
  }


}
