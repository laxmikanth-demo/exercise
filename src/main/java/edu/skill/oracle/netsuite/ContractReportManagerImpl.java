package edu.skill.oracle.netsuite;

import edu.skill.oracle.netsuite.model.Record;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class ContractReportManagerImpl implements ContractReportManager {
    private String runId;
    
    public ContractReportManagerImpl(String runId) {
        this.runId = runId;
    }

    @Override
    public Map<String, Integer> getCustomerCount(List<Record> records) {
        Map<String, Set<String>> customersByContract = records.parallelStream()
                .collect(
                        Collectors.groupingBy(Record::getContractId,
                                Collectors.mapping(Record::getCustomerId, Collectors.toSet())
                        )
                );
        
        return customersByContract.entrySet().parallelStream().collect(Collectors.toMap(Map.Entry::getKey, (Map.Entry<String, Set<String>> e) -> e.getValue().size()));
    }    
}
