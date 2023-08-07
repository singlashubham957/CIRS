package customer.issue.resolution.system.services;


public interface IAgentAssigner {
    public String selectAgentToResolveIssue(Integer issueId);
}
