package newhope.server.controller;

import newhope.server.dto.NearestTimeRequestDto;
import newhope.server.dto.TripFactDto;
import newhope.server.entity.RouteEntity;
import newhope.server.entity.ShipperEntity;
import newhope.server.entity.TotalAssessmentEntity;
import newhope.server.entity.TripFactEntity;
import newhope.server.service.RoutesService;
import newhope.server.service.ShippersService;
import newhope.server.service.TripsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/newhope")
public class NewHopeMainController {

    @Autowired
    ShippersService shippersService;

    @Autowired
    TripsService tripsService;

    @Autowired
    RoutesService routesService;

    @RequestMapping(value = "/shippers/list")
    public List<ShipperEntity> shippersList(){
        return shippersService.list();
    }

    @RequestMapping(value = "/routes/list")
    public List<RouteEntity> routesList(){
        return routesService.list();
    }

    @RequestMapping(value = "/trips/add")
    public void addAssessment(@RequestBody List<TripFactDto> tripFacts){
        tripsService.addAssessments(tripFacts);
    }

    @RequestMapping(value = "/trips/list")
    public List<TripFactEntity> listAssessments(){
        return tripsService.listAllTrips();
    }

    @RequestMapping(value = "/trips/total")
    public List<TotalAssessmentEntity> getTotalAssessments(){
        return tripsService.getTotalAssessments();
    }

    @RequestMapping(value = "/trips/nearest")
    public Long getNearest(@RequestBody NearestTimeRequestDto dto){
        return tripsService.getNearestDepartureTime(dto);
    }


}
