package iplanalysis;

import censusanalyser.CensusAnalyserException;
import censusanalyser.IndiaCensusCSV;
import com.google.gson.Gson;
import csvbulider_jar.CSVBuilderException;
import csvbulider_jar.CSVBuilderFactory;
import csvbulider_jar.ICSVBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class IPLAnalyser {
    Map< String, IPLRecordDAO> runCSVMap = new HashMap<>();
    private SortByField.Parameter parameter;

    public <T>int loadIPLMostRunsData(String csvFilePath) throws IPLCSVException {
        int count = 0;
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            Iterator mostRunCSVIterator = csvBuilder.getCSVFileIterator(reader, MostRunCSV.class);
            Iterable<T> mostRunCSVIterable = () -> mostRunCSVIterator;
            StreamSupport.stream(mostRunCSVIterable.spliterator(), false)
                    .map(MostRunCSV.class::cast)
                    .forEach(mostRunCSV -> runCSVMap.put(mostRunCSV.player, new IPLRecordDAO(mostRunCSV)));
            return runCSVMap.size();
        } catch (IOException e) {
            throw new IPLCSVException(e.getMessage(), IPLCSVException.ExceptionType.NO_CENSUS_DATA);
        } catch (CSVBuilderException e) {
            throw new IPLCSVException(e.getMessage(), IPLCSVException.ExceptionType.CENSUS_FILE_PROBLEM);
        } catch (RuntimeException e) {
            throw new IPLCSVException(e.getMessage(), IPLCSVException.ExceptionType.CSV_FILE_INTERNAL_ISSUES);
        }
    }

    public <T>int loadIPLMostWktsData(String csvFilePath) throws IPLCSVException {
        int count = 0;
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            Iterator mostRunCSVIterator = csvBuilder.getCSVFileIterator(reader, MostWktsCSV.class);
            Iterable<T> mostRunCSVIterable = () -> mostRunCSVIterator;
            StreamSupport.stream(mostRunCSVIterable.spliterator(), false)
                    .map(MostWktsCSV.class::cast)
                    .forEach(mostWktsCSV -> runCSVMap.put(mostWktsCSV.player, new IPLRecordDAO(mostWktsCSV)));
            return runCSVMap.size();
        } catch (IOException e) {
            throw new IPLCSVException(e.getMessage(), IPLCSVException.ExceptionType.NO_CENSUS_DATA);
        } catch (CSVBuilderException e) {
            throw new IPLCSVException(e.getMessage(), IPLCSVException.ExceptionType.CENSUS_FILE_PROBLEM);
        } catch (RuntimeException e) {
            throw new IPLCSVException(e.getMessage(), IPLCSVException.ExceptionType.CSV_FILE_INTERNAL_ISSUES);
        }
    }

    public String getFieldWiseSortedIPLPLayersRecords(SortByField.Parameter parameter) throws IPLCSVException {
        Comparator<IPLRecordDAO> censusComparator = null;
        if (runCSVMap == null || runCSVMap.size() == 0) {
            throw new IPLCSVException("NO_CENSUS_DATA", IPLCSVException.ExceptionType.NO_CENSUS_DATA);
        }
        censusComparator = SortByField.getParameter(parameter);
       ArrayList runCSVList =  runCSVMap.values().stream().
                sorted(censusComparator).collect(Collectors.toCollection(ArrayList::new));

        String sortedStateCensusJson = new Gson().toJson(runCSVList);
        return  sortedStateCensusJson;
    }

}
