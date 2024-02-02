/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package studentdataprocessor;
import java.io.*;
import java.util.regex.Pattern;

/**
 *
 * @author jakubzaruski
 */
public class StudentDataProcessor {
    public static void main(String[] args) {
        // File paths for input and output files
        String inputFile = "/Users/jakubzaruski/Desktop/students.txt";
        String outputFile = "/Users/jakubzaruski/Desktop/status.txt";
        
        //Try with resources statement
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
            
            // Variables for storing student data
            String nameLine, numClassesString, studentNumber;
            
            // Reading the file line by line
            while ((nameLine = reader.readLine()) != null &&
                   (numClassesString = reader.readLine()) != null &&
                   (studentNumber = reader.readLine()) != null) {
               
            // Splitting the name line into first and second names
                String[] names = nameLine.split(" ");
                if (names.length != 2) {
                    // Error handling for incorrect name format
                    System.out.println("Error in name format: \"" + nameLine + "\"");
                }
                // Assigning first and second names
                String firstName = names[0];
                String secondName = names[1];
                
                // Validating student data
                String validationResult = validateStudentData(firstName, secondName, studentNumber, numClassesString);
                if (validationResult.equals("Valid")) {
                    // Determining workload based on number of classes
                    String workload = getWorkloadDescription(Integer.parseInt(numClassesString));
                    // Writing valid data to the output file
                    writer.write(studentNumber + " - " + secondName + "\n" + workload);
                    writer.newLine();
                    writer.newLine(); // Adding extra newline for separation
                } else {
                    // Printing error message for invalid data
                    System.out.println("Error in data: \"" + nameLine + " " + numClassesString + " " + studentNumber + "\" - " + validationResult);
                }
            }
         } catch (IOException e) {
        
         }
    }

    // Method to validate student data
    private static String validateStudentData(String firstName, String secondName, String studentNumber, String numClassesString) {
        // Validating first and second names
        if (!Pattern.matches("[a-zA-Z]+", firstName)) return "Invalid first name. Should contain letters only.";
        if (!Pattern.matches("[a-zA-Z]+", secondName)) return "Invalid second name. Should contain letters only.";

       // Check the student number format 2 digits at the front representing the year
    if (!Pattern.matches("\\d{2}[a-zA-Z]{2,3}\\d+", studentNumber)) {
        return "Invalid student number. Should follow the format: YYXX(N) where 'YY' is the year, 'XX' are letters, and '(N)' is the numeric part.";
    }

    // Extract and validate the year part for being 20 or higher
    int year = Integer.parseInt(studentNumber.substring(0, 2));
    if (year < 20) {
        return "Invalid student number. The year part must be 20 or higher.";
    }
    
    // Extracting the numeric part after the letters
    String numericPartStr = studentNumber.substring(4); // Assuming at least 2 digits for year and 2-3 for letters
    // Adjust for possible 3-letter codes
    if (Character.isLetter(numericPartStr.charAt(0))) {
        numericPartStr = numericPartStr.substring(1);
    }

    // Validating the numeric part is between 1 and 200
    try {
        int numericPart = Integer.parseInt(numericPartStr);
        if (numericPart < 1 || numericPart > 200) {
            return "Invalid student number. The number after the letter(s) must be between 1 and 200.";
        }
    } catch (NumberFormatException e) {
        return "Invalid student number. The numeric part after the letters is not a valid number.";
    }
        
    // Validating the number of classes
        try {
            int numClasses = Integer.parseInt(numClassesString);
            if (numClasses < 1 || numClasses > 8) return "Invalid number of classes. Should be between 1 and 8.";
        } catch (NumberFormatException e) {
            return "Invalid number of classes. Should be an integer.";
        }

        return "Valid";
    }
    
// Method to determine workload description based on the number of classes
    private static String getWorkloadDescription(int numClasses) {
        if (numClasses == 1) {
            return "Very Light";
        } else if (numClasses == 2) {
            return "Light";
        } else if (numClasses >= 3 && numClasses <= 5) {
            return "Part Time";
        } else {
            return "Full Time";
        }
    }
}