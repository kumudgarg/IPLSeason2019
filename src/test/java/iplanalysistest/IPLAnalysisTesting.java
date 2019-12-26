package iplanalysistest;

import com.google.gson.Gson;
import iplanalysis.*;
import org.junit.Assert;
import org.junit.Test;

public class IPLAnalysisTesting {
    private static final String IPL_MOST_RUNS_CSV_FILE_PATH = "./src/test/resources/IPL2019FactsheetMostRuns.csv";
    private static final String IPL_Wrong_CSV_FILE_PATH = "./src/test/resources/IPL2019MostRuns.csv";
    private static final String Wrong_Delimiter_CSV_FILE = "./src/test/resources/WrongDelimiterCSV.csv";
    private static final String Missing_Header_CSV_File = "./src/test/resources/MissingHeaderCSV.csv";
    private static final String NON_Existing_IPL_CSV_File = "./src/test/resources/NonExistingIPLCSV.csv";
    private static final String IPL_MOST_WKTS_CSV_FILE_PATH = "./src/test/resources/IPL2019FactsheetMostWkts.csv";
    private static final String RUN_FILE_PATH = "./src/test/resources/runcsv.csv";
    private static final String BOWL_FILE_PATH = "./src/test/resources/bowl.csv";

    @Test
    public void givenIPLMOstRunsCSVFile_ShouldReturnCorrectRecords() {
        try {
            IPLAnalyser iplAnalyser = new IPLAnalyser(IPLEntity.BATING);
            iplAnalyser.setIplAdapter(new BatsmanAdapter());
            int iplRecords = iplAnalyser.loadIPLData(IPL_MOST_RUNS_CSV_FILE_PATH);
            Assert.assertEquals(100, iplRecords);
        } catch (IPLCSVException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenWrongIPLMOstRunsCSVFile_ShouldReturnCustomExceptionType() {
        try {
            IPLAnalyser iplAnalyser = new IPLAnalyser();
            iplAnalyser.setIplAdapter(new BatsmanAdapter());
            iplAnalyser.loadIPLData(IPL_Wrong_CSV_FILE_PATH);
        } catch (IPLCSVException e) {
            Assert.assertEquals(IPLCSVException.ExceptionType.IPL_FILE_INTERNAL_ISSUES, e.type);
        }
    }

    @Test
    public void givenWrongDelimiterIPLMOstRunsCSVFile_ShouldReturnCustomExceptionType() {
        try {
            IPLAnalyser iplAnalyser = new IPLAnalyser();
            iplAnalyser.setIplAdapter(new BatsmanAdapter());
            iplAnalyser.loadIPLData(Wrong_Delimiter_CSV_FILE);
        } catch (IPLCSVException e) {
            Assert.assertEquals(IPLCSVException.ExceptionType.IPL_FILE_INTERNAL_ISSUES, e.type);
        }
    }

    @Test
    public void givenMissingHeaderIPLMOstRunsCSVFile_ShouldReturnCustomExceptionType() {
        try {
            IPLAnalyser iplAnalyser = new IPLAnalyser();
            iplAnalyser.setIplAdapter(new BatsmanAdapter());
            iplAnalyser.loadIPLData(Missing_Header_CSV_File);
        } catch (IPLCSVException e) {
            Assert.assertEquals(IPLCSVException.ExceptionType.IPL_FILE_INTERNAL_ISSUES, e.type);
        }
    }

    @Test
    public void givenNonExistingIPLMOstRunsCSVFile_ShouldReturnCustomExceptionType() {
        try {
            IPLAnalyser iplAnalyser = new IPLAnalyser();
            iplAnalyser.setIplAdapter(new BatsmanAdapter());
            iplAnalyser.loadIPLData(NON_Existing_IPL_CSV_File);
        } catch (IPLCSVException e) {
            Assert.assertEquals(IPLCSVException.ExceptionType.NO_IPL_DATA, e.type);
        }
    }

    @Test
    public void givenIPLMOstRunsCSVFile_WhenSortedOnAvg_ShouldReturnCorrectDesiredSortedData() {
        try {
            IPLAnalyser iplAnalyser = new IPLAnalyser();
            iplAnalyser.setIplAdapter(new BatsmanAdapter());
            iplAnalyser.loadIPLData(IPL_MOST_RUNS_CSV_FILE_PATH);
            String iplpLayersRecords = iplAnalyser.getFieldWiseSortedIPLPLayersRecords(SortByField.Parameter.BATTING_AVG);
            MostRunCSV[] mostRunCSVS = new Gson().fromJson(iplpLayersRecords, MostRunCSV[].class);
            Assert.assertEquals("MS Dhoni", mostRunCSVS[mostRunCSVS.length - 1].player);
        } catch (IPLCSVException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIPLMOstRunsCSVFile_WhenSortedOnStrikeRate_ShouldReturnCorrectDesiredSortedData() {
        try {
            IPLAnalyser iplAnalyser = new IPLAnalyser();
            iplAnalyser.setIplAdapter(new BatsmanAdapter());
            iplAnalyser.loadIPLData(IPL_MOST_RUNS_CSV_FILE_PATH);
            String iplpLayersRecords = iplAnalyser.getFieldWiseSortedIPLPLayersRecords(SortByField.Parameter.STRIKERATE);
            MostRunCSV[] mostRunCSVS = new Gson().fromJson(iplpLayersRecords, MostRunCSV[].class);
            Assert.assertEquals("Ishant Sharma", mostRunCSVS[mostRunCSVS.length - 1].player);
        } catch (IPLCSVException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIPLMOstRunsCSVFile_WhenSortedOn4SAnd6s_ShouldReturnCorrectDesiredSortedData() {
        try {
            IPLAnalyser iplAnalyser = new IPLAnalyser();
            iplAnalyser.setIplAdapter(new BatsmanAdapter());
            iplAnalyser.loadIPLData(IPL_MOST_RUNS_CSV_FILE_PATH);
            String iplpLayersRecords = iplAnalyser.getFieldWiseSortedIPLPLayersRecords(SortByField.Parameter.SIX_AND_FOURS);
            MostRunCSV[] mostRunCSVS = new Gson().fromJson(iplpLayersRecords, MostRunCSV[].class);
            Assert.assertEquals("Andre Russell", mostRunCSVS[mostRunCSVS.length - 1].player);
        } catch (IPLCSVException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIPLMOstRunsCSVFile_WhenSortedOn4SAnd6sWithStrikeRate_ShouldReturnCorrectDesiredSortedData() {
        try {
            IPLAnalyser iplAnalyser = new IPLAnalyser(IPLEntity.BATING);
            iplAnalyser.setIplAdapter(new BatsmanAdapter());
            iplAnalyser.loadIPLData(IPL_MOST_RUNS_CSV_FILE_PATH);
            String iplpLayersRecords = iplAnalyser.getFieldWiseSortedIPLPLayersRecords(SortByField.Parameter.SIX_AND_FOURS_WITH_STRIKERATE);
            MostRunCSV[] mostRunCSVS = new Gson().fromJson(iplpLayersRecords, MostRunCSV[].class);
            Assert.assertEquals("Andre Russell", mostRunCSVS[mostRunCSVS.length - 1].player);
        } catch (IPLCSVException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIPLMOstRunsCSVFile_WhenSortedOnAverageWithStrikeRate_ShouldReturnCorrectDesiredSortedData() {
        try {
            IPLAnalyser iplAnalyser = new IPLAnalyser();
            iplAnalyser.setIplAdapter(new BatsmanAdapter());
            iplAnalyser.loadIPLData(IPL_MOST_RUNS_CSV_FILE_PATH);
            String iplpLayersRecords = iplAnalyser.getFieldWiseSortedIPLPLayersRecords(SortByField.Parameter.BAT_STATS_AVG_WITH_STRIKERATE);
            MostRunCSV[] mostRunCSVS = new Gson().fromJson(iplpLayersRecords, MostRunCSV[].class);
            Assert.assertEquals("MS Dhoni", mostRunCSVS[mostRunCSVS.length - 1].player);
        } catch (IPLCSVException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void givenIPLMOstRunsCSVFile_WhenSortedOnRunWithAvg_ShouldReturnCorrectDesiredSortedData() {
        try {
            IPLAnalyser iplAnalyser = new IPLAnalyser();
            iplAnalyser.setIplAdapter(new BatsmanAdapter());
            iplAnalyser.loadIPLData(IPL_MOST_RUNS_CSV_FILE_PATH);
            String iplpLayersRecords = iplAnalyser.getFieldWiseSortedIPLPLayersRecords(SortByField.Parameter.RUN_WITH_AVG);
            MostRunCSV[] mostRunCSVS = new Gson().fromJson(iplpLayersRecords, MostRunCSV[].class);
            Assert.assertEquals("David Warner ", mostRunCSVS[mostRunCSVS.length - 1].player);
        } catch (IPLCSVException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void givenIPLMOstWktsCSVFile_ShouldReturnCorrectRecords() {
        try {
            IPLAnalyser iplAnalyser = new IPLAnalyser();
            iplAnalyser.setIplAdapter(new BowlerAdapter());
            int iplRecords = iplAnalyser.loadIPLData(IPL_MOST_WKTS_CSV_FILE_PATH);
            Assert.assertEquals(86, iplRecords);
        } catch (IPLCSVException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIPLMOstWktsCSVFile_WhenSortedOnAvg_ShouldReturnCorrectDesiredSortedData() {
        try {
            IPLAnalyser iplAnalyser = new IPLAnalyser();
            iplAnalyser.setIplAdapter(new BowlerAdapter());
            iplAnalyser.loadIPLData(IPL_MOST_WKTS_CSV_FILE_PATH);
            String iplpLayersRecords = iplAnalyser.getFieldWiseSortedIPLPLayersRecords(SortByField.Parameter.BOWLING_AVG);
            MostWktsCSV[] mostRunCSVS = new Gson().fromJson(iplpLayersRecords, MostWktsCSV[].class);
            Assert.assertEquals("Anukul Roy", mostRunCSVS[0].player);
        } catch (IPLCSVException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIPLMOstWktsCSVFile_WhenSortedOnStrikeRate_ShouldReturnCorrectDesiredSortedData() {
        try {
            IPLAnalyser iplAnalyser = new IPLAnalyser();
            iplAnalyser.setIplAdapter(new BowlerAdapter());
            iplAnalyser.loadIPLData(IPL_MOST_WKTS_CSV_FILE_PATH);
            String iplpLayersRecords = iplAnalyser.getFieldWiseSortedIPLPLayersRecords(SortByField.Parameter.STRIKERATE);
            MostWktsCSV[] mostRunCSVS = new Gson().fromJson(iplpLayersRecords, MostWktsCSV[].class);
            Assert.assertEquals("Alzarri Joseph", mostRunCSVS[0].player);
        } catch (IPLCSVException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIPLMOstWktsCSVFile_WhenSortedOnEconomy_ShouldReturnCorrectDesiredSortedData() {
        try {
            IPLAnalyser iplAnalyser = new IPLAnalyser();
            iplAnalyser.setIplAdapter(new BowlerAdapter());
            iplAnalyser.loadIPLData(IPL_MOST_WKTS_CSV_FILE_PATH);
            String iplpLayersRecords = iplAnalyser.getFieldWiseSortedIPLPLayersRecords(SortByField.Parameter.ECONOMY);
            MostWktsCSV[] mostRunCSVS = new Gson().fromJson(iplpLayersRecords, MostWktsCSV[].class);
            Assert.assertEquals("Anukul Roy", mostRunCSVS[0].player);
        } catch (IPLCSVException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIPLMOstWktsCSVFile_WhenSortedOn5WAnd4WWithStrikeRate_ShouldReturnCorrectDesiredSortedData() {
        try {
            IPLAnalyser iplAnalyser = new IPLAnalyser();
            iplAnalyser.setIplAdapter(new BowlerAdapter());
            iplAnalyser.loadIPLData(IPL_MOST_WKTS_CSV_FILE_PATH);
            String iplpLayersRecords = iplAnalyser.getFieldWiseSortedIPLPLayersRecords(SortByField.Parameter.FIVEWKT_FOURWKT_STRIKERATE);
            MostWktsCSV[] mostRunCSVS = new Gson().fromJson(iplpLayersRecords, MostWktsCSV[].class);
            Assert.assertEquals("Kagiso Rabada", mostRunCSVS[0].player);
        } catch (IPLCSVException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIPLMOstWktsCSVFile_WhenSortedOnBowlingAvgWithStrikeRate_ShouldReturnCorrectDesiredSortedData() {
        try {
            IPLAnalyser iplAnalyser = new IPLAnalyser();
            iplAnalyser.setIplAdapter(new BowlerAdapter());
            iplAnalyser.loadIPLData(IPL_MOST_WKTS_CSV_FILE_PATH);
            String iplpLayersRecords = iplAnalyser.getFieldWiseSortedIPLPLayersRecords(SortByField.Parameter.BOWL_STATS_AVG_WITH_STRIKERATE);
            MostWktsCSV[] mostRunCSVS = new Gson().fromJson(iplpLayersRecords, MostWktsCSV[].class);
            Assert.assertEquals("Anukul Roy", mostRunCSVS[0].player);
        } catch (IPLCSVException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIPLMOstWktsCSVFile_WhenSortedOnWktsWithAvg_ShouldReturnCorrectDesiredSortedData() {
        try {
            IPLAnalyser iplAnalyser = new IPLAnalyser();
            iplAnalyser.setIplAdapter(new BowlerAdapter());
            iplAnalyser.loadIPLData(IPL_MOST_WKTS_CSV_FILE_PATH);
            String iplpLayersRecords = iplAnalyser.getFieldWiseSortedIPLPLayersRecords(SortByField.Parameter.BOWL_STATS_WKT_WITH_AVG);
            MostWktsCSV[] mostRunCSVS = new Gson().fromJson(iplpLayersRecords, MostWktsCSV[].class);
            Assert.assertEquals("Imran Tahir", mostRunCSVS[mostRunCSVS.length - 1].player);
        } catch (IPLCSVException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIPLMOstWktsCSVFile_WhenSortedOnBattingAndBowlingAvg_ShouldReturnCorrectDesiredSortedData() {
        try {
            IPLAnalyser iplAnalyser = new IPLAnalyser();
            iplAnalyser.setIplAdapter(new BatsmanAdapter());
            iplAnalyser.loadIPLData(IPL_MOST_RUNS_CSV_FILE_PATH, IPL_MOST_WKTS_CSV_FILE_PATH);
            String iplpLayersRecords = iplAnalyser.getFieldWiseSortedIPLPLayersRecords(SortByField.Parameter.BATTING_BOWLING_AVERAGE);
            MostRunCSV[] mostRunCSVS = new Gson().fromJson(iplpLayersRecords, MostRunCSV[].class);
            Assert.assertEquals("Andre Russell", mostRunCSVS[mostRunCSVS.length - 1].player);
        } catch (IPLCSVException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIPLMOstWktsCSVFile_WhenSortedOnALLRounders_ShouldReturnCorrectDesiredSortedData() {
        try {
            IPLAnalyser iplAnalyser = new IPLAnalyser();
            iplAnalyser.setIplAdapter(new BatsmanAdapter());
            iplAnalyser.loadIPLData(IPL_MOST_RUNS_CSV_FILE_PATH, IPL_MOST_WKTS_CSV_FILE_PATH);
            String iplpLayersRecords = iplAnalyser.getFieldWiseSortedIPLPLayersRecords(SortByField.Parameter.IPL_BEST_ALLROUNDER);
            MostRunCSV[] mostRunCSVS = new Gson().fromJson(iplpLayersRecords, MostRunCSV[].class);
            Assert.assertEquals("Hardik Pandya", mostRunCSVS[mostRunCSVS.length - 1].player);
        } catch (IPLCSVException e) {
            e.printStackTrace();
        }
    }
}
