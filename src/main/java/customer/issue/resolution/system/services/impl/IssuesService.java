package customer.issue.resolution.system.services.impl;

import customer.issue.resolution.system.dao.AgentDao;
import customer.issue.resolution.system.dao.IssueDao;
import customer.issue.resolution.system.enums.AgentWorkingStatus;
import customer.issue.resolution.system.enums.IssueStatus;
import customer.issue.resolution.system.models.Issue;
import customer.issue.resolution.system.services.IAgentAssigner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
@Lazy
public class IssuesService {
    @Autowired
    private IssueDao issueDao;
    @Autowired
    private AgentDao agentDao;
    @Autowired
    private IAgentAssigner agentAssigner;

    public IssuesService(IssueDao issueDao, AgentDao agentDao, IAgentAssigner agentAssigner){
        this.issueDao = issueDao;
        this.agentDao = agentDao;
        this.agentAssigner = agentAssigner;
    }

    public Integer createIssue(Issue issue) {
        System.out.println("in createIssue, issue: " + issue);
        Integer issueId =  issueDao.addIssue(issue);
        assignAgentAndUpdateStatus(issueId);
        return issueId;
    }

    public Issue getIssue(Integer id) {
        System.out.println("fetching issue with id " + id);
        return issueDao.getIssue(id);
    }

    public String assignAgentAndUpdateStatus(Integer issueId) {
        System.out.println("in assignAgentAndUpdateStatus for issueId: " + issueId);
        String agentId = agentAssigner.selectAgentToResolveIssue(issueId);
        updateIssueStatus(issueId, IssueStatus.IN_PROGRESS);
        updateIssueAgentEmailId(issueId, agentId);
        agentDao.updateAgentStatus(agentId, AgentWorkingStatus.WORKING);
        return agentId;
    }

    public void updateIssueStatus(Integer id, IssueStatus status) {
        System.out.println("updateIssueStatus for id: " + id + " status: " + status);
        issueDao.updateIssueStatus(id, status);
    }

    public void updateIssueAgentEmailId(Integer id, String agentEmailId) {
        System.out.println("in updateIssueAgentEmailId, id: " + id + "agentEmailId: " + agentEmailId);
        issueDao.updateAgentId(id, agentEmailId);
    }

}
