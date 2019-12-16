package edu.skill.oracle.netsuite;

import edu.skill.oracle.netsuite.model.Record;
import java.util.List;
import java.util.Map;
import java.util.OptionalDouble;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class GeoReportManagerImpl implements GeoReportManager {
    
    private Function<Map.Entry<String, List<Record>>, Set<String>> fetchCustomerId = (Map.Entry<String, List<Record>> e) -> e.getValue().stream().collect(Collectors.mapping(Record::getCustomerId, Collectors.toSet()));
    private String runId;
    
    public GeoReportManagerImpl(String runId) {
        this.runId = runId;
    }

    @Override
    public Map<String, Integer> getCustomerCount(List<Record> records) {
        Function<Map.Entry<String, Set<String>>, Integer> customerCount = (Map.Entry<String, Set<String>> e) -> e.getValue().size();
        return customers(records).entrySet().parallelStream().collect(Collectors.toMap(Map.Entry::getKey, customerCount));
    }

    @Override
    public Map<String, OptionalDouble> avgBuildDuration(List<Record> records) {
        Function<Map.Entry<String, List<Record>>, List<Integer>> fetchBuildDuration = (Map.Entry<String, List<Record>> e) -> e.getValue().stream().collect(Collectors.mapping(Record::getBuildDuration, Collectors.toList()));
        
        Function<Map.Entry<String, List<Integer>>, OptionalDouble> geoAvgDuration = (Map.Entry<String, List<Integer>> e) -> e.getValue().stream().mapToInt(Integer::intValue).average();
        
        Map<String, List<Integer>> durationsByGeo = byGeo(records).entrySet().parallelStream()
                .collect(Collectors.toMap(Map.Entry::getKey, fetchBuildDuration));
        return durationsByGeo.entrySet().parallelStream().collect(Collectors.toMap(Map.Entry::getKey, geoAvgDuration));        
    }

    @Override
    public Map<String, Set<String>> customers(List<Record> records) { 
         return byGeo(records).entrySet().parallelStream()
                .collect(Collectors.toMap(Map.Entry::getKey, fetchCustomerId));
    }
    
    private Map<String, List<Record>> byGeo(List<Record> records) {
        return records.parallelStream().collect(Collectors.groupingBy(Record::getGeozone, Collectors.toList()));
    }
    
}
