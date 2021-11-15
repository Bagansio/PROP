package com.recommender.recommenderapp.Data.Utils;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CSVReader {


    private static final String PATH = "src\\main\\resources\\datasets\\";
    private static final String EXTENSION = ".csv";
    private static final String DELIMITER = ",";
    private static final String QUOTE = "\"";

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

    public List<List<String>> readFile(String filename) {
        List<List<String>> fileData = new ArrayList<>();
        try (Scanner scanner = new Scanner(Paths.get(PATH+filename+EXTENSION).toFile())) {

            while(scanner.hasNextLine()) {
                String line = scanner.nextLine();
                int index = line.indexOf(QUOTE);
                while(index != -1 || index == line.length() - 1){
                    int endIndex = line.indexOf(QUOTE,index+2);
                    if(endIndex != -1) line = line.replace(line.substring(index,endIndex+1), "");
                    index = line.indexOf(QUOTE);
                }
                fileData.add(readLine(line,DELIMITER));
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return fileData;
    }
}
