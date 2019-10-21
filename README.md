<h4>Validity Co-Op Take Home Exercise</h4>
<h5>Summary</h5>
<p>This is a <strong>Java/Spring Boot web application</strong> to find duplicates in a CSV contact list. This application was developed using JetBrains IntelliJ Idea Ultimate.</p>
<h6>Instructions</h6>
<p>The application is only optimized and confirmed to run on IntelliJ Idea software. In order to run the application, clone this repository into a folder on your computer using Git and open the project folder on IntelliJ. On the solution explorer, right click on the project folder (named <strong>validity-test</strong> by default) and select <strong>Add Framework Support</strong>. Enable <strong>Maven</strong> support. Once Maven is enabled, right click on the project folder once again, select Maven, and click <strong>Reimport</strong>. This will download all other dependencies from the pom.xml file.</p>
<h5>Overview</h5>
<h6>Application files</h6>
<ul>
  <li><strong>DuplicatesApplication.java</strong>: contains the MAIN function to run the Spring Boot application.</li>
  <li><strong>DuplicatesApplicationController.java</strong>: controller to fetch data from the CSV files and return to the view.</li>
  <li><strong>Utils.java</strong>: Helpers to read and analyze CSV files and find duplicates based on Levenshtein distance and Double Metaphone.</li>
  </ul>
 <h6>Other</h6>
 <ul>
  <li><strong>DuplicatesApplicationTest.java</strong>: contains tests for Levenshtein and Metaphone implementations.</li>
  <li><strong>pom.xml</strong>: Maven project to import dependencies.</li>
 </ul>
 <h6>Dependencies</h6>
 <ul>
  <li>Spring Boot + Spring MVC</li>
  <li>JUnit 5.5.2</li>
  <li>Apache Commons Codec (Double Metaphone)</li>
  <li>Apache Commons Text (Levenshtein)</li>
  <li>Apache Commons CSV</li>
 </ul>
   
<p><strong>Works with both normal.csv and advanced.csv datasets.</strong></p>
