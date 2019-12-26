package iplanalysistest;

import iplanalysis.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class BowlerAdapterTest {
    private static final String IPL_MOST_RUNS_CSV_FILE_PATH = "./src/test/resources/IPL2019FactsheetMostRuns.csv";
    private static final String IPL_Wrong_CSV_FILE_PATH = "./src/test/resources/IPL2019MostRuns.csv";
    private static final String Wrong_Delimiter_CSV_FILE = "./src/test/resources/WrongDelimiterCSV.csv";
    private static final String Missing_Header_CSV_File = "./src/test/resources/MissingHeaderCSV.csv";
    private static final String NON_Existing_IPL_CSV_File = "./src/test/resources/NonExistingIPLCSV.csv";
    private static final String IPL_MOST_WKTS_CSV_FILE_PATH = "./src/test/resources/IPL2019FactsheetMostWkts.csv";
    private static final String RUN_FILE_PATH = "./src/test/resources/runcsv.csv";
    private static final String BOWL_FILE_PATH = "./src/test/resources/bowl.csv";

    Map<String, IPLRecordDAO> recordDAOMap;

    @Before
    public void setUp() throws Exception {
        recordDAOMap = new HashMap<>();

    }

    @Test
    public void givenIPLMOstRunsCSVFile_ShouldReturnCorrectRecords() {
        try {
            IPLAdapter iplAdapter = new BowlerAdapter();
            recordDAOMap = iplAdapter.loadIPLData(IPLEntity.BOWLING, IPL_MOST_WKTS_CSV_FILE_PATH);
            Assert.assertEquals(86, recordDAOMap.size());
        } catch (IPLCSVException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void givenWrongIPLMOstRunsCSVFile_ShouldReturnCustomExceptionType() {
        try {
            IPLAdapter iplAdapter = new BowlerAdapter();
            recordDAOMap = iplAdapter.loadIPLData(IPLEntity.BOWLING, IPL_Wrong_CSV_FILE_PATH);
        } catch (IPLCSVException e) {
            Assert.assertEquals(IPLCSVException.ExceptionType.IPL_FILE_INTERNAL_ISSUES, e.type);
        }
    }

    @Test
    public void givenWrongDelimiterIPLMOstRunsCSVFile_ShouldReturnCustomExceptionType() {
        try {
            IPLAdapter iplAdapter = new BowlerAdapter();
            recordDAOMap = iplAdapter.loadIPLData(IPLEntity.BOWLING, Wrong_Delimiter_CSV_FILE);
        } catch (IPLCSVException e) {
            Assert.assertEquals(IPLCSVException.ExceptionType.IPL_FILE_INTERNAL_ISSUES, e.type);
        }
    }

    @Test
    public void givenMissingHeaderIPLMOstRunsCSVFile_ShouldReturnCustomExceptionType() {
        try {
            IPLAdapter iplAdapter = new BowlerAdapter();
            recordDAOMap = iplAdapter.loadIPLData(IPLEntity.BOWLING, Missing_Header_CSV_File);
        } catch (IPLCSVException e) {
            Assert.assertEquals(IPLCSVException.ExceptionType.IPL_FILE_INTERNAL_ISSUES, e.type);
        }
    }

    @Test
    public void givenNonExistingIPLMOstRunsCSVFile_ShouldReturnCustomExceptionType() {
        try {
            IPLAdapter iplAdapter = new BowlerAdapter();
            recordDAOMap = iplAdapter.loadIPLData(IPLEntity.BOWLING, NON_Existing_IPL_CSV_File);
        } catch (IPLCSVException e) {
            Assert.assertEquals(IPLCSVException.ExceptionType.NO_IPL_DATA, e.type);
        }
    }

}
