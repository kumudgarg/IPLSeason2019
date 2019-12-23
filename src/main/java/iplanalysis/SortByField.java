package iplanalysis;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class SortByField {

    static Map<Parameter, Comparator> sortParameterComparator = new HashMap<>();

    public enum Parameter {
        BATTING_AVG, BOWLING_AVG, STRIKERATE, CENTUARY, FOURS, HALFCENTUARY, HIGHSCORE, SIX, RUNBYBATSMAN, RUNBYBOWLER,
        SIX_AND_FOURS, SIX_AND_FOURS_WITH_STRIKERATE, BAT_STATS_AVG_WITH_STRIKERATE, BOWL_STATS_AVG_WITH_STRIKERATE,
        RUN_WITH_AVG, ECONOMY, FIVEWKT_FOURWKT_STRIKERATE, WKT_WITH_AVG, BATTING_BOWLING_AVERAGE, IPL_BEST_ALLROUNDER;
    }

    SortByField() {

    }

    public static Comparator getParameter(SortByField.Parameter parameter) {

        Comparator<IPLRecordDAO> BatAvgComparator = Comparator.comparing(mostRunCSV -> mostRunCSV.battingAverage);
        Comparator<IPLRecordDAO> BowlAvgComparator = Comparator.comparing(mostRunCSV -> mostRunCSV.bowlingAverage);
        Comparator<IPLRecordDAO> strikeRateComparator = Comparator.comparing(mostRunCSV -> mostRunCSV.strikeRate);
        Comparator<IPLRecordDAO> foursComparator = Comparator.comparing(mostRunCSV -> mostRunCSV.fours);
        Comparator<IPLRecordDAO> sixComparator = Comparator.comparing(mostRunCSV -> mostRunCSV.six);
        Comparator<IPLRecordDAO> batRunComparator = Comparator.comparing(mostRunCSV -> mostRunCSV.batsmanRun);
        Comparator<IPLRecordDAO> bowlRunComparator = Comparator.comparing(mostRunCSV -> mostRunCSV.batsmanRun);
        Comparator<IPLRecordDAO> ecoComparator = Comparator.comparing(mostRunCSV -> mostRunCSV.economy);
        Comparator<IPLRecordDAO> wktComparator = Comparator.comparing(mostRunCSV -> mostRunCSV.wkts);

        sortParameterComparator.put(Parameter.BATTING_AVG, BatAvgComparator);
        sortParameterComparator.put(Parameter.BOWLING_AVG, BowlAvgComparator);
        sortParameterComparator.put(Parameter.STRIKERATE, strikeRateComparator);
        sortParameterComparator.put(Parameter.FOURS, foursComparator);
        sortParameterComparator.put(Parameter.SIX, sixComparator);
        sortParameterComparator.put(Parameter.RUNBYBATSMAN, batRunComparator);
        sortParameterComparator.put(Parameter.RUNBYBOWLER, bowlRunComparator);
        sortParameterComparator.put(Parameter.SIX_AND_FOURS, new SortSixAndFoursComparator());
        sortParameterComparator.put(Parameter.SIX_AND_FOURS_WITH_STRIKERATE, new SortSixAndFoursComparator().thenComparing(strikeRateComparator));
        sortParameterComparator.put(Parameter.BAT_STATS_AVG_WITH_STRIKERATE, BatAvgComparator.thenComparing(strikeRateComparator));
        sortParameterComparator.put(Parameter.BOWL_STATS_AVG_WITH_STRIKERATE, BowlAvgComparator.thenComparing(strikeRateComparator));
        sortParameterComparator.put(Parameter.RUN_WITH_AVG, batRunComparator.thenComparing(BatAvgComparator));
        sortParameterComparator.put(Parameter.RUN_WITH_AVG, bowlRunComparator.thenComparing(BowlAvgComparator));
        sortParameterComparator.put(Parameter.ECONOMY, ecoComparator);
        sortParameterComparator.put(Parameter.FIVEWKT_FOURWKT_STRIKERATE, new Sort5WAND4WComparator().reversed().thenComparing(strikeRateComparator));
        sortParameterComparator.put(Parameter.WKT_WITH_AVG, wktComparator.thenComparing(BatAvgComparator));
        sortParameterComparator.put(Parameter.WKT_WITH_AVG, wktComparator.thenComparing(BowlAvgComparator));
        sortParameterComparator.put(Parameter.BATTING_BOWLING_AVERAGE, new SortBattingBowlingAvg());
        sortParameterComparator.put(Parameter.IPL_BEST_ALLROUNDER, new SortIPLALLRounders());

        Comparator<IPLRecordDAO> comparator = sortParameterComparator.get(parameter);
        return comparator;
    }

}
