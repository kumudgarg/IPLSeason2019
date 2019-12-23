package iplanalysis;

import java.util.HashMap;
import java.util.Map;

public class BowlerAdapter extends IPLAdapter {

    @Override
    public Map<String, IPLRecordDAO> loadIPLData(IPLEntity iplEntity, String... csvFilePath) throws IPLCSVException {
        Map<String, IPLRecordDAO> recordDAOMap = super.loadIPLData(MostWktsCSV.class, csvFilePath);
        return recordDAOMap;

    }
}
