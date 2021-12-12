package com.recommender.recommenderapp.Data.Utils;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


/**
 * @author  Alex
 */
public class CSVReader {

    private static final String DELIMITER = ",";
    private static final String QUOTE = "\"";


    /**
     *
     * @param path -> to file to read
     * @return The HEADER of CSV file as Array of Strings
     * @throws FileNotFoundException
     */
    public String[] readFirstLine(String path) throws FileNotFoundException{

        File f = new File("\\" + path);
        Scanner scanner = new Scanner(f);

        return readLine(scanner.nextLine(),DELIMITER);
    }

    /**
     *
     * @param line -> line to analyze
     * @param delimiter -> how will divide de line
     * @return A list of String that represent the line delimited
     */
    public String[] readLine(String line, String delimiter){
        List<String> lineList = new ArrayList<>();

        Scanner lineScanner = new Scanner(line);
        lineScanner.useDelimiter(delimiter);
        while(lineScanner.hasNext()){
            lineList.add(lineScanner.next());
        }
        lineScanner.close();
        return lineList.toArray(new String[lineList.size()]);
    }

    /**
     *
     * @param path -> path of file to read
     * @return A Matrix of String that contains each line of CSV file separated by the attributes of it
     */
    public String[][] readFile(String path) throws Exception{
        List<String[]> fileData = new ArrayList<>();

        File f = new File(path);
        try (Scanner scanner = new Scanner(f)) {

            while(scanner.hasNextLine()) {
                String line = scanner.nextLine();
                fileData.add(line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1));
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return fileData.toArray(new String[fileData.size()][]);
    }



}
