package iplanalysis;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class SortByField {

    static Map<Parameter, Comparator> sortParameterComparator = new HashMap<>();

    public enum Parameter {
        AVG, STRIKERATE, CENTUARY, FOURS, HALFCENTUARY, HIGHSCORE, SIX, RUN;
    }

    SortByField() {

    }
    public static Comparator getParameter(SortByField.Parameter parameter) {

        Comparator<MostRunCSV> avgComparator = Comparator.comparing(mostRunCSV -> mostRunCSV.avg);
        Comparator<MostRunCSV> strikeRateComparator = Comparator.comparing(mostRunCSV -> mostRunCSV.strikeRate);
        Comparator<MostRunCSV> centuaryComparator = Comparator.comparing(mostRunCSV -> mostRunCSV.centuary);
        Comparator<MostRunCSV> foursComparator = Comparator.comparing(mostRunCSV -> mostRunCSV.fours);
        Comparator<MostRunCSV> HalfCentuaryComparator = Comparator.comparing(mostRunCSV -> mostRunCSV.halfCentuary);
        Comparator<MostRunCSV> highScoreComparator = Comparator.comparing(mostRunCSV -> mostRunCSV.highScore);
        Comparator<MostRunCSV> sixComparator = Comparator.comparing(mostRunCSV -> mostRunCSV.six);
        Comparator<MostRunCSV> runComparator = Comparator.comparing(mostRunCSV -> mostRunCSV.run);

        sortParameterComparator.put(Parameter.AVG, avgComparator);
        sortParameterComparator.put(Parameter.STRIKERATE, strikeRateComparator);
        sortParameterComparator.put(Parameter.CENTUARY, centuaryComparator);
        sortParameterComparator.put(Parameter.FOURS, foursComparator);
        sortParameterComparator.put(Parameter.HALFCENTUARY, HalfCentuaryComparator);
        sortParameterComparator.put(Parameter.HIGHSCORE, highScoreComparator);
        sortParameterComparator.put(Parameter.SIX, sixComparator);
        sortParameterComparator.put(Parameter.RUN, runComparator);


        Comparator<MostRunCSV> comparator = sortParameterComparator.get(parameter);

        return comparator;
    }
}
