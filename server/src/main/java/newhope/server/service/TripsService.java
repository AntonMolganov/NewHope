package newhope.server.service;

import newhope.server.dto.TripFactDto;
import newhope.server.entity.TripFactEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TripsService {
    void addAssessments(List<TripFactDto> assessments);

    String getTotalAssessments();

    List<TripFactEntity> listAllTrips();
}
