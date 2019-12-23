package iplanalysis;

public class IPLRecordDAO {

    public IPLRecordDAO() {

    }

    public String player;
    public int runs;
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
        runs = mostRunCSV.run;
        strikeRate = mostRunCSV.strikeRate;
        battingAverage = mostRunCSV.avg;
        fours = mostRunCSV.fours;
        six = mostRunCSV.six;
        match = mostRunCSV.match;
    }

    public IPLRecordDAO(MostWktsCSV mostWktsCSV) {
        player = mostWktsCSV.player;
        runs = mostWktsCSV.runs;
        strikeRate = mostWktsCSV.strikeRate;
        bowlingAverage = mostWktsCSV.average;
        wkts = mostWktsCSV.wkts;
        fourWkts = mostWktsCSV.fourWkts;
        fiveWkts = mostWktsCSV.fiveWkts;
        economy = mostWktsCSV.economy;
        match = mostWktsCSV.match;
        over = mostWktsCSV.over;

    }

    public IPLRecordDAO(String player, int runs, double strikeRate, double battingAverage, double bowlingAverage, int fours, int six, int wkts, int fourWkts, int fiveWkts, double economy, int match) {
        this.player = player;
        this.runs = runs;
        this.strikeRate = strikeRate;
        this.battingAverage = battingAverage;
        this.bowlingAverage = bowlingAverage;
        this.fours = fours;
        this.six = six;
        this.wkts = wkts;
        this.fourWkts = fourWkts;
        this.fiveWkts = fiveWkts;
        this.economy = economy;
        this.match = match;
    }
}
