package com.validity.duplicates;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.net.URL;
import java.util.ArrayList;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.codec.language.DoubleMetaphone;
import org.apache.commons.text.similarity.LevenshteinDistance;

public class Utils {

  // PATH to data being used for the web application
  private String file = "./data/normal.csv"; // <--- Change for different file
  // String dupes stores entries that are potential duplicates
  public String dupes = "";
  // String clear stores entries that are not potential duplicates
  public String clear = "";
  // Class-wide classes with methods for string comparison
  LevenshteinDistance levenshtein = new LevenshteinDistance();
  DoubleMetaphone metaphone = new DoubleMetaphone();

  public void findDuplicates() {
    // TODO: Look into improving this function with CSVParser.
    try {
      Iterable<CSVRecord> records = CSVFormat.DEFAULT.parse(new FileReader(this.file));
      ArrayList<Integer> hasMatch = new ArrayList();
      int left_row = 1;
      for (CSVRecord left : records) {
        boolean has_match = false;
        // Only checks the row if it was not already added as a duplicate:
        if (!hasMatch.contains(left_row)){
          // System.out.println(left);
          String first_name_LEFT = left.get(1);
          String last_name_LEFT = left.get(2);
          int right_row = 1;
          Iterable<CSVRecord> targets = CSVFormat.DEFAULT.parse(new FileReader(this.file));
          for (CSVRecord right : targets) {
            // This conditional checks whether or not the entry has been compared to the target
            // If right_row is greater, it ensures only forward checking happens:
            if (right_row > left_row) {
              String first_name_RIGHT = right.get(1);
              String last_name_RIGHT = right.get(2);
              // This block checks if the first name and last name match:
              if (this.nameMatch(first_name_LEFT, first_name_RIGHT,
                  last_name_LEFT, last_name_RIGHT)){
                System.out.println("NAME MATCH");
                this.dupes = this.dupes + "<li>" + left.toString() + "</li>"
                    + "<li>" + right.toString() + "</li>";
                // If the names match, set the flag to true
                has_match = true;
                hasMatch.add(left_row); // <--- Prevents from adding the record to non-dupes
                hasMatch.add(right_row); // <--- Prevents from adding the target to non-dupes
              }
            }
            right_row++;
          }
        }
        // If left item does not have duplicate, it is added to this.clear:
        if (!has_match && !hasMatch.contains(left_row)) {
          this.clear = this.clear + "<li>" + left.toString() + "</li>";
        }
        // System.out.println(has_match);
        left_row++;
      }
    }
    catch(FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private boolean nameMatch(String first_L, String first_R, String last_L, String last_R) {
    boolean levenshtein = this.levenshtein.apply(first_L, first_R) <= 2
        && this.levenshtein.apply(last_L, last_R) <= 2;
    boolean metaphone = this.metaphone.isDoubleMetaphoneEqual(first_L, first_R)
        && this.levenshtein.apply(last_L, last_R) <= 2;
    if (first_L.equals("Kale")) {
      System.out.println(first_L + last_L + " " + first_R + last_R);
      System.out.println(levenshtein || metaphone);
    }
    /*System.out.println(first_L + last_L + " " + first_R + last_R);
    System.out.println(levenshtein || metaphone);*/
    return levenshtein || metaphone;
  }
}
