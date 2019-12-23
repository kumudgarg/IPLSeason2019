package iplanalysis;

import java.util.Comparator;

public class SortBattingBowlingAvg implements Comparator<IPLRecordDAO> {
    @Override
    public int compare(IPLRecordDAO obj1, IPLRecordDAO obj2) {
        return (int) (obj1.battingAverage - (1d/obj1.bowlingAverage) - (obj2.battingAverage - (1d/obj2.bowlingAverage)));
    }
}
