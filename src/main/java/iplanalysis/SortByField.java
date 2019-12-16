package iplanalysis;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class SortByField {

    static Map<Parameter, Comparator> sortParameterComparator = new HashMap<>();

    public enum Parameter {
        AVG, STRIKERATE, CENTUARY, FOURS, HALFCENTUARY, HIGHSCORE, SIX, RUN , SIX_AND_FOURS, SIX_AND_FOURS_WITH_STRIKERATE, AVG_WITH_STRIKERATE, RUN_WITH_AVG;
    }

    SortByField() {

    }
    public static Comparator getParameter(SortByField.Parameter parameter) {

        Comparator<IPLRecordDAO> avgComparator = Comparator.comparing(mostRunCSV -> mostRunCSV.average);
        Comparator<IPLRecordDAO> strikeRateComparator = Comparator.comparing(mostRunCSV -> mostRunCSV.strikeRate);
        Comparator<IPLRecordDAO> foursComparator = Comparator.comparing(mostRunCSV -> mostRunCSV.fours);
        Comparator<IPLRecordDAO> sixComparator = Comparator.comparing(mostRunCSV -> mostRunCSV.six);
        Comparator<IPLRecordDAO> runComparator = Comparator.comparing(mostRunCSV -> mostRunCSV.runs);

        sortParameterComparator.put(Parameter.AVG, avgComparator);
        sortParameterComparator.put(Parameter.STRIKERATE, strikeRateComparator);
        sortParameterComparator.put(Parameter.FOURS, foursComparator);
        sortParameterComparator.put(Parameter.SIX, sixComparator);
        sortParameterComparator.put(Parameter.RUN, runComparator);
        sortParameterComparator.put(Parameter.SIX_AND_FOURS, new SortFieldComparator());
        sortParameterComparator.put(Parameter.SIX_AND_FOURS_WITH_STRIKERATE, new SortFieldComparator().thenComparing(strikeRateComparator));
        sortParameterComparator.put(Parameter.AVG_WITH_STRIKERATE,avgComparator.thenComparing(strikeRateComparator));
        sortParameterComparator.put(Parameter.RUN_WITH_AVG,runComparator.thenComparing(avgComparator));


        Comparator<MostRunCSV> comparator = sortParameterComparator.get(parameter);

        return comparator;
    }
}
