package edu.skill.oracle.netsuite.model;

public class Record {

    String customerId;
    String contractId;
    String geozone;
    String teamcode;
    String projectcode;
    Integer buildDuration;
    
    public Record(String customerId, String contractId, String geozone, String teamcode, String projectcode, Integer buildDuration) {
        this.customerId = customerId;
        this.contractId = contractId;
        this.geozone = geozone;
        this.teamcode = teamcode;
        this.projectcode = projectcode;
        this.buildDuration = buildDuration;
    }

    public String getCustomerId() {
        return customerId.trim();
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getContractId() {
        return contractId.trim();
    }

    public void setContractId(String contractId) {
        this.contractId = contractId;
    }

    public String getGeozone() {
        return geozone;
    }

    public void setGeozone(String geozone) {
        this.geozone = geozone;
    }

    public String getTeamcode() {
        return teamcode;
    }

    public void setTeamcode(String teamcode) {
        this.teamcode = teamcode;
    }

    public String getProjectcode() {
        return projectcode;
    }

    public void setProjectcode(String projectcode) {
        this.projectcode = projectcode;
    }

    public Integer getBuildDuration() {
        return buildDuration;
    }

    public void setBuildDurationInSeconds(Integer buildduration) {
        this.buildDuration = buildduration;
    }
    
    public String getCustomerContract() {
        return contractId + customerId;
    }
    
    @Override
    public String toString() { 
        return customerId+ " "+contractId+" "+geozone+" "+teamcode+" "+projectcode+" "+buildDuration; 
    }
}
