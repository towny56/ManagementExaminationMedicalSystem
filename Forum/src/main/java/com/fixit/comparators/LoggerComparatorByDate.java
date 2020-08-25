package com.fixit.comparators;


import java.util.Comparator;
import com.fixit.areas.log.models.service.LogServiceModel;

public class LoggerComparatorByDate implements Comparator<LogServiceModel> {
    @Override
    public int compare(LogServiceModel log1, LogServiceModel log2) {
        return log1.getModifyingDate().compareTo(log2.getModifyingDate());
    }
}
