package iplanalysis;

import censusanalyser.CensusAnalyserException;
import censusanalyser.IndiaStateCodeCSV;
import csvbulider_jar.CSVBuilderException;
import csvbulider_jar.CSVBuilderFactory;
import csvbulider_jar.ICSVBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.stream.StreamSupport;

public class BatsmanAdapter extends IPLAdapter {

    @Override
    public Map<String, IPLRecordDAO> loadIPLData(IPLEntity iplEntity, String... csvFilePath) throws IPLCSVException {
        Map<String, IPLRecordDAO> recordDAOMap = super.loadIPLData(MostRunCSV.class, csvFilePath[0]);
        if (csvFilePath.length == 2)
            this.loadMostWKTSCSV(recordDAOMap, csvFilePath[1]);
        return recordDAOMap;


    }

    private int loadMostWKTSCSV(Map<String, IPLRecordDAO> recordDAOMap, String csvFilePath) throws IPLCSVException {
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            Iterator<MostWktsCSV> wktsCSVIterator = csvBuilder.getCSVFileIterator(reader, MostWktsCSV.class);
            Iterable<MostWktsCSV> wktsCSVS = () -> wktsCSVIterator;
            StreamSupport.stream(wktsCSVS.spliterator(), false)
                    .filter(csvIPL -> recordDAOMap.get(csvIPL.player) != null)
                    .forEach(mostWktsCSV -> {
                        recordDAOMap.get(mostWktsCSV.player).bowlingAverage = mostWktsCSV.average;
                        recordDAOMap.get(mostWktsCSV.player).wkts = mostWktsCSV.wkts;
                    });
            return recordDAOMap.size();
        } catch (IOException e) {
            throw new IPLCSVException(e.getMessage(),
                    IPLCSVException.ExceptionType.IPL_FILE_PROBLEM);
        } catch (RuntimeException | CSVBuilderException e) {
            throw new IPLCSVException(e.getMessage(), IPLCSVException.ExceptionType.IPL_FILE_INTERNAL_ISSUES);
        }
    }

}
