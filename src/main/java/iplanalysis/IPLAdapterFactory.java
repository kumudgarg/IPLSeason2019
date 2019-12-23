package iplanalysis;

import java.util.Map;

public class IPLAdapterFactory {
    public Map<String, IPLRecordDAO> cricketleagueFactory(IPLEntity iplEntity, String... csvFilePath) throws IPLCSVException {
        if (iplEntity.equals(iplEntity.BATING))
            return new BatsmanAdapter().loadIPLData(iplEntity, csvFilePath);
        else if (iplEntity.equals(iplEntity.BOWLING))
            return new BowlerAdapter().loadIPLData(iplEntity, csvFilePath);
        return null;
    }
}
