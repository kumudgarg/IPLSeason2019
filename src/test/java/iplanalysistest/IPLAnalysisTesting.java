package iplanalysistest;

import iplanalysis.IPLAnalyser;
import iplanalysis.IPLCSVException;
import org.junit.Assert;
import org.junit.Test;

public class IPLAnalysisTesting {
    private static final String IPL_MOST_RUNS_CSV_FILE_PATH = "./src/test/resources/IPL2019FactsheetMostRuns.csv";
    private static final String IPL_Wrong_CSV_FILE_PATH = "./src/test/resources/IPL2019MostRuns.csv";
    private static final String Wrong_Delimiter_CSV_FILE = "./src/test/resources/WrongDelimiterCSV.csv";

    @Test
    public void givenIPLMOstRunsCSVFile_ShouldReturnCorrectRecords() {
        try {
            IPLAnalyser iplAnalyser = new IPLAnalyser();
            int iplRecords = iplAnalyser.loadIPLMostRunsData(IPL_MOST_RUNS_CSV_FILE_PATH);
            Assert.assertEquals(100, iplRecords);
        } catch (IPLCSVException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void givenWrongIPLMOstRunsCSVFile_ShouldReturnCustomExceptionType() {
        try {
            IPLAnalyser iplAnalyser = new IPLAnalyser();
            iplAnalyser.loadIPLMostRunsData(IPL_Wrong_CSV_FILE_PATH);
        } catch (IPLCSVException e) {
            Assert.assertEquals(IPLCSVException.ExceptionType.CSV_FILE_INTERNAL_ISSUES, e.type);
        }

    }

    @Test
    public void givenWrongDelimiterIPLMOstRunsCSVFile_ShouldReturnCustomExceptionType() {
        try {
            IPLAnalyser iplAnalyser = new IPLAnalyser();
            iplAnalyser.loadIPLMostRunsData(Wrong_Delimiter_CSV_FILE);
        } catch (IPLCSVException e) {
            Assert.assertEquals(IPLCSVException.ExceptionType.CSV_FILE_INTERNAL_ISSUES, e.type);
        }

    }
}
