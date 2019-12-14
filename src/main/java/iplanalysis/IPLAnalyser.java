package iplanalysis;

import com.google.gson.Gson;
import csvbulider_jar.CSVBuilderException;
import csvbulider_jar.CSVBuilderFactory;
import csvbulider_jar.ICSVBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.stream.StreamSupport;

public class IPLAnalyser {
    List<MostRunCSV> runCSVList = new ArrayList<MostRunCSV>();

    public int loadIPLMostRunsData(String csvFilePath) throws IPLCSVException {
        int count = 0;
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            Iterator<MostRunCSV> mostRunCSVIterator = csvBuilder.getCSVFileIterator(reader, MostRunCSV.class);
            while (mostRunCSVIterator.hasNext()) {
                MostRunCSV mostRunCSV = mostRunCSVIterator.next();
                runCSVList.add(mostRunCSV);
                count++;
            }
            return count;
        } catch (IOException e) {
            throw new IPLCSVException(e.getMessage(), IPLCSVException.ExceptionType.NO_CENSUS_DATA);
        } catch (CSVBuilderException e) {
            throw new IPLCSVException(e.getMessage(), IPLCSVException.ExceptionType.CENSUS_FILE_PROBLEM);
        } catch (RuntimeException e) {
            throw new IPLCSVException(e.getMessage(), IPLCSVException.ExceptionType.CSV_FILE_INTERNAL_ISSUES);
        }
    }

    public String getAvgWiseSortedIPLPLayersRecords() {
        runCSVList.sort(Comparator.comparing(mostRunCSV -> mostRunCSV.avg));
        String sortedStateCensusJson = new Gson().toJson(runCSVList);
        return  sortedStateCensusJson;

    }
}
