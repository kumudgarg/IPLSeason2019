package iplanalysis;

import com.opencsv.bean.CsvBindByName;

public class MostRunCSV {

    @CsvBindByName(column = "PLAYER", required = true)
    public String player;

    @CsvBindByName(column = "Mat", required = true)
    public int match;

    @CsvBindByName(column = "Inns", required = true)
    public int innings;

    @CsvBindByName(column = "Runs", required = true)
    public int run;

    @CsvBindByName(column = "HS", required = true)
    public String highScore;

    @CsvBindByName(column = "Avg")
    public double avg;

    @CsvBindByName(column = "SR")
    public double strikeRate;

    @CsvBindByName(column = "100", required = true)
    public int centuary;

    @CsvBindByName(column = "50", required = true)
    public int halfCentuary;

    @CsvBindByName(column = "4s", required = true)
    public int fours;

    @CsvBindByName(column = "6s", required = true)
    public int six;


    public MostRunCSV(MostRunCSV mostRunCSV) {

    }

    public MostRunCSV() {
    }

    public MostRunCSV(String player, int match, int innings, int run, String highScore, double avg, double strikeRate, int centuary, int halfCentuary, int fours, int six) {
        this.player = player;
        this.match = match;
        this.innings = innings;
        this.run = run;
        this.highScore = highScore;
        this.avg = avg;
        this.strikeRate = strikeRate;
        this.centuary = centuary;
        this.halfCentuary = halfCentuary;
        this.fours = fours;
        this.six = six;
    }
}
