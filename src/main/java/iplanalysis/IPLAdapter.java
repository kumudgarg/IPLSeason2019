package iplanalysis;

import censusanalyser.*;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.stream.StreamSupport;

public abstract class IPLAdapter {



    public abstract Map<String, IPLRecordDAO> loadIPLData(IPLAnalyser.IPLEntity iplEntity, String csvFilePath) throws IPLCSVException;

    public <T> Map<String, IPLRecordDAO> loadIPLData(Class<T> iplCSVClass, String csvFilePath) throws IPLCSVException {
        Map<String, IPLRecordDAO> iplRecordDAOMap = new HashMap<>();
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            Iterator mostRunCSVIterator = csvBuilder.getCSVFileIterator(reader, iplCSVClass);
            Iterable<T> mostRunCSVIterable = () -> mostRunCSVIterator;
            if(iplCSVClass.getName().equals("iplanalysis.MostRunCSV")) {
                StreamSupport.stream(mostRunCSVIterable.spliterator(), false)
                        .map(MostRunCSV.class::cast)
                        .forEach(mostRunCSV -> iplRecordDAOMap.put(mostRunCSV.player, new IPLRecordDAO(mostRunCSV)));
            } else if(iplCSVClass.getName().equals("iplanalysis.MostWktsCSV")) {
                    StreamSupport.stream(mostRunCSVIterable.spliterator(), false)
                            .map(MostWktsCSV.class::cast)
                            .forEach(mostWktsCSV -> iplRecordDAOMap.put(mostWktsCSV.player, new IPLRecordDAO(mostWktsCSV)));
                }
            return iplRecordDAOMap;

        } catch (IOException e) {
            throw new IPLCSVException(e.getMessage(), IPLCSVException.ExceptionType.NO_CENSUS_DATA);
        }  catch (RuntimeException e) {
            throw new IPLCSVException(e.getMessage(), IPLCSVException.ExceptionType.CSV_FILE_INTERNAL_ISSUES);
        } catch (CSVBuilderException e) {
            throw new IPLCSVException(e.getMessage(), IPLCSVException.ExceptionType.CENSUS_FILE_PROBLEM);
        }
    }




}
