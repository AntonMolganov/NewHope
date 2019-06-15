package newhope.server.controller;

import newhope.server.dto.TripFactDto;
import newhope.server.entity.ShipperEntity;
import newhope.server.entity.TripFactEntity;
import newhope.server.service.TestService;
import newhope.server.service.TripsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/newhope")
public class NewHopeMainController {

    @Autowired
    TestService testService;

    @Autowired
    TripsService tripsService;

    @RequestMapping("/test")
    public Iterable<ShipperEntity> main(){
        return testService.test();
    }

    @RequestMapping(value = "/trips/add", method = RequestMethod.POST)
    public void addAssessment(@RequestBody List<TripFactDto> tripFacts){
        tripsService.addAssessments(tripFacts);
    }

    @RequestMapping(value = "/trips/list", method = RequestMethod.POST)
    public List<TripFactEntity> listAssessments(){
        return tripsService.listAllTrips();
    }

    @RequestMapping(value = "/trips/total", method = RequestMethod.POST)
    public String getTotalAssessments(){
        return tripsService.getTotalAssessments();
    }
}
