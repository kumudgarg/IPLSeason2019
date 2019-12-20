package iplanalysis;

import java.util.Comparator;

public class Sort5WAND4WComparator implements Comparator<IPLRecordDAO> {
    @Override
    public int compare(IPLRecordDAO obj1, IPLRecordDAO obj2) {
        return ((obj1.fourWkts + obj1.fiveWkts) - (obj2.fourWkts + obj2.fiveWkts));
    }
}
