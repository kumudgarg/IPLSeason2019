package iplanalysis;

import java.util.Map;

public class IPLAdapterFactory {
    public Map<String, IPLRecordDAO> cricketleagueFactory(IPLAnalyser.IPLEntity iplEntity, String csvFilePath) throws IPLCSVException {
        if (iplEntity.equals(IPLAnalyser.IPLEntity.BATING))
            return new BatsmanAdapter().loadIPLData(iplEntity, csvFilePath);
        else if (iplEntity.equals(IPLAnalyser.IPLEntity.BOWLING))
            return new BowlerAdapter().loadIPLData(iplEntity, csvFilePath);
        return null;
    }
}
