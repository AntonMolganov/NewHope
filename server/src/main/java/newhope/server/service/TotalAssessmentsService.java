package newhope.server.service;

import newhope.server.dto.TotalAssessmentResultDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TotalAssessmentsService {
    List<TotalAssessmentResultDto> getTotalAssessmentsResult();
}
