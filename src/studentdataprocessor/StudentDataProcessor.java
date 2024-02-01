/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package studentdataprocessor;
import java.io.*;
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
            }
        }
          
        }
    }
