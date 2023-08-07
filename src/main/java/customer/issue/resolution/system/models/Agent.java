package customer.issue.resolution.system.models;

import customer.issue.resolution.system.enums.AgentWorkingStatus;
import customer.issue.resolution.system.enums.IssueType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.util.List;


@AllArgsConstructor
public class Agent {

    private String emailId;
    private String name;
    private List<IssueType> expertises;
    private AgentWorkingStatus status;

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<IssueType> getExpertises() {
        return expertises;
    }

    public void setExpertises(List<IssueType> expertises) {
        this.expertises = expertises;
    }

    public AgentWorkingStatus getStatus() {
        return status;
    }

    public void setStatus(AgentWorkingStatus status) {
        this.status = status;
    }
}
