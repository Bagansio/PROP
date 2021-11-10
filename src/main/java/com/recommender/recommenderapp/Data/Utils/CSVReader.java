package com.recommender.recommenderapp.Data.Utils;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CSVReader {


    private static final String PATH = "src\\datasets\\";
    private static final String EXTENSION = ".csv";
    private static final String DELIMITER = ",";


    private List<String> readLine(String line){
        List<String> lineList = new ArrayList<String>();
        Scanner lineScanner = new Scanner(line);
        lineScanner.useDelimiter(DELIMITER);

        while(lineScanner.hasNext()){
            lineList.add(lineScanner.next());
        }
        lineScanner.close();
        return lineList;
    }

    public List<List<String>> readFile(String filename) {
        List<List<String>> fileData = new ArrayList<>();
        try (Scanner scanner = new Scanner(Paths.get(PATH+filename+EXTENSION).toFile())) {

            while(scanner.hasNextLine())
                fileData.add(readLine(scanner.nextLine()));

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return fileData;
    }
}
