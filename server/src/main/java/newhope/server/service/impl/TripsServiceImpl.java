package newhope.server.service.impl;

import newhope.server.dao.RoutesDao;
import newhope.server.dao.TotalAssessmentsDao;
import newhope.server.dao.TripFactsDao;
import newhope.server.dict.Route;
import newhope.server.dto.NearestTimeRequestDto;
import newhope.server.dto.TripFactDto;
import newhope.server.entity.RouteEntity;
import newhope.server.entity.TotalAssessmentEntity;
import newhope.server.entity.TripFactEntity;
import newhope.server.service.TripsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TripsServiceImpl implements TripsService {

    @Autowired
    TripFactsDao tripsDao;

    @Autowired
    TotalAssessmentsDao assessmentDao;

    @Autowired
    RoutesDao routesDao;

    @Override
    public void addAssessments(List<TripFactDto> assessments) {
        for (TripFactDto dto: assessments) {
            Optional<RouteEntity> optional = routesDao.findByTransportTypeIdAndRouteName(
                                                            Route.getTransportTypeId(dto.getRouteType()),
                                                            dto.getRouteName());
            if (!optional.isPresent()) continue;;

            tripsDao.addAssessment(dto.getUserId(),
                    optional.get().getRouteId(),
                    optional.get().getShipperId(),
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
    public List<TotalAssessmentEntity> getTotalAssessments() {
        List<TotalAssessmentEntity> resultList = new LinkedList<>();
        assessmentDao.findAllByOrderByPositionAsc().forEach(resultList::add);
        return resultList;
    }

    @Override
    public List<TripFactEntity> listAllTrips() {
        List<TripFactEntity> resultList = new LinkedList<>();
        tripsDao.findAllByOrderByDateDesc().forEach(resultList::add);
        return resultList;
    }

    @Override
    public Long getNearestDepartureTime(NearestTimeRequestDto dto) {
//        В настоящей жизни данный метод будет анализировать расположение ТС с учётом расписания
//        и выдавать ближайшую к заданному времени прибытия отправку ТС на указанной остановке.
//        Пока сделана эмуляция такой аналитики.
        long max = 5 * 60 * 1000l;
        long delay = (long) (Math.random()*max);
        return (dto.getTime().getTime() - delay) / 1000;
    }


}
