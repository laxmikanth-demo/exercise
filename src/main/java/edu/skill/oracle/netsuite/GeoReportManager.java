package edu.skill.oracle.netsuite;

import edu.skill.oracle.netsuite.model.Record;
import java.util.List;
import java.util.Map;
import java.util.OptionalDouble;
import java.util.Set;

public interface GeoReportManager {
    public Map<String, Integer> getCustomerCount(List<Record> records);
    public Map<String, OptionalDouble> avgBuildDuration(List<Record> records);
    public Map<String, Set<String>> customers(List<Record> records);
}
