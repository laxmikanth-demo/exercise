package edu.skill.oracle.netsuite;

import edu.skill.oracle.netsuite.model.Record;
import java.util.List;
import java.util.Map;

public interface ContractReportManager {
    public Map<String, Integer> getCustomerCount(List<Record> records);
}
