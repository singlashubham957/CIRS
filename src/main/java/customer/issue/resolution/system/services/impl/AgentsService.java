package customer.issue.resolution.system.services.impl;

import customer.issue.resolution.system.dao.AgentDao;
import customer.issue.resolution.system.dao.IssueDao;
import customer.issue.resolution.system.enums.AgentWorkingStatus;
import customer.issue.resolution.system.enums.IssueStatus;
import customer.issue.resolution.system.models.Agent;
import customer.issue.resolution.system.models.Issue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgentsService {
    @Autowired
    private AgentDao agentDao;
    @Autowired
    private IssueDao issueDao;

    public AgentsService(IssueDao issueDao, AgentDao agentDao){
        this.issueDao = issueDao;
        this.agentDao = agentDao;
    }

    public void addAgent(Agent agent) {
        System.out.println(" addAgent request" + agent);
        agentDao.addAgent(agent);
    }

    public Agent getAgent(String emailId) {
       System.out.println("getAgent request for id: " + emailId);
        return agentDao.getAgent(emailId);
    }

    public void updateAgentStatus(String emailId, AgentWorkingStatus status) {
        System.out.println("in updateAgentStatus, id: " + emailId + " status: " + status);
        agentDao.updateAgentStatus(emailId, status);
    }

    public List<Issue> getHistory(String emailId) {
        System.out.println("fetching agents history with id: " + emailId);
        return  issueDao.getAgentsIssues(emailId);
    }

}
