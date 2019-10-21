package com.validity.duplicates;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.net.URL;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.codec.language.DoubleMetaphone;
import org.apache.commons.text.similarity.LevenshteinDistance;

public class Utils {

  // String dupes stores entries that are potential duplicates
  public String dupes = "<li>Test</li><li>Test</li>";
  // String clear stores entries that are not potential duplicates
  public String clear = "<li>Test</li><li>Test</li>";

  public void findDuplicates() {
    try {
      Reader in = new FileReader("./data/normal.csv");
      Iterable<CSVRecord> records = CSVFormat.EXCEL.parse(in);
      for (CSVRecord record : records) {
        System.out.println(record);
      }
    }
    catch(FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }


}
