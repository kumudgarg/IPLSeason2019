package iplanalysistest;

import com.google.gson.Gson;
import iplanalysis.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class IPLTestWithMockito {
    private static final String IPL_MOST_RUNS_CSV_FILE_PATH = "./src/test/resources/IPL2019FactsheetMostRuns.csv";
    private static final String IPL_Wrong_CSV_FILE_PATH = "./src/test/resources/IPL2019MostRuns.csv";
    private static final String Wrong_Delimiter_CSV_FILE = "./src/test/resources/WrongDelimiterCSV.csv";
    private static final String Missing_Header_CSV_File = "./src/test/resources/MissingHeaderCSV.csv";
    private static final String NON_Existing_IPL_CSV_File = "./src/test/resources/NonExistingIPLCSV.csv";
    private static final String IPL_MOST_WKTS_CSV_FILE_PATH = "./src/test/resources/IPL2019FactsheetMostWkts.csv";
    private static final String RUN_FILE_PATH = "./src/test/resources/runcsv.csv";
    private static final String BOWL_FILE_PATH = "./src/test/resources/bowl.csv";


    @Mock
    private IPLAdapterFactory iplAdapterFactory;

    @InjectMocks
    private IPLAnalyser iplAnalyser;

    Map<String, IPLRecordDAO> recordDAOMap;
    Map<SortByField.Parameter, Comparator> sortParameterComparator;

    @Before
    public void setUp() throws Exception {
        this.recordDAOMap = new HashMap<>();
        sortParameterComparator = new HashMap<>();
        this.recordDAOMap.put("shivam", new IPLRecordDAO("shivam", 1500, 23.36, 36.15, 27.12, 140, 100, 90, 89, 54, 26.12, 200));
        this.recordDAOMap.put("Rohit", new IPLRecordDAO("Rohit", 1500, 66.14, 36.15, 59.33, 140, 100, 110, 89, 54, 12.25, 202));
        this.recordDAOMap.put("kapil", new IPLRecordDAO("kapil", 900, 56.12, 29.98, 59.33, 95, 66, 90, 45, 15, 18.12, 190));
        Comparator<IPLRecordDAO> avgcomparator = Comparator.comparing(mostruncsv -> mostruncsv.battingAverage);
        sortParameterComparator.put(SortByField.Parameter.BATTING_AVG, avgcomparator);

        MockitoAnnotations.initMocks(this);

    }

    @Test
    public void givenIPLMOstRunsCSVFile_ShouldReturnCorrectRecords() {

        try {
            IPLAdapter iplAdapter = mock(BatsmanAdapter.class);
            when(iplAdapter.loadIPLData(IPLEntity.BATING, IPL_MOST_RUNS_CSV_FILE_PATH)).thenReturn(this.recordDAOMap);
            IPLAnalyser iplAnalyser = new IPLAnalyser(IPLEntity.BATING);
            iplAnalyser.setIplAdapter(iplAdapter);
            int iplRecords = iplAnalyser.loadIPLData(IPL_MOST_RUNS_CSV_FILE_PATH);
            Assert.assertEquals(3, iplRecords);
        } catch (IPLCSVException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenMissingHeaderIPLMOstRunsCSVFile_ShouldReturnCustomExceptionType() {
        try {
            IPLAdapter iplAdapter = mock(BatsmanAdapter.class);
            when(iplAdapter.loadIPLData(IPLEntity.BATING, IPL_MOST_RUNS_CSV_FILE_PATH)).thenReturn(this.recordDAOMap);
            IPLAnalyser iplAnalyser = new IPLAnalyser();
            iplAnalyser.setIplAdapter(iplAdapter);
            iplAnalyser.loadIPLData(Missing_Header_CSV_File);
        } catch (IPLCSVException e) {
            Assert.assertEquals(IPLCSVException.ExceptionType.IPL_FILE_INTERNAL_ISSUES, e.type);
        }
    }

    @Test
    public void givenNonExistingIPLMOstRunsCSVFile_ShouldReturnCustomExceptionType() {
        try {
            IPLAdapter iplAdapter = mock(BatsmanAdapter.class);
            when(iplAdapter.loadIPLData(IPLEntity.BATING, IPL_MOST_RUNS_CSV_FILE_PATH)).thenReturn(this.recordDAOMap);
            IPLAnalyser iplAnalyser = new IPLAnalyser(IPLEntity.BATING);
            iplAnalyser.setIplAdapter(iplAdapter);
            iplAnalyser.loadIPLData(NON_Existing_IPL_CSV_File);
        } catch (IPLCSVException e) {
            Assert.assertEquals(IPLCSVException.ExceptionType.NO_IPL_DATA, e.type);
        }
    }


    @Test
    public void givenIPLMOstRunsCSVFile_WhenSortedOnAvg_ShouldReturnCorrectDesiredSortedData() {
        try {
            IPLAdapter iplAdapter = mock(BatsmanAdapter.class);
            when(iplAdapter.loadIPLData(IPLEntity.BATING, IPL_MOST_RUNS_CSV_FILE_PATH)).thenReturn(this.recordDAOMap);
            IPLAnalyser iplAnalyser = new IPLAnalyser(IPLEntity.BATING);
            iplAnalyser.setIplAdapter(iplAdapter);
            int iplRecords = iplAnalyser.loadIPLData(IPL_MOST_RUNS_CSV_FILE_PATH);
            String iplpLayersRecords = iplAnalyser.getFieldWiseSortedIPLPLayersRecords(SortByField.Parameter.BATTING_AVG);
            System.out.println(iplpLayersRecords);
            MostRunCSV[] mostRunCSVS = new Gson().fromJson(iplpLayersRecords, MostRunCSV[].class);
            Assert.assertEquals("kapil", mostRunCSVS[mostRunCSVS.length - 1].player);
        } catch (IPLCSVException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIPLMOstRunsCSVFile_WhenSortedOnStrikeRate_ShouldReturnCorrectDesiredSortedData() {
        try {
            IPLAdapter iplAdapter = mock(BatsmanAdapter.class);
            when(iplAdapter.loadIPLData(IPLEntity.BATING, IPL_MOST_RUNS_CSV_FILE_PATH)).thenReturn(this.recordDAOMap);
            IPLAnalyser iplAnalyser = new IPLAnalyser(IPLEntity.BATING);
            iplAnalyser.setIplAdapter(iplAdapter);
            int iplRecords = iplAnalyser.loadIPLData(IPL_MOST_RUNS_CSV_FILE_PATH);
            String iplpLayersRecords = iplAnalyser.getFieldWiseSortedIPLPLayersRecords(SortByField.Parameter.STRIKERATE);
            System.out.println(iplpLayersRecords);
            MostRunCSV[] mostRunCSVS = new Gson().fromJson(iplpLayersRecords, MostRunCSV[].class);
            Assert.assertEquals("Rohit", mostRunCSVS[mostRunCSVS.length - 1].player);
        } catch (IPLCSVException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIPLMOstRunsCSVFile_WhenSortedOn4SAnd6s_ShouldReturnCorrectDesiredSortedData() {
        try {
            IPLAdapter iplAdapter = mock(BatsmanAdapter.class);
            when(iplAdapter.loadIPLData(IPLEntity.BATING, IPL_MOST_RUNS_CSV_FILE_PATH)).thenReturn(this.recordDAOMap);
            IPLAnalyser iplAnalyser = new IPLAnalyser(IPLEntity.BATING);
            iplAnalyser.setIplAdapter(iplAdapter);
            int iplRecords = iplAnalyser.loadIPLData(IPL_MOST_RUNS_CSV_FILE_PATH);
            String iplpLayersRecords = iplAnalyser.getFieldWiseSortedIPLPLayersRecords(SortByField.Parameter.SIX_AND_FOURS);
            System.out.println(iplpLayersRecords);
            MostRunCSV[] mostRunCSVS = new Gson().fromJson(iplpLayersRecords, MostRunCSV[].class);
            Assert.assertEquals("shivam", mostRunCSVS[mostRunCSVS.length - 1].player);
        } catch (IPLCSVException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIPLMOstRunsCSVFile_WhenSortedOn4SAnd6sWithStrikeRate_ShouldReturnCorrectDesiredSortedData() {
        try {
            IPLAdapter iplAdapter = mock(BatsmanAdapter.class);
            when(iplAdapter.loadIPLData(IPLEntity.BATING, IPL_MOST_RUNS_CSV_FILE_PATH)).thenReturn(this.recordDAOMap);
            IPLAnalyser iplAnalyser = new IPLAnalyser(IPLEntity.BATING);
            iplAnalyser.setIplAdapter(iplAdapter);
            int iplRecords = iplAnalyser.loadIPLData(IPL_MOST_RUNS_CSV_FILE_PATH);
            String iplpLayersRecords = iplAnalyser.getFieldWiseSortedIPLPLayersRecords(SortByField.Parameter.SIX_AND_FOURS_WITH_STRIKERATE);
            System.out.println(iplpLayersRecords);
            MostRunCSV[] mostRunCSVS = new Gson().fromJson(iplpLayersRecords, MostRunCSV[].class);
            Assert.assertEquals("Rohit", mostRunCSVS[mostRunCSVS.length - 1].player);
        } catch (IPLCSVException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIPLMOstRunsCSVFile_WhenSortedOnAverageWithStrikeRate_ShouldReturnCorrectDesiredSortedData() {
        try {
            IPLAdapter iplAdapter = mock(BatsmanAdapter.class);
            when(iplAdapter.loadIPLData(IPLEntity.BATING, IPL_MOST_RUNS_CSV_FILE_PATH)).thenReturn(this.recordDAOMap);
            IPLAnalyser iplAnalyser = new IPLAnalyser(IPLEntity.BATING);
            iplAnalyser.setIplAdapter(iplAdapter);
            int iplRecords = iplAnalyser.loadIPLData(IPL_MOST_RUNS_CSV_FILE_PATH);
            String iplpLayersRecords = iplAnalyser.getFieldWiseSortedIPLPLayersRecords(SortByField.Parameter.BAT_STATS_AVG_WITH_STRIKERATE);
            System.out.println(iplpLayersRecords);
            MostRunCSV[] mostRunCSVS = new Gson().fromJson(iplpLayersRecords, MostRunCSV[].class);
            Assert.assertEquals("Rohit", mostRunCSVS[mostRunCSVS.length - 1].player);
        } catch (IPLCSVException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void givenIPLMOstRunsCSVFile_WhenSortedOnRunWithAvg_ShouldReturnCorrectDesiredSortedData() {
        try {
            IPLAdapter iplAdapter = mock(BatsmanAdapter.class);
            when(iplAdapter.loadIPLData(IPLEntity.BATING, IPL_MOST_RUNS_CSV_FILE_PATH)).thenReturn(this.recordDAOMap);
            IPLAnalyser iplAnalyser = new IPLAnalyser(IPLEntity.BATING);
            iplAnalyser.setIplAdapter(iplAdapter);
            int iplRecords = iplAnalyser.loadIPLData(IPL_MOST_RUNS_CSV_FILE_PATH);
            String iplpLayersRecords = iplAnalyser.getFieldWiseSortedIPLPLayersRecords(SortByField.Parameter.RUN_WITH_AVG);
            System.out.println(iplpLayersRecords);
            MostRunCSV[] mostRunCSVS = new Gson().fromJson(iplpLayersRecords, MostRunCSV[].class);
            Assert.assertEquals("shivam", mostRunCSVS[mostRunCSVS.length - 1].player);
        } catch (IPLCSVException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIPLMOstWktsCSVFile_ShouldReturnCorrectRecords() {
        try {
            IPLAdapter iplAdapter = mock(BowlerAdapter.class);
            when(iplAdapter.loadIPLData(IPLEntity.BOWLING, IPL_MOST_WKTS_CSV_FILE_PATH)).thenReturn(this.recordDAOMap);
            IPLAnalyser iplAnalyser = new IPLAnalyser(IPLEntity.BOWLING);
            iplAnalyser.setIplAdapter(iplAdapter);
            int iplRecords = iplAnalyser.loadIPLData(IPL_MOST_WKTS_CSV_FILE_PATH);
            Assert.assertEquals(3, iplRecords);
        } catch (IPLCSVException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIPLMOstWktsCSVFile_WhenSortedOnAvg_ShouldReturnCorrectDesiredSortedData() {
        try {
            IPLAdapter iplAdapter = mock(BowlerAdapter.class);
            when(iplAdapter.loadIPLData(IPLEntity.BOWLING, IPL_MOST_WKTS_CSV_FILE_PATH)).thenReturn(this.recordDAOMap);
            IPLAnalyser iplAnalyser = new IPLAnalyser(IPLEntity.BOWLING);
            iplAnalyser.setIplAdapter(iplAdapter);
            int iplRecords = iplAnalyser.loadIPLData(IPL_MOST_WKTS_CSV_FILE_PATH);
            String iplpLayersRecords = iplAnalyser.getFieldWiseSortedIPLPLayersRecords(SortByField.Parameter.BOWLING_AVG);
            System.out.println(iplpLayersRecords);
            MostRunCSV[] mostRunCSVS = new Gson().fromJson(iplpLayersRecords, MostRunCSV[].class);
            Assert.assertEquals("kapil", mostRunCSVS[0].player);
        } catch (IPLCSVException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIPLMOstWktsCSVFile_WhenSortedOnStrikeRate_ShouldReturnCorrectDesiredSortedData() {
        try {
            IPLAdapter iplAdapter = mock(BowlerAdapter.class);
            when(iplAdapter.loadIPLData(IPLEntity.BOWLING, IPL_MOST_WKTS_CSV_FILE_PATH)).thenReturn(this.recordDAOMap);
            IPLAnalyser iplAnalyser = new IPLAnalyser(IPLEntity.BOWLING);
            iplAnalyser.setIplAdapter(iplAdapter);
            int iplRecords = iplAnalyser.loadIPLData(IPL_MOST_WKTS_CSV_FILE_PATH);
            String iplpLayersRecords = iplAnalyser.getFieldWiseSortedIPLPLayersRecords(SortByField.Parameter.BOWLING_AVG);
            System.out.println(iplpLayersRecords);
            MostRunCSV[] mostRunCSVS = new Gson().fromJson(iplpLayersRecords, MostRunCSV[].class);
            Assert.assertEquals("Rohit", mostRunCSVS[0].player);
        } catch (IPLCSVException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIPLMOstWktsCSVFile_WhenSortedOnEconomy_ShouldReturnCorrectDesiredSortedData() {
        try {
            IPLAdapter iplAdapter = mock(BowlerAdapter.class);
            when(iplAdapter.loadIPLData(IPLEntity.BOWLING, IPL_MOST_WKTS_CSV_FILE_PATH)).thenReturn(this.recordDAOMap);
            IPLAnalyser iplAnalyser = new IPLAnalyser(IPLEntity.BOWLING);
            iplAnalyser.setIplAdapter(iplAdapter);
            int iplRecords = iplAnalyser.loadIPLData(IPL_MOST_WKTS_CSV_FILE_PATH);
            String iplpLayersRecords = iplAnalyser.getFieldWiseSortedIPLPLayersRecords(SortByField.Parameter.ECONOMY);
            System.out.println(iplpLayersRecords);
            MostRunCSV[] mostRunCSVS = new Gson().fromJson(iplpLayersRecords, MostRunCSV[].class);
            Assert.assertEquals("kapil", mostRunCSVS[0].player);
        } catch (IPLCSVException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIPLMOstWktsCSVFile_WhenSortedOn5WAnd4WWithStrikeRate_ShouldReturnCorrectDesiredSortedData() {
        try {
            IPLAdapter iplAdapter = mock(BowlerAdapter.class);
            when(iplAdapter.loadIPLData(IPLEntity.BOWLING, IPL_MOST_WKTS_CSV_FILE_PATH)).thenReturn(this.recordDAOMap);
            IPLAnalyser iplAnalyser = new IPLAnalyser(IPLEntity.BOWLING);
            iplAnalyser.setIplAdapter(iplAdapter);
            int iplRecords = iplAnalyser.loadIPLData(IPL_MOST_WKTS_CSV_FILE_PATH);
            String iplpLayersRecords = iplAnalyser.getFieldWiseSortedIPLPLayersRecords(SortByField.Parameter.FIVEWKT_FOURWKT_STRIKERATE);
            System.out.println(iplpLayersRecords);
            MostRunCSV[] mostRunCSVS = new Gson().fromJson(iplpLayersRecords, MostRunCSV[].class);
            Assert.assertEquals("shivam", mostRunCSVS[0].player);
        } catch (IPLCSVException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIPLMOstWktsCSVFile_WhenSortedOnBowlingAvgWithStrikeRate_ShouldReturnCorrectDesiredSortedData() {
        try {
            IPLAdapter iplAdapter = mock(BowlerAdapter.class);
            when(iplAdapter.loadIPLData(IPLEntity.BOWLING, IPL_MOST_WKTS_CSV_FILE_PATH)).thenReturn(this.recordDAOMap);
            IPLAnalyser iplAnalyser = new IPLAnalyser(IPLEntity.BOWLING);
            iplAnalyser.setIplAdapter(iplAdapter);
            int iplRecords = iplAnalyser.loadIPLData(IPL_MOST_WKTS_CSV_FILE_PATH);
            String iplpLayersRecords = iplAnalyser.getFieldWiseSortedIPLPLayersRecords(SortByField.Parameter.BOWL_STATS_AVG_WITH_STRIKERATE);
            System.out.println(iplpLayersRecords);
            MostRunCSV[] mostRunCSVS = new Gson().fromJson(iplpLayersRecords, MostRunCSV[].class);
            Assert.assertEquals("Rohit", mostRunCSVS[mostRunCSVS.length - 1].player);
        } catch (IPLCSVException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIPLMOstWktsCSVFile_WhenSortedOnWktsWithAvg_ShouldReturnCorrectDesiredSortedData() {
        try {
            IPLAdapter iplAdapter = mock(BowlerAdapter.class);
            when(iplAdapter.loadIPLData(IPLEntity.BOWLING, IPL_MOST_WKTS_CSV_FILE_PATH)).thenReturn(this.recordDAOMap);
            IPLAnalyser iplAnalyser = new IPLAnalyser(IPLEntity.BOWLING);
            iplAnalyser.setIplAdapter(iplAdapter);
            int iplRecords = iplAnalyser.loadIPLData(IPL_MOST_WKTS_CSV_FILE_PATH);
            String iplpLayersRecords = iplAnalyser.getFieldWiseSortedIPLPLayersRecords(SortByField.Parameter.BOWL_STATS_WKT_WITH_AVG);
            System.out.println(iplpLayersRecords);
            MostRunCSV[] mostRunCSVS = new Gson().fromJson(iplpLayersRecords, MostRunCSV[].class);
            Assert.assertEquals("Rohit", mostRunCSVS[mostRunCSVS.length - 1].player);
        } catch (IPLCSVException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIPLMOstWktsCSVFile_WhenSortedOnBattingAndBowlingAvg_ShouldReturnCorrectDesiredSortedData() {
        try {
            IPLAdapter iplAdapter = mock(BowlerAdapter.class);
            when(iplAdapter.loadIPLData(IPLEntity.BOWLING, IPL_MOST_WKTS_CSV_FILE_PATH)).thenReturn(this.recordDAOMap);
            IPLAnalyser iplAnalyser = new IPLAnalyser(IPLEntity.BOWLING);
            iplAnalyser.setIplAdapter(iplAdapter);
            int iplRecords = iplAnalyser.loadIPLData(IPL_MOST_WKTS_CSV_FILE_PATH);
            String iplpLayersRecords = iplAnalyser.getFieldWiseSortedIPLPLayersRecords(SortByField.Parameter.BATTING_BOWLING_AVERAGE);
            System.out.println(iplpLayersRecords);
            MostRunCSV[] mostRunCSVS = new Gson().fromJson(iplpLayersRecords, MostRunCSV[].class);
            Assert.assertEquals("shivam", mostRunCSVS[mostRunCSVS.length - 1].player);
        } catch (IPLCSVException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIPLMOstWktsCSVFile_WhenSortedOnALLRounders_ShouldReturnCorrectDesiredSortedData() {
        try {
            IPLAdapter iplAdapter = mock(BowlerAdapter.class);
            when(iplAdapter.loadIPLData(IPLEntity.BOWLING, IPL_MOST_WKTS_CSV_FILE_PATH)).thenReturn(this.recordDAOMap);
            IPLAnalyser iplAnalyser = new IPLAnalyser(IPLEntity.BOWLING);
            iplAnalyser.setIplAdapter(iplAdapter);
            int iplRecords = iplAnalyser.loadIPLData(IPL_MOST_WKTS_CSV_FILE_PATH);
            String iplpLayersRecords = iplAnalyser.getFieldWiseSortedIPLPLayersRecords(SortByField.Parameter.IPL_BEST_ALLROUNDER);
            System.out.println(iplpLayersRecords);
            MostRunCSV[] mostRunCSVS = new Gson().fromJson(iplpLayersRecords, MostRunCSV[].class);
            Assert.assertEquals("Rohit", mostRunCSVS[mostRunCSVS.length - 1].player);
        } catch (IPLCSVException e) {
            e.printStackTrace();
        }
    }


}
