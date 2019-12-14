package iplanalysistest;

import com.google.gson.Gson;
import iplanalysis.IPLAnalyser;
import iplanalysis.IPLCSVException;
import iplanalysis.MostRunCSV;
import org.junit.Assert;
import org.junit.Test;

public class IPLAnalysisTesting {
    private static final String IPL_MOST_RUNS_CSV_FILE_PATH = "./src/test/resources/IPL2019FactsheetMostRuns.csv";
    private static final String IPL_Wrong_CSV_FILE_PATH = "./src/test/resources/IPL2019MostRuns.csv";
    private static final String Wrong_Delimiter_CSV_FILE = "./src/test/resources/WrongDelimiterCSV.csv";
    private static final String Missing_Header_CSV_File = "./src/test/resources/MissingHeaderCSV.csv";
    private static final String NON_Existing_IPL_CSV_File= "./src/test/resources/NonExistingIPLCSV.csv";

    @Test
    public void givenIPLMOstRunsCSVFile_ShouldReturnCorrectRecords() {
        try {
            IPLAnalyser iplAnalyser = new IPLAnalyser();
            int iplRecords = iplAnalyser.loadIPLMostRunsData(IPL_MOST_RUNS_CSV_FILE_PATH);
            Assert.assertEquals(101, iplRecords);
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

    @Test
    public void givenMissingHeaderIPLMOstRunsCSVFile_ShouldReturnCustomExceptionType() {
        try {
            IPLAnalyser iplAnalyser = new IPLAnalyser();
            iplAnalyser.loadIPLMostRunsData(Missing_Header_CSV_File);
        } catch (IPLCSVException e) {
            Assert.assertEquals(IPLCSVException.ExceptionType.CSV_FILE_INTERNAL_ISSUES, e.type);
        }

    }

    @Test
    public void givenNonExistingIPLMOstRunsCSVFile_ShouldReturnCustomExceptionType() {
        try {
            IPLAnalyser iplAnalyser = new IPLAnalyser();
            iplAnalyser.loadIPLMostRunsData(NON_Existing_IPL_CSV_File);
        } catch (IPLCSVException e) {
            Assert.assertEquals(IPLCSVException.ExceptionType.NO_CENSUS_DATA, e.type);
        }

    }

    @Test
    public void givenIPLMOstRunsCSVFile_WhenSortedOnAvg_ShouldReturnCorrectDesiredSortedData() {
        try {
            IPLAnalyser iplAnalyser = new IPLAnalyser();
            iplAnalyser.loadIPLMostRunsData(IPL_MOST_RUNS_CSV_FILE_PATH);
            String iplpLayersRecords = iplAnalyser.getAvgWiseSortedIPLPLayersRecords();
            MostRunCSV[] mostRunCSVS = new Gson().fromJson(iplpLayersRecords, MostRunCSV[].class);
            Assert.assertEquals("MS Dhoni", mostRunCSVS[mostRunCSVS.length - 1].player);
        } catch (IPLCSVException e) {
            e.printStackTrace();
        }

    }
}
