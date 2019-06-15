package newhope.server.service.impl;

import newhope.server.dao.TripFactsDao;
import newhope.server.dto.TripFactDto;
import newhope.server.entity.TripFactEntity;
import newhope.server.service.TripsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;

@Service
@Transactional
public class TripsServiceImpl implements TripsService {

    @Autowired
    TripFactsDao dao;
//
//    TripFactDaoMockImpl dao = new TripFactDaoMockImpl();


    @Override
    public void addAssessments(List<TripFactDto> assessments) {
        for (TripFactDto dto: assessments) {
            dao.addAssessment(dto.getUserId(),
                    1l,
                    1l,
                    dto.getTripDateTime(),
                    dto.getTimeTableComplianceLevel(),
                    dto.getTimeTableComplianceComment(),
                    dto.getFreeSpaceLevel(),
                    dto.getFreeSpaceComment(),
                    dto.getTechStateLevel(),
                    dto.getTechStateComment(),
                    dto.getLawViolenceLevel(),
                    dto.getLawViolenceComment(),
                    dto.getServiceLevel(),
                    dto.getServiceComment());
        }
    }

    @Override
    public String getTotalAssessments() {
        return null;
    }

    @Override
    public List<TripFactEntity> listAllTrips() {
        List<TripFactEntity> resultList = new LinkedList<>();
        dao.findAllByOrderByDateDesc().forEach(resultList::add);
        return resultList;
    }
}
