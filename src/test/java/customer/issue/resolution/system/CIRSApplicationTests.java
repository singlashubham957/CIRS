package customer.issue.resolution.system;

import customer.issue.resolution.system.dao.AgentDao;
import customer.issue.resolution.system.dao.IssueDao;
import customer.issue.resolution.system.enums.AgentWorkingStatus;
import customer.issue.resolution.system.enums.IssueStatus;
import customer.issue.resolution.system.enums.IssueType;
import customer.issue.resolution.system.models.Issue;
import customer.issue.resolution.system.services.impl.RandomAgentAssigner;
import customer.issue.resolution.system.services.impl.AgentsService;
import customer.issue.resolution.system.services.impl.IssuesService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import customer.issue.resolution.system.models.Agent;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CIRSApplicationTests {

    @Test
    void contextLoads() {
    }

    private IssueDao issueDao = new IssueDao();
    private AgentDao agentDao = new AgentDao();
    private RandomAgentAssigner agentAssigner = new RandomAgentAssigner(issueDao, agentDao);
    private IssuesService issuesService = new IssuesService(issueDao, agentDao, agentAssigner);
    private AgentsService agentsService = new AgentsService(issueDao, agentDao);

    @BeforeEach
    void reset() {
         IssueDao issueDao = new IssueDao();
         AgentDao agentDao = new AgentDao();
         RandomAgentAssigner agentAssigner = new RandomAgentAssigner(issueDao, agentDao);
         IssuesService issuesService = new IssuesService(issueDao, agentDao, agentAssigner);
         AgentsService agentsService = new AgentsService(issueDao, agentDao);
    }

    @Test
    public void testCreateAgent() {
        List<IssueType> expertises = Arrays.asList(IssueType.GOLD, IssueType.INSURANCE);
        agentsService.addAgent(new Agent("emailId1", "name1", expertises, AgentWorkingStatus.IDLE));
        agentsService.addAgent(new Agent("emailId2", "name2", Arrays.asList(IssueType.values()), AgentWorkingStatus.IDLE));
        Agent actualAgent = agentsService.getAgent("emailId1");
        assertEquals("name1", actualAgent.getName());
        assertEquals("emailId1", actualAgent.getEmailId());
    }


    @Test
    public void testCreateIssue() {
        //create agents for mock data
        List<IssueType> expertises = Arrays.asList(IssueType.GOLD, IssueType.INSURANCE);
        agentsService.addAgent(new Agent("emailId1", "name1", expertises, AgentWorkingStatus.IDLE));
        agentsService.addAgent(new Agent("emailId2", "name2", Arrays.asList(IssueType.values()), AgentWorkingStatus.IDLE));

        //create issue
        Integer id = issuesService.createIssue(
                new Issue(
                        null, "customerEmailId1", "transactionId1", IssueType.INSURANCE, "subject1",
                        "description1", IssueStatus.PENDING, "agentEmailId")
        );

        Issue createdIssue = issuesService.getIssue(id);
        assertEquals("customerEmailId1", createdIssue.getCustomerEmailId());
        assertEquals("description1", createdIssue.getDescription());
    }

    @Test
    public void testAssignIssue() {
        //create agents/issues for mock data
        List<IssueType> expertises = Arrays.asList(IssueType.GOLD, IssueType.INSURANCE);
        agentsService.addAgent(new Agent("emailId1", "name1", expertises, AgentWorkingStatus.IDLE));
        agentsService.addAgent(new Agent("emailId2", "name2", Arrays.asList(IssueType.values()), AgentWorkingStatus.IDLE));
        Integer id = issuesService.createIssue(
                new Issue(
                        null, "customerEmailId1", "transactionId1", IssueType.INSURANCE, "subject1",
                        "description1", IssueStatus.PENDING, "agentEmailId")
        );

        issuesService.assignAgentAndUpdateStatus(1);
        Issue createdIssue = issuesService.getIssue(1);
        assertEquals(IssueStatus.IN_PROGRESS, createdIssue.getStatus());
        assertNotNull(createdIssue.getAgentEmailId());
    }

    @Test
    public void testResolvingIssue() {
        //create agents/issues for mock data
        List<IssueType> expertises = Arrays.asList(IssueType.GOLD, IssueType.INSURANCE);
        agentsService.addAgent(new Agent("emailId1", "name1", expertises, AgentWorkingStatus.IDLE));
        agentsService.addAgent(new Agent("emailId2", "name2", Arrays.asList(IssueType.values()), AgentWorkingStatus.IDLE));
        Integer id = issuesService.createIssue(
                new Issue(
                        null, "customerEmailId1", "transactionId1", IssueType.INSURANCE, "subject1",
                        "description1", IssueStatus.PENDING, "agentEmailId")
        );

        issuesService.updateIssueStatus(1, IssueStatus.RESOLVED);
        Issue issue = issuesService.getIssue(1);
        assertEquals(IssueStatus.RESOLVED,issue.getStatus() );

    }



}
