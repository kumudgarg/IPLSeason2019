package iplanalysis;

public class IPLRecordDAO {

    public IPLRecordDAO() {

    }

    public String player;
    public int runs;
    public double strikeRate;
    public double average;
    public int fours;
    public int six;
    public int wkts;
    public int fourWkts;
    public int fiveWkts;
    public double economy;


    public IPLRecordDAO(MostRunCSV mostRunCSV) {
        player = mostRunCSV.player;
        runs = mostRunCSV.run;
        strikeRate = mostRunCSV.strikeRate;
        average = mostRunCSV.avg;
        fours = mostRunCSV.fours;
        six = mostRunCSV.six;
    }

    public IPLRecordDAO(MostWktsCSV mostWktsCSV) {
        player = mostWktsCSV.player;
        runs = mostWktsCSV.runs;
        strikeRate = mostWktsCSV.strikeRate;
        average = mostWktsCSV.average;
        wkts = mostWktsCSV.wkts;
        fourWkts = mostWktsCSV.fourWkts;
        fiveWkts = mostWktsCSV.fiveWkts;
        economy = mostWktsCSV.economy;

    }

    public IPLRecordDAO(String player, int runs, double strikeRate, double average, int fours, int six, int wkts, int fourWkts, int fiveWkts) {
        this.player = player;
        this.runs = runs;
        this.strikeRate = strikeRate;
        this.average = average;
        this.fours = fours;
        this.six = six;
        this.wkts = wkts;
        this.fourWkts = fourWkts;
        this.fiveWkts = fiveWkts;
    }
}
