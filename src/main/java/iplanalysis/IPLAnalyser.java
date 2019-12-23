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

    Map< String, IPLRecordDAO> runCSVMap ;
    private SortByField.Parameter parameter;
    public IPLEntity iplEntity;

    public IPLAnalyser(IPLEntity iplEntity) {
        this.iplEntity = iplEntity;
    }

    public IPLAnalyser() {
        this.runCSVMap = new HashMap<String, IPLRecordDAO>();
    }

    public <T>int loadIPLData(String... csvFilePath) throws IPLCSVException {
        runCSVMap = new IPLAdapterFactory().cricketleagueFactory(iplEntity, csvFilePath);
        return runCSVMap.size();
    }

    public String getFieldWiseSortedIPLPLayersRecords(SortByField.Parameter parameter) throws IPLCSVException {
        Comparator<IPLRecordDAO> iplComparator;
        if (runCSVMap == null || runCSVMap.size() == 0) {
            throw new IPLCSVException("NO_CENSUS_DATA", IPLCSVException.ExceptionType.NO_IPL_DATA);
        }
        iplComparator = SortByField.getParameter(parameter);
       ArrayList runCSVList =  runCSVMap.values().stream()
               .sorted(iplComparator).collect(Collectors.toCollection(ArrayList::new));

        String sortedIPLJson = new Gson().toJson(runCSVList);
        return  sortedIPLJson;
    }

}
