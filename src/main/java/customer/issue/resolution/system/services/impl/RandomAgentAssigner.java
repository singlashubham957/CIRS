package customer.issue.resolution.system.services.impl;

import customer.issue.resolution.system.dao.AgentDao;
import customer.issue.resolution.system.dao.IssueDao;
import customer.issue.resolution.system.enums.AgentWorkingStatus;
import customer.issue.resolution.system.enums.IssueType;
import customer.issue.resolution.system.models.Agent;
import customer.issue.resolution.system.services.IAgentAssigner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RandomAgentAssigner implements IAgentAssigner {
    private IssueDao issueDao;
    private AgentDao agentDao;

    public RandomAgentAssigner(IssueDao issueDao, AgentDao agentDao){
        this.issueDao = issueDao;
        this.agentDao = agentDao;
    }

    public String selectAgentToResolveIssue(Integer issueId) {
        System.out.println("got request to assign issue " + issueId + " to agent");
        IssueType type = issueDao.getIssue(issueId).getIssueType();
        List<Agent> idleAgents = agentDao.getAgents(AgentWorkingStatus.IDLE, type );
        if(idleAgents.size() == 0)return null;
        System.out.println("selected agent: "  + idleAgents.get(0));
        return idleAgents.get(0).getEmailId();
    }
}
