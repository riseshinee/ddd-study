import java.time.*;
import java.util.List;

public class ResponseTimeFrameCalculationService {
  private DepartmentRepository departmentRepository;

  public ResponseTimeFrame calculateAgentResponseDeadline(UserId agentId,
                                                          Priority priority,
                                                          boolean escalated,
                                                          LocalDateTime startTime) {
    DepartmentPolicy policy = departmentRepository.getDepartmentPolicy(agentId);
    Duration maxProcTime = policy.getMaxResponseTimeFor(priority);

    if (escalated) {
      maxProcTime = maxProcTime.multipliedBy(policy.getEscalationFactor());
    }

    List<Shift> shifts = departmentRepository.getUpcomingShifts(agentId,
            startTime, startTime.plus(policy.getMaxAgentResponseTime()));

    return calculateTargetTime(maxProcTime, shifts);
  }
}