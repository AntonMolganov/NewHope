package newhope.server.service;

import newhope.server.dto.NearestTimeRequestDto;
import newhope.server.dto.TripFactDto;
import newhope.server.entity.TotalAssessmentEntity;
import newhope.server.entity.TripFactEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TripsService {
    void addAssessments(List<TripFactDto> assessments);

    List<TotalAssessmentEntity> getTotalAssessments();

    List<TripFactEntity> listAllTrips();

    Long getNearestDepartureTime(NearestTimeRequestDto dto);
}
