package edu.skill.oracle.netsuite;

import edu.skill.oracle.netsuite.model.Record;
import java.util.List;
import java.util.UUID;

public class ReportManager {

    public static void main(String a[]) {
        String runId = UUID.randomUUID().toString();
        SourceManager source = new SourceManager("records.csv");
        RecordManager parser = new RecordManager(runId);
        GeoReportManager geo = new GeoReportManagerImpl(runId);
        ContractReportManager contract = new ContractReportManagerImpl(runId);
        
        try {
            System.out.println("Job started runInstance="+runId);
            List<Record> records = parser.getMapped(source.getContent());
            
            System.out.println("The number of unique customerId for each contractId runInstance="+runId);
            System.out.println(contract.getCustomerCount(records));
            
            System.out.println("The number of unique customerId for each geozone runInstance="+runId);
            System.out.println(geo.getCustomerCount(records));
            
            System.out.println("The average buildduration for each geozone runInstance="+runId);
            System.out.println(geo.avgBuildDuration(records));
            
            System.out.println("The list of unique customerId for each geozone runInstance="+runId);
            System.out.println(geo.customers(records));
            
            System.out.println("Job Completed Successfully; runInstance="+runId);
            System.exit(0);
        } catch (Exception e) {
            System.err.println("Unable to continue the job " + e.getMessage());
            System.exit(-1);
        }
    }
}
