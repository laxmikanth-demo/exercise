package edu.skill.oracle.netsuite;

import edu.skill.oracle.netsuite.model.Record;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class RecordManager {
    
    Pattern durationPattern = Pattern.compile("(?<duration>[0-9]*)s");
    private String runId;

    public RecordManager(String runId) {
        this.runId = runId;
    }

    public List<Record> getMapped(List<String> records) {
        return records.parallelStream()
                .map(r -> getRecord(r))
                .filter(r -> r.isPresent())
                .map(r -> r.get())
                .collect(Collectors.toList());
    }

    private String[] getFields(String record) {
        return record.split(",");
    }

    private Optional<Record> getRecord(String record) {
        String[] fields = getFields(record);
        if (fields.length == 6) {
            try {
                Matcher matcher = durationPattern.matcher(fields[5]);
                if(matcher.find()) {
                    String duration = matcher.group("duration");
                    Integer buildDuration = Integer.parseInt(duration);
                    return Optional.of(new Record(fields[0], fields[1], fields[2], fields[3], fields[4], buildDuration));
                } else {
                    System.err.println("Unable to parse the duration in runId="+runId+"; bad record="+record);
                    return Optional.empty();
                }
            } catch (Exception e) {
                System.err.println("Unable extract fields for runId" +runId+ " for record=" + record);
                return Optional.empty();
            }
        } else {
            System.err.println("Unexpected format record in runId="+runId+" found for record=" + record);
            return Optional.empty();
        }
    }
}
