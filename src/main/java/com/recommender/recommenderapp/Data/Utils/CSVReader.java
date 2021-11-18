package com.recommender.recommenderapp.Data.Utils;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


/**
 * @author  Alex
 */
public class CSVReader {

    private static final String PATH = "src\\main\\resources\\datasets\\";
    private static final String EXTENSION = ".csv";
    private static final String DELIMITER = ",";
    private static final String QUOTE = "\"";


    /**
     *
     * @param line -> line to analyze
     * @param delimiter -> how will divide de line
     * @return A list of String that represent the line delimited
     */
    public List<String> readLine(String line, String delimiter){
        List<String> lineList = new ArrayList<>();
        Scanner lineScanner = new Scanner(line);
        lineScanner.useDelimiter(delimiter);
        while(lineScanner.hasNext()){
            lineList.add(lineScanner.next());
        }
        lineScanner.close();
        return lineList;
    }


    /**
     *
     * @param filename -> file to read
     * @return A list that contains each line of CSV separated by the DELIMITER
     */
    public List<List<String>> readFile(String filename, String dataset) {
        List<List<String>> fileData = new ArrayList<>();
        String currentPath = PATH + "\\" + dataset + "\\" + filename + ".csv";
        try (Scanner scanner = new Scanner(Paths.get(currentPath).toFile())) {

            while(scanner.hasNextLine()) {
                String line = scanner.nextLine();
                line = deleteValues(line);
                fileData.add(readLine(line,DELIMITER));
            }

        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return fileData;
    }


    /**
     *
     * @param line -> line to modify
     * @return the same line without text in QUOTES
     */
    private String deleteValues(String line){
        int index = line.indexOf(QUOTE);
        while(index != -1 || index == line.length() - 1){
            int endIndex = line.indexOf(QUOTE,index+2);
            if(endIndex != -1) line = line.replace(line.substring(index,endIndex+1), "");
            index = line.indexOf(QUOTE);
        }
        return line;
    }
}
