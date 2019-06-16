package newhope.server.service;

import newhope.server.entity.RouteEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RoutesService {

    List<RouteEntity> list();
}
