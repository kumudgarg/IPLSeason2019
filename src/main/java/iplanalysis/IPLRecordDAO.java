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

    public IPLRecordDAO(String player, double battingAverage, int match) {
        this.player = player;
        this.battingAverage = battingAverage;
        this.match = match;
    }

    public IPLRecordDAO(String player, double strikeRate, double battingAverage, int match) {
        this.player = player;
        this.strikeRate = strikeRate;
        this.battingAverage = battingAverage;
        this.match = match;
    }

    public IPLRecordDAO(String player, double strikeRate, double battingAverage, int fours, int six, int match) {
        this.player = player;
        this.strikeRate = strikeRate;
        this.battingAverage = battingAverage;
        this.fours = fours;
        this.six = six;
        this.match = match;
    }

    public IPLRecordDAO(String player, int batsmanRun, double strikeRate, double battingAverage, int fours, int six, int match) {
        this.player = player;
        this.batsmanRun = batsmanRun;
        this.strikeRate = strikeRate;
        this.battingAverage = battingAverage;
        this.fours = fours;
        this.six = six;
        this.match = match;
    }

    public IPLRecordDAO(String player, int batsmanRun, double strikeRate, double battingAverage, double bowlingAverage, int fours, int six, double economy, int match) {
        this.player = player;
        this.batsmanRun = batsmanRun;
        this.strikeRate = strikeRate;
        this.battingAverage = battingAverage;
        this.bowlingAverage = bowlingAverage;
        this.fours = fours;
        this.six = six;
        this.economy = economy;
        this.match = match;
    }

    public IPLRecordDAO(String player, int batsmanRun, double strikeRate, double battingAverage, double bowlingAverage, int fours, int six, int fourWkts, int fiveWkts, double economy, int match) {
        this.player = player;
        this.batsmanRun = batsmanRun;
        this.strikeRate = strikeRate;
        this.battingAverage = battingAverage;
        this.bowlingAverage = bowlingAverage;
        this.fours = fours;
        this.six = six;
        this.fourWkts = fourWkts;
        this.fiveWkts = fiveWkts;
        this.economy = economy;
        this.match = match;
    }

    public IPLRecordDAO(String player, int batsmanRun, double strikeRate, double battingAverage, double bowlingAverage, int fours, int six, int wkts, int fourWkts, int fiveWkts, double economy, int match) {
        this.player = player;
        this.batsmanRun = batsmanRun;
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
