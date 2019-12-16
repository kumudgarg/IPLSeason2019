package iplanalysis;

import java.util.Comparator;

public class SortFieldComparator implements Comparator<MostRunCSV> {
    @Override
    public int compare(MostRunCSV obj1, MostRunCSV obj2) {
        return (((obj1.six * 6) + (obj1.fours * 4)) - ((obj2.six * 6) + (obj2.fours * 4)));
    }
}
