package customer.issue.resolution.system.dao;

import customer.issue.resolution.system.enums.AgentWorkingStatus;
import customer.issue.resolution.system.enums.IssueType;
import customer.issue.resolution.system.models.Agent;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class AgentDao {
    private final Map<String, Agent> agents = new HashMap<>();

    public void addAgent(Agent agent) {
        agents.put(agent.getEmailId(), agent);
    }

    public Agent getAgent(String emailId) {
        return agents.get(emailId);
    }


    public void updateAgentStatus(String  emailId, AgentWorkingStatus status) {
       agents.get(emailId).setStatus(status);
    }

    public List<Agent> getAgents(AgentWorkingStatus status, IssueType expertise) {
        List<Agent> filteredAgents = agents.values().stream()
                .filter(it -> it.getStatus() == AgentWorkingStatus.IDLE)
                .filter(it -> it.getExpertises().contains(expertise))
                .toList();
        return filteredAgents;
    }


}
