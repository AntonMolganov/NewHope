package newhope.server.service.impl;

import newhope.server.dto.TotalAssessmentResultDto;
import newhope.server.service.TotalAssessmentsService;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class TotalAssessmentsServiceImpl implements TotalAssessmentsService {

    @Override
    public List<TotalAssessmentResultDto> getTotalAssessmentsResult() {
        List<TotalAssessmentResultDto> resultList = new LinkedList<>();

        for (int i = 0; i < 50; i++){
            TotalAssessmentResultDto dto = new TotalAssessmentResultDto();
            dto.setId((long) i);
            dto.setShipperId(1l);
            dto.setShipperName("Shipper " + (i+1));
            dto.setPosition(i+1);
            dto.setAssessemnts(new Integer[]{(int)(Math.random()*10/2),(int)(Math.random()*10/2),(int)(Math.random()*10/2),(int)(Math.random()*10/2),(int)(Math.random()*10/2)});
            resultList.add(dto);
        }
        return resultList;
    }
}
