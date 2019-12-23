package iplanalysis;

public class IPLRecordDAO {

    public IPLRecordDAO() {

    }

    public String player;
    public int batsmanRun;
    public int bowlerRun;
    public double strikeRate;
    public double battingAverage;
    public double bowlingAverage;
    public int fours;
    public int six;
    public int wkts;
    public int fourWkts;
    public int fiveWkts;
    public double economy;
    public int match;
    public double over;


    public IPLRecordDAO(MostRunCSV mostRunCSV) {
        player = mostRunCSV.player;
        batsmanRun = mostRunCSV.run;
        strikeRate = mostRunCSV.strikeRate;
        battingAverage = mostRunCSV.avg;
        fours = mostRunCSV.fours;
        six = mostRunCSV.six;
        match = mostRunCSV.match;
    }

    public IPLRecordDAO(MostWktsCSV mostWktsCSV) {
        player = mostWktsCSV.player;
        bowlerRun = mostWktsCSV.runs;
        strikeRate = mostWktsCSV.strikeRate;
        bowlingAverage = mostWktsCSV.average;
        wkts = mostWktsCSV.wkts;
        fourWkts = mostWktsCSV.fourWkts;
        fiveWkts = mostWktsCSV.fiveWkts;
        economy = mostWktsCSV.economy;
        match = mostWktsCSV.match;
        over = mostWktsCSV.over;

    }
}
