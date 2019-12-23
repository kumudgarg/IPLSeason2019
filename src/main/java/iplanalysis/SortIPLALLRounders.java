package iplanalysis;

import java.util.Comparator;

public class SortIPLALLRounders implements Comparator<IPLRecordDAO> {

    @Override
    public int compare(IPLRecordDAO obj1, IPLRecordDAO obj2) {
        return (obj1.batsmanRun * obj1.wkts) - (obj2.batsmanRun * obj2.wkts);
    }
}
