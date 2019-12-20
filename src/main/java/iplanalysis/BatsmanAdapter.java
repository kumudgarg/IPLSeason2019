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
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.stream.StreamSupport;

public class BatsmanAdapter extends IPLAdapter{

    @Override
    public Map<String, IPLRecordDAO> loadIPLData(IPLAnalyser.IPLEntity iplEntity, String csvFilePath) throws IPLCSVException {
        Map<String, IPLRecordDAO>  recordDAOMap = super.loadIPLData(MostRunCSV.class, csvFilePath);
        return recordDAOMap;
    }


}
