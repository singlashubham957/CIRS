package customer.issue.resolution.system.models;

import customer.issue.resolution.system.enums.IssueStatus;
import customer.issue.resolution.system.enums.IssueType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@AllArgsConstructor
public class Issue {

    private Integer id;
    private String customerEmailId;
    private String transactionId;
    private IssueType issueType;
    private String subject;
    private String description;
    private IssueStatus status = IssueStatus.PENDING;
    private String agentEmailId = null;

    public int getId() {
        return id;
    }

    public String getCustomerEmailId() {
        return customerEmailId;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public IssueType getIssueType() {
        return issueType;
    }

    public String getSubject() {
        return subject;
    }

    public String getDescription() {
        return description;
    }

    public IssueStatus getStatus() {
        return status;
    }

    public String getAgentEmailId() {
        return agentEmailId;
    }

    public void setCustomerEmailId(String customerEmailId) {
        this.customerEmailId = customerEmailId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public void setIssueType(IssueType issueType) {
        this.issueType = issueType;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStatus(IssueStatus status) {
        this.status = status;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAgent(String agentEmailId) {
        this.agentEmailId = agentEmailId;
    }

}
