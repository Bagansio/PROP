package test.Data.Utils;

import com.recommender.recommenderapp.Data.Utils.CSVReader;
import org.junit.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CSVReaderTest {

    public static String filename = "ratings.db";
    public static String line = "LoreIpsum,IpsumLore";

    @Test
    public void readFileTest(){
        CSVReader reader = new CSVReader();
        List<String> expectedLine = new ArrayList<>(Arrays.asList("userId","itemId","rating"));
        List<String> line = reader.readFile(filename, "movies").get(0);
        Assert.assertEquals("Data are different", line, expectedLine);
    }

    @Test
    public void readLineTest(){
        CSVReader reader = new CSVReader();
        List<String> expectedLine =  new ArrayList<>(Arrays.asList("LoreIpsum","IpsumLore"));

        Assert.assertEquals("Lines are different", reader.readLine(line,","),expectedLine);
    }

}
