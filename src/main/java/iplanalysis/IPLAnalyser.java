package iplanalysis;

import csvbulider_jar.CSVBuilderException;
import csvbulider_jar.CSVBuilderFactory;
import csvbulider_jar.ICSVBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;

public class IPLAnalyser {
    public int loadIPLMostRunsData(String csvFilePath) {
        int count = -1;
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            Iterator<MostRunCSV> mostRunCSVIterator = csvBuilder.getCSVFileIterator(reader, MostRunCSV.class);
            while (mostRunCSVIterator.hasNext()) {
                mostRunCSVIterator.next();
                count++;
            }
            return count;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CSVBuilderException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
