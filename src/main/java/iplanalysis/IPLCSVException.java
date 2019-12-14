package iplanalysis;

import censusanalyser.CensusAnalyserException;

public class IPLCSVException extends Exception {

    public enum ExceptionType {
        CENSUS_FILE_PROBLEM, UNABLE_TO_PARSE,  NO_CENSUS_DATA, CSV_FILE_INTERNAL_ISSUES, INVALID_COUNTRY
    }

    public IPLCSVException.ExceptionType type;

    public IPLCSVException(String message, String name) {
        super(message);
        this.type = IPLCSVException.ExceptionType.valueOf(name);
    }

    public IPLCSVException(String message, IPLCSVException.ExceptionType type) {
        super(message);
        this.type = type;
    }

    public IPLCSVException(String message, IPLCSVException.ExceptionType type, Throwable cause) {
        super(message, cause);
        this.type = type;
    }
}
