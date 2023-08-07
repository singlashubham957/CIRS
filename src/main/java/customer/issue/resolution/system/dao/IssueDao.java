package customer.issue.resolution.system.dao;

import customer.issue.resolution.system.enums.AgentWorkingStatus;
import customer.issue.resolution.system.enums.IssueStatus;
import customer.issue.resolution.system.models.Agent;
import customer.issue.resolution.system.models.Issue;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class IssueDao {
    private int idCounter = 0;
    private final Map<Integer, Issue> issues = new HashMap<>();

    public Integer addIssue(Issue issue) {
        Integer issueId = ++idCounter;
        issue.setId(issueId);
        issues.put(issueId, issue);
        return idCounter;
    }

    public Issue getIssue(Integer id) {
        return issues.get(id);
    }

    public void updateIssueStatus(Integer id, IssueStatus status) {
        issues.get(id).setStatus(status);
    }

    public void updateAgentId(Integer id, String agentEmailId) {
        issues.get(id).setAgent(agentEmailId);
    }

    public List<Issue> getAgentsIssues(String agentEmailId) {
        List<Issue> filteredIssues = issues.values().stream()
                .filter(it -> it.getAgentEmailId().contains(agentEmailId))
                .toList();
        return filteredIssues;
    }

}
