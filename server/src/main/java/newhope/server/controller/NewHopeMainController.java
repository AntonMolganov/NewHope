package newhope.server.controller;

import newhope.server.dto.TotalAssessmentResultDto;
import newhope.server.service.TotalAssessmentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://newhope")
@RequestMapping("/newhope")
public class NewHopeMainController {

    @Autowired
    TotalAssessmentsService totalAssessmentsService;

    @RequestMapping("/test")
    public String main(){
        return "New Hope Test";
    }

    @RequestMapping(value = "/totalAssessments")
    public List<TotalAssessmentResultDto> getTotalAssessment(){
        return totalAssessmentsService.getTotalAssessmentsResult();
    }
}
