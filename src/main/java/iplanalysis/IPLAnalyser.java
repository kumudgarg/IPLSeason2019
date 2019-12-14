package iplanalysis;

import csvbulider_jar.CSVBuilderException;
import csvbulider_jar.CSVBuilderFactory;
import csvbulider_jar.ICSVBuilder;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.stream.StreamSupport;

public class IPLAnalyser {

    public int loadIPLMostRunsData(String csvFilePath) throws IPLCSVException {
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            Iterator<MostRunCSV> mostRunCSVIterator = csvBuilder.getCSVFileIterator(reader, MostRunCSV.class);
            Iterable<MostRunCSV> mostRunCSVS = () -> mostRunCSVIterator;
            long ipLRecords = StreamSupport.stream(mostRunCSVS.spliterator(), false).count();
            return (int) ipLRecords;
        } catch (IOException e) {
            throw new IPLCSVException(e.getMessage(), IPLCSVException.ExceptionType.NO_CENSUS_DATA);
        } catch (CSVBuilderException e) {
            throw new IPLCSVException(e.getMessage(), IPLCSVException.ExceptionType.CENSUS_FILE_PROBLEM);
        } catch (RuntimeException e) {
            throw new IPLCSVException(e.getMessage(), IPLCSVException.ExceptionType.CSV_FILE_INTERNAL_ISSUES);
        }
    }
}
