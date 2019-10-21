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

/**
 * Utils class contains function to find duplicates in a CSV file and variables
 * to store duplicates and non-duplicates as HTML list items. These variables are
 * later accessed by DuplicatesApplicationController.
 */
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
              /**
               * The following conditional statements contain the logic for finding duplicates.
               * Originally only one helper was called, which changed the has_match variable to
               * true if duplicate was found. However, by assuming the variable to be true, it is
               * possible to add many other comparisons as else if statements. The variable is then
               * set to false if none of those conditions are met.
               */
              has_match = true; // <--- NEW: assume true, only change back if there is no match.
              // This block checks if the first name and last name match:
              if (this.nameMatch(first_name_LEFT, first_name_RIGHT,
                  last_name_LEFT, last_name_RIGHT)){
                // System.out.println("NAME MATCH");
                this.dupes = this.dupes + "<li>" + left.toString() + "</li>"
                    + "<li>" + right.toString() + "</li>";
                // If the names match, set the flag to true
                hasMatch.add(left_row); // <--- Prevents from adding the record to non-dupes
                hasMatch.add(right_row); // <--- Prevents from adding the target to non-dupes
              }
              // This block checks if e-mails match:
              else if (this.emailMatch(left.get(4), right.get(4))){
                // TODO: Find a way to remove duplicate code without sacrificing expandability
                // Do the same as previous block if matches:
                this.dupes = this.dupes + "<li>" + left.toString() + "</li>"
                    + "<li>" + right.toString() + "</li>";
                hasMatch.add(left_row); // <--- Prevents from adding the record to non-dupes
                hasMatch.add(right_row); // <--- Prevents from adding the target to non-dupes
              }
              else {
                has_match = false;
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
    /*System.out.println(first_L + last_L + " " + first_R + last_R);
    System.out.println(levenshtein || metaphone);*/
    return levenshtein || metaphone;
  }

  private boolean emailMatch(String email_L, String email_R) {
    return this.levenshtein.apply(email_L, email_R) <= 3
        || this.metaphone.isDoubleMetaphoneEqual(email_L, email_R);
  }
}
